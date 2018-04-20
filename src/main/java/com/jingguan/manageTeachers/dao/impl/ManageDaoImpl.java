package com.jingguan.manageTeachers.dao.impl;

import com.jingguan.common.dao.impl.BaseDao;
import com.jingguan.common.dao.impl.UserDao;
import com.jingguan.common.vo.Page;
import com.jingguan.manageTeachers.dao.ManageDao;
import com.jingguan.manageTeachers.po.TUsersEntity;
import com.jingguan.manageTeachers.po.TUsersRoleEntity;
import com.jingguan.manageTeachers.po.VManageteachersBaseinfoEntity;
import com.jingguan.manageTeachers.po.VTeachersAccountsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 * Created by 陈 on 2017/12/12.
 */
@Repository("manageDao")
public class ManageDaoImpl extends BaseDao implements ManageDao {
    @Override
    public Page loadTeachers(Page page) {
        return listRecordsByCon(page, VManageteachersBaseinfoEntity.class);
    }

    @Override
    public Page loadRecord(Page page) {
        return listRecordsByCon(page, VTeachersAccountsEntity.class);
    }

    @Override
    public void saveRecord(TUsersEntity user) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);
        transaction.commit();
    }

    @Override
    public void saveTeacherRole(TUsersRoleEntity userRole) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.save(userRole);
        transaction.commit();
    }

    @Override
    public int editAccount(int id, String account) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TUsersEntity user = session.load(TUsersEntity.class,id);
        //如果account已经存在
        UserDao userDao = new UserDao();
        Integer user_id = userDao.findUserIdByUserAccount(account);
        if(user_id != null){
            return 201;
        }
        try{
            user.setAccount(account);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
            return 0;
        }


    }

    @Override
    public void deleteAccount(int id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try{
            TUsersEntity user = session.load(TUsersEntity.class,id);
            session.delete(user);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }

    }
}
