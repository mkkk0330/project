/*
package com.xxxx.crm.controller;

import com.xxxx.crm.base.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

*/
/**
 * @author mk
 *//*

@Controller
@RequestMapping("image")
public class UploadController {
    @PostMapping("/upload")
    public ModelAndView uploadFile(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("notice/m");
        MultipartHttpServletRequest mr=(MultipartHttpServletRequest) request;
        MultipartFile multipartFile=mr.getFile("file");
        String path=request.getSession().getServletContext().getRealPath("upload");
        System.out.println(path);

        if(null!=multipartFile&&!multipartFile.isEmpty()){
            String fileName=multipartFile.getOriginalFilename();
        try {
	        multipartFile.transferTo(new File(path,fileName));
            mv.addObject("msg", "文件上传成功!");
        } catch (Exception e) {
            mv.addObject("msg", "上传失败!");
            e.printStackTrace();
            }
        }
        return mv;
  }


    @RequestMapping("index")
    public String index(){
        return "notice/upload";
    }
}
*/
