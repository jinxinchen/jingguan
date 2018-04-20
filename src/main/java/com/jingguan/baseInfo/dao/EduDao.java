package com.jingguan.baseInfo.dao;

import com.jingguan.baseInfo.po.TEducationExperienceEntity;
import com.jingguan.common.vo.Page;

import java.util.List;

/**
 * Created by 陈 on 2017/10/17.
 */
public interface EduDao {
    List<TEducationExperienceEntity> loadEducationExp(int user_id);
    Page findEduExp(Page page);
    void inEdu(TEducationExperienceEntity tEducationExperienceEntity);
}
