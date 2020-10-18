package com.tcl.uf.member.dto;

/**
 * @author youyun.xu
 * @ClassName: ${CLASSNAME}
 * @Description: 文章内容管理
 * @date 2020/8/18 16:42
 */
public class IntegralRuleConfigureDto {

    private Integer id;
    private String name;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
