package com.tcl.uf.content.vo;

/**
 * @author youyun.xu
 * @ClassName: ${CLASSNAME}
 * @Description: 文章内容管理
 * @date 2020/8/19 17:07
 */
public class OfficialOauthEntity {

    private Integer exp; //过期时间
    private String token;//令牌

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
