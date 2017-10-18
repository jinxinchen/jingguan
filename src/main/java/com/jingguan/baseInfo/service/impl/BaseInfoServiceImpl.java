package com.jingguan.baseInfo.service.impl;

import com.jingguan.baseInfo.dao.GetBaseInfoDao;
import com.jingguan.baseInfo.po.TTeacherBaseinfoEntity;
import com.jingguan.baseInfo.service.BaseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 陈 on 2017/10/13.
 */
@Service("baseInfoService")
public class BaseInfoServiceImpl implements BaseInfoService {
    @Resource(name="GetBaseInfoDao")
    private GetBaseInfoDao getBaseInfoDao;

    @Override
    public List<TTeacherBaseinfoEntity> getBaseInfo(int user_id) {
        List<TTeacherBaseinfoEntity> info = getBaseInfoDao.getBaseInfo(user_id);
        return info;
    }
}
