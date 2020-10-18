package com.tcl.uf.controller;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.common.base.util.ExceptionStackTraceUtil;
import com.tcl.uf.common.base.util.FtpUtils;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.tcl.uf.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author youyun.xu
 * @ClassName: FileController
 * @Description: 文件管理控制器(七牛云)
 * @date 2020/07/22 上午10:53
 */
@Api(value = "会员等级与成长值")
@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileUploadService fileUploadService;

    @ApiOperation(value = "公共空间上传文件服务", notes = "公共空间上传文件服务", httpMethod = "POST")
    @RequestMapping(value = "/public/upload", method = RequestMethod.POST)
    public ResponseData<String> upload(@RequestParam("file") MultipartFile file) {
        byte[] bytes = new byte[0];
        String url = null;
        try {
            bytes = file.getBytes();
            url = fileUploadService.uploadBytes(bytes);
        } catch (IOException e) {
            log.error("file upload get MultipartFile error: {}", e.getMessage());
        }
        return new ResponseData<String>(url);
    }

    @ApiOperation(value = "公共空间上传文件服务(取上传原始文件名)", notes = "公共空间上传文件服务(可传自定文件名)", httpMethod = "POST")
    @RequestMapping(value = "/public/uploadwithname", method = RequestMethod.POST)
    public ResponseData<String> uploadwithname(@RequestParam("file") MultipartFile file) {
        byte[] bytes = new byte[0];
        String url = null;
        try {
            bytes = file.getBytes();
            url = fileUploadService.uploadBytes(bytes, file.getOriginalFilename());
        } catch (IOException e) {
            log.error("file upload get MultipartFile error: {}", e.getMessage());
        }
        return new ResponseData<String>(url);
    }

    @ApiOperation(value = "私有空间上传文件服务", notes = "私有空间上传文件服务", httpMethod = "POST")
    @RequestMapping(value = "/private/upload", method = RequestMethod.POST)
    public ResponseData<String> uploadToPrivate(@RequestParam("file") MultipartFile file) {
        byte[] bytes = new byte[0];
        String url = null;
        try {
			bytes = file.getBytes();
            url = fileUploadService.uploadBytesToPrivate(file.getBytes());
        } catch (IOException e) {
            log.error("file upload get MultipartFile error: {}", e.getMessage());
        }
        return new ResponseData<String>(url);
    }

    @ApiOperation(value = "私有空间上传文件服务(取上传原始文件名)", notes = "私有空间上传文件服务(取上传原始文件名)", httpMethod = "POST")
    @RequestMapping(value = "/private/uploadwithname", method = RequestMethod.POST)
    public ResponseData<String> uploadwithnamePrivate(@RequestParam("file") MultipartFile file) {
        byte[] bytes = new byte[0];
        String url = null;
        try {
            bytes = file.getBytes();
            url = fileUploadService.uploadBytesToPrivate(bytes,file.getOriginalFilename());
        } catch (IOException e) {
            log.error("file upload get MultipartFile error: {}", e.getMessage());
        }
        return new ResponseData<String>(url);
    }

    @ApiOperation(value = "拷贝指定FTP空间上传文件至七牛云私有空间", notes = "拷贝指定FTP空间上传文件至七牛云私有空间", httpMethod = "GET")
    @RequestMapping(value = "/ftp/private/upload", method = RequestMethod.GET)
    public ResponseData<String> uploadToPrivateFromFtp(@RequestParam("ftpUrl")String ftpUrl,
                                                       @RequestParam("userName")String userName,
                                                       @RequestParam("port")int port,
                                                       @RequestParam("password")String password,
                                                       @RequestParam("directory")String directory,
                                                       @RequestParam("downloadName")String downloadName) {
        byte[] bytes = null;
        InputStream ins = null;
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        try {
            ins = FtpUtils.download(ftpUrl,userName,port,password,directory,downloadName);
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = ins.read(buff, 0, 1024)) > 0){
                swapStream.write(buff, 0, rc);
            }
            bytes = swapStream.toByteArray();
        } catch (IOException e) {
            log.info("ftp file upload get bytes error: {}" + ExceptionStackTraceUtil.getStackTrace(e));
        }finally {
            if (ins != null){
                try {
                    ins.close();
                } catch (IOException e) {
                    log.info("ftp file upload close error: {}" + ExceptionStackTraceUtil.getStackTrace(e));
                }
            }
        }
        return new ResponseData<String>(fileUploadService.uploadBytesToPrivate(bytes));
    }

    @ApiOperation(value = "获取客户端上传私有空间的token", notes = "获取客户端上传私有空间的token", httpMethod = "GET")
    @RequestMapping(value = "/private/uptoken", method = RequestMethod.GET)
    public ResponseData<String> getPrivateUpToken() {
        String token = null;
        try {
            token = fileUploadService.getUploadToken();
        } catch (Exception e) {
            log.error("get upload token: {}", e.getMessage());
        }
        return new ResponseData<String>(token);
    }

    @ApiOperation(value = "授权访问私有空间文件(expires单位秒,不传默认600秒后过期)", notes = "授权访问私有空间文件(expires不传默认10分钟过期)", httpMethod = "GET")
    @RequestMapping(value = "/private/authorize/url", method = RequestMethod.GET)
    public ResponseData<String> privateAuthorizeUrl(@RequestParam("url") String url,@RequestParam(value = "expires", required = false) Long expires) {
        String newUrl = null;
        try {
            if(expires == null){
                expires = 600l;
            }
            newUrl = fileUploadService.authorizeFile(url,expires);
        } catch (Exception e) {
            log.error("get upload token: {}", e.getMessage());
        }
        return new ResponseData<String>(newUrl);
    }

    @ApiOperation(value = "根据参数生成指定大小的二维码并且上传至七牛云服务器", notes = "二维码尺寸, 单位像素", httpMethod = "GET")
    @RequestMapping(value = "/qr", method = RequestMethod.GET)
    public ResponseData<String> qrCode(@RequestParam("url") String url, @RequestParam("size") Integer size) {
        String newUrl = null;
        try {
            ByteArrayOutputStream stream = QRCode.from(url).withSize(size, size).to(ImageType.JPG).stream();
            newUrl = fileUploadService.uploadBytes(stream.toByteArray());
        } catch (Exception e) {
            log.error("get upload token: {}", e.getMessage());
        }
        return new ResponseData<String>(newUrl);
    }

    @ApiOperation(value = "根据参数生成指定大小的二维码并且上传至七牛云服务器", notes = "二维码尺寸, 单位像素", httpMethod = "POST")
    @RequestMapping(value = "/qr_name", method = RequestMethod.GET)
    public ResponseData<String> qrCode(@RequestParam("url") String url, @RequestParam("name") String name, @RequestParam("size") Integer size) {
        String newUrl = null;
        try {
            ByteArrayOutputStream stream = QRCode.from(url).withSize(size, size).to(ImageType.JPG).stream();
            newUrl = fileUploadService.uploadBytes(stream.toByteArray(),name);
        } catch (Exception e) {
            log.error("get upload token: {}", e.getMessage());
        }
        return new ResponseData<String>(newUrl);
    }

    @ApiOperation(value = "私有空间上传文件服务(字符型的字节数组)", notes = "私有空间上传文件服务(字符型的字节数组)", httpMethod = "POST")
    @RequestMapping(value = "/private/byte", method = RequestMethod.POST)
    public ResponseData<String> uploadToPrivateByByteInfo(@RequestBody String file) {
        if(StringUtils.isEmpty(file)) {
            return null;
        }
        byte[] bytes = file.getBytes();
        return new ResponseData<String>(fileUploadService.uploadBytesToPrivate(bytes));
    }
}
