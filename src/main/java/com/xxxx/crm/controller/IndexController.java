package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;

import com.xxxx.crm.dao.SystemAdminMapper;
import com.xxxx.crm.service.SystemAdminService;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.LoginUserUtil;
import com.xxxx.crm.vo.SystemAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mk
 */
@Controller
public class IndexController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private SystemAdminService systemAdminService;

    /**
     * 系统登录页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }


    // 系统界面欢迎页
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 后端管理主页面
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request){
        // 通过⼯具类，从cookie中获取Id
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 调⽤对应Service层的⽅法，通过userId主键查询⽤户对象
        SystemAdmin systemAdmin = systemAdminService.selectByPrimaryKey(userId);
        // 将⽤户对象设置到request作⽤域中
        request.setAttribute("systemAdmin", systemAdmin);
        return "main";
    }
}
