package com.xxxx.crm.vo;

public class Repair {
    private Integer id;
    //楼id
    private String buildingId;
    //宿舍id
    private Integer name;
    //报修说明
    private String illustrate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId == null ? null : buildingId.trim();
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate == null ? null : illustrate.trim();
    }
}