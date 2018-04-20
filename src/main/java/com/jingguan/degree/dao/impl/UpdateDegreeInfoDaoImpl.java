package com.jingguan.degree.dao.impl;

import com.jingguan.baseInfo.dao.UpdateBaseInfoDao;
import com.jingguan.common.dao.impl.BaseDao;
import com.jingguan.degree.dao.UpdateDegreeInfoDao;
import com.jingguan.degree.po.TEducateDegreeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 陈 on 2017/11/10.
 */
@Repository("updateDegreeInfo")
public class UpdateDegreeInfoDaoImpl extends BaseDao implements UpdateDegreeInfoDao{
    @Override
    public int updateDegreeInfo(int user_id,int degree_id, TEducateDegreeEntity tEducateDegreeEntity) {
        System.out.println(123);
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        tEducateDegreeEntity.setId(degree_id);

        tEducateDegreeEntity.setUserId(user_id);

        //获取status
        Session session1 = getCurrentSession();
        Transaction transaction1 = session1.beginTransaction();
        TEducateDegreeEntity tEducateDegreeEntity1 = session1.load(TEducateDegreeEntity.class,degree_id);
        tEducateDegreeEntity.setEducateDegreeSrc(tEducateDegreeEntity1.getEducateDegreeSrc());
        tEducateDegreeEntity.setFileName(tEducateDegreeEntity1.getFileName());
        tEducateDegreeEntity.setMentorFileName(tEducateDegreeEntity1.getMentorFileName());
        tEducateDegreeEntity.setMentorEvidenceSrc(tEducateDegreeEntity1.getMentorEvidenceSrc());

        try{
            session.update(tEducateDegreeEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            System.out.println("###" + tEducateDegreeEntity.getEducateDegreeSrc());
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public void uploadFile(String fileName, String filename, int id, int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TEducateDegreeEntity tEducateDegreeEntity = session.load(TEducateDegreeEntity.class,id);
        System.out.println(fileName);
        tEducateDegreeEntity.setEducateDegreeSrc(fileName);
        tEducateDegreeEntity.setFileName(filename);

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tEducateDegreeEntity.setUploadTime(ft.format(dNow).toString());

        session.update(tEducateDegreeEntity);
        transaction.commit();
    }

    @Override
    public void uploadMentorFile(String fileName, String filename, int id, int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TEducateDegreeEntity tEducateDegreeEntity = session.load(TEducateDegreeEntity.class,id);
        System.out.println(fileName);
        tEducateDegreeEntity.setMentorEvidenceSrc(fileName);
        tEducateDegreeEntity.setMentorFileName(filename);

//        Date dNow = new Date();
//        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        tEducateDegreeEntity.setMentorUploadTime(ft.format(dNow).toString());

        session.update(tEducateDegreeEntity);
        transaction.commit();
    }

    @Override
    public String getDegreeSrcById(int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TEducateDegreeEntity tEducateDegreeEntity = session.load(TEducateDegreeEntity.class,id);
        return tEducateDegreeEntity.getEducateDegreeSrc();
    }

    @Override
    public String getDegreeMentorSrcById(int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TEducateDegreeEntity tEducateDegreeEntity = session.load(TEducateDegreeEntity.class,id);
        return tEducateDegreeEntity.getMentorEvidenceSrc();
    }
}
