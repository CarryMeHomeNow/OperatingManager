package com.tcl.uf.management.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tcl.commondb.management.model.*;
import com.tcl.commondb.management.repository.*;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.management.config.SsoConfiguration;
import com.tcl.uf.management.param.user.OaUserListResp;
import com.tcl.uf.management.param.user.UserAuthReq;
import com.tcl.uf.management.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private OaUserRepository oaUserRepository;
    @Autowired
    private OaDepartmentRepository oaDepartmentRepository;
    @Autowired
    private ManageUserRepository manageUserRepository;
    @Autowired
    private SsoConfiguration ssoConfiguration;
    @Autowired
    private ManageRoleRepository manageRoleRepository;
    @Autowired
    private ManageUserRoleRepository manageUserRoleRepository;
    PoolingHttpClientConnectionManager pool = null;

    @Override
    public Object listBySearchKey(String searchKey, String umAppid, Pageable pageable) {
        Page<OaSystemUser> list = null;
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
            list = oaUserRepository.findAllByUserIdLikeOrUsernameLikeOrEmailLikeOrMobileLike(searchKey, searchKey, searchKey, searchKey, pageable);

        } else {
            list = oaUserRepository.findAll(pageable);
        }
        List<OaUserListResp> resultList = new ArrayList<>();
        Set<String> departIds = new HashSet<>();
         list.forEach(item -> {
            OaUserListResp v = new OaUserListResp(item.getUserId(), item.getUsername(), null, item.getMobile(), item.getEmail(), umAppid, item.getId(), item.getDepartmentId(), item.getManageId());
            resultList.add(v);
            departIds.add(item.getDepartmentId());
        });
        List<OaDepartment> departmentList = oaDepartmentRepository.findAllByDepartmentIdInAndUmAppid(departIds, umAppid);
        Map<String, OaDepartment> departmentMap = departmentList.stream().collect(Collectors.toMap(OaDepartment::getDepartmentId, oaDepartment -> oaDepartment));
        resultList.forEach(e -> {
            if (departmentMap.containsKey(e.getDepartmentId())) {
                e.setDepartmentName(departmentMap.get(e.getDepartmentId()).getName());
            }
        });
