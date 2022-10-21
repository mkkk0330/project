package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.SystemAdminMapper;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.vo.SystemAdmin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SystemAdminService extends BaseService<SystemAdmin,Integer> {
    @Autowired
    private SystemAdminMapper systemAdminMapper;

    public SystemAdmin userLogin(String userName , String userPwd) {
        //判断用户名 密码是否为空
        checkLoginParams(userName,userPwd);
        //根据用户名查询用户对象
        SystemAdmin systemAdmin = systemAdminMapper.queryUserByName(userName);
        //判断用户对象是否存在
        AssertUtil.isTrue(systemAdmin == null,"用户姓名不存在");
        //判断密码是否正确
        checkUserPwd(userPwd,systemAdmin.getPassword());
        return systemAdmin;
    }

    private void checkLoginParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空");
    }

    //判断密码是否正确
    private void checkUserPwd(String userPwd, String pwd) {
        AssertUtil.isTrue(!userPwd.equals(pwd),"用户密码不正确");
    }


    /*
    * 修改密码
    */
    public void updatePassword(Integer userid,String oldPassword,String newPassword,String aginPassword){
        //通过用户id获取用户对象
        SystemAdmin systemAdmin = systemAdminMapper.selectByPrimaryKey(userid);
        //参数校验
        checkPasswordParams(systemAdmin, oldPassword, newPassword, aginPassword);
        systemAdmin.setPassword(aginPassword);
        AssertUtil.isTrue(systemAdminMapper.updateByPrimaryKeySelective(systemAdmin) < 1, "⽤户密码更新失败！");
    }

    private void checkPasswordParams(SystemAdmin systemAdmin, String oldPassword, String newPassword, String aginPassword) {
        AssertUtil.isTrue(null == systemAdmin, "⽤户未登录或不存在！");
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "请输⼊原始密码！");
        AssertUtil.isTrue(! (systemAdmin.getPassword().equals(oldPassword)), "原始密码不正确！");
        // 新密码 ⾮空校验
        AssertUtil.isTrue(StringUtils.isBlank(newPassword), "请输⼊新密码！");
        AssertUtil.isTrue(oldPassword.equals(newPassword), "新密码不能与原始密码相同！");
        AssertUtil.isTrue(StringUtils.isBlank(aginPassword), "请输⼊确认密码！");
        AssertUtil.isTrue(!(newPassword.equals(aginPassword)), "新密码与确认密码不⼀致！");
    }
}
