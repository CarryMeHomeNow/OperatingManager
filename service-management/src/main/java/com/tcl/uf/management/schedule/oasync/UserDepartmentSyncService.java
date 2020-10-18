package com.tcl.uf.management.schedule.oasync;

import com.alibaba.fastjson.JSON;
import com.tcl.commondb.management.model.OaDepartment;
import com.tcl.commondb.management.model.OaOnlineUserDepartmentManager;
import com.tcl.commondb.management.model.OaSystemUser;
import com.tcl.commondb.management.repository.OaDepartmentRepository;
import com.tcl.commondb.management.repository.OaOnlineUserDepartmentManagerRepository;
import com.tcl.commondb.management.repository.OaUserRepository;
import com.tcl.uf.common.base.manage.RedisManager;
import com.tcl.uf.management.schedule.oasync.param.DT_OrgInfoSync_ResponseZORG_INFOItem;
import com.tcl.uf.management.schedule.oasync.param.LoadOrgInfo;
import com.tcl.uf.management.schedule.oasync.param.ZHR_OA_PERSON_OUTResponse;
import com.tcl.uf.management.schedule.oasync.param.ZTHR_PERSON_OUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class UserDepartmentSyncService {

    private static final Logger log = LoggerFactory.getLogger(UserDepartmentSyncService.class);
    final static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private static final String STATUS_ON = "3";
    private static final String STATUS_ON_UM = "1";
    private static final String STATUS_OFF = "0";
    private static final String UM_APPID_TCL ="TCL";
    
    public static String ROOT = "90004909";

    @Autowired
    private OaUserRepository oaUserRepository;

    @Autowired
    private OaDepartmentRepository umDepartmentRepository;

    @Autowired
    private SalesDepartInfoService salesDepartInfoService;

    @Autowired
    private OaOnlineUserDepartmentManagerRepository onlineUserDepartmentManagerRepository;

    public void userSyncNew() {
    }

    /**
     * dateStr 起始同步日期   "20180601"
     */
    public void userSync(String dateStr) {

        log.info(sf.format(new Date()) + ": -------------- 开始保存用户 --------------");

        try {
            long begin = System.currentTimeMillis();
            ZHR_OA_PERSON_OUTResponse res = salesDepartInfoService.salesSyncNew(dateStr);
            long end = System.currentTimeMillis();
            log.info("[get emps time] == " + (end - begin) / 1000 + " S");

            if ("S".equals(res.getRET_FLAG()) && res.getIT_PERSON() != null && res.getIT_PERSON().length > 0) {
                long totalBegin = System.currentTimeMillis();
                int i = 0;
                for (ZTHR_PERSON_OUT emp : res.getIT_PERSON()) {

//                    if ("李晓玲".equals(emp.getUSRID())) {
//                        System.out.println(JSON.toJSONString(emp.getUSRID()));
//                    }
                    
                    long bTouser = System.currentTimeMillis();
                    OaSystemUser user = oaUserRepository.findFirstByPersonCodeEquals(emp.getPERNR());
                    if (user == null&&"".equals(emp.getUSRID()))
                    	continue;
                    if (user == null) {
                        user = new OaSystemUser();
                        user.setUserId(emp.getUSRID());
                        user.setDepartmentId(emp.getORGEH());
                        user.setCreateTime(new Timestamp(new Date().getTime()));
                        user.setUpdateTime(new Timestamp(new Date().getTime()));
                    } else if (StringUtils.isEmpty(user.getDepartmentId())) {
                        user.setDepartmentId(emp.getORGEH());
                        user.setUpdateTime(new Timestamp(new Date().getTime()));
                    } else {
                        if (user.getDepartmentId().equals(user.getOriginalDepartmentId()) || StringUtils.isEmpty(user.getOriginalDepartmentId())) {
                            user.setDepartmentId(emp.getORGEH());
                        }
                        user.setUpdateTime(new Timestamp(new Date().getTime()));
                    }
                    if(!StringUtils.isEmpty(emp.getUSRID())){
                    	user.setUserId(emp.getUSRID());                    	
                    }
                    user.setOriginalDepartmentId(emp.getORGEH());
                    user.setDepartmentName(emp.getORGEH_TEXT());
                    user.setEmail(emp.getEMAIL());
                    user.setMobile(emp.getTEL_NO());
                    user.setPosition(emp.getPLANS_TEXT());
                    user.setSex(emp.getEMSEX());
                    user.setUsername(emp.getNACHN());
                    user.setPersonCode(emp.getPERNR());
                    user.setUmAppid(UM_APPID_TCL);
                    String userStatus = emp.getSTAT2_TXT();
                    if(STATUS_OFF.equals(userStatus)){
                    	user.setUserStatus(STATUS_OFF);
                    }else if(STATUS_ON.equals(userStatus)){
                    	user.setUserStatus(STATUS_ON_UM);
                    }

                    oaUserRepository.saveAndFlush(user);
                    long eTouser = System.currentTimeMillis();
//                    log.info("[save user time] " + emp.getNACHN() + " == " + (eTouser - bTouser) / 1000 + " S");
                    i++;

//					if("00712698".equals(emp.getPERNR())){
//						log.info(emp.getNACHN()+"::"+emp.getTEL_NO());
//					}
                }
                long totalEnd = System.currentTimeMillis();
                log.info("[save user total time] == " + (totalEnd - totalBegin) / 1000 + " S");
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("员工数据同步失败",e.fillInStackTrace());
        }
        log.info(sf.format(new Date()) + ": -------------- 保存用户结束 --------------");

    }

    
    /**
     * dateStr 起始同步日期   "20180601"
     */
    public void userIdSync(String dateStr) {

        log.info(sf.format(new Date()) + ": -------------- 开始更新用户userId --------------");

        try {
            long begin = System.currentTimeMillis();
            ZHR_OA_PERSON_OUTResponse res = salesDepartInfoService.salesSyncNew(dateStr);
            long end = System.currentTimeMillis();
            log.info("[get emps time] == " + (end - begin) / 1000 + " S");

            if ("S".equals(res.getRET_FLAG()) && res.getIT_PERSON() != null && res.getIT_PERSON().length > 0) {
                long totalBegin = System.currentTimeMillis();
                int i = 0;
                for (ZTHR_PERSON_OUT emp : res.getIT_PERSON()) {

//                    if ("李晓玲".equals(emp.getUSRID())) {
//                        System.out.println(JSON.toJSONString(emp.getUSRID()));
//                    }

                    OaSystemUser user = oaUserRepository.findFirstByPersonCodeEquals(emp.getPERNR());
                    
                    if(user!=null&&"".equals(user.getUserId())){
                        if(!StringUtils.isEmpty(emp.getUSRID())){
                        	user.setUserId(emp.getUSRID());                    	
                        }
                    	String userStatus = emp.getSTAT2_TXT();
                    	if(STATUS_OFF.equals(userStatus)){
                        	user.setUserStatus(STATUS_OFF);
                        }else if(STATUS_ON.equals(userStatus)){
                        	user.setUserStatus(STATUS_ON_UM);
                        }
                    	user.setUpdateTime(new Timestamp(new Date().getTime()));
                        oaUserRepository.saveAndFlush(user);
                    }
                    
                    long eTouser = System.currentTimeMillis();
                    i++;
                }
                long totalEnd = System.currentTimeMillis();
                log.info("[save user total time] == " + (totalEnd - totalBegin) / 1000 + " S");
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("员工数据同步失败",e.fillInStackTrace());
        }
        log.info(sf.format(new Date()) + ": -------------- 保存用户结束 --------------");

    }
    
    
    /**
     * 员工全量同步接口
     */
    public void userSyncAll() {

        log.info(sf.format(new Date()) + ": -------------- 开始同步全量用户 --------------");

        try {
            long begin = System.currentTimeMillis();
            ZHR_OA_PERSON_OUTResponse res = salesDepartInfoService.salesSyncAll();
            long end = System.currentTimeMillis();
            log.info("[get emps time] == " + (end - begin) / 1000 + " S");

            Map<String, ZTHR_PERSON_OUT> eMap = new HashMap<>();
            
            if ("S".equals(res.getRET_FLAG()) && res.getIT_PERSON() != null && res.getIT_PERSON().length > 0) {
                long totalBegin = System.currentTimeMillis();
                int i = 0;
                for (ZTHR_PERSON_OUT emp : res.getIT_PERSON()) {
                    long bTouser = System.currentTimeMillis();

                    if (emp==null) {
                            continue;
                    }
                    eMap.put(emp.getPERNR(), emp);
                    OaSystemUser user = oaUserRepository.findFirstByPersonCodeEquals(emp.getPERNR());
                    if (user == null&&"".equals(emp.getUSRID()))
                    	continue;
                    if (user == null) {
                        user = new OaSystemUser();
                        user.setUserId(emp.getUSRID());
                        user.setDepartmentId(emp.getORGEH());
                        user.setCreateTime(new Timestamp(new Date().getTime()));
                        user.setUpdateTime(new Timestamp(new Date().getTime()));
                    } else if (StringUtils.isEmpty(user.getDepartmentId())) {
                        user.setDepartmentId(emp.getORGEH());
                        user.setUpdateTime(new Timestamp(new Date().getTime()));
                    } else {
                        if (user.getDepartmentId().equals(user.getOriginalDepartmentId()) || StringUtils.isEmpty(user.getOriginalDepartmentId())) {
                            user.setDepartmentId(emp.getORGEH());
                        }
                        user.setUpdateTime(new Timestamp(new Date().getTime()));
                    }
                    
                    if(!StringUtils.isEmpty(emp.getUSRID())){
                    	user.setUserId(emp.getUSRID());                    	
                    }
                    user.setOriginalDepartmentId(emp.getORGEH());
                    user.setDepartmentName(emp.getORGEH_TEXT());
                    user.setEmail(emp.getEMAIL());
                    user.setMobile(emp.getTEL_NO());
                    user.setPosition(emp.getPLANS_TEXT());
                    user.setSex(emp.getEMSEX());
                    user.setUsername(emp.getNACHN());
                    user.setPersonCode(emp.getPERNR());
                    user.setUmAppid(UM_APPID_TCL);
                    String userStatus = emp.getSTAT2_TXT();
                    if(STATUS_OFF.equals(userStatus)){
                    	user.setUserStatus(STATUS_OFF);
                    }else if(STATUS_ON.equals(userStatus)){
                    	user.setUserStatus(STATUS_ON_UM);
                    }
                    oaUserRepository.saveAndFlush(user);
                    long eTouser = System.currentTimeMillis();
                    log.info("[save user time] " + emp.getNACHN() + " == " + (eTouser - bTouser) / 1000 + " S");
                    i++;
                }
                long totalEnd = System.currentTimeMillis();
                log.info("[save user total time] == " + (totalEnd - totalBegin) / 1000 + " S");
            }

            List<OaSystemUser> umUserList = oaUserRepository.findAll();
            
            // 全量同步数据里没有的人为离职状态，更新数据库状态为离职
            for(OaSystemUser user:umUserList){
            	if(user!=null&&eMap.get(user.getPersonCode())!=null){
            		continue;
            	}else{
            		user.setUserStatus(STATUS_OFF);
            		user.setUpdateTime(new Timestamp(new Date().getTime()));
                    oaUserRepository.saveAndFlush(user);
            	}
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(sf.format(new Date()) + ": -------------- 保存用户结束 --------------");
    }
    
    
    /**
     * 员工全量同步接口
     */
    public void userIdSyncAll() {

        log.info(sf.format(new Date()) + ": -------------- 开始同步全量用户 --------------");
        StringBuffer s =  new StringBuffer();
        try {
            long begin = System.currentTimeMillis();
            ZHR_OA_PERSON_OUTResponse res = salesDepartInfoService.salesSyncAll();
            long end = System.currentTimeMillis();
            log.info("[get emps time] == " + (end - begin) / 1000 + " S");

            if ("S".equals(res.getRET_FLAG()) && res.getIT_PERSON() != null && res.getIT_PERSON().length > 0) {
                long totalBegin = System.currentTimeMillis();
                for (ZTHR_PERSON_OUT emp : res.getIT_PERSON()) {

                    if (emp==null) {
                            continue;
                    }
                    OaSystemUser user = oaUserRepository.findFirstByPersonCodeEquals(emp.getPERNR());
                    
                    if(user!=null&&!emp.getUSRID().equals(user.getUserId())){
                    	s.append(",emp="+emp.getUSRID()+"--um="+user.getUserId());
                    	if(!StringUtils.isEmpty(emp.getUSRID())){
                    		user.setUserId(emp.getUSRID());                    		
                    	}
                    	String userStatus = emp.getSTAT2_TXT();
                    	if(STATUS_OFF.equals(userStatus)){
                    		user.setUserStatus(STATUS_OFF);
                    	}else if(STATUS_ON.equals(userStatus)){
                    		user.setUserStatus(STATUS_ON_UM);
                    	}
                    	user.setUpdateTime(new Timestamp(new Date().getTime()));
                        oaUserRepository.saveAndFlush(user);
                    }
                }
                long totalEnd = System.currentTimeMillis();
                
                log.info("[save user total time] == " + (totalEnd - totalBegin) / 1000 + " S");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("yichanguserid"+s.toString());
        log.info(sf.format(new Date()) + ": -------------- 保存用户结束 --------------");
    }
    
    
    
    /**
     * 旧代码
     */
        public void departmentSync() {
            log.info(sf.format(new Date()) + ": -------------- 开始保存机构 --------------");
            LoadOrgInfo info = new LoadOrgInfo();
            DT_OrgInfoSync_ResponseZORG_INFOItem[] orgInfo = null;
            try {
                orgInfo = info.getOrgInfo();
                Map<String, String> orgMap = new HashMap<>();
                for (DT_OrgInfoSync_ResponseZORG_INFOItem org : orgInfo) {
                    orgMap.put(org.getOBJID(), org.getUP_OBJID());
                }

                for (DT_OrgInfoSync_ResponseZORG_INFOItem org : orgInfo) {
                    String orgId = org.getOBJID();
                    String orgName = org.getSTEXT();
                    String pOrgId = org.getUP_OBJID();
                    OaDepartment umDepartment = umDepartmentRepository.findFirstByDepartmentIdEquals(orgId);
                    if (umDepartment == null) {
                        umDepartment = new OaDepartment();
                        umDepartment.setDepartmentId(orgId);
                        umDepartment.setCreateTime(new Timestamp(new Date().getTime()));
                        umDepartment.setUpdateTime(new Timestamp(new Date().getTime()));
                    } else {
                        umDepartment.setUpdateTime(new Timestamp(new Date().getTime()));
                    }

                    StringBuilder sb = new StringBuilder();
                    // 非根节点下部门搜索其父节点数据
                    while (!ROOT.equals(orgId)) {
                        orgId = orgMap.get(orgId);
                        sb.append("," + orgId);
                    }

                    umDepartment.setParentIdList(sb.toString());
                    umDepartment.setName(orgName);
                    umDepartment.setParentId(pOrgId);
                    umDepartment.setUmAppid("TCL");
                    umDepartmentRepository.saveAndFlush(umDepartment);

                    if("00086768".equals(orgId)){
                    	System.out.println(JSON.toJSON(org));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info(sf.format(new Date()) + ": -------------- 保存机构结束 --------------");

        }


        public void countDepartmentUserNum() {
            // ROOT_DepartmentId
            long allNumber = oaUserRepository.count();
            RedisManager.set("ONLINE_DEPARTMENT_USER_NUM_ID_" + ROOT, String.valueOf(allNumber), 24 * 60 * 60);


            List<OaOnlineUserDepartmentManager> departmentManagerList = onlineUserDepartmentManagerRepository.findAll();
            for (OaOnlineUserDepartmentManager item : departmentManagerList) {

                List<String> tempDepartmentIds = new ArrayList<>();
                List<OaDepartment> contains = umDepartmentRepository.findAllByDepartmentIdOrParentIdListContainsAndUmAppid(item.getDepartmentId(), item.getDepartmentId(), "TCL");
                for (OaDepartment temp : contains) {
                    tempDepartmentIds.add(temp.getDepartmentId());
                }
                tempDepartmentIds.add(item.getDepartmentId());
                long l = oaUserRepository.countByDepartmentIdIn(tempDepartmentIds);
                RedisManager.set("ONLINE_DEPARTMENT_USER_NUM_ID_" + ROOT, String.valueOf(l), 24 * 60 * 60);
            }

        }
}
