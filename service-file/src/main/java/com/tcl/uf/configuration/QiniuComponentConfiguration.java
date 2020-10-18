package com.tcl.uf.configuration;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class QiniuComponentConfiguration {

    @Autowired
    private QiniuConfiguration qiniuConfiguration;

    public static StringMap putPrivatePolicy = new StringMap();
    
    @Bean
    public Auth auth() {
    	Auth auth = Auth.create(qiniuConfiguration.getAccesskey(), qiniuConfiguration.getSecretkey());
        putPrivatePolicy.put("returnBody", "{\"key\":\"" + qiniuConfiguration.getPrivateHostname() + "/$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        return auth;
    }
    
    @Bean
    public UploadManager uploadManager() {
    	 Configuration cfg = new Configuration(Zone.zone2());
    	 UploadManager uploadManager = new UploadManager(cfg);
    	 return uploadManager;
    }
}
