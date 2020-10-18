package com.tcl.commondb.activity.repository;

import com.tcl.commondb.activity.model.PropertiesConfigModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertiesConfigModelRepository extends JpaRepository<PropertiesConfigModel, Long> {

	PropertiesConfigModel findFirstByPkeyEquals(String pkey);
}
