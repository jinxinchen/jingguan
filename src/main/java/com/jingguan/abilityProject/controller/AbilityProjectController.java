package com.jingguan.abilityProject.controller;

import com.jingguan.abilityProject.po.TAbilityProjectEntity;
import com.jingguan.abilityProject.service.AbilityProjectService;
import com.jingguan.common.vo.Page;
import com.jingguan.download.downLoadController;
import com.jingguan.uploadExcel.controller.test2;
import com.jingguan.uploadImage.controller.uploadImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 陈 on 2017/11/18.
 */
@Controller
@RequestMapping("abilityProject")
public class AbilityProjectController extends uploadImage {

    @Resource(name = "abilityProjectService")
    private AbilityProjectService abilityProjectService;

    @RequestMapping("loadAbilityProject")
    @ResponseBody
    Page<TAbilityProjectEntity> loadAbilityProject(HttpServletRequest request,Page<TAbilityProjectEntity> page){
        int user_id = (int) request.getSession().getAttribute("user_id");
        return abilityProjectService.loadAbilityProject(user_id,page);
    }

    @RequestMapping("editAbilityProject")
    @ResponseBody
    int editAbilityProject(HttpServletRequest request,TAbilityProjectEntity tAbilityProjectEntity){
        int user_id = (int) request.getSession().getAttribute("user_id");

        return abilityProjectService.editAbilityProject(user_id,tAbilityProjectEntity);
    }

    @RequestMapping("addAbilityProject")
    @ResponseBody
    int addAbilityProject(HttpServletRequest request,TAbilityProjectEntity tAbilityProjectEntity){
        int user_id = (int) request.getSession().getAttribute("user_id");
        return abilityProjectService.addAbilityProject(user_id,tAbilityProjectEntity);
    }

    @RequestMapping("deleteAbilityProject")
    @ResponseBody
    int deleteAbilityProject(HttpServletRequest request,int id){
        return abilityProjectService.deleteAbilityProject(id);
    }

    @RequestMapping("getAbilityProjectSrcById")
    @ResponseBody
    String getAbilityProjectSrcById(int id,HttpServletRequest request){
        return abilityProjectService.getAbilityProjectSrcById(id);
    }

    @RequestMapping("uploadAbility")
    @ResponseBody
    void uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, int id){
        int user_id = (int) request.getSession().getAttribute("user_id");
        String path = "abilityProject";

        String fileName = upload(file,path,request);
        String filename = file.getOriginalFilename();//不带路径
        System.out.println(fileName);
        System.out.println(id);
        abilityProjectService.uploadFile(fileName,filename,id,user_id);

    }

    //下载文件
    @RequestMapping("downLoadAbility")
    @ResponseBody
    void downLoadArticle(@RequestParam("fileName")String fileName, HttpServletRequest request, HttpServletResponse response){
        String path = "abilityProject";
        System.out.println("@@@"+fileName);
        downLoadController downLoadController = new downLoadController();
        downLoadController.downLoad(fileName,path,request,response);

    }


    //导入模板
    @RequestMapping("inAbilityProject")
    void in(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            System.out.println("start");
            List<String[]> list = test2.uploadExcelTest(request,file);
            int user_id = (int)request.getSession().getAttribute("user_id");
            abilityProjectService.inAbilityProject(list,user_id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取user_id
    @RequestMapping("getUserIdInAbility")
    @ResponseBody
    Map getUserId(HttpServletRequest request){
        int user_id = (int) request.getSession().getAttribute("user_id");
        Map map = new HashMap();
        System.out.println(user_id);
        map.put("user_id",user_id);
        return map;
    }
}
