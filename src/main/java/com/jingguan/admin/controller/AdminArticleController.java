package com.jingguan.admin.controller;

import com.jingguan.admin.po.VAdminArticleEntity;
import com.jingguan.admin.po.VAdminCopyrightEntity;
import com.jingguan.admin.service.AdminArticleService;
import com.jingguan.admin.service.AdminCopyRightService;
import com.jingguan.common.vo.Page;
import com.jingguan.uploadExcel.controller.test2;
import jxl.write.WriteException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈 on 2017/10/8.
 */
@Controller
@RequestMapping("adminArticle")
public class AdminArticleController extends test2{

    @Resource(name = "adminArticleService")
    private AdminArticleService adminArticleService;

    @Resource(name = "adminCopyRightService")
    private AdminCopyRightService adminCopyRightService;

    static Page.FilterModel Articlecondition;
    static List<VAdminArticleEntity> Articlelist;

    static Page.FilterModel CopyRightcondition;
    static List<VAdminCopyrightEntity> CopyRightlist;

    @RequestMapping("adminArticle")
    @ResponseBody
    Page<VAdminArticleEntity> loadAdminArticle(HttpServletRequest request,Page<VAdminArticleEntity> page){

        Articlecondition=page.getFilterModel();//为了 下载做准备
        page = adminArticleService.loadAdminArticle(page);
        Articlelist=page.getResult();          //判断有没有数据可以下载
        return page;

    }

    @RequestMapping("passArticle")
    @ResponseBody
    int passArticle(HttpServletRequest request,Integer aid){
        return adminArticleService.passArticle(aid);
    }


    @RequestMapping("adminCopyRight")
    @ResponseBody
    Page<VAdminCopyrightEntity> loadAdminCopyRight(HttpServletRequest request, Page<VAdminCopyrightEntity> page){
        CopyRightcondition=page.getFilterModel();//为了下载做准备
        page = adminCopyRightService.loadAdminCopyRight(page);
        CopyRightlist=page.getResult();          //判断有没有数据可以下载
        return page;

    }

    @RequestMapping("passCopyRight")
    @ResponseBody
    int passCopyRight(HttpServletRequest request,Integer cid){
        return adminCopyRightService.passCopyRight(cid);
    }

    @RequestMapping(value = "requestArticleUpload")
    @ResponseBody
    public String requestArticleExcelData(HttpServletRequest request) {

        System.out.println(123);
        if(Articlelist==null||Articlelist.size()==0){
            System.out.println("false");
            return "false";

        }
        return "success";

    }


    @RequestMapping("requestCopyRightUpload")
    @ResponseBody
    public String requestCopyRightExcelData(HttpServletRequest request) {

        if(CopyRightlist==null||CopyRightlist.size()==0){
            return "false";
        }
        return "success";

    }

    @RequestMapping("downloadArticleExcel")
    @ResponseBody
    public void downloadArticleExcel(HttpServletRequest request,HttpServletResponse response) throws IOException,WriteException {

        request.setCharacterEncoding("UTF-8");
        //region 设置返回头文件,对于各个浏览器的兼容性
        String strFileName="论文.xls";// 默认Excel名称
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
        adminArticleService.getOutStream(os,Articlecondition);
        os.close();
        os.flush();


    }

    @RequestMapping("downloadCopyRightExcel")
    @ResponseBody
    public void downloadCopyRightExcel(HttpServletRequest request,HttpServletResponse response) throws IOException,WriteException {

        request.setCharacterEncoding("UTF-8");
        //region 设置返回头文件,对于各个浏览器的兼容性
        String strFileName="著作.xls";// 默认Excel名称
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
        adminArticleService.getOutCopyRightStream(os,CopyRightcondition);
        os.close();
        os.flush();


    }

    @RequestMapping("inArticle")
    @ResponseBody
    public int inArticle(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            System.out.println("start");
            List<String[]> list = uploadExcelTest(request,file);
            adminArticleService.inArticle(list);
            return 200;

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping("inCopyRight")
    @ResponseBody
    public int inCopyRight(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            System.out.println("startCopy");
            List<String[]> list = uploadExcelTest(request,file);
            adminCopyRightService.inCopyRight(list);
            return 200;

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
