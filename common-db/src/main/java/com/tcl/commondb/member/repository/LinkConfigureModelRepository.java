package com.tcl.commondb.member.repository;

import com.tcl.commondb.member.model.LinkConfigureModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkConfigureModelRepository extends JpaRepository<LinkConfigureModel, Integer> {

    List<LinkConfigureModel> findByOrderByCreateTimeDesc();

    List<LinkConfigureModel> findByType(String type);

    LinkConfigureModel findFirstByType(String type);
}
