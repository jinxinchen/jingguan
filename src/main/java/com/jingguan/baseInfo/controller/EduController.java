package com.jingguan.baseInfo.controller;

import com.jingguan.baseInfo.po.TEducationExperienceEntity;
import com.jingguan.baseInfo.service.EduService;
import com.jingguan.common.vo.Page;
import com.jingguan.uploadExcel.controller.test2;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 陈 on 2017/10/17.
 */
@Controller
@RequestMapping("education")
public class EduController extends test2 {

    @Resource(name = "EduService")
    private EduService eduService;

    @RequestMapping(value = "loadEduExp")
    @ResponseBody
    Page<TEducationExperienceEntity> loadEduExp(HttpServletRequest request, Page<TEducationExperienceEntity> page,String userId){
        int user_id;
        if(StringUtils.isEmpty(userId)){
            user_id = (int) request.getSession().getAttribute("user_id");
            System.out.println(1);
        }else{
            System.out.println(2);
            user_id = Integer.parseInt(userId);
        }
        page = eduService.searchFromId(page,user_id);

        if(page != null){
            return page;
        }else{
            return null;
        }


    }

    @RequestMapping(value = "addEduExp")
    @ResponseBody
    Map addEduExp(HttpServletRequest request, String userId,String school, String major, String education,String entrance ,String graduationYear){
        Map map = new HashMap();
        int user_id;
        if(StringUtils.isEmpty(userId)){
            user_id = (int) request.getSession().getAttribute("user_id");
            System.out.println(1);
        }else{
            System.out.println(2);
            user_id = Integer.parseInt(userId);
        }
        int status = eduService.addEduExp(user_id,school,major,education,entrance,graduationYear);
        if(status == 200){
            map.put("status",200);
            return map;
        }else{
            map.put("status",199);
            return map;
        }
    }

    @RequestMapping(value = "editEduExp")
    @ResponseBody
    Map editEduExp(HttpServletRequest request,String userId, int id, String school, String major, String education,String entrance ,String graduationYear){
        Map map = new HashMap();
        int user_id;
        if(StringUtils.isEmpty(userId)){
            user_id = (int) request.getSession().getAttribute("user_id");
            System.out.println(1);
        }else{
            System.out.println(2);
            user_id = Integer.parseInt(userId);
        }
        int status = eduService.editEduExp(id,user_id,school,major,education,entrance,graduationYear);
        if(status == 200){
            map.put("status",200);
            return map;
        }else{
            map.put("status",199);
            return map;
        }
    }

    @RequestMapping(value = "deleteEduExp")
    @ResponseBody
    List deleteEduExp(HttpServletRequest request, int id){
        List list = new ArrayList();
        int user_id = (int)request.getSession().getAttribute("user_id");
//        System.out.println("id==="+id);
        int status = eduService.deleteEduExp(id);
        if(status == 200){
            list.add(1);
            return list;
        }
        return null;
    }

    //导入模板
    @RequestMapping("inEdu")
    void in(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            System.out.println("start");
            List<String[]> list = uploadExcelTest(request,file);
            int user_id = (int)request.getSession().getAttribute("user_id");
            eduService.InEdu(list,user_id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
