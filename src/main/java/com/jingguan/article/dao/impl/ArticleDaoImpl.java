package com.jingguan.article.dao.impl;

import com.jingguan.article.dao.ArticleDao;
import com.jingguan.article.po.TArticleEntity;
import com.jingguan.common.dao.impl.BaseDao;
import com.jingguan.common.vo.Page;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 陈 on 2017/11/11.
 */
@Repository("articleDao")
public class ArticleDaoImpl extends BaseDao implements ArticleDao {
    @Override
    public Page loadArticle(Page page) {
        return listRecordsByCon(page, TArticleEntity.class);
    }

    @Override
    public int addArticle(int user_id, TArticleEntity tArticleEntity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        tArticleEntity.setUserId(user_id);
        tArticleEntity.setStatus("未审核");
        try{
            session.save(tArticleEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public int editArticle(int user_id, TArticleEntity tArticleEntity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //获取status
        Session session1 = getCurrentSession();
        Transaction transaction1 = session1.beginTransaction();
        TArticleEntity tArticleEntity1 = session1.load(TArticleEntity.class,tArticleEntity.getId());


        System.out.println(tArticleEntity.getPeriods());
        try {
            tArticleEntity.setStatus(tArticleEntity1.getStatus());
            tArticleEntity.setUploadTime(tArticleEntity1.getUploadTime());
            tArticleEntity.setFileName(tArticleEntity1.getFileName());
            tArticleEntity.setArticleSrc(tArticleEntity1.getArticleSrc());
            tArticleEntity.setUserId(user_id);

            session.update(tArticleEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            System.out.println("wrong");
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public int deleteArticle(int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try{
            TArticleEntity tArticleEntity = session.load(TArticleEntity.class,id);
            session.delete(tArticleEntity);
            transaction.commit();
            return 200;
        }catch (Exception E){
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public void uploadFile(String fileName, String filename, int id, int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TArticleEntity tArticleEntity = session.load(TArticleEntity.class,id);
        System.out.println(fileName);
        tArticleEntity.setArticleSrc(fileName);
        tArticleEntity.setFileName(filename);

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tArticleEntity.setUploadTime(ft.format(dNow).toString());

        session.update(tArticleEntity);
        transaction.commit();

    }

    @Override
    public String getArticleSrcById(int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TArticleEntity tArticleEntity = session.load(TArticleEntity.class,id);
        System.out.println(tArticleEntity.getArticleSrc());
        return tArticleEntity.getArticleSrc();
    }

    @Override
    public void inArticle(TArticleEntity tArticleEntity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(tArticleEntity);
        transaction.commit();
    }
}
