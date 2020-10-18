package com.tcl.uf.member.service;

import com.tcl.uf.member.dto.MemberRightsSetDto;
import com.tcl.uf.member.vo.MemberRightsSetVo;

import java.util.List;

/**
 * @author gaofan
 */
public interface MemberRightsSetService {

    /**
     * 查询所有权益信息
     *
     * @return
     */
    List<MemberRightsSetVo> findAll();

    /**
     * 新增(修改)权益信息
     */
    void insertMemberRights(MemberRightsSetDto memberRightsSetDto,String username);

    /**
     * 根据ID查询权益信息
     */
    MemberRightsSetVo findById(Integer id);


    /**
     * 根据id删除权信息
     * @param id
     * @param username
     */
    void delete(Integer id, String username);
}
