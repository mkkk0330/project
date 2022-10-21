package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.dao.LouCengMapper;
import com.xxxx.crm.query.QueryLouCeng;
import com.xxxx.crm.service.LouCengService;
import com.xxxx.crm.vo.LouCeng;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("louceng")
public class LouCengController extends BaseController{

    @Resource
    private LouCengService louCengService;

    @Resource
    private LouCengMapper louCengMapper;

    @RequestMapping("index")
    public String index(){
        return "louceng/louceng";
    }

    /*多条件查询*/
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(QueryLouCeng queryLouCeng) {
        return louCengService.queryLouByParams(queryLouCeng);
    }

    /*打开添加，修改管理*/
    @RequestMapping("toUpdateAddPage")
    public String toUpdateAddPage(Integer id, HttpServletRequest request){
        if (id != null){
            LouCeng louCeng = louCengService.queryLouById(id);
            request.setAttribute("user",louCeng);
        }
        return "louceng/add_update";
    }

    /*保存添加的管理*/
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveLou(LouCeng louCeng){
        louCengService.saveLou(louCeng);
        return success("添加成功");
    }

    /*保存修改的管理*/
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(LouCeng louCeng){
        louCengService.updateLou(louCeng);
        return success("修改成功");
    }

    /*批量删除*/
    @PostMapping("deleteBatch")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[] ids){
        louCengService.deleteById(ids);
        return success("删除成功");
    }

    /*查询所有管理者*/
    @PostMapping("queryAllAdmin")
    @ResponseBody
    public List<Map<String,Object>> queryAllAdmin(){
        return louCengMapper.queryAllAdmin();
    }
}
