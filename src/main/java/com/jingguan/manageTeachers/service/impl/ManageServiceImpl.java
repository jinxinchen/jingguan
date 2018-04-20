package com.jingguan.manageTeachers.service.impl;

import com.jingguan.common.dao.impl.UserDao;
import com.jingguan.common.vo.Page;
import com.jingguan.manageTeachers.dao.ManageDao;
import com.jingguan.manageTeachers.po.TUsersEntity;
import com.jingguan.manageTeachers.po.TUsersRoleEntity;
import com.jingguan.manageTeachers.service.ManageService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 陈 on 2017/12/12.
 */
@Service("manageService")
public class ManageServiceImpl extends UserDao implements ManageService {

    @Resource(name = "manageDao")
    private ManageDao manageDao;

    @Override
    public Page loadTeachers(Page page) {
        Page.FilterModel filterModel = new Page.FilterModel();
        filterModel.setGroupOp(Page.FilterModel.GROUP_AND);
        filterModel.getRules().add(new Page.FilterModel.Rule("roleId", 1, "eq"));
        page.addFilter(filterModel);
        return manageDao.loadTeachers(page);
    }

    @Override
    public Page loadRecord(Page page) {
        Page.FilterModel filterModel = new Page.FilterModel();
        filterModel.setGroupOp(Page.FilterModel.GROUP_AND);
        filterModel.getRules().add(new Page.FilterModel.Rule("roleId", 1, "eq"));
        page.addFilter(filterModel);
        return manageDao.loadRecord(page);
    }

    @Override
    public void saveUserAccounts(List<String> accounts) {
        for(int i=0;i<accounts.size();i++){
            String account = accounts.get(i);
            //如果数据库里面没有记录则保存
            Integer user_id = findUserIdByUserAccount(account);
            System.out.println("account" + user_id);
            if(user_id == null){
                TUsersEntity user = new TUsersEntity();
                user.setAccount(account);
                user.setPassword(account);
                user.setStatus(1);
                Date dnow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                user.setCreateTime(ft.format(dnow).toString());
                manageDao.saveRecord(user);
                //添加教师角色
                TUsersRoleEntity userRole = new TUsersRoleEntity();
                userRole.setUserId(user.getId());
                userRole.setRoleId(1);
                userRole.setStatus(1);
                manageDao.saveTeacherRole(userRole);

            }else{

            }
        }
    }

    @Override
    public void saveUserAccountByExcel(List<String[]> list) {
        System.out.println("size:"+list.size());
        for(int i=40;i<list.size();i++){
//            System.out.println(list.get(i)[0]);
            String account = list.get(i)[0];
            //如果数据库里面没有记录则保存
            Integer user_id = findUserIdByUserAccount(account);
            System.out.println("account" + user_id);
            if(user_id == null){
                TUsersEntity user = new TUsersEntity();
                user.setAccount(account);
                user.setPassword(account);
                user.setStatus(1);
                Date dnow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                user.setCreateTime(ft.format(dnow).toString());
                manageDao.saveRecord(user);
                //添加教师角色
                TUsersRoleEntity userRole = new TUsersRoleEntity();
                userRole.setUserId(user.getId());
                userRole.setRoleId(1);
                userRole.setStatus(1);
                manageDao.saveTeacherRole(userRole);

            }else{

            }





        }
    }

    @Override
    public int editAccount(int id, String account) {
        return manageDao.editAccount(id,account);
    }

    @Override
    public void deleteAccount(int id) {
        manageDao.deleteAccount(id);
    }
}
