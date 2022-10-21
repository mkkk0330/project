package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.LouCengMapper;
import com.xxxx.crm.query.QueryLouCeng;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.LouCeng;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LouCengService extends BaseService<LouCeng,Integer> {


    @Resource
    private LouCengMapper louCengMapper;

    /*多条件查询*/
    public Map<String, Object> queryLouByParams(QueryLouCeng queryLouCeng) {
        Map<String, Object> map = new HashMap<>();
        //开始分页
        PageHelper.startPage(queryLouCeng.getPage(),queryLouCeng.getLimit());
        List<QueryLouCeng> queryLouCengs = louCengMapper.queryLouCengParams(queryLouCeng);
        //按照分页, 格式化数据
        PageInfo<QueryLouCeng> PageInfo = new PageInfo<>(queryLouCengs);
        map.put("code",0);
        map.put("msg","");
        map.put("count",PageInfo.getTotal());
        map.put("data",PageInfo.getList());
        return map;
    }

    /**
     * 添加，修改管理
     * @param id
     * @return
     */
    public LouCeng queryLouById(Integer id) {
        AssertUtil.isTrue(id == null,"数据异常，请重试");
        return louCengMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     */
    public void deleteById(Integer[] ids) {
        AssertUtil.isTrue(ids == null,"数据异常，请重试");
        louCengMapper.updateId(ids);
    }

    /**
     * 添加用户
     *       1验证
     *          验证用户名是否唯一
     *          设置密码 （默认123456）
     *          设置真实名字 （必填）
     *          设置手机号码 （随意）
     *       2执行
     *         将状态码设置为1
     *         添加操作
     * @param louCeng
     */
    public void saveLou(LouCeng louCeng) {
        AssertUtil.isTrue(louCeng == null,"数据异常，请重试");
        AssertUtil.isTrue(louCengMapper.insertSelective(louCeng) < 1,"添加失败");
    }

    /**
     * 修改用户
     *      1 验证
     *          该用户是否存在
     *          用户名 非空
     *          真实名字 非空
     *      2  执行
     * @param louCeng
     */
    public void updateLou(LouCeng louCeng) {
        AssertUtil.isTrue(louCengMapper.selectByPrimaryKey(louCeng.getId()) == null,"楼层不存在");
        AssertUtil.isTrue(louCeng.getName() == null,"楼层信息不能为空");
        AssertUtil.isTrue(louCengMapper.updateByPrimaryKeySelective(louCeng) < 1,"修改失败");
    }
}
