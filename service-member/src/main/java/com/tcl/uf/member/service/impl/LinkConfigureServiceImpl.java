package com.tcl.uf.member.service.impl;

import com.tcl.commondb.member.model.LinkConfigureModel;
import com.tcl.commondb.member.repository.LinkConfigureModelRepository;
import com.tcl.uf.member.service.LinkConfigureService;
import com.tcl.uf.member.vo.LinkConfigureVo;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("linkConfigureService")
public class LinkConfigureServiceImpl implements LinkConfigureService {

    @Autowired
    private LinkConfigureModelRepository linkConfigureModelRepository;

    @Override
    public List<LinkConfigureVo> findLinkConfigure(String type) {
        List<LinkConfigureModel> list;
        if(StringUtil.isBlank(type)){
            list = linkConfigureModelRepository.findByOrderByCreateTimeDesc();
        }else{
            list = linkConfigureModelRepository.findByType(type);
        }
        return each(list);
    }

    private List<LinkConfigureVo> each(List<LinkConfigureModel> list) {
        List<LinkConfigureVo> linkConfigureVo = new ArrayList<LinkConfigureVo>();
        for (LinkConfigureModel linkConfigure : list) {
            LinkConfigureVo linkVo = new LinkConfigureVo();
            linkVo.setName(linkConfigure.getName());
            linkVo.setType(linkConfigure.getType());
            linkVo.setJumpUrl(linkConfigure.getJumpUrl());
            linkConfigureVo.add(linkVo);
        }
        return linkConfigureVo;
    }
}
