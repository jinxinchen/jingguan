package com.jingguan.baseInfo.service;

import com.jingguan.baseInfo.po.TEducationExperienceEntity;
import com.jingguan.common.vo.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by 陈 on 2017/10/17.
 */
public interface EduService {
    List<TEducationExperienceEntity> loadEduExp(int user_id);
    int addEduExp(Integer user_id, String school, String major, String education, String entrance,String graduationYear);
    int editEduExp(int id,Integer user_id, String school, String major, String education,String entrance ,String graduationYear);
    int deleteEduExp(Integer id);
    Page<TEducationExperienceEntity> searchFromId(Page page,int user_id);
    void InEdu(List<String[]> list,int user_id);
}
