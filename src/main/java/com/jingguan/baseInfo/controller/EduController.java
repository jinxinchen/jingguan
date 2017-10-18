package com.jingguan.baseInfo.controller;

import com.jingguan.baseInfo.po.TEducationExperienceEntity;
import com.jingguan.baseInfo.service.EduService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 陈 on 2017/10/17.
 */
@Controller
@RequestMapping("education")
public class EduController {

    @Resource(name = "EduService")
    private EduService eduService;

    @RequestMapping("loadEduExp")
    @ResponseBody
    List<TEducationExperienceEntity> loadEduExp(HttpServletRequest request){
        int user_id = (int)request.getSession().getAttribute("user_id");
//        int user_id = 1;
        List<TEducationExperienceEntity> info = eduService.loadEduExp(user_id);
        if(info != null){
            return info;
        }else{
            return null;
        }


    }

    @RequestMapping("addEduExp")
    @ResponseBody
    Map addEduExp(HttpServletRequest request, String school, String major, String education, Integer graduationYear){
        Map map = new HashMap();
        int user_id = (int) request.getSession().getAttribute("user_id");
        int status = eduService.addEduExp(user_id,school,major,education,graduationYear);
        if(status == 200){
            map.put("status",200);
            return map;
        }else{
            map.put("status",199);
            return map;
        }
    }

    @RequestMapping("editEduExp")
    @ResponseBody
    Map editEduExp(HttpServletRequest request, String school, String major, String education, Integer graduationYear){
        Map map = new HashMap();
        int user_id = (int)request.getSession().getAttribute("user_id");
        int status = eduService.editEduExp(user_id,school,major,education,graduationYear);
        if(status == 200){
            map.put("status",200);
            return map;
        }else{
            map.put("status",199);
            return map;
        }
    }

    @RequestMapping("deleteEduExp")
    @ResponseBody
    Map deleteEduExp(HttpServletRequest request, Integer id){
        Map map = new HashMap();
        int user_id = (int)request.getSession().getAttribute("user_id");
        int status = eduService.deleteEduExp(id);
        if(status == 200){
            map.put("status",200);
            return map;
        }else{
            map.put("status",199);
            return map;
        }
    }
}
