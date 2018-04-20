package com.jingguan.copyRight.dao.impl;

import com.jingguan.article.po.TArticleEntity;
import com.jingguan.common.dao.impl.BaseDao;
import com.jingguan.common.vo.Page;
import com.jingguan.copyRight.dao.CopyRightDao;
import com.jingguan.copyRight.po.TCopyrightEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 * Created by 陈 on 2017/11/12.
 */
@Repository("copyRightDao")
public class CopyRightDaoImpl extends BaseDao implements CopyRightDao{
    @Override
    public Page loadCopyRight(Page page) {
        return listRecordsByCon(page, TCopyrightEntity.class);
    }

    @Override
    public int addCopyRight(int user_id, TCopyrightEntity tCopyrightEntity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        tCopyrightEntity.setuser_id(user_id);
        tCopyrightEntity.setStatus("未审核");
        try{
            session.save(tCopyrightEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public int editCopyRight(int user_id, TCopyrightEntity tCopyrightEntity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        tCopyrightEntity.setuser_id(user_id);
        try{
            session.update(tCopyrightEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public int deleteCopyRight(int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TCopyrightEntity tCopyrightEntity = session.load(TCopyrightEntity.class,id);
        try{
            session.delete(tCopyrightEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public void inCopyRight(TCopyrightEntity tCopyrightEntity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(tCopyrightEntity);
        transaction.commit();
    }
}
