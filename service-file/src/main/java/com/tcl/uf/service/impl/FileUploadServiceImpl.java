package com.tcl.uf.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.tcl.uf.common.base.util.ExceptionStackTraceUtil;
import com.tcl.uf.configuration.QiniuComponentConfiguration;
import com.tcl.uf.configuration.QiniuConfiguration;
import com.tcl.uf.dto.MyPutRet;
import com.tcl.uf.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author youyun.xu
 * @ClassName: FileUploadServiceImpl
 * @Description: 文件管理(七牛云)service
 * @date 2020/8/22 15:00
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger log = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Autowired
    private QiniuConfiguration qiniuConfiguration;

    @Autowired
    private Auth auth;

    @Autowired
    private UploadManager uploadManager;


    @Override
    public String uploadBytes(byte[] bytes) {
        return uploadBytes(bytes, null);
    }

    @Override
    public String uploadBytes(byte[] bytes, String name) {
        try {
            String upToken = auth.uploadToken(qiniuConfiguration.getPublicBucket());
            Response response = uploadManager.put(bytes, name, upToken);
            if (!response.isOK()) {
                log.error("Qiniu Upload File Error: [{}], JSON.toJSONString(response)");
                return null;
            }
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("Qiniu Upload File Success: [{}]", response.bodyString());
            return qiniuConfiguration.getPublicHostname() + "/" + putRet.key;
        } catch (QiniuException e) {
            log.error("upload file to qiniu error: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public String uploadBytesToPrivate(byte[] bytes) {
        return uploadBytes(bytes, null);
    }

    @Override
    public String uploadBytesToPrivate(byte[] bytes, String name) {
        if (bytes == null){
            return null;
        }
        try {
            String uptoken = auth.uploadToken(qiniuConfiguration.getPrivateBucket(), null, qiniuConfiguration.getPrivateUptokenExpire(), QiniuComponentConfiguration.putPrivatePolicy);
            Response response = uploadManager.put(bytes, name, uptoken);
            if (!response.isOK()) {
                log.error("Qiniu Upload File Error: [{}], JSON.toJSONString(response)");
                return null;
            }
            MyPutRet putRet = new Gson().fromJson(response.bodyString(), MyPutRet.class);
            if (putRet != null && !StringUtils.isEmpty(putRet.key)){
                return putRet.key;
            }
            log.info("Qiniu Upload File Success: [{}]", response.bodyString());
            return null;
        } catch (QiniuException e) {
            log.info("upload file to qiniu  error: {}" + ExceptionStackTraceUtil.getStackTrace(e));
            return null;
        }
    }

    @Override
    public String getUploadToken() {
        String uptoken = auth.uploadToken(qiniuConfiguration.getPrivateBucket(), null, qiniuConfiguration.getPrivateUptokenExpire(), QiniuComponentConfiguration.putPrivatePolicy);
        return uptoken;
    }

    @Override
    public String authorizeFile(String url,long expires) {
        String newUrl  = auth.privateDownloadUrl(url, expires);
        return newUrl;
    }
}
