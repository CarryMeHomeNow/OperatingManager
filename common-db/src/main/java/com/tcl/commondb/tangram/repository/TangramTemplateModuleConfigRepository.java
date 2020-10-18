package com.tcl.commondb.tangram.repository;

import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhongfk on 2020/8/14.
 * @version 1.0
 */
public interface TangramTemplateModuleConfigRepository extends JpaRepository<TangramTemplateModuleConfig,Long> {
    /**
     * 根据组件名称 查询组件信息
     * @param stringList
     * @return
     */
    List<TangramTemplateModuleConfig> findByModuleNameIn(List<String> stringList);
}
