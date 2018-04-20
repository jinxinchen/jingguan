package com.jingguan.teacherMaterialsCheck.service.impl;


import com.jingguan.common.vo.Page;
import com.jingguan.teacherMaterialsCheck.dao.MaterialDao;
import com.jingguan.teacherMaterialsCheck.po.VTeacherMaterialsEntity;
import com.jingguan.teacherMaterialsCheck.service.MaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhouliang on 2018/2/9 0009.
 */
@Service("materialAdminService")
public class MaterialServiceImpl implements MaterialService {

    @Resource(name="materialAdminDao")
    private MaterialDao materialDao;

    @Override
    public Page<VTeacherMaterialsEntity> loadMaterial(Page page) {
        return materialDao.loadMaterial(page);
    }



    @Override
    public String getMaterialSrcById(int id) {
        return materialDao.getMaterialSrcById(id);
    }
}
