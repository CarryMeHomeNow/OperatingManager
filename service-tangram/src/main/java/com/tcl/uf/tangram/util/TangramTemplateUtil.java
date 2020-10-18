package com.tcl.uf.tangram.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Desc : TangramTemplate解析工具
 * @Author
 * @Email
 * @Date 2020/8/12 18:55
 */
public class TangramTemplateUtil {

    private static Pattern regex = Pattern.compile("\\$\\{([^}]*)\\}");

    /**
     * 替换
     *
     * @param source                        源内容
     * @param parameter                     占位符参数
     * @param prefix                        占位符前缀 例如:${
     * @param suffix                        占位符后缀 例如:}
     * @param enableSubstitutionInVariables 是否在变量名称中（${}内部）进行替换 例如:${system-${版本}}
     *                                      <p>
     *                                      转义符默认为'$'。如果这个字符放在一个变量引用之前，这个引用将被忽略，不会被替换 如$${a}将直接输出${a}
     * @return
     */
    public static String replace(String source, Map<String, Object> parameter, String prefix, String suffix, boolean enableSubstitutionInVariables) {
        //StrSubstitutor不是线程安全的类
        StrSubstitutor strSubstitutor = new StrSubstitutor(parameter, prefix, suffix);
        //是否在变量名称中进行替换
        strSubstitutor.setEnableSubstitutionInVariables(enableSubstitutionInVariables);
        return strSubstitutor.replace(source);
    }

    /**
     * 解析模板中的组件
     *
     * @param templateJson 带有占位符的模板
     * @return List<String> 组件编号集合
     */
    public static List<String> resolveModule(String templateJson) {
        // TODO zfk
        List<String> list = new ArrayList<String>();
        if (StringUtils.isBlank(templateJson)) {
            return list;
        }
        Matcher matcher = regex.matcher(templateJson);
        while (matcher.find()) {
            list.add(matcher.group(1));
        }

        return list;
    }

    /**
     * 替换组件值
     *
     * @param templateJson 带有占位符的模板
     * @param moduleValues key-moduleCode 组件编号    value-jsonValue
     * @return 加工后的JSON（前端可直接使用）
     */
    public static String replaceModuleValue(String templateJson, Map<String, Object> moduleValues) {
        // TODO zfk
        if (CollectionUtils.isEmpty(moduleValues) || StringUtils.isBlank(templateJson)) {
            return null;
        }
        return replace(templateJson, moduleValues, "${", "}", false);
    }

    public static Map<String, String>  analysisJson(Object objJson){
        Map<String,String> resultMap = new HashMap<>();
        //如果obj为json数组
        if(objJson instanceof JSONArray){
            JSONArray objArray = (JSONArray)objJson;
            for (int i = 0; i < objArray.size(); i++) {
                analysisJson(objArray.get(i));
            }
        }
        //如果为json对象
        else if(objJson instanceof JSONObject){
            JSONObject jsonObject = (JSONObject)objJson;
            for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                String key = entry.getKey();
                Object object = jsonObject.get(key);
                if(null==object){
                    continue;
                }
                //如果得到的是数组
                if(object instanceof JSONArray){
                    JSONArray objArray = (JSONArray)object;
                    analysisJson(objArray);
                }
                //如果key中是一个json对象
                else if(object instanceof JSONObject){
                    analysisJson((JSONObject)object);
                }
                //如果key中是其他
                else{
                    System.out.println("["+key+"]:"+object.toString()+" ");
                    resultMap.put(key,object.toString());
                }

            }
        }
        return resultMap;
    }
}
