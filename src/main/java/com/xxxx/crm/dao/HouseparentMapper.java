package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.query.QueryHouseparent;
import com.xxxx.crm.vo.Houseparent;

import java.util.List;

public interface HouseparentMapper extends BaseMapper<Houseparent,Integer> {

    /*多条件查询*/
    List<Houseparent> queryHouseparentByParams(QueryHouseparent queryHouseparent);

    /*批量删除*/
    void updateById(Integer[] ids);

    /*通过用户名查询是否唯一*/
    Houseparent selectByUserName(String username);

//    /*执行添加操作*/
//    Integer insertSelective(Houseparent houseparent);
}