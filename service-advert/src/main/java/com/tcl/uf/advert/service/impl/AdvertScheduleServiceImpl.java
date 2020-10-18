package com.tcl.uf.advert.service.impl;

import com.tcl.commondb.advert.model.*;
import com.tcl.commondb.advert.repository.*;
import com.tcl.uf.advert.consts.AdvertConstants;
import com.tcl.uf.advert.dto.LocationScheduleDto;
import com.tcl.uf.advert.dto.ScheduleListParams;
import com.tcl.uf.advert.dto.ScheduleResourceListParams;
import com.tcl.uf.advert.service.AdvertDepartmentService;
import com.tcl.uf.advert.service.AdvertLocationGroupService;
import com.tcl.uf.advert.service.AdvertScheduleService;
import com.tcl.uf.advert.service.AdvertUserService;
import com.tcl.uf.advert.vo.ImportScheduleVo;
import com.tcl.uf.advert.vo.ResourceDetailVo;
import com.tcl.uf.advert.vo.ScheduleDayListVo;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.common.base.util.SqlBuilder;
import com.tcl.uf.common.base.util.TimeUtils;
import com.tcl.uf.common.base.util.pageutiles.ListWithPage;
import com.tcl.uf.common.base.util.pageutiles.PageUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author linhuimin@tcl.com
 * @ClassName: AdvertScheduleServiceImpl
 * @Description:广告管理平台投放排期管理
 * @date 2020/8/24 16:59
 */
@Service("AdvertScheduleService")
public class AdvertScheduleServiceImpl implements AdvertScheduleService {

    private static final Logger _log = LoggerFactory.getLogger(AdvertScheduleServiceImpl.class);

    @Autowired
    AdvertUserModelRepository advertUserModelRepository;

    @Autowired
    AdvertDepartmentModelRepository advertDepartmentModelRepository;

    @Autowired
    AdvertLocationGroupModelRepository avertLocationGroupModelRepository;

    @Autowired
    AdvertLocationModelRepository advertLocationModelRepository;

    @Autowired
    AdvertDepartmentService advertDepartmentService;

    @Autowired
    AdvertLocationGroupService advertLocationGroupService;

    @Autowired
    AdvertScheduleModelRepository advertScheduleModelRepository;

    @Autowired
    AdvertUserService advertUserService;

    @Autowired
    EntityManager entityManager;

