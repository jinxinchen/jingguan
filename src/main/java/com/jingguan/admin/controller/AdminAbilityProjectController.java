package com.jingguan.admin.controller;

import com.jingguan.admin.dao.AdminAbilityProjectDao;
import com.jingguan.admin.po.VAdminAbilityprojectEntity;
import com.jingguan.admin.service.AdminAbilityProjectService;
import com.jingguan.common.vo.Page;
import com.jingguan.uploadExcel.controller.test2;
import jxl.write.WriteException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 陈 on 2017/11/20.
 */
@Controller
@RequestMapping("adminAbilityProject")
public class AdminAbilityProjectController extends test2 {

    @Resource(name = "adminabilityProjectService")
    private AdminAbilityProjectService adminAbilityProjectService;

    static Page.FilterModel Abilityprojectcondition;
    static List<VAdminAbilityprojectEntity> Abilityprojectlist;

    @RequestMapping("loadAbilityProject")
    @ResponseBody
    Page<VAdminAbilityprojectEntity> loadAbilityProject(HttpServletRequest request,Page<VAdminAbilityprojectEntity> page){
        Abilityprojectcondition=page.getFilterModel();//为了 下载做准备
        page = adminAbilityProjectService.loadAbilityProject(page);
        Abilityprojectlist=page.getResult();          //判断有没有数据可以下载
        return page;
    }

    @RequestMapping("passAbilityProject")
    @ResponseBody
    int passAbilityProject(HttpServletRequest request,int id){
        return adminAbilityProjectService.passAbilityProject(id);
    }

    @RequestMapping("inAbilityProject")
    @ResponseBody
    public int inAbilityProject(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            System.out.println("start~~");
            List<String[]> list = uploadExcelTest(request,file);
            adminAbilityProjectService.inAbilityProject(list);
            return 200;

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping("requestAbilityprojectUpload")
    @ResponseBody
    public String requestAbilityprojectExcelData(HttpServletRequest request) {

        if(Abilityprojectlist==null||Abilityprojectlist.size()==0){
            return "false";
        }
        return "success";

    }

    @RequestMapping("downloadAbilityprojectExcel")
    @ResponseBody
    public void downloadAbilityprojectExcel(HttpServletRequest request,HttpServletResponse response) throws IOException,WriteException {

        request.setCharacterEncoding("UTF-8");
        //region 设置返回头文件,对于各个浏览器的兼容性
        String strFileName="Abilityproject.xls";// 默认Excel名称
        response.setContentType("application/octet-stream; charset=utf-8");

        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0){

            // firefox浏览器
            response.setHeader("Content-Disposition", "attachment; filename=" +  new String(strFileName.getBytes("UTF-8"), "ISO8859-1"));

        } else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){

            // IE浏览器
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(strFileName, "UTF-8"));

        }else{

            //其他浏览器
            response.setHeader("Content-disposition", "attachment;filename="+strFileName);
        }

        //设置返回的参数
        ServletOutputStream os=response.getOutputStream();
        adminAbilityProjectService.getOutAbilityProjectStream(os,Abilityprojectcondition);
        os.close();
        os.flush();


    }
}
