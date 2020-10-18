package com.tcl.commondb.member.repository;

import com.tcl.commondb.member.model.LevelRuleConfigureModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRuleConfigureModelRepository extends JpaRepository<LevelRuleConfigureModel, Integer> {

    LevelRuleConfigureModel findFirstByOrderByCreateTimeDesc();

}
