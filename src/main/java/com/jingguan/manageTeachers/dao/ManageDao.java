package com.jingguan.manageTeachers.dao;

import com.jingguan.common.vo.Page;
import com.jingguan.manageTeachers.po.TUsersEntity;
import com.jingguan.manageTeachers.po.TUsersRoleEntity;

/**
 * Created by 陈 on 2017/12/12.
 */
public interface ManageDao {
    Page loadTeachers(Page page);
    Page loadRecord(Page page);
    void saveRecord(TUsersEntity user);
    void saveTeacherRole(TUsersRoleEntity userRole);
    int editAccount(int id,String account);
    void deleteAccount(int id);
}
