package com.xxxx.crm.controller;


import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.service.SystemAdminService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.SystemAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Resource
    private SystemAdminService systemAdminService;

    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String userName, String userPwd) {

        ResultInfo resultInfo = new ResultInfo();
        //调用service登陆方法
        SystemAdmin systemAdmin = systemAdminService.userLogin(userName,userPwd);
        resultInfo.setResult(systemAdmin);
        return resultInfo;
    }
    @PostMapping("/update")
    @ResponseBody
    public ResultInfo updateUserPassword (HttpServletRequest request,
                                          String oldPassword, String newPassword,
                                          String aginPassword) {
        ResultInfo resultInfo = new ResultInfo();
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        systemAdminService.updatePassword(userId, oldPassword, newPassword, aginPassword);

        return resultInfo;
    }

    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "password"; }

}
