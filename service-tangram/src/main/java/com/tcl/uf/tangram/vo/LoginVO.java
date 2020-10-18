package com.tcl.uf.tangram.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhongfk on 2020/9/2.
 * @version 1.0
 */
public class LoginVO implements Serializable {

    private String type;

    private String id;

    private Style style;

    private List<Items> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
