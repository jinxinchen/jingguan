package com.jingguan.teacherMaterials.controller;

import com.jingguan.common.vo.Page;
import com.jingguan.common.vo.ResponseWrapper;
import com.jingguan.teacherMaterials.po.TTeachingMaterialEntity;
import com.jingguan.teacherMaterials.po.TimeMan;
import com.jingguan.teacherMaterials.service.MaterialsService;
import com.jingguan.uploadImage.controller.UploadImages;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhouliang on 2017/12/17 0017.
 */

@Controller
@RequestMapping("teacher/teacherMaterials")
public class MaterialsController extends UploadImages {



    @Resource(name = "materialsService")
    private MaterialsService materialsService;


    @RequestMapping(value = "listMaterials",method = RequestMethod.GET)
    @ResponseBody
    Page listRecords(HttpServletRequest request, Page page){
        try {
            String user_id = request.getSession().getAttribute("user_id").toString();
            materialsService.listRecordsByCondition(user_id,page);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return  page;
    }

    @RequestMapping(value = "listMaterialsDate",method = RequestMethod.GET)
    @ResponseBody
    Page listDateRecords(HttpServletRequest request, Page page){
        try {
            String user_id = request.getSession().getAttribute("user_id").toString();
            materialsService.listDateRecordsByCondition(user_id,page);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return  page;
    }

    @RequestMapping(value="deleteMaterials",method =RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper deleteRecord(HttpServletRequest request, String id){
        ResponseWrapper wrapper=new ResponseWrapper();
        wrapper.setSuccess(true);
        try {
            materialsService.deleteRecord(id,request.getSession().getServletContext().getRealPath("/"));
            wrapper.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return wrapper;
    }



    @RequestMapping(value="updateMaterials")
    @ResponseBody
    public ResponseWrapper updateRecord(HttpServletRequest request,
                                        String id,
                                        Integer userId,
                                        String courseName,
                                        String semester,
                                        String type,
                                        String file
                                        ){
        ResponseWrapper wrapper=new ResponseWrapper();
        wrapper.setSuccess(false);
        TTeachingMaterialEntity record=new TTeachingMaterialEntity();
        record.setId(Integer.valueOf(id.trim()));
        record.setCourseName(courseName);
        record.setSemester(semester);
        record.setUserId(userId);
        record.setType(type);
        record.setFile(file);
        try{
            materialsService.updateRecord(record);
            wrapper.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return wrapper;
    }


    @RequestMapping(value="saveMaterials",method = RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper saveRecord(HttpServletRequest request,
                                      Integer userId,
                                      String courseName,
                                      String semester,
                                      String type,
                                      String file
                                        ){
        ResponseWrapper wrapper=new ResponseWrapper();
        wrapper.setSuccess(false);
        TTeachingMaterialEntity record=new TTeachingMaterialEntity();
        record.setCourseName(courseName);
        record.setSemester(semester);
        record.setUserId(userId);
        record.setType(type);
        record.setFile(file);
        //封装一下

        try{
            String userid=request.getSession().getAttribute("user_id").toString();
            materialsService.saveRecord(userid,record);
            wrapper.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return wrapper;
    }

    @RequestMapping(value="uploadMaterials",method = RequestMethod.POST)
    @ResponseBody
    public String uploadRecord(@RequestParam("file") MultipartFile file,@RequestParam("select") String select, @RequestParam("id") String id, HttpServletRequest request){
        String  path=upload(file,request);
        TimeMan timeMan=materialsService.getTimeRecord(Integer.valueOf(id.trim()));
        TTeachingMaterialEntity tTeachingMaterialEntity=materialsService.getRecord(Integer.valueOf(id.trim()));

        int change=Integer.valueOf(select.trim());
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        timeMan.setFileTime(time);
        tTeachingMaterialEntity.setFile(path);
        /*
        switch (change){
            case 1:
                tTeachingMaterialEntity.setSynopsis(path);
                timeMan.setSynopsisTime(time);

                System.out.println("Sys");
                break;
            case 2:
                tTeachingMaterialEntity.setSchedule(path);
                timeMan.setScheduleTime(time);
                System.out.println("Sch");
                break;
            case 3:
                tTeachingMaterialEntity.setPpt(path);
                timeMan.setPptTime(time);
                System.out.println("Ppt");
                break;
            case 4:
                tTeachingMaterialEntity.setAttendanceSheet(path);
                timeMan.setAttendanceSheetTime(time);
                System.out.println("Att");
                break;
            default:break;
        }
        */

        materialsService.updateRecord(tTeachingMaterialEntity);
        materialsService.updateTimeRecord(timeMan);

        System.out.println(path);
        return path;
    }

    @RequestMapping(value = "downloadMaterials",method = RequestMethod.POST)
    @ResponseBody
    public String download(String id,String select){
        String path="";
        TTeachingMaterialEntity tTeachingMaterialEntity=materialsService.getRecord(Integer.valueOf(id.trim()));
        int selectp=Integer.valueOf(select.trim());
        /*switch (selectp){
            case 1:
                path=tTeachingMaterialEntity.getSynopsis();
                System.out.println("Sys");
                break;
            case 2:
                path=tTeachingMaterialEntity.getSchedule();
                System.out.println("Sch");
                break;
            case 3:
                path=tTeachingMaterialEntity.getPpt();
                System.out.println("Ppt");
                break;
            case 4:
                path=tTeachingMaterialEntity.getAttendanceSheet();
                System.out.println("Att");
                break;
            default:break;
        }
        */
        path=tTeachingMaterialEntity.getFile();
        return  path;
    }
}
