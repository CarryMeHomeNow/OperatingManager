package com.tcl.uf.tangram.vo;

import java.util.List;

/**
 * @author zhongfk on 2020/9/1.
 * @version 1.0
 */
public class Items {

    private String day;

    private String type;

   private List<ItemsDetail> itemsDetails ;

    public void setDay(String day){
        this.day = day;
    }
    public String getDay(){
        return this.day;
    }

    public List<ItemsDetail> getItemsDetails() {
        return itemsDetails;
    }

    public void setItemsDetails(List<ItemsDetail> itemsDetails) {
        this.itemsDetails = itemsDetails;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
