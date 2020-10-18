package com.tcl.commondb.content.repository;

import com.tcl.commondb.content.model.ArticleOperationalRecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleOperationalRecordModelRepository extends JpaRepository<ArticleOperationalRecordModel, Long>, JpaSpecificationExecutor<ArticleOperationalRecordModel> {
}
