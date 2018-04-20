package com.jingguan.scientificResearchMaterials.controller;

import com.jingguan.common.vo.Page;
import com.jingguan.scientificResearchMaterials.po.TScientificresearchmaterialsEntity;
import com.jingguan.scientificResearchMaterials.service.MaterialService;
import com.jingguan.uploadImage.controller.uploadImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈 on 2017/12/10.
 */
@Controller
@RequestMapping("scientificResearchMaterials")
public class MaterialController extends uploadImage {

    @Resource(name = "materialService")
    private MaterialService materialService;

    @RequestMapping("loadMaterial")
    @ResponseBody
    Page<TScientificresearchmaterialsEntity> loadMaterial(HttpServletRequest request,Page<TScientificresearchmaterialsEntity> page){
        page = materialService.loadMaterial(page);
        return page;
    }

    @RequestMapping("uploadMaterial")
    @ResponseBody
    void uploadMaterial(@RequestParam("file") MultipartFile file, HttpServletRequest request, int id){
        String path = "scientificResearchMaterials";

        String fileName = upload(file,path,request);
        String filename = file.getOriginalFilename();//不带路径
        System.out.println(fileName);
        System.out.println(id);
        materialService.uploadFile(fileName,filename,id);
    }

    @RequestMapping("getMaterialSrcById")
    @ResponseBody
    String getMaterialSrcById(int id){
        return materialService.getMaterialSrcById(id);
    }

    @RequestMapping("addMaterial")
    @ResponseBody
    int addMaterial(HttpServletRequest request,String name){
        return materialService.addMaterial(name);
    }

    @RequestMapping("editMaterial")
    @ResponseBody
    int editMaterial(HttpServletRequest request,int id,String name){
//        System.out.println(name+id);
        return materialService.editMaterial(id,name);
    }

    @RequestMapping("deleteMaterial")
    @ResponseBody
    List deleteMaterial(HttpServletRequest request, int id){
        List list = new ArrayList();
        int status = materialService.deleteMaterial(id);
        if(status == 200){
            System.out.println("deletesucess");
            list.add(1);
            return list;
        }else{
            return null;
        }
    }


}
