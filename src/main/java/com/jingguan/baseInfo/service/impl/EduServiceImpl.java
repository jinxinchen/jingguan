package com.jingguan.baseInfo.service.impl;

import com.jingguan.baseInfo.dao.AddEduExpDao;
import com.jingguan.baseInfo.dao.DeleteEduExpDao;
import com.jingguan.baseInfo.dao.EditEduExpDao;
import com.jingguan.baseInfo.dao.EduDao;
import com.jingguan.baseInfo.dao.impl.EditEduExpDaoImpl;
import com.jingguan.baseInfo.po.TEducationExperienceEntity;
import com.jingguan.baseInfo.service.EduService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by 陈 on 2017/10/17.
 */
@Service("EduService")
public class EduServiceImpl implements EduService {

    @Resource(name = "EduDao")
    private EduDao eduDao;

    @Resource(name = "addEduExpDao")
    private AddEduExpDao addEduExpDao;

    @Resource(name = "editEduExpDao")
    private EditEduExpDao editEduExpDao;

    @Resource(name = "deleteEduExpDao")
    private DeleteEduExpDao deleteEduExpDao;

    @Override
    public List<TEducationExperienceEntity> loadEduExp(int user_id) {
        return eduDao.loadEducationExp(user_id);
    }

    @Override
    public int addEduExp(Integer user_id, String school, String major, String education, Integer graduationYear) {

        return addEduExpDao.addEduExp(user_id,school,major,education,graduationYear);
    }

    @Override
    public int editEduExp(int id,Integer user_id, String school, String major, String education, Integer graduationYear) {

        return editEduExpDao.editEduExp(id,user_id,school,major,education,graduationYear);
    }

    @Override
    public int deleteEduExp(Integer user_id) {

        return deleteEduExpDao.deleteEduExp(user_id);
    }

}
