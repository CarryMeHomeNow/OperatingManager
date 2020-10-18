package com.tcl.commondb.version.repository;

import com.tcl.commondb.version.model.ExternalVersionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExternalVersionRepository extends JpaRepository<ExternalVersionModel, Long>, JpaSpecificationExecutor<ExternalVersionModel> {

    @Modifying
    @Query("update ExternalVersionModel e set e.useStatus=0 where e.id in (:ids)")
    int deleteByIdIn(@Param("ids") List<Long> ids);

    @Query(nativeQuery = true, value = "select t.platform_id, p.platform_name, t.ver "
            + "from "
            + "( select v.platform_id, max( v.app_ver ) ver "
            + "  from app_external_version v where v.use_status = 1 group by v.platform_id /* #pageable# */ ) t "
            + "left join app_publish_platform p on t.platform_id = p.platform_id ",
            countQuery = "select v.platform_id, max( v.app_ver ) ver from app_external_version v where v.use_status = 1 group by v.platform_id ")
    Page<Object[]> onlineList(Pageable pageable);

    List<ExternalVersionModel> queryByPlatformIdAndAppVerAndUseStatus(@Param("platformId") String platformId, @Param("appVer") String appVer,
                                                                      @Param("useStatus") Integer useStatus);


}
