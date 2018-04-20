package com.jingguan.article.controller;

import com.jingguan.article.po.TArticleEntity;
import com.jingguan.article.service.ArticleService;
import com.jingguan.common.vo.Page;
import com.jingguan.uploadExcel.controller.test2;
import com.jingguan.uploadImage.controller.uploadImage;
import com.jingguan.download.downLoadController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 陈 on 2017/11/11.
 */
@Controller
@RequestMapping("article")
public class ArticleController extends uploadImage {

    @Resource(name = "articleService")
    private ArticleService articleService;


    @RequestMapping("loadArticle")
    @ResponseBody
    Page<TArticleEntity> loadArticle(HttpServletRequest request, Page<TArticleEntity> page){
        int user_id = (int) request.getSession().getAttribute("user_id");
        page = articleService.loadArticle(page,user_id);
        return page;
    }

    @RequestMapping("addArticle")
    @ResponseBody
    int addArticle(HttpServletRequest request, TArticleEntity tArticleEntity){
        int user_id = (int) request.getSession().getAttribute("user_id");
        System.out.println("addd"+tArticleEntity.getArticleName());
        int status = articleService.addArticle(user_id,tArticleEntity);

        if(status == 200){
            return 200;
        }else{
            return 0;
        }
    }

    @RequestMapping("editArticle")
    @ResponseBody
    int editArticle(HttpServletRequest request, TArticleEntity tArticleEntity){
        int user_id = (int) request.getSession().getAttribute("user_id");
        int res = articleService.editArticle(user_id,tArticleEntity);
        if(res == 200){
            return 200;
        }else{
            return 0;
        }
    }

    @RequestMapping("deleteArticle")
    @ResponseBody
    List deleteWorkExp(HttpServletRequest request,int id){
        List list = new ArrayList();
        int status = articleService.deleteArticle(id);
        if(status == 200){
            System.out.println("deletesucess");
            list.add(1);
            return list;
        }else{
            return null;
        }
    }


    //上传文件
    @RequestMapping("uploadArticle")
    @ResponseBody
    void uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request,int id){
        int user_id = (int) request.getSession().getAttribute("user_id");
        String path = "article";

        String fileName = upload(file,path,request);
        String filename = file.getOriginalFilename();//不带路径
        System.out.println(fileName);
        System.out.println(id);
        articleService.uploadFile(fileName,filename,id,user_id);

    }


    //下载文件
    @RequestMapping("downLoadArticle")
    @ResponseBody
    void downLoadArticle(@RequestParam("fileName")String fileName, HttpServletRequest request, HttpServletResponse response){
        String path = "imageUploadFile";
        System.out.println("@@@"+fileName);
        downLoadController downLoadController = new downLoadController();
        downLoadController.downLoad(fileName,path,request,response);

    }


    //获取该记录文件路径
    @RequestMapping("getArticleSrcById")
    @ResponseBody
    String getArticleSrcById(int id,HttpServletRequest request){
        return articleService.getArticleSrcById(id);
    }

    //导入模板
    @RequestMapping("inArticle")
    void in(HttpServletRequest request, @RequestParam(required = false) MultipartFile file){
        try {
            System.out.println("start");
            List<String[]> list = test2.uploadExcelTest(request,file);
            int user_id = (int)request.getSession().getAttribute("user_id");
            articleService.InArticle(list,user_id);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //获取user_id
    @RequestMapping("getUserIdInArticle")
    @ResponseBody
    Map getUserId(HttpServletRequest request){
        int user_id = (int) request.getSession().getAttribute("user_id");
        Map map = new HashMap();
        map.put("user_id",user_id);
        return map;
    }

}
