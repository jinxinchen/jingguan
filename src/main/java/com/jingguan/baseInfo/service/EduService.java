package com.jingguan.baseInfo.service;

import com.jingguan.baseInfo.po.TEducationExperienceEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 陈 on 2017/10/17.
 */
public interface EduService {
    List<TEducationExperienceEntity> loadEduExp(int user_id);
    int addEduExp(Integer user_id, String school, String major, String education, Integer graduationYear);
    int editEduExp(Integer user_id, String school, String major, String education, Integer graduationYear);
    int deleteEduExp(Integer id);
}
