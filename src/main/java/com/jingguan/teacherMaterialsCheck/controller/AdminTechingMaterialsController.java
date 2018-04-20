package com.jingguan.teacherMaterialsCheck.controller;

import com.jingguan.common.vo.Page;
import com.jingguan.teacherMaterialsCheck.po.VTeacherMaterialsEntity;
import com.jingguan.teacherMaterialsCheck.service.MaterialService;
import com.jingguan.uploadImage.controller.uploadImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 陈 on 2017/12/23.
 */
@Controller
@RequestMapping("admin/teachingMaterials")
public class AdminTechingMaterialsController extends uploadImage {


    @Resource(name = "materialAdminService")
    private MaterialService materialService;

    @RequestMapping("loadMaterial")
    @ResponseBody
    Page<VTeacherMaterialsEntity> loadMaterial(HttpServletRequest request, Page<VTeacherMaterialsEntity> page){
        page = materialService.loadMaterial(page);
        return page;
    }
/*
    @RequestMapping("uploadMaterial")
    @ResponseBody
    void uploadMaterial(@RequestParam("file") MultipartFile file, HttpServletRequest request, int id){
        String path = "adminTeachingMaterials";

        String fileName = upload(file,path,request);
        String filename = file.getOriginalFilename();//不带路径
        System.out.println(fileName);
        System.out.println(id);
        materialService.uploadFile(fileName,filename,id);
    }
*/


    @RequestMapping("getMaterialSrcById")
    @ResponseBody
    String getMaterialSrcById(int id){
        return materialService.getMaterialSrcById(id);
    }


}