    @Override
    public ImportScheduleVo importSchedule(MultipartFile file, Integer month) {
        if (file == null) {
            throw new JMException("请选择正确的EXCEL文件导入");
        }
        if (month < 1 || month > 12) {
            throw new JMException("输入月份错误");
        }
        String name = file.getOriginalFilename();
        if (!name.endsWith(".xls") && !name.endsWith(".xlsx")) {
            throw new JMException("请选择正确的EXCEL文件导入");
        }
        Workbook wb = null;
        InputStream in = null;
        ImportScheduleVo importScheduleVo= new ImportScheduleVo();
        try {
            try {
                in = file.getInputStream();
                if (name.endsWith(".xls")) {
                    wb = new HSSFWorkbook(in);
                } else {
                    wb = new XSSFWorkbook(in);
                }
            } catch (Exception e) {
                throw new JMException("请选择正确的EXCEL文件导入");
            }

            Map<String, Long> departmentMap = advertDepartmentService.getDepartmentTitleMap();
            Map<String, AdvertLocationGroupModel> locationGroupMap = advertLocationGroupService.getGroupTitleMap();
            Map<String, String> failMap = new HashMap<>();
            List<LocationScheduleDto> saveList = new ArrayList<>();
            Integer successNum = 0;
            Integer failNum = 0;

            //定义日期数组
            int[] ymd = TimeUtils.dateToArr(new Date());
            ymd[1] = month;
            int daysOfMonth = TimeUtils.getLengthOfMonth(ymd[0],ymd[1]);

            Sheet sheet = wb.getSheetAt(0);
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int rowNo = 1; rowNo < physicalNumberOfRows; rowNo++) {

                Row row = sheet.getRow(rowNo);
                String groupTitle = getCellStringValue(row.getCell(0), null);
                Long groupId = Long.getLong("0");
                Integer frameId = parseFrameValue(row.getCell(1));

                if(locationGroupMap.containsKey(groupTitle)){
                    AdvertLocationGroupModel locationGroupModel = locationGroupMap.get(groupTitle);
                    groupId = locationGroupModel.getId();
                    if(frameId > locationGroupModel.getFrameNum() || frameId == 0){
                        failNum++;
                        failMap.put(rowNo + "-" + 2,"广告组帧ID不存在");
                        continue;
                    }
                }else{
                    failNum++;
                    failMap.put(rowNo + "-" + 1,"广告组标题不存在");
                    continue;
                }

                AdvertLocationModel locationModel = advertLocationModelRepository.findByIsDeletedAndGidAndFrameId(AdvertConstants.DELETE_FLAG_FALSE, groupId, frameId);
                if(locationModel == null){
                    failNum++;
                    failMap.put(rowNo + "-" + 2,"广告位不存在");
                    continue;
                }

                for(int c = 2; c < daysOfMonth + 2; c++){
                    Cell cell = row.getCell(c);
                    String departmentName = getCellStringValue(cell,null);
                    if(StringUtils.isEmpty(departmentName)){
                        continue;
                    }else if(!departmentMap.containsKey(departmentName)) {
                        failNum++;
                        failMap.put(rowNo + "-" + c+1,"业务方不存在");
                        continue;
                    }

                    ymd = TimeUtils.dateToArr(new Date());
                    ymd[1] = month;
                    ymd[2] = c - 1;
                    Date effectiveDay = TimeUtils.arrToDate(ymd);
                    effectiveDay = TimeUtils.conversionDate(TimeUtils.formatDate(effectiveDay));
                    LocationScheduleDto locationScheduleDto = new LocationScheduleDto(locationModel.getId(), locationModel.getGid(), locationModel.getFrameId(),departmentMap.get(departmentName), TimeUtils.formatDate(effectiveDay));
                    saveList.add(locationScheduleDto);
                    successNum++;
                }
            }
            importScheduleVo.setFailMap(failMap);
            importScheduleVo.setFailNum(failNum);
            importScheduleVo.setSuccessNum(successNum);
            importScheduleVo.setSuccessList(saveList);
        } finally {
            try {
                if (wb != null) {
                    wb.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return importScheduleVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer batchSaveSchedule(List<LocationScheduleDto> scheduleDtoList, String username){
        Integer successNum = 0;
        for (LocationScheduleDto locationScheduleDto : scheduleDtoList) {
            try {
                Date effectiveDate = TimeUtils.getDate(locationScheduleDto.getEffectiveDate());
                Integer effectiveDay = Integer.parseInt(TimeUtils.formatDate(effectiveDate, "yyyyMMdd"));
                Integer effectiveMonth = Integer.parseInt(TimeUtils.formatDate(effectiveDate, "yyyyMM"));
                AdvertScheduleModel advertScheduleModel = advertScheduleModelRepository.findFirstByIsDeletedAndLocIdAndEffectiveDay(AdvertConstants.DELETE_FLAG_FALSE, locationScheduleDto.getLocId(), effectiveDay);
                if (advertScheduleModel != null) {
                    advertScheduleModel.setEditor(username);
                    advertScheduleModel.setUpdateTime(TimeUtils.getTimestamp());
                } else {
                    advertScheduleModel = new AdvertScheduleModel();
                    advertScheduleModel.setCreator(username);
                    advertScheduleModel.setCreateTime(TimeUtils.getTimestamp());
                }
                advertScheduleModel.setLocId(locationScheduleDto.getLocId());
                advertScheduleModel.setDepartmentId(locationScheduleDto.getDepartmentId());
                advertScheduleModel.setEffectiveDay(effectiveDay);
                advertScheduleModel.setEffectiveMonth(effectiveMonth);
                advertScheduleModel.setEffectiveDate(TimeUtils.getTimestamp(locationScheduleDto.getEffectiveDate()));
                advertScheduleModel.setGroupId(locationScheduleDto.getGroupId());
                advertScheduleModel.setFrameId(locationScheduleDto.getFrameId());
                advertScheduleModel.setIsDeleted(AdvertConstants.DELETE_FLAG_FALSE);
                advertScheduleModelRepository.saveAndFlush(advertScheduleModel);
                successNum++;
            } catch (Exception e) {
                _log.error("批量保存排期表数据失败locId=={} departmentId=={} effectiveDay=={} message=={} ", locationScheduleDto.getLocId(), locationScheduleDto.getDepartmentId(), locationScheduleDto.getEffectiveDate(), e.getMessage());
                throw new JMException(e.getMessage());
            }
        }
        return successNum;
    }

    @Override
    public List<ScheduleDayListVo> findList(ScheduleListParams scheduleListParams) {
        AdvertLocationModel advertLocationModel;
        if(scheduleListParams.getGroupId() != null && scheduleListParams.getFrameId() != null){
            advertLocationModel = advertLocationModelRepository.findByIsDeletedAndGidAndFrameId(AdvertConstants.DELETE_FLAG_FALSE,scheduleListParams.getGroupId(),scheduleListParams.getFrameId());
            if(advertLocationModel == null){
                return new ArrayList<>();
            }
            scheduleListParams.setLocId(advertLocationModel.getId());
        }

        if (StringUtils.isBlank(scheduleListParams.getStartDate()) || StringUtils.isBlank(scheduleListParams.getEndDate())) {
            throw new JMException("请选择日期条件");
        }

        List<AdvertScheduleModel> result = advertScheduleModelRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> createConfigurationRecordQueryCondition(root, criteriaQuery, criteriaBuilder, scheduleListParams));

        Set<Long> groupIds = new HashSet<>();
        Set<Long> departmentIds = new HashSet<>();
        List<ScheduleDayListVo> scheduleVoList = new ArrayList<>();
        Map<Long,Map<String, String>> scheduleMap = new HashMap<>();

        result.forEach(item -> {
            Map<String, String> dayMap = new HashMap<>();
            String dayKey = "";
            if (item.getEffectiveDate() != null) {
                dayKey = TimeUtils.formatDate(item.getEffectiveDate(),"yyyy-MM-dd");
            }
            if(scheduleMap.containsKey(item.getLocId())){
                dayMap = scheduleMap.get(item.getLocId());
            }else{
                ScheduleDayListVo scheduleDayListVo = new ScheduleDayListVo(item.getLocId(), item.getGroupId(), null, item.getFrameId(), item.getFrameId().toString());
                scheduleVoList.add(scheduleDayListVo);
            }
            dayMap.put(dayKey,item.getDepartmentId().toString());
            scheduleMap.put(item.getLocId(),dayMap);
            groupIds.add(item.getGroupId());
            departmentIds.add(item.getDepartmentId());
        });

        Map<Long, AdvertLocationGroupModel> groupMap = advertLocationGroupService.getGroupMap(groupIds);
        Map<Long, AdvertDepartmentModel> departmentMap = advertDepartmentService.getDepartmentMap(departmentIds);

        scheduleVoList.forEach(e -> {
            Map<String, String> dayIdMap;
            Map<String, String> dayTitleMap = new TreeMap<>();
            if (groupMap.containsKey(e.getGroupId())) {
                e.setGroupName(groupMap.get(e.getGroupId()).getTitle());
            }
            if(scheduleMap.containsKey(e.getLocId())){
                dayIdMap = scheduleMap.get(e.getLocId());
                dayIdMap.forEach((key, value) -> {
                    if (departmentMap.containsKey(Long.parseLong(value))) {
                        value = departmentMap.get(Long.parseLong(value)).getDepartmentName();
                    }
                    dayTitleMap.put(key, value);
                });
                e.setDayMap(dayTitleMap);
            }
        });
        return scheduleVoList;
    }

    @Override
    public Set<String> findLocationDateList(String username, Long groupId, Integer frameId) {
        Set<String> dateSet = new TreeSet<>();
        Integer today = Integer.parseInt(TimeUtils.formatDate(new Date(), "yyyyMMdd"));
        List<Timestamp> timestampList;
        if(username != null){
            AdvertUserModel advertUserModel = advertUserService.findUserByName(username);
            timestampList = advertScheduleModelRepository.findEffectiveDateByDepartmentIdAndGroupIdAndFrameId(AdvertConstants.DELETE_FLAG_FALSE,groupId,frameId,advertUserModel.getDepartmentId(),today);
        }else{
            timestampList = advertScheduleModelRepository.findEffectiveDateByGroupIdAndFrameId(AdvertConstants.DELETE_FLAG_FALSE, groupId, frameId, today);
        }

        timestampList.forEach(e -> {
            if(e != null){
                dateSet.add(TimeUtils.formatDate(e,"yyyy-MM-dd"));
            }
        });
        return dateSet;
    }

    @Override
    public ListWithPage<ResourceDetailVo> resourceList(Pageable pageable, ScheduleResourceListParams resourceListParams, String username) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.hasRole(AdvertConstants.ADVERT_ROLE_ADMIN)) {
            AdvertUserModel userModel = advertUserService.findUserByName(username);
            resourceListParams.setDepartmentId(userModel.getDepartmentId());
        }

        Integer startDay = null;
        Integer endDay = null;
        if(StringUtils.isNotBlank(resourceListParams.getStartTime())){
            Date effectiveDate = TimeUtils.parseDate(resourceListParams.getStartTime(), "yyyy-MM-dd");
            startDay = Integer.parseInt(TimeUtils.formatDate(effectiveDate, "yyyyMMdd"));
        }

        if(StringUtils.isNotBlank(resourceListParams.getEndTime())){
            Date effectiveDate = TimeUtils.parseDate(resourceListParams.getEndTime(), "yyyy-MM-dd");
            endDay = Integer.parseInt(TimeUtils.formatDate(effectiveDate, "yyyyMMdd"));
        }

        List<ResourceDetailVo> resultList = queryScheduleResourceList(resourceListParams.getGroupId(),resourceListParams.getFrameId(),resourceListParams.getDepartmentId(), startDay, endDay, pageable);

        Long sumTotal;

        Integer listSize = resultList.size();

        //数据库实体对象转VO对象
        if (!resultList.isEmpty()) {
            sumTotal = listSize.longValue();
        } else {
            sumTotal = Long.getLong("0");
        }
        return PageUtils.formatData(resultList, pageable, sumTotal);
    }

