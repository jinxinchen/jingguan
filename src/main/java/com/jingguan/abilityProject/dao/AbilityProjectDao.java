package com.jingguan.abilityProject.dao;

import com.jingguan.abilityProject.po.TAbilityProjectEntity;
import com.jingguan.common.vo.Page;

/**
 * Created by 陈 on 2017/11/18.
 */
public interface AbilityProjectDao {
    Page loadAbilityProject(Page page);
    int editAbilityProject(int user_id, TAbilityProjectEntity tAbilityProjectEntity);
    int addAbilityProject(int user_id, TAbilityProjectEntity tAbilityProjectEntity);
    int deleteAbilityProject(int id);
    String getAbilityProjectSrcById(int id);
    void uploadFile(String fileName, String filename, int id, int user_id);
    void inAbilityProject(TAbilityProjectEntity tAbilityProjectEntity);
}
