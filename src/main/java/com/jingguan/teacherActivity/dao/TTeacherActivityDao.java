package com.jingguan.teacherActivity.dao;

import com.jingguan.common.vo.Page;
import com.jingguan.teacherActivity.po.TTeacherActivityEntity;

/**
 * Created by zhouliang on 2017/11/13 0013.
 */
public interface TTeacherActivityDao {

    public Page<TTeacherActivityEntity> listRecordsByCondition(Page page);
    public  void saveRecords(TTeacherActivityEntity tTeacherActivityEntity);

    public  void updateRecords(TTeacherActivityEntity tTeacherActivityEntity);

    public  void deleteRecords(TTeacherActivityEntity tTeacherActivityEntity);

    public TTeacherActivityEntity getRecords(Integer id);
}
