package com.tcl.uf.tangram.vo;

/**
 * @author zhongfk on 2020/8/14.
 * @version 1.0
 */
public class TemplateAndModuleVO {

    private Long id;
    /**
     * 组件编号
     */
    private String moduleCode;
    /**
     * 组件名称
     */
    private String moduleName;
    /**
     * 模板数据
     */
    private String tangramTemplate;

    private String invokeModuleClass;

    private String invokeModuleMethod;

    private String dataSource;
    /**
     * 接口名称
     */
    private String urlInfo;

    private String invokeApiClass;

    private String invokeApiMethod;
    /**
     * 数据类型
     */
    private String dataType;

    private String templateName ;

    private String tangramCode ;

    @Override
    public String toString() {
        return "TemplateAndModuleVO{" +
                "id=" + id +
                ", moduleCode='" + moduleCode + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", tangramTemplate='" + tangramTemplate + '\'' +
                ", invokeModuleClass='" + invokeModuleClass + '\'' +
                ", invokeModuleMethod='" + invokeModuleMethod + '\'' +
                ", dataSource='" + dataSource + '\'' +
                ", urlInfo='" + urlInfo + '\'' +
                ", invokeApiClass='" + invokeApiClass + '\'' +
                ", invokeApiMethod='" + invokeApiMethod + '\'' +
                ", dataType='" + dataType + '\'' +
                ", templateName='" + templateName + '\'' +
                ", tangramCode='" + tangramCode + '\'' +
                '}';
    }
}
