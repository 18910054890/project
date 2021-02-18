package com.lyzd.om.workflow.emp.service.impl;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lyzd.om.emp.sdk.audit.representation.EmployeeApplyRepresentation;
import com.lyzd.om.emp.sdk.audit.support.AuditableSevice;
import com.lyzd.om.emp.sdk.audit.support.BusinessDataProcessCallBack;
import com.lyzd.om.emp.sdk.command.EmpInfoUpdateReApplyCommand;
import com.lyzd.om.emp.sdk.command.EmployeeApplyCommand;
import com.lyzd.om.shared.utils.DateUtil;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.workflow.emp.model.EmpInfoUpdateApplyTask;
import com.lyzd.om.workflow.emp.model.repository.EmployeeTaskRepository;
import com.lyzd.om.workflow.emp.service.TaskRelatedCommonService;

import lombok.extern.slf4j.Slf4j;

/**
 * Employee info update related services.
 * @author Thinker
 *
 */
@Slf4j
@Component
public class EmployeeApplyService extends TaskRelatedCommonService implements AuditableSevice {

	@Autowired
    private EmployeeTaskRepository employeeTaskRepository;
    
	/**
	 * @param command
	 * @param businessDataProcessCallBack
	 */
	@Transactional
    public void apply(EmployeeApplyCommand command, BusinessDataProcessCallBack businessDataProcessCallBack) {
    	EmpInfoUpdateApplyTask ea = new EmpInfoUpdateApplyTask(
    			command.getUserId(), command.getName(), "HR", command.getApplyType(), "1", command.getDeptName());
    	employeeTaskRepository.save(ea);
    	if(businessDataProcessCallBack != null) {
    		command.setId(ea.getId());
    		businessDataProcessCallBack.call(command);
    	}
        log.info("Created EmployeeInfoUpdateApply [{}].", ea.getId());
        apply(ea);
    }
    /**
	 * 人员提交申请
	 * @param EmpInfoUpdateApplyTask
	 */
	private void apply(EmpInfoUpdateApplyTask ea) {
		ProcessInstance processInstance = ProcessEngines.getDefaultProcessEngine().getRuntimeService()
				.startProcessInstanceByKey(EmpInfoUpdateApplyTask.PROCESSINSTANCEBYKEY,ea.getId(), ea.getApplyViablesMap());
		Task tmp = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId())
				.singleResult();
		taskService.complete(tmp.getId(), ea.getApplyViablesMap());
	}
	
    
    /**
     * 更新是否通过状态
     * @param userId
     * @param isPass
     * @return
     */
    @Transactional
    public String updateIsPasse(String userId, String isPass) {
    	EmpInfoUpdateApplyTask ea = employeeTaskRepository.byId(userId);
        ea.setIsPass(isPass);
        employeeTaskRepository.save(ea);
        return ea.getId();
    }
    
	/**
	 *  人员重新申请
	 * @param command
	 * @param businessDataProcessCallBack
	 */
	public void reApply(EmpInfoUpdateReApplyCommand command, BusinessDataProcessCallBack businessDataProcessCallBack) {
		
		EmpInfoUpdateApplyTask ea = employeeTaskRepository.byId(command.getBusinessId());
		ea.setApplyType(command.getApplyType());
		
		Task task = taskService.createTaskQuery().processInstanceBusinessKey(ea.getId()).singleResult();
		if(task != null) {
			taskService.complete(task.getId(), ea.getApplyViablesMap());
			ea.setCreatedAt(DateUtil.now());
			employeeTaskRepository.save(ea);
			if(businessDataProcessCallBack != null) {
				businessDataProcessCallBack.call(command);
			}
		}
		// 根据流程id获取属于自己的待办。
//		List<Task> list = taskService.createTaskQuery().taskAssignee(ea.getUserId()+"").
//				taskDefinitionKey(EmpInfoUpdateApplyTask.taskDefinitionKey).list();
//		if (!ObjectUtils.isEmpty(list)) {
//			for (Task item : list) {
//				taskService.complete(item.getId(), ea.getApplyViablesMap());
//
//			}
//		}
//		employeeTaskRepository.save(ea);
		
	}
    
    /**
     * Query by id
     * @param id
     * @return
     */
    public EmployeeApplyRepresentation byId(String id) {
    	
    	return toRepresentation(employeeTaskRepository.byId(id));
    	
    }
    
    /**
     * Query an application
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Result queryApply(String userId, int currentPage, int pageSize) {
    	return employeeTaskRepository.queryApply(userId, currentPage, pageSize);
    }
    
    /**
	 * 前端展现
	 * @return
	 */
	private EmployeeApplyRepresentation toRepresentation(EmpInfoUpdateApplyTask ea) {
		
		EmployeeApplyRepresentation target = new EmployeeApplyRepresentation();
		BeanUtils.copyProperties(ea, target);
		
		return target;
	}
}
