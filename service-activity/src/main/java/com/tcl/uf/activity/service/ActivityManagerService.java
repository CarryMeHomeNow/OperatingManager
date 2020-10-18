package com.tcl.uf.activity.service;

import com.tcl.uf.activity.dto.ActivityManagerConfigureDto;
import com.tcl.uf.activity.dto.ActivityManagerConfigureParams;
import com.tcl.uf.activity.vo.ActivityManagerConfigureDetailsVo;
import com.tcl.uf.activity.vo.ActivityManagerConfigureVo;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import org.springframework.data.domain.Pageable;

/**
 * @author youyun.xu
 * @ClassName: ${CLASSNAME}
 * @Description: 文章内容管理
 * @date 2020/8/17 18:53
 */
public interface ActivityManagerService {

    /**
     * 获取抽奖配置列表信息(分页)
     * @param paramBody
     * @param pageable
     * @param uid
     * @return
     */
    ListWithPage<ActivityManagerConfigureVo> advancedSearchWithCondition(ActivityManagerConfigureParams paramBody, Pageable pageable, Long uid);

    /**
     * 新增或修改活动配置
     * @param params
     * @return void
     */
    void saveOrUpdate(ActivityManagerConfigureDto params);

    /**
     * 新增或修改活动配置
     * @param activityConfigureId
     * @return void
     */
    ActivityManagerConfigureDetailsVo findActivityConfigureDetails(String activityConfigureId);


    /**
     * 删除活动配置信息
     * @param activityConfigureId
     * @return void
     */
    void delete(String activityConfigureId);

}
