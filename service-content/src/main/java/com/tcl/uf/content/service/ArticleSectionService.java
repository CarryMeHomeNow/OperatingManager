package com.tcl.uf.content.service;

import com.tcl.uf.content.dto.ArticleSectionDto;
import com.tcl.uf.content.vo.ArticleSectionPositionSortVo;
import com.tcl.uf.content.vo.ArticleSectionVo;

import java.util.List;

public interface ArticleSectionService {

    List<ArticleSectionVo> findBackSectionList();

    List<ArticleSectionVo> findSectionList();

    ArticleSectionVo saveOrUpdate(ArticleSectionDto articleSectionDto);

    void delete(Long id);

    void positionSort(List<ArticleSectionPositionSortVo> position);
}
