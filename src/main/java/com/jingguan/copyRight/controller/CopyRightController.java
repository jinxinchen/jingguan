package com.jingguan.copyRight.controller;

import com.jingguan.common.vo.Page;
import com.jingguan.copyRight.po.TCopyrightEntity;
import com.jingguan.copyRight.service.CopyRightService;
import com.jingguan.uploadExcel.controller.test2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by 陈 on 2017/11/12.
 */
@Controller
@RequestMapping("copyRight")
public class CopyRightController {

    @Resource(name = "copyRightService")
    private CopyRightService copyRightService;

    @RequestMapping("loadCopyRight")
    @ResponseBody
    Page<TCopyrightEntity> loadCopyRight(HttpServletRequest request,Page<TCopyrightEntity> page){
        int user_id = (int) request.getSession().getAttribute("user_id");
        page = copyRightService.loadCopyRight(user_id,page);
        return page;
    }

    @RequestMapping("addCopyRight")
    @ResponseBody
    int addCopyRight(HttpServletRequest request,TCopyrightEntity tCopyrightEntity){
        int user_id = (int) request.getSession().getAttribute("user_id");
        return copyRightService.addCopyRight(user_id,tCopyrightEntity);
    }

    @RequestMapping("editCopyRight")
    @ResponseBody
    int editCopyRight(HttpServletRequest request,TCopyrightEntity tCopyrightEntity){
        int user_id = (int) request.getSession().getAttribute("user_id");
        return copyRightService.editCopyRight(user_id,tCopyrightEntity);
    }


    @RequestMapping("deleteCopyRight")
    @ResponseBody
    int deleteCopyRight(HttpServletRequest request,int id){
        return copyRightService.deleteCopyRight(id);
    }

    //导入模板
    @RequestMapping("inCopyRight")
    void in(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            System.out.println("start");
            List<String[]> list = test2.uploadExcelTest(request,file);
            int user_id = (int)request.getSession().getAttribute("user_id");
            copyRightService.inCopyRight(list,user_id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
