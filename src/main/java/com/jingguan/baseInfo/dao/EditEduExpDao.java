package com.jingguan.baseInfo.dao;

/**
 * Created by 陈 on 2017/10/18.
 */
public interface EditEduExpDao {
    int editEduExp(Integer user_id, String school, String major, String education, Integer graduationYear);
}