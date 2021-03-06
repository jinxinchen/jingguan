package com.jingguan.teacherActivityCheck.controller;

import com.jingguan.common.vo.Page;
import com.jingguan.common.vo.ResponseWrapper;
import com.jingguan.teacherActivityCheck.po.VTeachersActivityCheckEntity;
import com.jingguan.teacherActivityCheck.service.ActivityCheckService;
import com.jingguan.uploadExcel.controller.test2;
import jxl.write.WriteException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * Created by zhouliang on 2017/11/11 0011.
 */
@Controller
@RequestMapping("admin/teacherActivity")
public class ActivityCheckController extends test2 {

    @Resource(name="activityCheckService")
    ActivityCheckService activityCheckService;

    static Page.FilterModel condition;
    static List<VTeachersActivityCheckEntity> list;

    @RequestMapping(value = "listActivity",method = RequestMethod.GET)
    @ResponseBody
    Page listRecords(HttpServletRequest request,Page page){
        try {
            condition=page.getFilterModel();//为了下载做准备
            page=activityCheckService.listRecordsByConditions(page);

            list=page.getResult();          //判断有没有数据可以下载



        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  page;
    }

    @RequestMapping(value="updateActivity")
    @ResponseBody
    public ResponseWrapper updateRecord(HttpServletRequest request,String id,String status){

        //System.out.println(id);
        ResponseWrapper wrapper=new ResponseWrapper();
        wrapper.setSuccess(false);
        try {
            activityCheckService.updateRecord(id,status);
            wrapper.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return wrapper;
    }





    @RequestMapping(value = "downloadExcel")
    @ResponseBody
    public void requestExcelData(HttpServletRequest request,HttpServletResponse response) throws IOException,WriteException {

        request.setCharacterEncoding("UTF-8");
        //region 设置返回头文件,对于各个浏览器的兼容性
        String strFileName="activity.xls";// 默认Excel名称
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
        activityCheckService.getOutStream(os,condition);
        os.close();
        os.flush();


    }


    @RequestMapping(value = "requestUpload")
    @ResponseBody
    public String requestExcelData(HttpServletRequest request) {

        if(list==null||list.size()==0){
            return "false";
        }
        return "success";

    }

    @RequestMapping(value="check",method = RequestMethod.POST)
    @ResponseBody
    public  String check(HttpServletRequest request,String id){
        activityCheckService.check(id);
        return "success";

    }

    @RequestMapping("inActivity")
    @ResponseBody
    public int inActivity(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            System.out.println("startCopy");
            List<String[]> list = uploadExcelTest(request,file);
            activityCheckService.inActivity(list);
            return 200;

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
