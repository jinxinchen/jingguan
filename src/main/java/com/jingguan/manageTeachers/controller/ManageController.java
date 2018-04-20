package com.jingguan.manageTeachers.controller;

import com.jingguan.common.vo.Page;
import com.jingguan.manageTeachers.po.TUsersEntity;
import com.jingguan.manageTeachers.po.VManageteachersBaseinfoEntity;
import com.jingguan.manageTeachers.service.ManageService;
import com.jingguan.uploadExcel.controller.test2;
import com.jingguan.uploadImage.controller.uploadImage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈 on 2017/12/11.
 */
@Controller
@RequestMapping("manageTeachers")
public class ManageController extends uploadImage {

    @Resource(name = "manageService")
    private ManageService manageService;

    @RequestMapping("loadTeachers")
    @ResponseBody
    Page<VManageteachersBaseinfoEntity> loadTeachers(HttpServletRequest request,Page<VManageteachersBaseinfoEntity
            > page){
        System.out.println("here");
        return manageService.loadTeachers(page);
    }

    @RequestMapping("loadRecord")
    @ResponseBody

    Page<TUsersEntity> loadRecord(HttpServletRequest request,Page<TUsersEntity> page){
        return manageService.loadRecord(page);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public int uploadUserAccount(HttpServletRequest request, @RequestParam(required = false) MultipartFile file) {


        try {
            List<String[]> list = test2.uploadExcelTest(request,file);
            manageService.saveUserAccountByExcel(list);

            return 200;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    @RequestMapping("editAccount")
    @ResponseBody
    public int editAccount(HttpServletRequest request,int id,String account){
        int status = manageService.editAccount(id,account);
        return status;
    }

    @RequestMapping("deleteAccount")
    @ResponseBody
    public int deleteAccount(HttpServletRequest request,int id){
        manageService.deleteAccount(id);
        return 200;
    }

    @RequestMapping("addRecord")
    @ResponseBody

    public void addAccount(String account,HttpServletRequest request){
        List<String> accounts = new ArrayList<>();
        accounts.add(account);
        manageService.saveUserAccounts(accounts);
    }



}

