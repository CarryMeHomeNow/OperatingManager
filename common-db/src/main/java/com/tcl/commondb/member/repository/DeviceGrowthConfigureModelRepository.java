package com.tcl.commondb.member.repository;

import com.tcl.commondb.member.model.DeviceGrowthConfigureModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface DeviceGrowthConfigureModelRepository extends JpaRepository<DeviceGrowthConfigureModel, Integer>, JpaSpecificationExecutor<DeviceGrowthConfigureModel> {

    DeviceGrowthConfigureModel findFirstByModel(String model);

    DeviceGrowthConfigureModel findFirstByBrandAndModel(String brand,String model);
}
