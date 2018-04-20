package com.jingguan.baseInfo.service.impl;

import com.jingguan.baseInfo.dao.WorkExpDao;
import com.jingguan.baseInfo.po.TWorkExperienceEntity;
import com.jingguan.baseInfo.service.WorkExpService;
import com.jingguan.common.vo.Page;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.jingguan.common.dao.impl.BaseDao.getCurrentSession;

/**
 * Created by 陈 on 2017/10/29.
 */
@Service("workExpService")
public class WorkExpServiceImpl implements WorkExpService{
    @Resource(name = "workExpDao")
    private WorkExpDao workExpDao;

    @Override
    public Page loadWorkExp(int user_id, Page page) {
        Page.FilterModel filterModel = new Page.FilterModel();
        filterModel.setGroupOp(Page.FilterModel.GROUP_AND);
        filterModel.getRules().add(new Page.FilterModel.Rule("userId", user_id, "eq"));
        page.addFilter(filterModel);
        return workExpDao.loadWorkExp(page);

    }

    @Override
    public void addWorkExp(TWorkExperienceEntity tWorkExperienceEntity, int user_id) {
        workExpDao.addWorkExp(tWorkExperienceEntity,user_id);
    }

    @Override
    public void editWorkExp(TWorkExperienceEntity tWorkExperienceEntity,int user_id) {
        workExpDao.editWorkExp(tWorkExperienceEntity,user_id);
    }

    @Override
    public void deleteWorkExp(int id) {
        workExpDao.deleteWorkExp(id);
    }

    @Override
    public void InWork(List<String[]> list, int user_id) {
        System.out.println("size:"+list.size());
        System.out.println(user_id);
        for(int i=0;i<list.size();i++){
            //排除重复导入
            Session session = getCurrentSession();
            Transaction transaction = session.beginTransaction();
            List<TWorkExperienceEntity> tWorkExperienceEntities = session.createCriteria(TWorkExperienceEntity.class).add(Restrictions.eq("userId",user_id)).add(Restrictions.eq("startTime",list.get(i)[5])).list();
            if(tWorkExperienceEntities.size() == 0){
                TWorkExperienceEntity TworkExperienceEntity = new TWorkExperienceEntity();
                String organization = list.get(i)[0];
                String professionalTitle = list.get(i)[1];
                String department = list.get(i)[2];
                String post = list.get(i)[3];
                String content = list.get(i)[4];
                String startTime = list.get(i)[5];
                String endTime = list.get(i)[6];
                TworkExperienceEntity.setOrganization(organization);
                TworkExperienceEntity.setProfessionalTitle(professionalTitle);
                TworkExperienceEntity.setDepartment(department);
                TworkExperienceEntity.setPost(post);
                TworkExperienceEntity.setContent(content);
                TworkExperienceEntity.setStartTime(startTime);
                TworkExperienceEntity.setEndTime(endTime);
                TworkExperienceEntity.setUserId(user_id);
                TworkExperienceEntity.setStatus(1);

                workExpDao.inWork(TworkExperienceEntity);
            }





        }
    }
}
