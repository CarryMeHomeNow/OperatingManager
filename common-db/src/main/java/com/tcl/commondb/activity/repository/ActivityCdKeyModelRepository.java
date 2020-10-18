package com.tcl.commondb.activity.repository;


import com.tcl.commondb.activity.model.ActivityCdkeyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;


public interface ActivityCdKeyModelRepository extends JpaRepository<ActivityCdkeyModel,Long>, JpaSpecificationExecutor<ActivityCdkeyModel> {
    //查询除去已删除状态的
    @Query(value = "select p from ActivityCdkeyModel p where p.isDelete =1")
    Page<ActivityCdkeyModel> findAll(Pageable pageable);
    //软删除

    //失效生效
    //      @Query( value ="update ActivityCdkeyModel a set a.status = :status where a.id = :id")
    @Modifying
    @Transactional
    @Query( value ="update ActivityCdkeyModel a set a.status =:status  where a.id = :id")
   void updateStatus(@Param("id")Long id,@Param("status") Integer status);

    @Modifying
    @Transactional      //update activity_cdkey a set a.is_delete =2  where a.id = 8
    @Query(nativeQuery = true,value ="update activity_cdkey a set a.is_delete =2  where a.id =:id")
    void deleteById(@Param("id")Long id);

    //条件查询除去已删除状态的
//select * from activity_cdkey a where a.name = "兑换积分" or a.cd_type =2 or (a.create_time >= 17653214 and a.create_time <= 1598765713) and a.is_delete =1
    @Query(value = "select a from ActivityCdkeyModel a where a.name like CONCAT('%',:name,'%') or a.cdType =:cdType or (a.createTime >= :createTime and a.createTime <= :endTime) and a.isDelete =1")
    Page<ActivityCdkeyModel> findByCdTypeAndCreateTimeAndName (@Param("name")String name, @Param("cdType")Integer cdType, @Param("createTime") Date createTime,@Param("endTime") Date endTime, Pageable pageable);

}
