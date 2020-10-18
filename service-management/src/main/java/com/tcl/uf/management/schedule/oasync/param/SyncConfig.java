package com.tcl.uf.management.schedule.oasync.param;

public class SyncConfig {
	
	/**
	JAVA代码的配置文件
    SYNC.SAP.IP=10.118.1.233
    SYNC.SAP.PORT=50000
    SYNC.SAP.USERNAME=SAPA
    SYNC.SAP.USERPSW=tcl@321
	*/
	
	public static final String SYNC_SAP_IP = "10.118.1.233";
	public static final String SYNC_SAP_PORT = "50000";
	public static final String SYNC_SAP_USERNAME = "SAPA";
	public static final String SYNC_SAP_USERPSW = "tcl@321";
	public static final String SYNC_USERNAME = "PI_RFC_USER";
	public static final String SYNC_USERPSW = "tclking2010";
	public static final String SYNC_SOURCE_ID = "e21c13e0-b57a-49f1-985b-01af30232ksr"; //数据源, 系统分配
	public static final String SYNC_SYS_KEY = "tcl_test"; //系统Key, 系统分配
	public static final String SYNC_HOST = "https://tmtbi.tcl.com:4433";
	public static final String SYNC_HOST_TEST = "http://10.126.124.31";
	
	
//	public static void getConfig(){
//		Properties prop = System.getProperties();
//		String property = prop.getProperty("user.dir")+File.separator+"src\\log4j.properties";
//		String c3p0 = prop.getProperty("user.dir")+File.separator+"src\\c3p0.properties";
//		PropertyConfigurator.configure(property);
//	}

}
