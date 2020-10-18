package com.tcl.commondb.tangram.repository;

import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhongfk on 2020/8/14.
 * @version 1.0
 */
public interface TangramTemplateConfigRepository extends JpaRepository<TangramTemplateConfig,Long>,JpaSpecificationExecutor<TangramTemplateConfigRepository> {

    /**
     * 根据模板编号查询模板
     * @param TangramCode
     * @return
     */
    TangramTemplateConfig findTangramTemplateConfigByTangramCode(String TangramCode);

}
