package com.jingguan.project.dao;

import com.jingguan.common.vo.Page;
import com.jingguan.project.po.TScientificEntity;

/**
 * Created by zhouliang on 2017/11/21 0021.
 */
public interface TScientificEntityDao {

    public  int saveRecords(TScientificEntity record);

    public  void updateRecords(TScientificEntity record);

    public  void deleteRecords(TScientificEntity record);

    public TScientificEntity getRecords(Integer id);
    public Page<TScientificEntity> listRecordsByCondition(Page page);


}
