package com.tcl.uf.content.service;

import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.content.dto.ArticleAuditParams;
import com.tcl.uf.content.dto.ArticleRejectDto;
import com.tcl.uf.content.vo.ArticleAuditVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleAuditService {

    void auditPass(List<Long> articleId);

    void auditReject(ArticleRejectDto articleRejectDto);

    ListWithPage<ArticleAuditVo> findList(Pageable pageable, ArticleAuditParams articleAuditParams);
}
