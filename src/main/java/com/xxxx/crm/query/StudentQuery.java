package com.xxxx.crm.query;

import com.xxxx.crm.base.BaseQuery;

public class StudentQuery extends BaseQuery {
    private String name;
    private Integer dormitoryId;
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
