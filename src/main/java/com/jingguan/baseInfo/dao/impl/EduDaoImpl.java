package com.jingguan.baseInfo.dao.impl;

import com.jingguan.baseInfo.dao.EduDao;
import com.jingguan.baseInfo.po.TEducationExperienceEntity;
import com.jingguan.common.dao.impl.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 陈 on 2017/10/17.
 */
@Repository("EduDao")
public class EduDaoImpl extends BaseDao implements EduDao {
    @Override
    public List<TEducationExperienceEntity> loadEducationExp(int user_id) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        List<TEducationExperienceEntity> eduExp = session.createCriteria(TEducationExperienceEntity.class).add(Restrictions.eq("user_id",user_id)).list();
//        System.out.println(eduExp.get(1).getSchool().toString());
        return eduExp;
    }
}
