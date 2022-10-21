package com.xxxx.crm;

import com.alibaba.fastjson.JSON;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.exceptions.AuthException;
import com.xxxx.crm.exceptions.NoLoginException;
import com.xxxx.crm.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author mk
 */

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    /**
     * 如果⽅法级别配置了 @ResponseBody 注解，表示⽅法返回的是JSON；反之，返回的是视图界面
     *
     * @param request a
     * @param response a
     * @param handler  方法
     * @param e 异常
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        //非法请求拦截
        if (e instanceof NoLoginException) {
            ModelAndView mv = new ModelAndView("redirect:/index");
            return mv;
        }


        //默认异常(返回视图)
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("code",500);
        modelAndView.addObject("msg","系统异常,请重试...");

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ResponseBody responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
            if (responseBody == null ) {    //返回视图
                if (e instanceof ParamsException) {
                    ParamsException p = (ParamsException) e;
                    modelAndView.addObject("code",p.getCode());
                    modelAndView.addObject("msg",p.getMsg());
                } else if (e instanceof AuthException) { //认证异常
                    AuthException a = (AuthException) e;
                    modelAndView.addObject("code",a.getCode());
                    modelAndView.addObject("msg",a.getMsg());
                }
                return modelAndView;
            } else {  //返回json
                //默认异常处理
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(500);
                resultInfo.setMsg("系统异常，请重试...");

                if (e instanceof ParamsException) {
                    ParamsException p = (ParamsException) e;
                    resultInfo.setCode(p.getCode());
                    resultInfo.setMsg(p.getMsg());
                } else if (e instanceof AuthException) {
                    AuthException a = (AuthException) e;
                    resultInfo.setCode(a.getCode());
                    resultInfo.setMsg(a.getMsg());
                }
                //设置响应类型和编码
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out=null;
                try {
                    out = response.getWriter();
                    //将返回的对象转换为json
                    String json = JSON.toJSONString(resultInfo);
                    out.write(json);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    if (out!=null){
                        out.close();
                    }
                }
                return null;
            }
        }
        return modelAndView;
    }
}