    private Predicate createConfigurationRecordQueryCondition(Root<AdvertScheduleModel> root, CriteriaQuery<?> query, CriteriaBuilder cb, ScheduleListParams paramBody) {
        List<Predicate> list = new ArrayList<>();
        List<Order> orders = new ArrayList();
        list.add(cb.equal(root.get("isDeleted").as(Integer.class), AdvertConstants.DELETE_FLAG_FALSE));

        if (paramBody.getLocId() != null) {
            list.add(cb.equal(root.get("locId").as(Long.class), paramBody.getLocId()));
        }

        if (paramBody.getGroupId() != null) {
            list.add(cb.equal(root.get("groupId").as(Long.class), paramBody.getGroupId()));
        }

        if (paramBody.getDepartmentId() != null) {
            list.add(cb.equal(root.get("departmentId").as(Long.class), paramBody.getDepartmentId()));
        }

        if (StringUtils.isNotBlank(paramBody.getStartDate()) && StringUtils.isNotBlank(paramBody.getEndDate())) {
            Date startDate = TimeUtils.parseDate(paramBody.getStartDate(), "yyyy-MM-dd");
            Date endDate = TimeUtils.parseDate(paramBody.getEndDate(), "yyyy-MM-dd");
            Integer startDay = Integer.parseInt(TimeUtils.formatDate(startDate,"yyyyMMdd"));
            Integer endDay = Integer.parseInt(TimeUtils.formatDate(endDate,"yyyyMMdd"));
            list.add(cb.between(root.get("effectiveDay").as(Integer.class), startDay, endDay));
        }

        Predicate[] p = new Predicate[list.size()];
        Predicate res = cb.and(list.toArray(p));
        query.where(res);
        orders.add(cb.asc(root.get("groupId").as(Long.class)));
        orders.add(cb.asc(root.get("frameId").as(Integer.class)));
        orders.add(cb.asc(root.get("effectiveDay").as(Integer.class)));
        query.orderBy(orders);
        return query.getRestriction();
    }

