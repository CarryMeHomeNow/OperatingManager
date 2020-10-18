package com.tcl.uf.activity.service.impl;

import com.tcl.uf.activity.dto.ActivityManagerConfigureDto;
import com.tcl.uf.activity.dto.ActivityManagerConfigureParams;
import com.tcl.uf.activity.service.ActivityManagerService;
import com.tcl.uf.activity.vo.ActivityManagerConfigureDetailsVo;
import com.tcl.uf.activity.vo.ActivityManagerConfigureVo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author youyun.xu
 * @ClassName: ActivityManagerServiceImpl
 * @Description: 活动管理Service
 * @date 2020/8/17 18:54
 */
@Service("activityManagerService")
public class ActivityManagerServiceImpl implements ActivityManagerService {

    @Override
    public ListWithPage<ActivityManagerConfigureVo> advancedSearchWithCondition(ActivityManagerConfigureParams paramBody, Pageable pageable, Long uid) {


        return null;
    }

    @Override
    public void saveOrUpdate(ActivityManagerConfigureDto params) {

    }

    @Override
    public ActivityManagerConfigureDetailsVo findActivityConfigureDetails(String activityConfigureId) {
        return null;
    }

    @Override
    public void delete(String activityConfigureId) {

    }
}
