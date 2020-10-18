package com.tcl.uf.advert.service.impl;

import com.tcl.commondb.advert.model.AdvertLocationGroupModel;
import com.tcl.commondb.advert.repository.AdvertLocationGroupModelRepository;
import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.service.AdvertLocationGroupService;
import com.tcl.uf.advert.vo.LocationGroupListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertLocationGroupServiceImpl
 * @Description:广告管理平台广告位组管理
 * @date 2020/8/17 19:09
 */
@Service("AdvertLocationGroupService")
public class AdvertLocationGroupServiceImpl implements AdvertLocationGroupService {

    @Autowired
    AdvertLocationGroupModelRepository avertLocationGroupModelRepository;

    @Override
    public Map<Long, AdvertLocationGroupModel> getGroupMap(Set<Long> groupIds) {
        List<AdvertLocationGroupModel> groupList = avertLocationGroupModelRepository.findAllByIdIn(groupIds);
        return groupList.stream().collect(Collectors.toMap(AdvertLocationGroupModel::getId, advertLocationGroupModel -> advertLocationGroupModel));
    }

    @Override
    public List<LocationGroupListVo> findList() {
        List<AdvertLocationGroupModel> groupList = avertLocationGroupModelRepository.findAllByIsDeletedAndStatusOrderById(AdvertConstants.DELETE_FLAG_FALSE, AdvertConstants.COMMON_STATUS_TRUE);

        List<LocationGroupListVo> resultList = new ArrayList<>();

        groupList.forEach(item -> {
            LocationGroupListVo vo = new LocationGroupListVo(item.getId(), item.getTitle(), item.getPicWidth(), item.getPicHeight(), item.getPicSize(), item.getFrameNum(),item.getAppVer());
            Map<Integer, String> frameMap = new HashMap<>();
            for (int index = 1; index <= item.getFrameNum(); index++) {
                frameMap.put(index,"第"+index+"帧");
            }
            vo.setFrameMap(frameMap);
            resultList.add(vo);
        });

        return resultList;
    }

    @Override
    public Map<String, AdvertLocationGroupModel> getGroupTitleMap() {
        List<AdvertLocationGroupModel> groupList = avertLocationGroupModelRepository.findAllByIsDeletedAndStatusOrderById(AdvertConstants.DELETE_FLAG_FALSE, AdvertConstants.COMMON_STATUS_TRUE);
        return groupList.stream().collect(Collectors.toMap(AdvertLocationGroupModel::getTitle, advertLocationGroupModel -> advertLocationGroupModel));
    }
}