//        int totalPages = list.getTotalPages();
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("total", list.getTotalElements());
        pageMap.put("pages", list.getTotalPages());
        resultMap.put("list", resultList);
        resultMap.put("page", pageMap);

        return resultMap;
    }

    @Override
    public Object listAuthBySearchKey(String searchKey, String umappid, String departMentId, Pageable pageable) {
        // 1 判断是不是root
        Page<ManageUser> pageList = null;
        if (umappid.equals("TCL")) {
            if (!StringUtils.isEmpty(searchKey)) {
                pageList = manageUserRepository.findAllByUsernameLikeOrEmailLikeOrPhoneLike(searchKey, searchKey, searchKey, pageable);
            } else {
                pageList = manageUserRepository.findAll(pageable);
            }

        } else {
            // 2 不是 查询部门
            // 3 带部门查询所有
            List<OaDepartment> partList = oaDepartmentRepository.findAllByParentIdListContainingAndUmAppid(departMentId, umappid);
            OaDepartment part = oaDepartmentRepository.findFirstByDepartmentIdAndUmAppid(departMentId, umappid);
            partList.add(part);
            List<String> partIds = partList.stream().map(OaDepartment::getDepartmentId).collect(Collectors.toList());
            pageList = manageUserRepository.findAllByDepartmentIdIn(partIds, pageable);
        }

        List<OaUserListResp> resultList = new ArrayList<>();
        Set<String> departIds = new HashSet<>();
        pageList.forEach(item -> {
            OaUserListResp v = new OaUserListResp(item.getUsername(), item.getRealname(), null, item.getPhone(), item.getEmail(), umappid, item.getId(), item.getDepartmentId(), item.getId());
            resultList.add(v);
            if(item.getDepartmentId() != null){
                departIds.add(item.getDepartmentId());
            }
        });
        List<OaDepartment> departmentList = oaDepartmentRepository.findAllByDepartmentIdInAndUmAppid(departIds, umappid);
        Map<String, OaDepartment> departmentMap = departmentList.stream().collect(Collectors.toMap(OaDepartment::getDepartmentId, oaDepartment -> oaDepartment));
        resultList.forEach(e -> {
            if (departmentMap.containsKey(e.getDepartmentId())) {
                e.setDepartmentName(departmentMap.get(e.getDepartmentId()).getName());
            }
        });

        List<Long> manageIds = pageList.getContent().stream().map(ManageUser::getId).collect(Collectors.toList());

        List<ManageUserRole> mUserRole = manageUserRoleRepository.findAllBymIdIn(manageIds);
        List<Long> roleIds = mUserRole.stream().map(ManageUserRole::getcId).collect(Collectors.toList());
        List<ManageRole> roles = manageRoleRepository.findAllByIdInAndIsDelete(roleIds, (byte) 1);
        resultList.forEach(e -> {
            Long manageId = e.getManageId();
            mUserRole.forEach(f -> {
                if (f.getmId().equals(manageId)) {
                    Long roleId = f.getcId();
                    roles.forEach(z -> {
                        if (z.getId().equals(roleId)) {
                            e.setRoleName(z.getName());
                            e.setRoleId(roleId);
                        }
                    });
                }
            });
        });

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("total", pageList.getTotalElements());
        pageMap.put("pages", pageList.getTotalPages());
        resultMap.put("list", resultList);
        resultMap.put("page", pageMap);

        return resultMap;

    }

    @Override
    public boolean authUser(UserAuthReq userAuthReq) {
//        ManageRole role = manageRoleRepository.findOne(userAuthReq.getRoleId());
        ManageRole role = manageRoleRepository.getOne(userAuthReq.getRoleId());
        if (role == null) {
            throw new JMException("角色不存在");
        }

        OaSystemUser oaUSer = oaUserRepository.findFirstByUserId(userAuthReq.getUsername());
        if (null == oaUSer || oaUSer.getManageId() != null) {
            return false;
        }

        ManageUser user = manageUserRepository.findFirstByUsername(userAuthReq.getUsername());
        if (null != user) {
            return false;
        }
        user = new ManageUser();
        user.setUsername(oaUSer.getUserId());
        user.setRealname(oaUSer.getUsername());
        user.setPhone(oaUSer.getMobile());
        user.setEmail(oaUSer.getEmail());
        user.setStatus(1);
        user.setPasswd(null);
        user.setAuthority("");
        user.setGroupId(0);
        user.setCharacterId(0);
        user.setStatus(1);
        user.setDepartmentId(oaUSer.getDepartmentId());
        manageUserRepository.saveAndFlush(user);
        oaUSer.setManageId(user.getId());
        oaUserRepository.saveAndFlush(oaUSer);

        ManageUserRole m = new ManageUserRole();
        m.setcId(userAuthReq.getRoleId());
        m.setmId(user.getId());
        m.setCreateTime(System.currentTimeMillis());
        manageUserRoleRepository.save(m);
        return true;
    }

    @Override
    public boolean validMemberPassword(String username, String password) {
        ManageUser member = manageUserRepository.findFirstByUsername(username);
        if (member == null) {
            return false;
        }

        if (member.getPasswd() == null) {
            return false;
        }

        if (member.getPasswd().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void delUser(Long manageUserId) {
        ManageUser member = manageUserRepository.getOne(manageUserId);
//        ManageUser member = manageUserRepository.findOne(manageUserId);
        if (member == null) {
            throw new JMException("用户不存在");
        }
        OaSystemUser oaUser = oaUserRepository.findFirstByManageId(manageUserId);
        oaUser.setManageId(null);
        oaUserRepository.save(oaUser);
        // TODO delete(T)
//        manageUserRepository.delete(manageUserId);
        return;
    }

    @Override
    public boolean IDMLogin(String username, String password) {
        String body = "";

        CloseableHttpResponse response = null;
        try {
            //创建post方式请求对象
            HttpPost httpPost = new HttpPost(ssoConfiguration.getHost() + "/siam/rest/authenticate");


            //指定报文头Content-type、User-Agent
            httpPost.setHeader("Content-Type", "application/json");

            httpPost.setHeader("appcode", ssoConfiguration.getAppcode());

            httpPost.setHeader("appkey", ssoConfiguration.getAppkey());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            String time = sdf.format(new Date());
            httpPost.setHeader("timestamp", time);

//            String encodekey = encodePassword(ssoConfiguration.getAppcode() + ssoConfiguration.getAppkey() + time, ssoConfiguration.getAppkey());
            MessageDigestPasswordEncoder encoder = new ShaPasswordEncoder(256);
            String encodekey = encoder.encodePassword(ssoConfiguration.getAppcode() + ssoConfiguration.getAppkey() + time, ssoConfiguration.getAppkey());
            httpPost.setHeader("encode", encodekey);
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("username", username);
            jsonobj.put("password", password);
            httpPost.setEntity(new StringEntity(jsonobj.toString(), "application/json", "utf-8"));


            response = getHttpClient().execute(httpPost);
            InputStream content = response.getEntity().getContent();
            body = IOUtils.toString(content, "UTF-8");

            content.close();
            log.info("body: {}", body);
        } catch (IOException e) {
            log.error("请求IDM登入异常", e.fillInStackTrace());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (body.contains("true")) {
            return true;
        }
        return false;
    }

    private CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(pool)
                .build();

        return httpClient;
    }

    @PostConstruct
    public void init() {
        System.setProperty("jsse.enableSNIExtension", "false");

        // https证书初始化
        SSLContext sslcontext = null;
        try {
            //
            InputStream store = getClass().getClassLoader().getResourceAsStream("cacerts/tclstore");
            System.out.println(store);

            File file = new File("/tmp/" + "sslstore_" + System.currentTimeMillis());
//            File file = new File("F:/" + "sslstore_" + System.currentTimeMillis());
            inputStreamToFile(store, file);

            sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(file, "changeit".toCharArray(),
                            new TrustSelfSignedStrategy())
                    .build();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        pool.setMaxTotal(20);
        pool.setDefaultMaxPerRoute(20);
    }

    private void inputStreamToFile(InputStream ins, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[1024];
        while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {

            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }

}
