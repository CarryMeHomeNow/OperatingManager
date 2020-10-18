package com.tcl.commondb.member.repository;

import com.tcl.commondb.member.model.AfterSaleConfigureModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AfterSaleConfigureModelRepository extends JpaRepository<AfterSaleConfigureModel, Integer> {

    List<AfterSaleConfigureModel> findByOrderByPositionAsc();

}
