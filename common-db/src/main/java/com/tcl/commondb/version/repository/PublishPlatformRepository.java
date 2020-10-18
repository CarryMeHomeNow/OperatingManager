package com.tcl.commondb.version.repository;
import com.tcl.commondb.version.model.PublishPlatformModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublishPlatformRepository  extends JpaRepository<PublishPlatformModel, Long>  {

    List<PublishPlatformModel> queryByPlatformId(@Param("platformId") String platformId);

    List<PublishPlatformModel> findByAndUseStatusOrderByPlatformIdAscCreateTimeDesc(@Param("useStatus") Integer useStatus);
}
