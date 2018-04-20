package com.jingguan.project.controller;

import com.jingguan.common.vo.Page;
import com.jingguan.common.vo.ResponseWrapper;
import com.jingguan.project.dao.UserIdDao;
import com.jingguan.project.po.TScientificEntity;
import com.jingguan.project.service.ProjectService;
import com.jingguan.uploadExcel.controller.test2;
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
 * Created by zhouliang on 2017/11/21 0021.
 */
@Controller
@RequestMapping("teacher/project")
public class ProjectController {

    @Resource(name = "projectService")
    private ProjectService projectService;
    @Resource(name = "userIdDao")
    private UserIdDao userIdDao;
    //有异常
    @RequestMapping(value = "listProjectRecord",method = RequestMethod.GET)
    @ResponseBody
    Page listRecords(HttpServletRequest request, Page page){
        try {
                String user_id = request.getSession().getAttribute("user_id").toString();
                page=projectService.listRecordsByCondition(user_id,page);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return  page;
    }

    //审核的
    @RequestMapping(value = "listCheckProjectRecord",method = RequestMethod.GET)
    @ResponseBody
    Page listCheckRecords(HttpServletRequest request, Page page){
        try {
            //String user_id = request.getSession().getAttribute("user_id").toString();
            page=projectService.listRecordsByCondition(page);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return  page;
    }

    @RequestMapping(value="deleteProjectRecord",method =RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper deleteRecord(HttpServletRequest request, String id){
        ResponseWrapper wrapper=new ResponseWrapper();
        wrapper.setSuccess(true);
        try {
            projectService.deleteRecord(id,request.getSession().getServletContext().getRealPath("/"));
            wrapper.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return wrapper;
    }



    @RequestMapping(value="updateProjectRecord")
    @ResponseBody
    public ResponseWrapper updateRecord(HttpServletRequest request,
                                        String id,
                                        String headName,
                                        String projectId,
                                        String userId,
                                        String scientificName,
                                        String scientificSource,
                                        String level,
                                        String createTime,
                                        String endTime,
                                        String isMarch,
                                        String type,
                                        String memberList,
                                        String grants,
                                        String createScientificEvidenceSrc,
                                        String createUpdateTime,
                                        String endScientificEvidenceSrc,
                                        String endUpdateTime,
                                        String others,
                                        String status){
        ResponseWrapper wrapper=new ResponseWrapper();
        wrapper.setSuccess(false);

        //封装成class
        TScientificEntity record=new TScientificEntity();
        record.setUserId(Integer.valueOf(request.getSession().getAttribute("user_id").toString()));
        record.setHeadName(headName);
        record.setProjectId(projectId);
        record.setStatus(status);
        record.setId(Integer.valueOf(id.trim()));
        record.setScientificName(scientificName);
        record.setScientificSource(scientificSource);
        record.setLevel(level);
        record.setCreateTime(createTime);
        record.setEndTime(endTime);
        record.setIsMarch(isMarch);
        record.setType(type);
        record.setMemberList(memberList);
        record.setGrants(grants);
        record.setCreateScientificEvidenceSrc(createScientificEvidenceSrc);
        record.setCreateUpdateTime(createUpdateTime);
        record.setCreateScientificEvidenceSrc(endScientificEvidenceSrc);
        record.setEndUpdateTime(endUpdateTime);
        record.setOthers(others);


        try{
            projectService.updateRecord(record);
            wrapper.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return wrapper;
    }


    @RequestMapping(value="saveProjectRecord",method = RequestMethod.POST)
    @ResponseBody
    public ResponseWrapper saveRecord(HttpServletRequest request,
                                      String id,
                                      String projectId,
                                      String scientificName,
                                      String scientificSource,
                                      String level,
                                      String createTime,
                                      String endTime,
                                      String isMarch,
                                      String type,
                                      String memberList,
                                      String grants,
                                      String createScientificEvidenceSrc,
                                      String createUpdateTime,
                                      String endScientificEvidenceSrc,
                                      String endUpdateTime,
                                      String others
                                     ){
        ResponseWrapper wrapper=new ResponseWrapper();
        wrapper.setSuccess(false);

        //封装成class
        TScientificEntity record=new TScientificEntity();
        record.setScientificName(scientificName);
        record.setScientificSource(scientificSource);
        record.setProjectId(projectId);
        record.setLevel(level);
        record.setCreateTime(createTime);
        record.setEndTime(endTime);
        record.setIsMarch(isMarch);
        record.setType(type);
        record.setMemberList(memberList);
        record.setGrants(grants);
        record.setCreateScientificEvidenceSrc(createScientificEvidenceSrc);
        record.setCreateUpdateTime(createUpdateTime);
        record.setCreateScientificEvidenceSrc(endScientificEvidenceSrc);
        record.setEndUpdateTime(endUpdateTime);
        record.setOthers(others);

        try{

            String user_id = request.getSession().getAttribute("user_id").toString();
            record.setUserId(Integer.valueOf(user_id.trim()));
            record.setHeadName(userIdDao.getNameByUserId(user_id.trim()));
            projectService.saveRecord(user_id,record);
            wrapper.setSuccess(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return wrapper;
    }
    @RequestMapping(value = "inProject",method = RequestMethod.POST)
    @ResponseBody
    public void in(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            int user_id = (int)request.getSession().getAttribute("user_id");
            List<String[]> list = test2.uploadExcelTest(request,file);

            projectService.inSciencePrizeTemp(list,user_id);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
