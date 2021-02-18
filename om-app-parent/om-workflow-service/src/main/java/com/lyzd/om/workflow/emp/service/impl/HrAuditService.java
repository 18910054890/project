package com.lyzd.om.workflow.emp.service.impl;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lyzd.om.emp.sdk.audit.support.BusinessDataProcessCallBack;
import com.lyzd.om.emp.sdk.command.AuditCommand;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.workflow.emp.model.EmpInfoUpdateApplyTask;
import com.lyzd.om.workflow.emp.model.HrAuditTask;
import com.lyzd.om.workflow.emp.model.repository.EmployeeTaskRepository;
import com.lyzd.om.workflow.emp.service.TaskRelatedCommonService;

/**
 * HR audit
 * 
 * @author Thinker
 *
 */
@Component
public class HrAuditService extends TaskRelatedCommonService {

	
	
	@Autowired
	private EmployeeTaskRepository employeeTaskRepository;

	/**
	 * 流程部署
	 * 
	 * @return
	 */
	public void deploy() {
		// 第一步
		DeploymentBuilder builder = repositoryService.createDeployment();
		builder.addClasspathResource("processes/employeeInfoAudit.bpmn20.xml");
		String id = builder.deploy().getId();
		repositoryService.setDeploymentKey(id, "employeeInfoAudit");
	}
	
	 /**
     * 统计HR的任务数
     * 
     * @return
     */
    public long countHrTask() {
    	return count("hr");
    }
    
	/**
	 * hr审批
	 * @param AuditCommand
	 * @param BusinessDataProcessCallBack  
	 */
    @Transactional
	public void hrAudit(AuditCommand ac, BusinessDataProcessCallBack businessDataProcessCallBack) {
//		HrAuditTask ht = new HrAuditTask(ac.getUserId(), ac.getTaskId(), ac.getIsPass(), ac.getAuditOpinion());
//		Task task = taskService.createTaskQuery().taskId(ht.getTaskId()).singleResult();
//		String instanceid= task.getProcessInstanceId();
//		ProcessInstance ins = runtimeService.createProcessInstanceQuery().processInstanceId(instanceid).singleResult();
//		//提交申请时的	业务标示
//		String businesskey = ins.getBusinessKey();
//        taskService.setVariableLocal(task.getId(), "isSuccess", "1".equals(ht.getIsPass()));
//		taskService.addComment(task.getId(), task.getProcessInstanceId(), ht.getAuditOpinion());
//		// 完成此次审批。由下节点审批
//		taskService.complete(task.getId(), ht.getHrAuditMap());
		//return businesskey;
    	String auditor = ac.getAuditor();
		HrAuditTask ht = new HrAuditTask(ac.getId(), ac.getUserId(), ac.getIsPass(), ac.getAuditOpinion());
		Task task = taskService.createTaskQuery().processInstanceBusinessKey(ht.getId()).singleResult();
		if(task != null) {
			taskService.complete(task.getId(), ht.getHrAuditMap());
			EmpInfoUpdateApplyTask et = employeeTaskRepository.byId(ht.getId());
			et.setIsPass(ht.getIsPass());
			et.setAuditOpinion(ht.getAuditOpinion());
			et.raiseEvent(ht.getUserId(), et.getUserId() + "", et.getId(), ac.getAuditOpinion());
			et.setAuditor(auditor);
			employeeTaskRepository.save(et);
			if(businessDataProcessCallBack != null) {
				ac.setTaskId(task.getId());
				businessDataProcessCallBack.call(ac);
			}
		}
	}
	
	
	/**
	 * Conditional query.
	 * @param name
	 * @param deptName
	 * @param startTime
	 * @param endTime
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Result queryTasks(String name, String deptName, String startTime, String endTime,  int currentPage, int pageSize) {
		return employeeTaskRepository.queryTasks(name, deptName, startTime, endTime, currentPage, pageSize);
	}
	
	
	 /**
	  * Query by id
	 * @param id
	 * @return
	 */
	public EmpInfoUpdateApplyTask byId(String id) {
	    	
	    	return employeeTaskRepository.byId(id);
	    	
	}
	
	/**
	 * 查询wmz未完成的历史记录
	 * 
	 * @return
	 */
	public Object unfinish(String name) {
		return ProcessEngines.getDefaultProcessEngine().getHistoryService().createHistoricProcessInstanceQuery()
				.startedBy(name).unfinished().list();

	}

	/**
	 * 查询完成的历史记录
	 * 
	 * @return
	 */
	public Object finish(String name) {
		return ProcessEngines.getDefaultProcessEngine().getHistoryService().createHistoricProcessInstanceQuery()
				.startedBy(name).finished().list();
	}
}
