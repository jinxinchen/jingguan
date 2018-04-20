package com.jingguan.manageTeachers.service;

import com.jingguan.common.vo.Page;

import java.util.List;

/**
 * Created by 陈 on 2017/12/12.
 */
public interface ManageService {
    Page loadTeachers(Page page);
    Page loadRecord(Page page);
    void saveUserAccounts(List<String> accounts);
    void saveUserAccountByExcel(List<String[]> list);
    int editAccount(int id,String account);
    void deleteAccount(int id);
}
