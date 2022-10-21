package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.model.UserModel;
import com.xxxx.crm.query.UserQuery;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mk
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName,String userPwd) {
        ResultInfo resultInfo = new ResultInfo();
        //调用service登陆方法
        UserModel userModel = userService.userLogin(userName,userPwd);
        resultInfo.setResult(userModel);
        return resultInfo;
    }

    @RequestMapping("updatePwd")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest request,String oldPassword,String newPassword,String repeatPassword) {
        ResultInfo resultInfo = new ResultInfo();
        //从cookie中取出userId
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //修改密码
        userService.updatePassWord(userId,oldPassword,newPassword,repeatPassword);
        /*try {
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            userService.updatePassWord(userId,oldPassword,newPassword,repeatPassword);
        } catch (ParamsException p) {
            resultInfo.setCode(p.getCode());
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        } catch (Exception e) {
            resultInfo.setCode(500);
            resultInfo.setMsg("修改密码失败！");
        }*/
        return resultInfo;
    }

    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "user/password";
    }



    //分页多条件查询用户列表
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectByParams(UserQuery userQuery){
        return userService.queryByParamsForTable(userQuery);
    }

    //进入用户管理界面
    @RequestMapping("index")
    public String index(){
        return "user/user";
    }
    //添加用户
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addUser(User user) {
        userService.addUser(user);
        return success("用户添加成功");
    }

    @RequestMapping("toAddOrUpdateUserPage")
    public String toAddOrUpdateUserPage(Integer id ,HttpServletRequest request){
        if (id !=null) {
            User user = userService.selectByPrimaryKey(id);
            request.setAttribute("userInfo",user);
        }
        return "user/add_update";
    }

    //更新用户
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user) {
        userService.updateUser(user);
        return success("用户更新成功");
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        userService.deleteByIds(ids);
        return success("用户记录删除成功");
    }

}
