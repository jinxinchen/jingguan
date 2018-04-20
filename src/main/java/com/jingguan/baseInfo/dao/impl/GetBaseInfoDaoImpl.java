package com.jingguan.baseInfo.dao.impl;

import com.jingguan.baseInfo.dao.GetBaseInfoDao;
import com.jingguan.baseInfo.po.TTeacherBaseinfoEntity;
import com.jingguan.common.dao.impl.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 陈 on 2017/10/13.
 */
@Repository("GetBaseInfoDao")
public class GetBaseInfoDaoImpl extends BaseDao implements GetBaseInfoDao {

    @Override
    public List<TTeacherBaseinfoEntity> getBaseInfo(int user_id) {
        Session session=getCurrentSession();
        Transaction transaction=session.beginTransaction();

        List<TTeacherBaseinfoEntity> baseInfo = session.createCriteria(TTeacherBaseinfoEntity.class).add(Restrictions.eq("userId",user_id)).list();
//        System.out.println("info = " + baseInfo.size());
        return baseInfo;
    }
}
