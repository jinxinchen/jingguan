package com.jingguan.scientificResearchMaterials.dao.impl;

import com.jingguan.common.dao.impl.BaseDao;
import com.jingguan.common.vo.Page;
import com.jingguan.scientificResearchMaterials.dao.MaterialDao;
import com.jingguan.scientificResearchMaterials.po.TScientificresearchmaterialsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 陈 on 2017/12/10.
 */
@Repository("materialDao")
public class MaterialDaoImpl extends BaseDao implements MaterialDao {
    @Override
    public Page loadMaterial(Page page) {
        return listRecordsByCon(page, TScientificresearchmaterialsEntity.class);
    }

    @Override
    public void uploadFile(String fileName, String filename, int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TScientificresearchmaterialsEntity tScientificresearchmaterialsEntity = session.load(TScientificresearchmaterialsEntity.class,id);
        System.out.println(fileName);
        tScientificresearchmaterialsEntity.setFileSrc(fileName);
        tScientificresearchmaterialsEntity.setFileName(filename);

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tScientificresearchmaterialsEntity.setTime(ft.format(dNow).toString());

        session.update(tScientificresearchmaterialsEntity);
        transaction.commit();
    }

    @Override
    public String getMaterialSrcById(int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TScientificresearchmaterialsEntity tScientificresearchmaterialsEntity = session.load(TScientificresearchmaterialsEntity.class,id);
        System.out.println(tScientificresearchmaterialsEntity.getFileSrc());
        return tScientificresearchmaterialsEntity.getFileSrc();
    }

    @Override
    public int addMaterial(String name) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TScientificresearchmaterialsEntity tScientificresearchmaterialsEntity = new TScientificresearchmaterialsEntity();
        tScientificresearchmaterialsEntity.setName(name);
        try{
            session.save(tScientificresearchmaterialsEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public int editMaterial(int id, String name) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        //获取status
        Session session1 = getCurrentSession();
        Transaction transaction1 = session1.beginTransaction();
        TScientificresearchmaterialsEntity tScientificresearchmaterialsEntity1 = session1.load(TScientificresearchmaterialsEntity.class,id);

        TScientificresearchmaterialsEntity tScientificresearchmaterialsEntity = session.load(TScientificresearchmaterialsEntity.class,id);
        tScientificresearchmaterialsEntity.setName(name);
        tScientificresearchmaterialsEntity.setFileSrc(tScientificresearchmaterialsEntity1.getFileSrc());
        tScientificresearchmaterialsEntity.setTime(tScientificresearchmaterialsEntity1.getTime());
        tScientificresearchmaterialsEntity.setFileName(tScientificresearchmaterialsEntity1.getFileName());

        try{
            session.update(tScientificresearchmaterialsEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public int deleteMaterial(int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try{
            TScientificresearchmaterialsEntity tScientificresearchmaterialsEntity = session.load(TScientificresearchmaterialsEntity.class,id);
            session.delete(tScientificresearchmaterialsEntity);
            transaction.commit();
            return 200;
        }catch (Exception E){
            transaction.rollback();
        }
        return 0;
    }
}
