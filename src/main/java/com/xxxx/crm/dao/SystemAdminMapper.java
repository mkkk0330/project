package com.xxxx.crm.dao;


import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.SystemAdmin;

public interface SystemAdminMapper extends BaseMapper<SystemAdmin,Integer> {

    SystemAdmin queryUserByName(String userName);
}