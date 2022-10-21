package com.xxxx.crm.query;


import com.xxxx.crm.base.BaseQuery;

public class StudentinFormationQuery extends BaseQuery {
    private String number;
    private String name;
    private String state;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
