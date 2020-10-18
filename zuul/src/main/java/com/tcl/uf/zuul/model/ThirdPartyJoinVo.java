package com.tcl.uf.zuul.model;

import javax.persistence.*;

/**
 * @author chiwm@kuyumall.com
 * @ClassName: RouterRule
 * @Description:
 * @date 2018/1/30 下午8:40
 */

@Entity
@Table(name = "uf_third_party_join")
public class ThirdPartyJoinVo {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 第三方appid
     */
    @Column(name = "appid")
    private String appid;
    /**
     * 第三方秘钥
     */
    @Column(name = "app_secret")
    private String appSecret;
    /**
     * 可访问接口列表
     */
    @Column(name = "interface_list")
    private String interfaceList;

    /**
     * 状态 ： 1可使用，0禁用
     */
    @Column(name = "status")
    private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(String interfaceList) {
		this.interfaceList = interfaceList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

   
}
