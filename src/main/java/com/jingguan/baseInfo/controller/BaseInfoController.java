package com.jingguan.baseInfo.controller;

import com.jingguan.baseInfo.po.TTeacherBaseinfoEntity;
import com.jingguan.baseInfo.service.BaseInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 陈 on 2017/10/13.
 */
@Controller
@RequestMapping("baseInfo")
public class BaseInfoController {
    @Resource(name = "baseInfoService")
    private BaseInfoService baseInfoSerivce;

    @RequestMapping("getBaseInfo")
    @ResponseBody
    List<TTeacherBaseinfoEntity> info(HttpServletRequest request){
        int user_id = 1;
        List<TTeacherBaseinfoEntity> info = baseInfoSerivce.getBaseInfo(user_id);
        System.out.println(info);
        return info;
    }
}
