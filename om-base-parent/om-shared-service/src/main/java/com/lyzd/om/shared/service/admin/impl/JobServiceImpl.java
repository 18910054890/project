package com.lyzd.om.shared.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyzd.om.shared.dao.admin.JobDao;
import com.lyzd.om.shared.dao.admin.UserJobDao;
import com.lyzd.om.shared.dto.admin.JobQueryDto;
import com.lyzd.om.shared.entity.admin.MyJob;
import com.lyzd.om.shared.exception.MyException;
import com.lyzd.om.shared.service.admin.JobService;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.spring.common.dto.ResultCode;
import com.lyzd.om.spring.common.utils.UserConstants;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;



/**
 * @author Thinker
 *
 */
@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private UserJobDao userJobDao;

    @Override
    public Result<MyJob> getJobAll(Integer offectPosition, Integer limit, JobQueryDto jobQueryDto) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<MyJob> fuzzyJob = jobDao.getFuzzyJob(jobQueryDto.getQueryName(), jobQueryDto.getQueryStatus());
        return Result.ok().count(page.getTotal()).data(fuzzyJob).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    public int insertJob(MyJob job) {
        return jobDao.insertDept(job);
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param job 岗位信息
     * @return 结果
     */
    @Override
    public String checkJobNameUnique(MyJob job) {
        MyJob info = jobDao.checkJobNameUnique(job.getJobName());
        if (ObjectUtil.isNotEmpty(info) && !info.getJobId().equals (job.getJobId())){
            return UserConstants.JOB_NAME_NOT_UNIQUE;
        }
        return UserConstants.JOB_NAME_UNIQUE;
    }

    @Override
    public MyJob getJobById(Integer jobId) {
        return jobDao.getJobById(jobId);
    }

    @Override
    public int deleteJobByIds(String ids) throws MyException {
        Integer[] jobIds = Convert.toIntArray(ids);
        for (Integer jobid:jobIds){
            MyJob job = getJobById(jobid);
            if (countUserJobtById(jobid)>0){
                throw new MyException(ResultCode.ERROR,job.getJobName()+ "已分配,不能删除");
            }
        }
        return jobDao.deleteJobByIds(jobIds);
    }

    @Override
    public int countUserJobtById(Integer jobId) {
        return userJobDao.countUserJobtById(jobId);
    }

    @Override
    public List<MyJob> selectJobAll() {
        return jobDao.selectJobAll();
    }

    @Override
    public List<MyJob> selectJobsByUserId(Integer userId) {
        List<MyJob> userJobs = jobDao.selectJobsByUserId(userId);
        List<MyJob>jobs =jobDao.selectJobAll();
        for (MyJob job : jobs)
        {
            for (MyJob userJob : userJobs)
            {
                if (job.getJobId().equals(userJob.getJobId()))
                {
                    job.setFlag(true);
                    break;
                }
            }
        }
        return jobs;
    }

    @Override
    public int updateJob(MyJob myJob) {
        return jobDao.updateJob(myJob);
    }

    @Override
    public int changeStatus(MyJob myJob) {
        return jobDao.updateJob(myJob);
    }
}
