package com.xxxx.crm.service;

import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.UserMapper;
import com.xxxx.crm.model.UserModel;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.utils.Md5Util;
import com.xxxx.crm.utils.PhoneUtil;
import com.xxxx.crm.utils.UserIDBase64;
import com.xxxx.crm.vo.User;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author mk
 */
@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;

    public UserModel userLogin(String userName , String userPwd) {
        checkLoginParams(userName,userPwd);
        User user = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(user == null,"用户姓名不存在");
        checkUserPwd(userPwd,user.getUserPwd());

        return buildUserInfo(user);


    }

    private UserModel buildUserInfo(User user) {
        UserModel userModel = new UserModel();
        //userModel.setUserId(user.getId());
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    private void checkUserPwd(String userPwd, String pwd) {
        userPwd = Md5Util.encode(userPwd);
        AssertUtil.isTrue(!userPwd.equals(pwd),"用户密码不正确");
    }


    private void checkLoginParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空");
    }


    /**
     ⽤户密码修改
        1. 参数校验
            ⽤户ID：userId ⾮空 ⽤户对象必须存在
            原始密码：oldPassword ⾮空 与数据库中密⽂密码保持⼀致
            新密码：newPassword ⾮空 与原始密码不能相同
            确认密码：confirmPassword ⾮空 与新密码保持⼀致
        2. 设置⽤户新密码
            新密码进⾏加密处理
        3. 执⾏更新操作
            受影响的⾏数⼩于1，则表示修改失败

            注：在对应的更新⽅法上，添加事务控制
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassWord(Integer userId,String oldPwd,String newPwd,String repeatPwd) {

        //AssertUtil.isTrue(userId==null,"用户id不能为空");
        User user = userMapper.selectByPrimaryKey(userId);

        AssertUtil.isTrue(user == null,"待更新记录不存在！");
        //参数验证
        checkPasswordParams(user,oldPwd,newPwd,repeatPwd);
        user.setUserPwd(Md5Util.encode(newPwd));
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1,"更新密码失败");

    }
    //更新密码的参数校验
    private void checkPasswordParams(User user, String oldPwd, String newPwd, String repeatPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd),"原始密码不能为空！");
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPwd)),"原始密码不正确！");

        AssertUtil.isTrue(StringUtils.isBlank(newPwd),"新密码不能为空！");
        AssertUtil.isTrue(oldPwd.equals(newPwd),"新密码不能与原始密码相同！");

        AssertUtil.isTrue(StringUtils.isBlank(repeatPwd),"确认密码不能为空！");
        AssertUtil.isTrue(!newPwd.equals(repeatPwd),"确认密码与新密码不一致！");
    }



    //添加用户
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        //System.out.println(user.getRoleIds());
        //参数校验
        checkUserParams(user.getUserName(),user.getEmail(),user.getPhone(),null);
        //默认值
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setUserPwd(Md5Util.encode("123456"));
        //执行添加操作
        AssertUtil.isTrue(userMapper.insertSelective(user)<1,"用户添加失败！");



    }


    //更新用户
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user) {
        //参数校验
        AssertUtil.isTrue(user.getId() == null,"待更新记录不存在");
        User temp = userMapper.selectByPrimaryKey(user.getId());
        AssertUtil.isTrue(temp==null,"待更新记录不存在");
        checkUserParams(user.getUserName(),user.getEmail(),user.getPhone(),user.getId());
        //默认值
        user.setUpdateDate(new Date());
        //执行更新操作
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)!=1,"用户添加失败！");


    }

    private void checkUserParams(String userName, String email, String phone,Integer userId) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        User temp = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(temp!=null && !(temp.getId().equals(userId)),"用户名已存在，请重试");
        AssertUtil.isTrue(StringUtils.isBlank(email),"邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone),"号码不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"手机号码格式不正确");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByIds(Integer[] ids) {
        AssertUtil.isTrue(ids == null ||ids.length == 0,"待删除记录不存在");
        AssertUtil.isTrue(userMapper.deleteBatch(ids) != ids.length,"删除用户失败");
    }
}