    private String getCellStringValue(Cell cell, String numFormat) {
        if (cell == null) {
            return "";
        }
        if (CellType.NUMERIC == cell.getCellType()) {
            if (StringUtils.isNotBlank(numFormat)) {
                NumberFormat nf = new DecimalFormat(numFormat);
                return nf.format(cell.getNumericCellValue());
            } else {
                return String.valueOf(cell.getNumericCellValue());
            }
        } else {
            return cell.getStringCellValue();
        }
    }

    private Integer parseFrameValue(Cell cell) {
        if (cell == null) {
            return 0;
        }
        if (CellType.NUMERIC == cell.getCellType()) {
            return BigDecimal.valueOf(cell.getNumericCellValue()).intValue();
        } else if (CellType.STRING == cell.getCellType()) {
            String cellValue = cell.getStringCellValue();
            String frameValue = cellValue.substring(1, cellValue.length() - 1);
            if (StringUtils.isNumeric(frameValue)) {
                return Integer.parseInt(frameValue);
            }
        }
        return 0;
    }

    private List<ResourceDetailVo> queryScheduleResourceList(Long groupId, Integer frameId, Long departmentId, Integer startDay, Integer endDay, Pageable pageable) {
        SqlBuilder sb = new SqlBuilder("SELECT s.loc_id,s.group_id,g.title as groupName,s.frame_id,l.title as locName,s.effective_date,r.id,r.ad_link,r.ad_pic,r.ad_title,r.audit_status,r.creator,r.status,r.create_time,r.department_id,d.department_name " +
                "FROM advert_location_schedule s " +
                "LEFT JOIN advert_location_resource r ON s.loc_id=r.loc_id AND s.effective_day = r.effective_day AND r.is_deleted = 0 " +
                "LEFT JOIN advert_department d ON d.id  = r.department_id " +
                "JOIN advert_location_configure l ON l.id = s.loc_id " +
                "JOIN advert_location_group g ON g.id  = l.gid " +
                "WHERE 1=1 ");

        if (groupId != null && groupId > 0) {
            sb.appendStatement(" AND s.group_id = ").appendParamValue(groupId);
        }

        if (frameId != null && frameId > 0) {
            sb.appendStatement(" AND s.frame_id = ").appendParamValue(frameId);
        }

        if (departmentId != null && departmentId > 0) {
            sb.appendStatement(" AND s.department_id = ").appendParamValue(departmentId);
        }

        if (startDay != null) {
            sb.appendStatement(" AND s.effective_day >= ").appendParamValue(startDay);
        }

        if (endDay != null) {
            sb.appendStatement(" AND s.effective_day <= ").appendParamValue(endDay);
        }

        String sql = sb.toStatement();
        sql += " ORDER BY s.group_id,s.frame_id,r.seq_no DESC LIMIT " + pageable.getOffset() + ", " + pageable.getPageSize();

        Query rowQ = entityManager.createNativeQuery(sql);
        Object[] params = sb.toParams();
        int p = 1;
        for (Object param : params) {
            rowQ.setParameter(p++, param);
        }
        List<Object[]> rowList = rowQ.getResultList();

        List<ResourceDetailVo> resultList = new ArrayList<>();

        rowList.forEach(item ->{
            Object[] colArr = item;
            ResourceDetailVo  detailVo = new ResourceDetailVo();
            detailVo.setLocId(Long.valueOf(colArr[0].toString()));
            detailVo.setGroupId(Long.valueOf(colArr[1].toString()));
            detailVo.setGroupName(colArr[2].toString());
            byte iFrameId = (byte)colArr[3];
            detailVo.setFrameId((int) iFrameId);
            detailVo.setLocName(colArr[4].toString());
            Timestamp effectiveDate= (Timestamp) colArr[5];
            if(effectiveDate!=null) {
                detailVo.setEffectiveDate(TimeUtils.getLongDate(effectiveDate)); //排期时间
            }

            if(colArr[6] != null){
                detailVo.setId(Long.valueOf(colArr[6].toString()));
                detailVo.setAdUrl(colArr[7] == null ? "" : colArr[7].toString());
                detailVo.setAdPic(colArr[8] == null ? "" : colArr[8].toString());
                detailVo.setAdTitle(colArr[9] == null ? "" : colArr[9].toString());

                byte auditStatus = (byte)colArr[10];
                detailVo.setAuditStatus((int) auditStatus);
                detailVo.setCreator(colArr[11].toString());

                byte status = (byte)colArr[12];
                detailVo.setStatus((int) status);
                Timestamp createTime = (Timestamp) colArr[13];
                if (createTime != null) {
                    detailVo.setCreateTime(TimeUtils.getLongDateStr(createTime));
                }
                if(colArr[14] != null){
                    detailVo.setDepartmentId(Long.valueOf(colArr[14].toString()));
                }
                detailVo.setDepartmentName(colArr[15].toString());
            }
            resultList.add(detailVo);
        });

        return resultList;
    }

}
