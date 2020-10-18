package com.tcl.uf.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.tcl.commondb.member.model.MemberGrade;
import com.tcl.commondb.member.model.MemberRightsAndInterestsModel;
import com.tcl.commondb.member.model.MemberRightsSet;
import com.tcl.commondb.member.repository.*;
import com.tcl.uf.common.base.constant.MessageConstant;
import com.tcl.uf.common.base.constant.RedisConstant;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.member.dto.MemberGradeDto;
import com.tcl.uf.member.service.MemberGradeService;
import com.tcl.uf.member.service.MemberLevelInfoService;
import com.tcl.uf.member.utils.RedisUtils;
import com.tcl.uf.member.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author gaofan
 */
@Service
public class MemberGradeServiceImpl implements MemberGradeService {

    @Autowired
    private MemberGradeRepository memberGradeRepository;

    @Autowired
    private MemberRightsSetRepository memberRightsSetRepository;

    @Autowired
    private MemberGrowthValueDetailModelRepository memberGrowthValueDetailModelRepository;

    @Autowired
    private MemberNativeQueryRepository memberNativeQueryRepository;

    @Autowired
    private MemberLevelInfoService levelInfoService;

    @Autowired
    private MemberRightsAndInterestsModelRepository memberRightsAndInterestsModelRepository;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 查询会员等级详细信息（转换;后台管理权益管理页面接口）
     *
     * @return
     */
    @Override
    public List<MemberGradeVo> findAllDetails() {
        List<MemberGrade> memberGrades = this.findAll();
        List<MemberGradeVo> memberGradeVos = new ArrayList<>();
        for (MemberGrade memberGrade : memberGrades) {
            MemberGradeVo memberGradeVo = formatMemberGrade(memberGrade);
            memberGradeVos.add(memberGradeVo);
        }
        return memberGradeVos;
    }

    /**
     * 查询会员等级详细信息
     *
     * @return
     */
    public List<MemberGrade> findAll() {
        Object value = redisUtils.get(RedisConstant.REDIS_MEMBER_GRADES);
        List<MemberGrade> memberGrades;
        if (value == null) {
            memberGrades = memberGradeRepository.findAll();
            redisUtils.set(RedisConstant.REDIS_MEMBER_GRADES, memberGrades);
        } else {
            memberGrades = (List<MemberGrade>) value;
        }
        return memberGrades;


    }

    /**
     * 会员等级分布
     *
     * @return
     */
    @Override
    public List<MemberGradeStatVo> gradeDistribution() {
        List<MemberGradeStatVo> result = new ArrayList<>();
        List<MemberGrade> configList = memberGradeRepository.findByOrderByGradeAsc();
        if (configList.isEmpty()) {
            return result;
        }
        List<Object[]> list = memberNativeQueryRepository.queryGradeDistribution(configList);
        float total = 0F;
        for (Object[] objects : list) {
            MemberGradeStatVo vo = new MemberGradeStatVo();
            vo.setUserNum(objects[0] != null ? Long.parseLong(objects[0].toString()) : 0);
            vo.setGrade(objects[1] != null ? objects[1].toString() : "");

            total = total + vo.getUserNum();
            MemberGradeStatVo.GradeValue gradeValue = new MemberGradeStatVo.GradeValue();
            configList.forEach(item -> {
                if (item.getGrade() == Integer.parseInt(objects[2].toString())) {
                    gradeValue.setMax(item.getMaxGrowth());
                    gradeValue.setMin(item.getMinGrowth());
                }
            });
            vo.setGradeValue(gradeValue);
            result.add(vo);
        }
        for (MemberGradeStatVo vo : result) {
            if (total > 0) {
                vo.setRatio(BigDecimal.valueOf(vo.getUserNum() / total).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue());
            }
        }
        return result;
    }

