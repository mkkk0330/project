package com.xxxx.crm.dao;


import com.xxxx.crm.base.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface StudentinFormationMapper extends BaseMapper {

    //根据name查询宿舍号
    public String selectByPrimaryKey2(String name) throws DataAccessException;


    List<Map<String, Object>> queryAll();
}