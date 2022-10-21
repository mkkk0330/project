package com.xxxx.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.HouseparentMapper;
import com.xxxx.crm.query.QueryHouseparent;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.utils.Md5Util;
import com.xxxx.crm.vo.Houseparent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseparentService extends BaseService<Houseparent,Integer> {

    @Resource
    private HouseparentMapper houseparentMapper;

    /*多条件查询*/
    public Map<String, Object> queryHouseByParams(QueryHouseparent queryHouseparent) {
        Map<String, Object> map = new HashMap<>();
        //开始分页
        PageHelper.startPage(queryHouseparent.getPage(),queryHouseparent.getLimit());
        List<Houseparent> queryHouseparents = houseparentMapper.queryHouseparentByParams(queryHouseparent);
        //按照分页, 格式化数据
        PageInfo<Houseparent> userPageInfo = new PageInfo<>(queryHouseparents);
        map.put("code",0);
        map.put("msg","");
        map.put("count",userPageInfo.getTotal());
        map.put("data",userPageInfo.getList());
        return map;
    }

    /**
     * 添加，修改管理
     * @param id
     * @return
     */
    public Houseparent queryHouseById(Integer id) {
        AssertUtil.isTrue(id == null,"数据异常，请重试");
        return houseparentMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param ids
     */
    public void deleteById(Integer[] ids) {
        AssertUtil.isTrue(ids == null,"数据异常，请重试");
        houseparentMapper.updateById(ids);
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
     * @param houseparent
     */
    public void saveHouse(Houseparent houseparent) {
        AssertUtil.isTrue(houseparent == null,"数据异常，请重试");
        AssertUtil.isTrue(houseparentMapper.selectByUserName(houseparent.getUsername()) != null,"该用户已存在，请重新输入");
        AssertUtil.isTrue(houseparent.getName() == null, "真实名字不能为空");
        houseparent.setIs_valid(1);
        if (houseparent.getPassword() == null) {
            houseparent.setPassword(Md5Util.encode("123456"));
        }
        AssertUtil.isTrue(houseparentMapper.insertSelective(houseparent) < 1,"添加失败");
    }

    /**
     * 修改用户
     *      1 验证
     *          该用户是否存在
     *          用户名 非空
     *          真实名字 非空
     *      2  执行
     * @param houseparent
     */
    public void updateHouse(Houseparent houseparent) {
        AssertUtil.isTrue(houseparentMapper.selectByPrimaryKey(houseparent.getId()) == null,"用户不存在");
        AssertUtil.isTrue(houseparent.getUsername() == null,"用户名不能为空");
        AssertUtil.isTrue(houseparent.getName() == null,"真实名字不能为空");
        AssertUtil.isTrue(houseparentMapper.updateByPrimaryKeySelective(houseparent) < 1,"修改失败");
    }
}
