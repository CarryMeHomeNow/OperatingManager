package com.tcl.uf.management.schedule.oasync;

import com.tcl.uf.management.schedule.oasync.param.*;
import org.springframework.stereotype.Component;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

@Component
public class SalesDepartInfoService {

	public static void main(String[] args) throws ServiceException, RemoteException {
		SalesDepartInfoService se= new SalesDepartInfoService();
		se.salesSyncNew("20180601");
		
	}
	
	public void salesSync() throws  RemoteException {
//		BC_HR_SalesDepartInfo_SI_SalesDepartInfo_OBLocator locator = new BC_HR_SalesDepartInfo_SI_SalesDepartInfo_OBLocator();
//		javax.xml.namespace.QName qName = new javax.xml.namespace.QName("HTTP_Port");
//		SI_SalesDepartInfo_OBBindingStub stub = (SI_SalesDepartInfo_OBBindingStub)locator.getPort(qName,SI_SalesDepartInfo_OBBindingStub.class);
//		
//		stub.setTimeout(1000 * 600);
//		stub.setUsername(SyncConfig.SYNC_USERNAME);
//		stub.setPassword(SyncConfig.SYNC_USERPSW);
//		ZHR_OA_03Response res = stub.SI_SalesDepartInfo_OB(new ZHR_OA_03("00751956"));
//		
//		System.out.println(res.toString());
	}
	
	//增量同步
	public ZHR_OA_PERSON_OUTResponse salesSyncNew(String dateStr) throws ServiceException, RemoteException {
		BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator locator = new BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator();
		javax.xml.namespace.QName qName = new javax.xml.namespace.QName("HTTP_Port");
		SI_HR_PERSON_OUT_OBBindingStub stub = (SI_HR_PERSON_OUT_OBBindingStub)locator.getPort(qName,SI_HR_PERSON_OUT_OBBindingStub.class);
		
		stub.setTimeout(1000 * 600);
		stub.setUsername(SyncConfig.SYNC_USERNAME);
		stub.setPassword(SyncConfig.SYNC_USERPSW);
		
		ZHR_OA_PERSON_OUT param= new ZHR_OA_PERSON_OUT();
		param.setP_DATUM(new Date(dateStr));
		param.setP_PWD(SyncConfig.SYNC_USERPSW);
//		param.setP_ALL("X");
		ZHR_OA_PERSON_OUTResponse res = stub.SI_HR_PERSON_OUT_OB(param);
		
		return res;
	}
	
	//全量同步
	public ZHR_OA_PERSON_OUTResponse salesSyncAll() throws ServiceException, RemoteException {
		BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator locator = new BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator();
		javax.xml.namespace.QName qName = new javax.xml.namespace.QName("HTTP_Port");
		SI_HR_PERSON_OUT_OBBindingStub stub = (SI_HR_PERSON_OUT_OBBindingStub)locator.getPort(qName,SI_HR_PERSON_OUT_OBBindingStub.class);
		
		stub.setTimeout(1000 * 600);
		stub.setUsername(SyncConfig.SYNC_USERNAME);
		stub.setPassword(SyncConfig.SYNC_USERPSW);
		
		ZHR_OA_PERSON_OUT param= new ZHR_OA_PERSON_OUT();
		param.setP_PWD(SyncConfig.SYNC_USERPSW);
		param.setP_ALL("X");
		ZHR_OA_PERSON_OUTResponse res = stub.SI_HR_PERSON_OUT_OB(param);
		
		return res;
	}
	
	//根据单个工号同步
	public ZHR_OA_PERSON_OUTResponse salesSyncByPersonCode(String personCode) throws ServiceException, RemoteException {
		BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator locator = new BC_PERSON_OUT_Sender_SI_HR_PERSON_OUT_OBLocator();
		javax.xml.namespace.QName qName = new javax.xml.namespace.QName("HTTP_Port");
		SI_HR_PERSON_OUT_OBBindingStub stub = (SI_HR_PERSON_OUT_OBBindingStub)locator.getPort(qName,SI_HR_PERSON_OUT_OBBindingStub.class);
		
		stub.setTimeout(1000 * 600);
		stub.setUsername(SyncConfig.SYNC_USERNAME);
		stub.setPassword(SyncConfig.SYNC_USERPSW);
		
		ZHR_OA_PERSON_OUT param= new ZHR_OA_PERSON_OUT();
		param.setP_PWD(SyncConfig.SYNC_USERPSW);
//		param.setP_ALL("X");
		param.setP_PERNR(personCode);
		ZHR_OA_PERSON_OUTResponse res = stub.SI_HR_PERSON_OUT_OB(param);
		
		return res;
	}
	
	
	
}
