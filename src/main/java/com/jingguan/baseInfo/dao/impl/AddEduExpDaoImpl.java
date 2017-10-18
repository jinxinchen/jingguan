package com.jingguan.baseInfo.dao.impl;

import com.jingguan.baseInfo.dao.AddEduExpDao;
import com.jingguan.baseInfo.po.TEducationExperienceEntity;
import com.jingguan.common.dao.impl.BaseDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 陈 on 2017/10/18.
 */
@Repository("addEduExpDao")
public class AddEduExpDaoImpl extends BaseDao implements AddEduExpDao{


    @Override
    public int addEduExp(Integer user_id, String school, String major, String education, Integer graduationYear) {
//        Map map = new HashMap();
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();

        TEducationExperienceEntity tEducationExperienceEntity = new TEducationExperienceEntity();
        tEducationExperienceEntity.setSchool(school);
        tEducationExperienceEntity.setMajor(major);
        tEducationExperienceEntity.setEducation(education);
        tEducationExperienceEntity.setGraduationYear(graduationYear);
        tEducationExperienceEntity.setUser_id(user_id);
        try {
            session.save(tEducationExperienceEntity);
            transaction.commit();
            return 200;
        }catch (Exception e){
            transaction.rollback();
        }
//        map.put("status",199);
        return 199;


    }
}
