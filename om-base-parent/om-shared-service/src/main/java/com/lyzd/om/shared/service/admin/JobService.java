package com.lyzd.om.shared.service.admin;

import java.util.List;

import com.lyzd.om.shared.dto.admin.JobQueryDto;
import com.lyzd.om.shared.entity.admin.MyJob;
import com.lyzd.om.shared.exception.MyException;
import com.lyzd.om.spring.common.dto.Result;

/**
 * @author Thinker
 *
 */
public interface JobService {
    /**
     * 返回岗位
     * @param offectPosition
     * @param limit
     * @param jobQueryDto
     * @return
     */
    Result<MyJob> getJobAll(Integer offectPosition, Integer limit, JobQueryDto jobQueryDto);

    /**
     * 新增岗位信息
     * @param job 岗位信息
     * @return 结果
     */
    int insertJob(MyJob job);

    /**
     * 校验岗位名称
     *
     * @param job 岗位信息
     * @return 结果
     */
    String checkJobNameUnique(MyJob job);

    /**
     * 通过id获得岗位信息
     * @param jobId
     * @return
     */
    MyJob getJobById(Integer jobId);

    /**
     * 批量删除岗位信息
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws MyException 异常
     */
    int deleteJobByIds(String ids) throws MyException;

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param jobId 岗位ID
     * @return 结果
     */
     int countUserJobtById(Integer jobId);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
     List<MyJob> selectJobAll();
    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
     List<MyJob> selectJobsByUserId(Integer userId);

     /**
      * 修改保存岗位信息
      *
      * @param myJob 岗位信息
      * @return 结果
      */
    int updateJob(MyJob myJob);

    /**
     * 修改岗位状态
     * @param myUser
     * @return
     */
    int changeStatus(MyJob myUser);
}
