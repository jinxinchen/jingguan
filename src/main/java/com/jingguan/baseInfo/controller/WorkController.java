package com.jingguan.baseInfo.controller;

import com.jingguan.baseInfo.po.TWorkExperienceEntity;
import com.jingguan.baseInfo.service.WorkExpService;
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
import java.util.List;

/**
 * Created by 陈 on 2017/10/29.
 */
@Controller
@RequestMapping("work")
public class WorkController extends test2 {

    @Resource(name = "workExpService")
    private WorkExpService workExpService;

    @RequestMapping(value = "loadWorkExp")
    @ResponseBody
    Page<TWorkExperienceEntity> loadWorkExp(HttpServletRequest request,Page<TWorkExperienceEntity> page,String userId){
        int user_id;
        if(StringUtils.isEmpty(userId)){
            user_id = (int) request.getSession().getAttribute("user_id");
            System.out.println(1);
        }else{
            System.out.println(2);
            user_id = Integer.parseInt(userId);
        }
        page = workExpService.loadWorkExp(user_id,page);
        if(page != null){
            return page;
        }else{
            return null;
        }
    }

    @RequestMapping("addWorkExp")
    @ResponseBody
    int addWorkExp(HttpServletRequest request, TWorkExperienceEntity tWorkExperienceEntity,String userId){
        int user_id;
        if(StringUtils.isEmpty(userId)){
            user_id = (int) request.getSession().getAttribute("user_id");
            System.out.println(1);
        }else{
            System.out.println(2);
            user_id = Integer.parseInt(userId);
        }
        workExpService.addWorkExp(tWorkExperienceEntity,user_id);
        return 0;
    }

    @RequestMapping("editWorkExp")
    @ResponseBody
    int editWorkExp(HttpServletRequest request,TWorkExperienceEntity tWorkExperienceEntity,String userId){
        int user_id;
        if(StringUtils.isEmpty(userId)){
            user_id = (int) request.getSession().getAttribute("user_id");
            System.out.println(1);
        }else{
            System.out.println(2);
            user_id = Integer.parseInt(userId);
        }
        workExpService.editWorkExp(tWorkExperienceEntity,user_id);
        return 0;
    }

    @RequestMapping("deleteWorkExp")
    @ResponseBody
    int deleteWorkExp(HttpServletRequest request,int id){
        workExpService.deleteWorkExp(id);
        return 0;
    }

    //导入模板
    @RequestMapping("inWork")
    void in(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            System.out.println("start");
            List<String[]> list = uploadExcelTest(request,file);
            int user_id = (int)request.getSession().getAttribute("user_id");
            workExpService.InWork(list,user_id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
