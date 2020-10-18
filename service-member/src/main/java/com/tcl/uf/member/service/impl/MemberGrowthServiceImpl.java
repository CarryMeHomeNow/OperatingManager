package com.tcl.uf.member.service.impl;
import java.util.Date;

import com.tcl.commondb.member.model.DeviceGrowthConfigureModel;
import com.tcl.commondb.member.model.MemberGrowthValueDetailModel;
import com.tcl.commondb.member.model.MemberRightsAndInterestsModel;
import com.tcl.commondb.member.repository.DeviceGrowthConfigureModelRepository;
import com.tcl.commondb.member.repository.MemberGrowthValueDetailModelRepository;
import com.tcl.commondb.member.repository.MemberRightsAndInterestsModelRepository;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.member.annotation.InitGrowth;
import com.tcl.uf.member.dto.HardwareDeviceGrowthValueDto;
import com.tcl.uf.member.dto.ServiceGrowthValueDto;
import com.tcl.uf.member.enums.GrowthOperationalTypeEnums;
import com.tcl.uf.member.service.MemberGrowthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author youyun.xu
 * @ClassName: MemberGrowthService
 * @Description: 成长值管理service
 * @date 2020/8/31 10:01
 */
@Service("memberGrowthService")
public class MemberGrowthServiceImpl implements MemberGrowthService {

    @Autowired
    private DeviceGrowthConfigureModelRepository deviceGrowthConfigureModelRepository;

    @Autowired
    private MemberGrowthValueDetailModelRepository growthValueDetailModelRepository;

    @Autowired
    private MemberRightsAndInterestsModelRepository rightsAndInterestsModelRepository;

    @Override
    @InitGrowth
    @Transactional(rollbackFor = Exception.class)
    public String hardwareDevice(HardwareDeviceGrowthValueDto growthValueDto) throws JMException {
        DeviceGrowthConfigureModel deviceGrowthConfigureModel= deviceGrowthConfigureModelRepository.findFirstByModel(growthValueDto.getModel());
        if(deviceGrowthConfigureModel == null){
            throw new JMException(-1,"查无此型号对应成长值数据");
        }
        Integer growthValue  = deviceGrowthConfigureModel.getGrowthValue();
        MemberGrowthValueDetailModel memberGrowthValueDetailModel= new MemberGrowthValueDetailModel();
        memberGrowthValueDetailModel.setSsoid(growthValueDto.getSsoid());
        memberGrowthValueDetailModel.setGrowthValue(growthValue);
        memberGrowthValueDetailModel.setType(GrowthOperationalTypeEnums.ADD.getValue());
        memberGrowthValueDetailModel.setSn(growthValueDto.getSn());
        memberGrowthValueDetailModel.setDeviceModel(growthValueDto.getModel());
        memberGrowthValueDetailModel.setSource(growthValueDto.getSource());
        memberGrowthValueDetailModel.setComment("");
        memberGrowthValueDetailModel.setCreateTime(new Date());

        /*每份成长值在获取后即时生效，其有效期从下个月1日开始计算，往后推算24个月。到期则失效。
        （如2020年7月10日获取成长值100，则该成长值的有效期从2020年8月1日开始推算，直至2022年7月31日23:59:59失效）*/
        Date firstDate = TimeUtils.nextMonthFirstDate();
        memberGrowthValueDetailModel.setExpiredTime(TimeUtils.addDateMonth(firstDate,24));
        growthValueDetailModelRepository.saveAndFlush(memberGrowthValueDetailModel);

        Long ssoid = growthValueDto.getSsoid();
        MemberRightsAndInterestsModel rightsAndInterestsModel = rightsAndInterestsModelRepository.findBySsoid(ssoid);
        if(rightsAndInterestsModel == null){
            rightsAndInterestsModel= new MemberRightsAndInterestsModel();
            rightsAndInterestsModel.setCreateTime(new Date());
            rightsAndInterestsModel.setUpdateTime(new Date());
            rightsAndInterestsModel.setSsoid(ssoid);
        }
        rightsAndInterestsModel.setGrowthValue(growthValue); //此处是否考虑维护等级?
        rightsAndInterestsModelRepository.saveAndFlush(rightsAndInterestsModel);
        return "添加成功";
    }

    @Override
    @InitGrowth
    public String softwareService(ServiceGrowthValueDto growthValueDto) {
        return null;
    }
}
