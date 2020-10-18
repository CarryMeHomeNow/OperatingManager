package com.tcl.commondb.version.repository;


import com.tcl.commondb.version.model.VersionFeedbackModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionFeedbackRepository extends JpaRepository<VersionFeedbackModel, Long> {


}
