package com.tcl.commondb.activity.model;

import javax.persistence.*;

@Entity
@Table(name = "activity_properties_config")
public class PropertiesConfigModel {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "pkey", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '键'")
    private String pkey;

    @Column(name = "pvalue", columnDefinition = "varchar(255) DEFAULT NULL COMMENT '值'")
    private String pvalue;
    
    @Column(name = "note", columnDefinition = "varchar(20) DEFAULT NULL COMMENT '备注'")
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getPvalue() {
		return pvalue;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "PropertiesConfigModel [id=" + id + ", pkey=" + pkey + ", pvalue=" + pvalue + ", note=" + note + "]";
	}



}
