package com.tcl.uf.member.service.impl;

import com.tcl.commondb.member.model.MemberRightsSet;
import com.tcl.commondb.member.repository.MemberRightsSetRepository;
import com.tcl.uf.common.base.constant.MessageConstant;
import com.tcl.uf.common.base.constant.RedisConstant;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.member.dto.MemberRightsSetDto;
import com.tcl.uf.member.service.MemberRightsSetService;
import com.tcl.uf.member.utils.RedisUtils;
import com.tcl.uf.member.vo.MemberRightsSetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author gaofan
 */
@Service
public class MemberRightsSetServiceImpl implements MemberRightsSetService {

    @Autowired
    private MemberRightsSetRepository memberRightsSetRepository;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 查询所有权益信息
     *
     * @return
     */
    @Override
    public List<MemberRightsSetVo> findAll() {
        Object object = redisUtils.get(RedisConstant.REDIS_MEMBER_RIGHTS_LIST);
        List<MemberRightsSetVo> list = new ArrayList<>();
        if (object == null) {
            List<MemberRightsSet> memberRightsSets = memberRightsSetRepository.findAll();
            if (memberRightsSets.isEmpty()) {
                throw new JMException(MessageConstant.MSG_NULL_OBJECT);
            }
            for (MemberRightsSet memberRightsSet : memberRightsSets) {
                Integer isDelete = memberRightsSet.getIsDelete();
                if (StringUtils.isEmpty(isDelete)) {
                    isDelete = 0;
                }
                if (isDelete == 0) {
                    list.add(fomatMemberRightSrtVo(memberRightsSet));
                }
            }
            redisUtils.set(RedisConstant.REDIS_MEMBER_RIGHTS_LIST, list);
        } else {
            list = (List<MemberRightsSetVo>) object;
        }
        return list;
    }

    /**
     * 新增(修改)权益信息
     *
     * @param memberRightsSetDto
     */
    @Override
    public void insertMemberRights(MemberRightsSetDto memberRightsSetDto, String username) {
        MemberRightsSet memberRightsSet;
        if (StringUtils.isEmpty(memberRightsSetDto.getId())) {
            //新增
            memberRightsSet = new MemberRightsSet();
            memberRightsSet.setCreator(username);
            memberRightsSet.setSn(UUID.randomUUID().toString().replace("-", ""));
        } else {
            //修改
            Optional<MemberRightsSet> optional = memberRightsSetRepository.findById(memberRightsSetDto.getId());
            if (optional.isPresent()) {
                memberRightsSet = optional.get();
                memberRightsSet.setEditor(username);
                memberRightsSet.setUpdateTime(new Timestamp(System.currentTimeMillis() + (1000 * 60 * 60 * 13)));
            } else {
                throw new JMException(MessageConstant.MSG_NULL_OBJECT);
            }
        }
        MemberRightsSet data = validateMemberRightSet(memberRightsSet, memberRightsSetDto);
        memberRightsSetRepository.save(data);
        redisUtils.del(RedisConstant.REDIS_MEMBER_RIGHTS_LIST);
        this.findAll();
    }

    /**
     * 数据效验
     */
    private MemberRightsSet validateMemberRightSet(MemberRightsSet  memberRightsSet,MemberRightsSetDto memberRightsSetDto) {

        //权益名称
        if (!StringUtils.isEmpty(memberRightsSetDto.getName())) {
            memberRightsSet.setName(memberRightsSetDto.getName());
        } else {
            throw new JMException(MessageConstant.MSG_NULL_RIGHTS + ": name is null");
        }
        //权益图标地址
        if (!StringUtils.isEmpty(memberRightsSetDto.getCoverUrl())) {
            memberRightsSet.setCoverUrl(memberRightsSetDto.getCoverUrl());
        } else {
            throw new JMException(MessageConstant.MSG_NULL_RIGHTS + ": coverUrl is null");
        }
        //权益介绍
        memberRightsSet.setDescription(memberRightsSetDto.getDescription());
        //权益获取条件
        memberRightsSet.setGetCondition(memberRightsSetDto.getGetCondition());
        //权益跳转地址
        if (!StringUtils.isEmpty(memberRightsSetDto.getJumpUrl())) {
            memberRightsSet.setJumpUrl(memberRightsSetDto.getJumpUrl());
        } else {
            throw new JMException(MessageConstant.MSG_NULL_RIGHTS + ": jumpUrl is not null");
        }
        //设置删除状态为0
        memberRightsSet.setIsDelete(0);
        return memberRightsSet;
    }

    /**
     * 根据ID查询权益信息
     */
    @Override
    public MemberRightsSetVo findById(Integer id) {
        Optional<MemberRightsSet> optional = memberRightsSetRepository.findById(id);
        MemberRightsSet memberRightsSet;
        if (optional.isPresent()) {
            memberRightsSet = optional.get();
        }else {
            throw new JMException(MessageConstant.MSG_FINDBYID_NULL_RIGHTS);
        }
        if (memberRightsSet.getIsDelete() == 1) {
            throw new JMException(MessageConstant.MSG_FINDBYID_NULL_RIGHTS);
        }
        return fomatMemberRightSrtVo(memberRightsSet);

    }

    /**
     * 根据权益id删除权益信息
     * @param id
     * @param username
     */
    @Override
    public void delete(Integer id, String username) {
        Optional<MemberRightsSet> optional = memberRightsSetRepository.findById(id);
        if (optional.isPresent()) {
            MemberRightsSet memberRightsSet = optional.get();
            memberRightsSet.setIsDelete(1);
            memberRightsSet.setEditor(username);
            memberRightsSet.setUpdateTime(new Timestamp(System.currentTimeMillis() + (1000 * 60 * 60 * 13)));
            memberRightsSetRepository.save(memberRightsSet);
            redisUtils.del(RedisConstant.REDIS_MEMBER_RIGHTS_LIST);
            this.findAll();
        }else {
            throw new JMException(MessageConstant.MSG_FINDBYID_NULL_RIGHTS);
        }

    }

    private MemberRightsSetVo fomatMemberRightSrtVo(MemberRightsSet memberRightsSet) {
        MemberRightsSetVo memberRightsSetVo = new MemberRightsSetVo();
        //id
        memberRightsSetVo.setId(memberRightsSet.getId());
        //权益图标地址
        memberRightsSetVo.setCoverUrl(memberRightsSet.getCoverUrl());
        //权益唯一标识
        memberRightsSetVo.setSn(memberRightsSet.getSn());
        //获取条件
        memberRightsSetVo.setGetCondition(memberRightsSet.getGetCondition());
        //权益介绍
        memberRightsSetVo.setDescription(memberRightsSet.getDescription());
        //领取链接
        memberRightsSetVo.setJumpUrl(memberRightsSet.getJumpUrl());
        //权益名称
        memberRightsSetVo.setName(memberRightsSet.getName());

        return memberRightsSetVo;
    }


}
