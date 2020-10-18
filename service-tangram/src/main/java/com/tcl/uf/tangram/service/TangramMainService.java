package com.tcl.uf.tangram.service;

import com.alibaba.fastjson.JSONArray;
import com.tcl.commondb.tangram.model.TangramTemplateConfig;
import com.tcl.commondb.tangram.model.TangramTemplateModuleConfig;
import com.tcl.commondb.tangram.repository.TangramTemplateConfigRepository;
import com.tcl.commondb.tangram.repository.TangramTemplateModuleConfigRepository;
import com.tcl.uf.common.base.util.spring.SpringBeanUtil;
import com.tcl.uf.tangram.util.TangramTemplateUtil;
import com.tcl.uf.tangram.vo.TangramRequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc : 处理Tangram数据的统一入口
 * @Author Mr.HePeng
 * @Date 2020/8/8 16:11
 */
@Service
public class TangramMainService {

    @Autowired
    private SpringBeanUtil springBeanUtil;
    @Autowired
    private TangramTemplateConfigRepository tangramTemplateConfigRepository;
    @Autowired
    private TangramTemplateModuleConfigRepository tangramTemplateModuleConfigRepository;

    /**
     * 处理类
     *
     * @return
     */
    public String execute(String reqCode,Map<String,JSONArray> apiDataMap) {
        TangramTemplateConfig config = queryTangramTemplateConfig(reqCode);
        return etl(config, apiDataMap);
    }

    /**
     * 查询模板配置信息
     *
     * @param templateCode
     */
    private TangramTemplateConfig queryTangramTemplateConfig(String templateCode) {
        return tangramTemplateConfigRepository.findTangramTemplateConfigByTangramCode(templateCode);
    }

    /**
     * 数据加工处理
     * @param config
     * @param apiDataMap 处理好的每个组件对应的api数据
     */
    private String etl(TangramTemplateConfig config, Map<String,JSONArray> apiDataMap) {
        // 解析JSON数据
        String tangramTemplate = config.getTangramTemplate();
        //组件编号
        List<String> stringList = TangramTemplateUtil.resolveModule(tangramTemplate);
        List<TangramTemplateModuleConfig> moduleConfigs =tangramTemplateModuleConfigRepository.findByModuleNameIn(stringList);

        if (moduleConfigs == null || moduleConfigs.size() == 0) {
            // 返回模板数据
            return tangramTemplate;
        }
        // 调用相关组件进行处理
        Map<String, Object> resultMap = new HashMap<>(16);
        for (TangramTemplateModuleConfig moduleConfig : moduleConfigs) {
            String tangramResult = "";
            if(apiDataMap.get(moduleConfig.getModuleName())==null){
                tangramResult = moduleConfig.getTangramTemplate();
            }else{
               tangramResult = this.processModule(moduleConfig, apiDataMap.get(moduleConfig.getModuleName()));
            }
            resultMap.put(moduleConfig.getModuleName(), tangramResult);
        }
        // 返回值设置进JSON
        return TangramTemplateUtil.replaceModuleValue(tangramTemplate, resultMap);
    }

    private String processModule(TangramTemplateModuleConfig moduleConfig, JSONArray data) {
        String className = moduleConfig.getInvokeApiClass();
        TangramModuleService moduleService = (TangramModuleService) springBeanUtil.getBean(className);
        return (String) moduleService.transData(moduleConfig,data);
    }
}
