package com.jingguan.sciencePrize.dao;

import com.jingguan.common.vo.Page;
import com.jingguan.sciencePrize.po.TEducateScientificEntity;

/**
 * Created by zhouliang on 2017/11/18 0018.
 */
public interface TEducateScientificEntityDao {

    public Page<TEducateScientificEntity> listRecordsByCondition(Page page);

    public  void saveRecords(TEducateScientificEntity record);

    public  void updateRecords(TEducateScientificEntity record);

    public  void deleteRecords(TEducateScientificEntity record);

    public TEducateScientificEntity getRecords(Integer id);

    void inSciencePrizeTemp(TEducateScientificEntity tEducateScientificEntity);

}
