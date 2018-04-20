package com.jingguan.scientificResearchMaterials.service.impl;

import com.jingguan.common.vo.Page;
import com.jingguan.scientificResearchMaterials.dao.MaterialDao;
import com.jingguan.scientificResearchMaterials.po.TScientificresearchmaterialsEntity;
import com.jingguan.scientificResearchMaterials.service.MaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 陈 on 2017/12/10.
 */
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    @Resource(name = "materialDao")
    private MaterialDao materialDao;


    @Override
    public Page<TScientificresearchmaterialsEntity> loadMaterial(Page page) {
        Page.FilterModel filterModel = new Page.FilterModel();
        filterModel.setGroupOp(Page.FilterModel.GROUP_AND);
        page.addFilter(filterModel);
        return materialDao.loadMaterial(page);
    }

    @Override
    public void uploadFile(String fileName, String filename, int id) {
        materialDao.uploadFile(fileName,filename,id);
    }

    @Override
    public String getMaterialSrcById(int id) {
        return materialDao.getMaterialSrcById(id);
    }

    @Override
    public int addMaterial(String name) {
        return materialDao.addMaterial(name);
    }

    @Override
    public int editMaterial(int id, String name) {
        return materialDao.editMaterial(id,name);
    }

    @Override
    public int deleteMaterial(int id) {
        return materialDao.deleteMaterial(id);
    }
}
