package com.jingguan.baseInfo.controller;

import com.jingguan.baseInfo.po.TTeacherBaseinfoEntity;
import com.jingguan.baseInfo.service.BaseInfoService;
import com.jingguan.uploadImage.controller.uploadImage;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 陈 on 2017/10/13.
 */
@Controller
@RequestMapping("baseInfo")
public class BaseInfoController extends uploadImage {
    @Resource(name = "baseInfoService")
    private BaseInfoService baseInfoSerivce;

    @RequestMapping(value = "getBaseInfo", method = RequestMethod.GET)
    @ResponseBody
    List<TTeacherBaseinfoEntity> info(HttpServletRequest request,String id){
        int user_id;
        if(StringUtils.isEmpty(id)){
            user_id = (int) request.getSession().getAttribute("user_id");
            System.out.println(1);
        }else{
            System.out.println(2);
            user_id = Integer.parseInt(id);
        }
        System.out.println("user_id = "+user_id);
        List<TTeacherBaseinfoEntity> info = baseInfoSerivce.getBaseInfo(user_id);
        if(info.size() != 0){
            request.getSession().setAttribute("id",info.get(0).getId());
            return info;
        }else{
            return null;
        }
    }

    @RequestMapping("updateBaseInfo")
    @ResponseBody
    int updateBaseInfo(HttpServletRequest request,TTeacherBaseinfoEntity tTeacherBaseinfoEntity){
        int user_id = (int) request.getSession().getAttribute("user_id");
        int id = (int) request.getSession().getAttribute("id");
        int rmsg = baseInfoSerivce.updateBaseInfo(tTeacherBaseinfoEntity,id);
        if(rmsg == 200){
            return 200;
        }else{
            return 0;
        }

    }

    @RequestMapping("saveBaseInfo")
    @ResponseBody
    int saveBaseInfo(HttpServletRequest request,TTeacherBaseinfoEntity tTeacherBaseinfoEntity){
        int user_id = (int) request.getSession().getAttribute("user_id");
        int rmsg = baseInfoSerivce.saveBaseInfo(tTeacherBaseinfoEntity,user_id);

        if(rmsg != 0){
            request.getSession().setAttribute("id",rmsg);
            return rmsg;
        }else{
            return 0;
        }

    }

    @RequestMapping("uploadPic")
    @ResponseBody
    void uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        int user_id = (int) request.getSession().getAttribute("user_id");
        String path = "personalPic";
        int id = (int) request.getSession().getAttribute("id");
        String fileName = upload(file,path,request);
//        String filename = file.getOriginalFilename();//不带路径
        System.out.println(fileName);
        baseInfoSerivce.uploadFile(fileName,id);

    }

    @RequestMapping("uploadIdentityPic")
    @ResponseBody
    void uploadIdentityFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        int user_id = (int) request.getSession().getAttribute("user_id");
        String path = "personalPic";
        int id = (int) request.getSession().getAttribute("id");
        String fileName = upload(file,path,request);
//        String filename = file.getOriginalFilename();//不带路径
        System.out.println(fileName);
        baseInfoSerivce.uploadIdentityFile(fileName,id);

    }

    @RequestMapping("getUserIdAndId")
    @ResponseBody
    Map getUserIdAndId(HttpServletRequest request){
        int user_id = (int) request.getSession().getAttribute("user_id");
        int id = (int) request.getSession().getAttribute("id");
        Map map = new HashMap();
        map.put("user_id",user_id);
        map.put("id",id);
        return map;
    }
}
