package com.tcl.uf.service;

import com.qiniu.common.QiniuException;

public interface FileUploadService {

    String uploadBytes(byte[] bytes, String name);

    String uploadBytes(byte[] bytes);

    String uploadBytesToPrivate(byte[] bytes);

    String uploadBytesToPrivate(byte[] bytes, String name);

    String getUploadToken();

    String authorizeFile(String url, long expires);
}