    /**
     * 成长值分布
     *
     * @return
     */
    @Override
    public List<MemberGrowthStatVo> growthDistribution() {
        List<Object[]> objectList = memberGrowthValueDetailModelRepository.queryGrowthDistribution();
        List<MemberGrowthStatVo> result = new ArrayList<>();
        if (objectList.isEmpty()) {
            return result;
        }
        float total = 0F;
        for (Object[] objects : objectList) {
            MemberGrowthStatVo vo = new MemberGrowthStatVo();
            vo.setSource(objects[1] == null ? "" : objects[1].toString());
            vo.setGrowth(objects[2] == null ? 0 : Long.parseLong(objects[2].toString()));
            total = total + vo.getGrowth();
            result.add(vo);
        }
        for (MemberGrowthStatVo vo : result) {
            if (total > 0) {
                vo.setRatio(BigDecimal.valueOf(vo.getGrowth() / total).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue());
            }
        }
        return result;
    }

    /**
     * 查询会员等级信息(app服务接口)
     *
     * @return
     */
    @Override
    public Map<String, Object> findMemberGradeMsg(Long ssoid) {

        Map<String, Object> map = new HashMap<>(4);

        //根据会员ssoid获取改会员的成长值
        MemberRightsAndInterestsModel memberRightsAndInterestsModel = memberRightsAndInterestsModelRepository.findBySsoid(ssoid);
        int growthValue = 0;
        if (memberRightsAndInterestsModel != null) {
            growthValue = memberRightsAndInterestsModel.getGrowthValue();
        }
        //获取会员等级信息
        List<MemberGrade> memberGrades = this.findAll();
        MemberVo memberVo = formatMemberDao(memberGrades, growthValue);

        //获得权益列表
        List<MemberRightsSet> memberRightsSets = memberRightsSetRepository.findAll();

        //获取会员等级列表
        Map<String, Object> configureMap = new HashMap<>(4);

        //获取等级规则信息
        String content = levelInfoService.getRule().getContent();
        //将等级规则信息加入信息表
        configureMap.put("levelRule", content);

        //获取等级集合
        List<String> grades = formatGrades(memberGrades);

        configureMap.put("milestone", grades);
        //将会员对象添加入集合
        map.put("member", memberVo);
        //将权益列表添加入对象
        map.put("rights", memberRightsSets);
        //将会员等级列表信息添加入对象
        map.put("configure", configureMap);
        return map;
    }

    /**
     * 封装会员等级信息
     */
    private List<String> formatGrades(List<MemberGrade> memberGrades) {
        List<String> list = new ArrayList<>();
        for (MemberGrade memberGrade : memberGrades) {
            list.add("V" + memberGrade.getGrade());
        }
        return list;
    }

    /**
     * 查询会员等级列表
     *
     * @return
     */
    @Override
    public List<String> findGrades() {
        List<MemberGrade> list = this.findAll();
        List<String> grades = new ArrayList<>();
        for (MemberGrade memberGrade : list) {
            grades.add("V" + memberGrade.getGrade());
        }
        return grades;
    }

    /**
     * 更新用户等级规则方法
     *
     * @param memberGradeDto
     */
    @Override
    public void saveMemberGrade(MemberGradeDto memberGradeDto,String username) {

        //获取需要更新的对象
        Optional<MemberGrade> optionalGrade = memberGradeRepository.findById(memberGradeDto.getId());
        if (!optionalGrade.isPresent()) {
            throw new JMException("更新失败：请稍后重试");
        }
        MemberGrade memberGrade = optionalGrade.get();
        //设置消费折扣
        Integer cosumeDis = memberGradeDto.getCosumeDis();
        //折扣最大值
        int cosumeDisMax = 99;
        if (cosumeDis == null || cosumeDis > cosumeDisMax || cosumeDis < 0) {
            cosumeDis = 0;
        }
        memberGrade.setCosumeDis(cosumeDis);
        //设置积分奖励
        Integer rewardPoints = memberGradeDto.getRewardPoints();
        if (rewardPoints == null || rewardPoints < 0) {
            rewardPoints = 0;
        }
        memberGrade.setRewardPoints(rewardPoints);
        //设置消费卷
        memberGrade.setCoupons(JSON.toJSONString(memberGradeDto.getCoupons()));
        //设置等级名称
        if (StringUtils.isEmpty(memberGradeDto.getGradeName())) {
            throw new JMException(MessageConstant.MSG_NULL_GRADE+": name is null");
        }
        memberGrade.setGradeName(memberGradeDto.getGradeName());
        //设置更新时间
        memberGrade.setUpdateTime(new Timestamp(System.currentTimeMillis()+(1000*60*60*13)));
        //设置修改人
        memberGrade.setEditor(username);
        //调用更新方法
        memberGradeRepository.save(memberGrade);
        //删除redis缓存中的数据
        redisUtils.del(RedisConstant.REDIS_MEMBER_GRADES);
        //缓存最新的等级规则信息
        this.findAll();
    }

