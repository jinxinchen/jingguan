package com.jingguan.abilityProject.dao.impl;

import com.jingguan.abilityProject.dao.AbilityProjectDao;
import com.jingguan.abilityProject.po.TAbilityProjectEntity;
import com.jingguan.baseInfo.po.TEducationExperienceEntity;
import com.jingguan.common.dao.impl.BaseDao;
import com.jingguan.common.vo.Page;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 陈 on 2017/11/30.
 */
@Repository("abilityProjectDao")
public class AbilityProjectDaoImpl extends BaseDao implements AbilityProjectDao {
    @Override
    public Page loadAbilityProject(Page page) {
        return listRecordsByCon(page, TAbilityProjectEntity.class);
    }

    @Override
    public int editAbilityProject(int user_id, TAbilityProjectEntity tAbilityProjectEntity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        //获取status
        Session session1 = getCurrentSession();
        Transaction transaction1 = session1.beginTransaction();
        TAbilityProjectEntity tAbilityProjectEntity1 = session1.load(TAbilityProjectEntity.class,tAbilityProjectEntity.getId());

        tAbilityProjectEntity.setUserId(user_id);
        tAbilityProjectEntity.setStatus(tAbilityProjectEntity1.getStatus());
        tAbilityProjectEntity.setUploadTime(tAbilityProjectEntity1.getUploadTime());
        tAbilityProjectEntity.setFileName(tAbilityProjectEntity1.getFileName());
        tAbilityProjectEntity.setPrizeEvidenceSrc(tAbilityProjectEntity1.getPrizeEvidenceSrc());
        try {
            session.update(tAbilityProjectEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public int addAbilityProject(int user_id, TAbilityProjectEntity tAbilityProjectEntity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        tAbilityProjectEntity.setUserId(user_id);
        tAbilityProjectEntity.setStatus("未审核");

        try {
            session.save(tAbilityProjectEntity);
            transaction.commit();

            return 200;
        }catch (Exception e){
            System.out.println(e);
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public int deleteAbilityProject(int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TAbilityProjectEntity tAbilityProjectEntity = session.load(TAbilityProjectEntity.class,id);
        try{
            session.delete(tAbilityProjectEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
        }

        return 0;
    }

    @Override
    public String getAbilityProjectSrcById(int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TAbilityProjectEntity tAbilityProjectEntity = session.load(TAbilityProjectEntity.class,id);
        System.out.println(tAbilityProjectEntity.getPrizeEvidenceSrc());
        return tAbilityProjectEntity.getPrizeEvidenceSrc();
    }

    @Override
    public void uploadFile(String fileName, String filename, int id, int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TAbilityProjectEntity tArticleEntity = session.load(TAbilityProjectEntity.class,id);
        System.out.println(fileName);
        tArticleEntity.setPrizeEvidenceSrc(fileName);
        tArticleEntity.setFileName(filename);

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tArticleEntity.setUploadTime(ft.format(dNow).toString());

        session.update(tArticleEntity);
        transaction.commit();
    }

    @Override
    public void inAbilityProject(TAbilityProjectEntity tAbilityProjectEntity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(tAbilityProjectEntity);
        transaction.commit();
    }
}
