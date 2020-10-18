package com.tcl.uf.member.service;

import com.tcl.uf.member.vo.LinkConfigureVo;

import java.util.List;

public interface LinkConfigureService {

    List<LinkConfigureVo> findLinkConfigure(String type);
}
