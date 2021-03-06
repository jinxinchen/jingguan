package com.jingguan.sciencePrize.controller;

import com.jingguan.common.vo.Page;
import com.jingguan.common.vo.ResponseWrapper;
import com.jingguan.sciencePrize.po.TEducateScientificEntity;
import com.jingguan.sciencePrize.service.SciencePrizeService;
import com.jingguan.uploadExcel.controller.test2;
import com.jingguan.uploadImage.controller.UploadImages;
import org.springframework.stereotype.Controller;
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
 * Created by zhouliang on 2017/11/18 0018.
 */
@Controller
@RequestMapping("teacher/sciencePrize")
public class SciencePrizeController extends UploadImages {

    @Resource(name = "sciencePrizeService")
    private SciencePrizeService sciencePrizeService;

    @RequestMapping(value = "listPrizeRecord",method = RequestMethod.GET)
    @ResponseBody
    Page listPrizeRecord(HttpServletRequest request, Page page){
        try {
            String user_id = request.getSession().getAttribute("user_id").toString();
            page=sciencePrizeService.listRecordsByCondition(user_id,page);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return  page;
    }

    @RequestMapping(value = "uploadEvidence",method = RequestMethod.POST)
    @ResponseBody
    String uploadEvidence(@RequestParam("file") MultipartFile file,@RequestParam("id") String id, HttpServletRequest request){
        String  path=upload(file,request);

        String filename = file.getOriginalFilename();//不带路径
        sciencePrizeService.updatePath(id,path,filename);
        return path;
    }
    @RequestMapping(value = "downloadEvidence",method = RequestMethod.POST)
    @ResponseBody
    String downloadEvidence(String id, HttpServletRequest request){
        String  path= sciencePrizeService.getPath(id);
        System.out.println(path);
        return path;
    }
    @RequestMapping(value="deletePrizeRecord",method =RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper deletePrizeRecord(HttpServletRequest request, String id){
        ResponseWrapper wrapper=new ResponseWrapper();
        wrapper.setSuccess(true);
        try {
            sciencePrizeService.deleteRecord(id);
            wrapper.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return wrapper;
    }



    @RequestMapping(value="updatePrizeRecord")
    @ResponseBody
    public ResponseWrapper updatePrizeRecord(HttpServletRequest request,
                                             String prizeName,
                                             String educateScientificName,
                                             String certifyNumber,
                                             String type,
                                             String prizeTime,
                                             String theUnit,
                                             String schoolName,
                                             String author,
                                             String membersList,
                                             String evidencePath,
                                             String others,
                                             String status,
                                             String id){
        ResponseWrapper wrapper=new ResponseWrapper();
        wrapper.setSuccess(false);
        //封装成class
        TEducateScientificEntity record=new TEducateScientificEntity();
        record.setEducateScientificName(evidencePath);
        record.setStatus(status);
        record.setPrizeName(prizeName);
        record.setEducateScientificName(educateScientificName);
        record.setType(type);
        record.setPrizeTime(prizeTime);
        record.setTheUnit(theUnit);
        record.setSchoolName(schoolName);
        record.setAuthor(author);
        record.setMembersList(membersList);
        record.setOthers(others);
        record.setCertifyNumber(certifyNumber);
        record.setId(Integer.valueOf(id.trim()));

        try{
            sciencePrizeService.updateRecord(record);
            wrapper.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return wrapper;
    }


    @RequestMapping(value="savePrizeRecord",method = RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper savePrizeRecord(HttpServletRequest request,
                                           String prizeName,
                                           String educateScientificName,
                                           String certifyNumber,
                                           String type,
                                           String prizeTime,
                                           String theUnit,
                                           String schoolName,
                                           String author,
                                           String membersList,
                                           String evidencePath,
                                           String others){
        ResponseWrapper wrapper=new ResponseWrapper();
        wrapper.setSuccess(false);

        //封装成class
        TEducateScientificEntity record=new TEducateScientificEntity();
        record.setPrizeName(prizeName);
        record.setEducateScientificName(educateScientificName);
        record.setType(type);
        record.setPrizeTime(prizeTime);
        record.setTheUnit(theUnit);
        record.setSchoolName(schoolName);
        record.setAuthor(author);
        record.setMembersList(membersList);
        record.setOthers(others);
        record.setCertifyNumber(certifyNumber);
        try{
            int userid=(int) request.getSession().getAttribute("user_id");
            sciencePrizeService.saveRecord(userid,record);
            wrapper.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return wrapper;
    }


    //导入模板
    @RequestMapping("inSciencePrizeTemp")
    void in(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            List<String[]> list = test2.uploadExcelTest(request,file);
            int user_id = (int)request.getSession().getAttribute("user_id");
            sciencePrizeService.inSciencePrizeTemp(list,user_id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
