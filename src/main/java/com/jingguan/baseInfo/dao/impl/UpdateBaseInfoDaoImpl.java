package com.jingguan.baseInfo.dao.impl;

import com.jingguan.baseInfo.dao.UpdateBaseInfoDao;
import com.jingguan.baseInfo.po.TTeacherBaseinfoEntity;
import com.jingguan.common.dao.impl.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 陈 on 2017/10/23.
 */
@Repository("updateBaseInfoDao")
public class UpdateBaseInfoDaoImpl extends BaseDao implements UpdateBaseInfoDao{
    @Override
    public int updateBaseInfo(TTeacherBaseinfoEntity tTeacherBaseinfoEntity,int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();


        TTeacherBaseinfoEntity baseInfo = session.load(TTeacherBaseinfoEntity.class,id);
        baseInfo.setName(tTeacherBaseinfoEntity.getName());
        baseInfo.setGender(tTeacherBaseinfoEntity.getGender());
        baseInfo.setBirthday(tTeacherBaseinfoEntity.getBirthday());
        baseInfo.setEmail(tTeacherBaseinfoEntity.getEmail());
        baseInfo.setIdentityNum(tTeacherBaseinfoEntity.getIdentityNum());
        baseInfo.setAddress(tTeacherBaseinfoEntity.getAddress());
        baseInfo.setPhoneNum(tTeacherBaseinfoEntity.getPhoneNum());
        baseInfo.setIsMoreLangue(tTeacherBaseinfoEntity.getIsMoreLangue());
        baseInfo.setDegree(tTeacherBaseinfoEntity.getDegree());
        baseInfo.setDegreeType(tTeacherBaseinfoEntity.getDegreeType());
        System.out.println(tTeacherBaseinfoEntity.getDegreeType());
        try{
            session.update(baseInfo);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public void uploadFile(String fileName, int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TTeacherBaseinfoEntity tTeacherBaseinfoEntity = session.load(TTeacherBaseinfoEntity.class,id);
        System.out.println("dao");
        System.out.println(fileName);
        tTeacherBaseinfoEntity.setPicture(fileName);

        session.update(tTeacherBaseinfoEntity);
        transaction.commit();
    }

    @Override
    public void uploadIdentityFile(String fileName, int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TTeacherBaseinfoEntity tTeacherBaseinfoEntity = session.load(TTeacherBaseinfoEntity.class,id);
        System.out.println("dao");
        System.out.println(fileName);
        tTeacherBaseinfoEntity.setIdentityPic(fileName);

        session.update(tTeacherBaseinfoEntity);
        transaction.commit();
    }
}
