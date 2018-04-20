package com.jingguan.teacherMaterialsCheck.service;

import com.jingguan.common.vo.Page;
import com.jingguan.teacherMaterialsCheck.po.VTeacherMaterialsEntity;

/**
 * Created by 陈 on 2017/12/10.
 */
public interface MaterialService {
    Page<VTeacherMaterialsEntity> loadMaterial(Page page);
    String getMaterialSrcById(int id);
}
