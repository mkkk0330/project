package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.query.QueryLouCeng;
import com.xxxx.crm.vo.LouCeng;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;


public interface LouCengMapper extends BaseMapper<LouCeng,Integer> {


    /*多条件查询*/
    List<QueryLouCeng> queryLouCengParams(QueryLouCeng queryLouCeng);

    /*批量删除*/
    void updateId(Integer[] ids);

    /*通过用户名查询是否唯一*/
    LouCeng selectByName(String name);

   /* *//*执行添加操作*//*
    Integer insertSelective(LouCeng louCeng);*/

    /*查询所有的管理员*/
    @MapKey("rname")
    List<Map<String, Object>> queryAllAdmin();
}