    /**
     * 根据成长值查询用户等级
     *
     * @param growth
     */
    @Override
    public MemberVo findGradeByGrowth(Integer growth) {
        List<MemberGrade> memberGrades = this.findAll();
        return formatMemberDao(memberGrades, growth);
    }

    /**
     *    格式化返回会员对象
     */
    private MemberVo formatMemberDao(List<MemberGrade> memberGrades, Integer growthNum) {
        MemberVo memberVo = new MemberVo();
        //设置会员成长值
        memberVo.setGrowValue("" + growthNum);
        //便利集合获得对应的等级信息
        for (MemberGrade memberGrade : memberGrades) {
            //获取最大值
            Integer maxGrowth = memberGrade.getMaxGrowth();
            //获取等级W
            Integer grade = memberGrade.getGrade();
            //判断等级是否有最大值
            if (maxGrowth != null) {
                if (growthNum <= maxGrowth && growthNum >= memberGrade.getMinGrowth()) {
                    //设置等级
                    memberVo.setLevel("V" + grade);
                    //获取下一等级
                    Integer nextLevel = grade + 1;
                    //设置下一等级
                    memberVo.setNextLevel("V" + nextLevel);
                    //获取下一等级的最小成长值
                    Integer nextMinGrowth = memberGrades.get(nextLevel).getMinGrowth();
                    //设置距离下一等级
                    memberVo.setNextValueDifference(nextMinGrowth - growthNum + "");
                    //设置会员等级名称
                    memberVo.setLevelName(memberGrade.getGradeName());
                    break;
                }
            } else { //等级为最高等级时
                //设置会员等级
                memberVo.setLevel("V" + grade);
                //设置会员等级名称
                memberVo.setLevelName(memberGrade.getGradeName());
            }
        }
        return memberVo;
    }

    /**
     * 数据转换
     * @author gaofan
     */
    private MemberGradeVo formatMemberGrade(MemberGrade memberGrade) {
        MemberGradeVo memberGradeVo = new MemberGradeVo();
        //设置会员等级id
        memberGradeVo.setId(memberGrade.getId());
        //设置会员等级
        memberGradeVo.setGrade("V" + memberGrade.getGrade());

        //设置积分奖励
        Integer rewardPoints = memberGrade.getRewardPoints();
        if (rewardPoints >= 0) {
            if (rewardPoints == 0) {
                memberGradeVo.setRewardPoints("正常");
            } else {
                memberGradeVo.setRewardPoints("+" + rewardPoints + "%");
            }
        }
        //设置消费折扣
        Integer cosumeDis = memberGrade.getCosumeDis();
        double dis = new BigDecimal(Integer.toString(cosumeDis)).multiply(new BigDecimal("0.1")).doubleValue();
        if (dis == 0) {
            memberGradeVo.setCosumeDis("不打折");
        } else if (dis > 0) {
            memberGradeVo.setCosumeDis(dis + "折");
        }
        //设置等级名称
        memberGradeVo.setGradeName(memberGrade.getGradeName());

        //设置优惠卡卷
        memberGradeVo.setCoupons(JSON.parseArray(memberGrade.getCoupons(), CouponsVo.class));

        return memberGradeVo;
    }
}

