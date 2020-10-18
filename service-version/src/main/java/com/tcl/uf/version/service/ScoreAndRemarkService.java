package com.tcl.uf.version.service;

import com.tcl.uf.version.dto.ScoreAndRemarkDto;

import javax.servlet.http.HttpServletRequest;

public interface ScoreAndRemarkService {
    /**
     * app用户评分
     * @param scoreAndRemarkDto
     * @return
     */
    String addScoreAndRemark(ScoreAndRemarkDto scoreAndRemarkDto, HttpServletRequest httpServletRequest);
}
