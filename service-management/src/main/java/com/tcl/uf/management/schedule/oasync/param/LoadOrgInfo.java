package com.tcl.uf.management.schedule.oasync.param;

import com.tcl.uf.management.schedule.oasync.SI_OrgInfoSync_OBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.log4j.Logger;

public class LoadOrgInfo {

	Logger log = LoggerFactory.getLogger(LoadOrgInfo.class);

	public DT_OrgInfoSync_ResponseZORG_INFOItem[] getOrgInfo() throws Exception {
		DT_OrgInfoSync_ResponseZORG_INFOItem[] zorgInfos = null;
		try {
			long start = System.currentTimeMillis();
			log.info("************开始读取组织架构************");
			SI_OrgInfoSync_OBService service = new SI_OrgInfoSync_OBServiceLocator();
			SI_OrgInfoSync_OBBindingStub stub = (SI_OrgInfoSync_OBBindingStub) service.getSI_OrgInfoSync_OBPort();
			stub.setTimeout(1000 * 60);
			stub.setUsername(SyncConfig.SYNC_SAP_USERNAME);
			stub.setPassword(SyncConfig.SYNC_SAP_USERPSW);
			DT_OrgInfoSync_Request dtReq = new DT_OrgInfoSync_Request();
			dtReq.setZ_PWD("");
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			String date = sf.format(new Date());
			dtReq.setZ_KEYDATE(date);
			DT_OrgInfoSync_Response dtResp = stub.SI_OrgInfoSync_OB(dtReq);
			zorgInfos = dtResp.getZORG_INFO();
			log.info("************组织架构部门总数" + zorgInfos.length + "************");
			long end = System.currentTimeMillis();
			log.info("************用时：" + (end - start) / 1000 + "秒************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zorgInfos;
	}
	
	/**
	 * 保存机构信息
	 * @version 1.0
	 * @author hls
	 * @date 2018年2月1日 上午9:16:16
	 * @param orgs
	 * @return
	 * @throws SQLException
	 */
	public int saveOrgInfo(DT_OrgInfoSync_ResponseZORG_INFOItem[] orgs) throws SQLException{
		List<String[]> list = new ArrayList<String[]>();
		List<String> ids = new ArrayList<String>();
		for (int i = 0; i < orgs.length; i++) {
			String orgId = orgs[i].getOBJID();
			String orgName = orgs[i].getSTEXT();
			String pOrgId = orgs[i].getUP_OBJID();
			String[] str = {orgId,orgName,pOrgId};
			list.add(str);
			ids.add(orgId);
		}
		/**
		 * 这里需要查询出有编码的机构，如果存在就更新，不存在就添加
		 * 不能删除数据，因为机构映射是手工的，恢复很慢
		 */
		
		
//		int dCount = C3P0Util.deleteObjectsById(ids, deleteSql());
//		log.info("************删除：" + dCount + "条数据************");
//		int count = C3P0Util.saveObjects(list, insertSql());
//		log.info("************插入：" + count + "条数据************");
//		System.out.println("************插入：" + count + "条数据************");
//		return count;
		return 1;
	}
	
//	public void updateDept(List<String[]> list) throws Exception {
//		List<Dept> depList = (List<Dept>) C3P0Util.queryObjcetList(Dept.class,queryDeptList());
//		if(null != depList) {
//			/**对比新旧集合中的数据是否有已经存在的**/
//			for(String[] ary : list) {
//				for (Dept dept : depList) {
//					if(ary[0].equals(dept.getDepartmentNumber())) {
//						dept.setDisplayName(ary[1]);
//						dept.setParentId(ary[2]);
//						
//					}
//				}
//				
//			}
//			
//		}else {
//			/**添加数据**/
//			C3P0Util.saveObjects(list, insertSql());
//		}
//	}
	
	public String queryDeptList(){
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from T_DM_DEPT ");
		return sb.toString();
	}
	
	
	public String insertSql(){
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO T_DM_DEPT(DEPARTMENT_NUMBER, ");
		sb.append("  DISPLAY_NAME, PARENT_ID  ) VALUES( ?,?,? ) ");
		return sb.toString();
	}
	
	public String deleteSql(){
		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE T_DM_DEPT T WHERE T.DEPARTMENT_NUMBER = ? ");
		return sb.toString();
	}
}
