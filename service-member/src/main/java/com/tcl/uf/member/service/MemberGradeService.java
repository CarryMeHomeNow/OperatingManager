package com.tcl.uf.member.service;

import com.tcl.uf.member.dto.MemberGradeDto;
import com.tcl.uf.member.vo.MemberGradeStatVo;
import com.tcl.uf.member.vo.MemberGradeVo;
import com.tcl.uf.member.vo.MemberGrowthStatVo;
import com.tcl.uf.member.vo.MemberVo;

import java.util.List;
import java.util.Map;

/**
 * @author gaofan
 * 2020年8月10日15:38:10
 * 权益管理服务接口
 */
public interface MemberGradeService {

    /**
     * 获取权益列表
     * @return
     */
    List<MemberGradeVo> findAllDetails();


    /**
     * 查询会员等级信息(app)
     * @return
     */
    Map<String,Object> findMemberGradeMsg(Long ssoid);

    /**
     *  更新用户等级规则方法
     * @param memberGradeDto
     */
    void saveMemberGrade(MemberGradeDto memberGradeDto,String username);

    /**
     * 根据成长值查询用户等级信息
     */
    MemberVo findGradeByGrowth(Integer growth);

    /**
     * 会员等级分布
     *
     * @return
     */
    List<MemberGradeStatVo> gradeDistribution();

    /**
     * 成长值分布
     *
     * @return
     */
    List<MemberGrowthStatVo> growthDistribution();


    /**
     * 查询会员等级列表
     * @return
     */
    List<String> findGrades();
}