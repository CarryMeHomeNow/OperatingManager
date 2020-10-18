package com.tcl.uf.gateway.exception;

import com.tcl.uf.common.base.util.ExceptionStackTraceUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc : 测试用，默认的模板数据
 * @Author Mr.HePeng
 * @Email peng6.he@tcl.com
 * @Date 2020/9/1 18:52
 */
public class DefaultTangramData {

    static Logger logger = LoggerFactory.getLogger(DefaultTangramData.class);

    static Map<String, String> tangramData = new HashMap<>(16);

    static {
        tangramData.put("1001", loadDefaultTangramData("1001", "json/mainMall.json"));
        tangramData.put("1002", loadDefaultTangramData("1002", "json/commodityCategory.json"));
        tangramData.put("1014", loadDefaultTangramData("1014", "json/paySuccess.json"));
        tangramData.put("1015", loadDefaultTangramData("1015", "json/searchpage.json"));
        tangramData.put("1003", loadDefaultTangramData("1003", "json/no_toekn.json"));
        tangramData.put("100301", loadDefaultTangramData("100301", "json/has_token.json"));
        tangramData.put("1018", loadDefaultTangramData("1018", "json/empt.json"));
        tangramData.put("", loadDefaultTangramData("", ""));
    }

    protected static String loadDefaultTangramData(String code, String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }

        logger.info("加载[{}]默认数据路径[{}]", code, filePath);

        String data = "";
        String s = "";
        try {
            InputStream stream = DefaultTangramData.class.getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            while ((s = br.readLine()) != null) {
                data = data + s;
            }
        } catch (Exception e) {
            logger.info("读取文件[{}]失败：{}", filePath, ExceptionStackTraceUtil.getStackTrace(e));
        }
        return data;
    }

    public static String getDataByTangramCode(String tangramCode) {
        return tangramData.get(tangramCode);
    }

    public static void main(String[] args) {
        System.out.println(getDataByTangramCode("1009"));
    }
}
