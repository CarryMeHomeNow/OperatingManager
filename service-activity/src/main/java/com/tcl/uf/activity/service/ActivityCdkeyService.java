package com.tcl.uf.activity.service;



import com.tcl.commondb.activity.model.ActivityCdkeyModel;
import com.tcl.uf.activity.dto.ActivityCdkeyListDto;
import com.tcl.uf.activity.vo.ActivityCdkeySaveVo;
import com.tcl.uf.activity.vo.ActivityCdkeyVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ActivityCdkeyService {

    Map<String,Object> findAll(ActivityCdkeyListDto activityCdkeyListDto, Integer page, Integer size);

     boolean insertCdKey(ActivityCdkeySaveVo activityCdkeySaveVo);

    boolean updateCdKey(ActivityCdkeySaveVo activityCdkeySaveVo);

     boolean deleteCdKey(Long id);

     boolean updateCdKeyStatus(Long id,Integer status);

    Page<ActivityCdkeyModel> exportCdKey(Pageable pageable);


    boolean exchangeCdKey(String cdk) throws Exception;
}
