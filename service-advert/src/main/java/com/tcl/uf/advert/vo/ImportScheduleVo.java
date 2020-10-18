package com.tcl.uf.advert.vo;

import com.tcl.uf.advert.dto.LocationScheduleDto;

import java.util.List;
import java.util.Map;

public class ImportScheduleVo {

    private Integer successNum;

    private Integer failNum;

    private Map<String, String> failMap;

    private List<LocationScheduleDto> successList;

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    public Map<String, String> getFailMap() {
        return failMap;
    }

    public void setFailMap(Map<String, String> failMap) {
        this.failMap = failMap;
    }

    public List<LocationScheduleDto> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<LocationScheduleDto> successList) {
        this.successList = successList;
    }
}
