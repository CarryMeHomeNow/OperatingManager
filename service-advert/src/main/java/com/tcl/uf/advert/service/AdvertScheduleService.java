package com.tcl.uf.advert.service;

import com.tcl.uf.advert.dto.LocationScheduleDto;
import com.tcl.uf.advert.dto.ScheduleListParams;
import com.tcl.uf.advert.dto.ScheduleResourceListParams;
import com.tcl.uf.advert.vo.ImportScheduleVo;
import com.tcl.uf.advert.vo.ResourceDetailVo;
import com.tcl.uf.advert.vo.ScheduleDayListVo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface AdvertScheduleService {

    ImportScheduleVo importSchedule(MultipartFile file, Integer month);

    Integer batchSaveSchedule(List<LocationScheduleDto> scheduleDtoList, String username);

    List<ScheduleDayListVo> findList(ScheduleListParams scheduleListParams);

    Set<String> findLocationDateList(String username, Long groupId, Integer frameId);

    ListWithPage<ResourceDetailVo> resourceList(Pageable pageable, ScheduleResourceListParams resourceListParams, String username);

}
