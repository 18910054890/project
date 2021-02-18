package com.lyzd.om.workflow.emp.service;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyzd.om.emp.sdk.audit.representation.TaskIdAware;
import com.lyzd.om.emp.sdk.audit.support.BusinessDataQueryCallBack;

/**
 * Common task related common services.
 * @author Thinker
 *
 */
public abstract class TaskRelatedCommonService {
	
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected RuntimeService runtimeService;
	
	/**
	 * 查询总数，分页时使用
	 * @return
	 */
	public long count(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).count();
	}
	
	/**
	 * 装配 taskId
	 * @param businessKey
	 * @param taskIdAware
	 */
	public void awareTaskId(String businessKey, TaskIdAware taskIdAware) {
		Task task = taskService.createTaskQuery().processInstanceBusinessKey(businessKey).asc().singleResult();
		taskIdAware.setTaskId(task.getId());
		
	}
	
	/**
	 * 根据角色信息获取自己的待办
	 * @param assignee
	 * @param currentPage
	 * @param pageSize
	 * @param BusinessDataQueryCallBack 添加业务数据
	 * @return
	 */
	public List<TaskIdAware> queryTasks(String assignee, int currentPage, 
			int pageSize, BusinessDataQueryCallBack bc) {
		List<Task> taskList = 
		taskService.createTaskQuery().taskAssignee(assignee).listPage(currentPage, pageSize);
		List<TaskIdAware> results = new ArrayList<TaskIdAware>();
		int no = 1;
		try {
			for(Task task:taskList){
				String instanceid=task.getProcessInstanceId();
				ProcessInstance ins = runtimeService.createProcessInstanceQuery().processInstanceId(instanceid).singleResult();
				if(ins != null) {
					String businesskey = ins.getBusinessKey();
					//查询业务数据
					TaskIdAware ta = bc.call(businesskey);
					ta.setNo(no++); 
					//返回前端任务Id，以便审批操作使用
					ta.setTaskId(task.getId());
					results.add(ta);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return results;
				
	}
	
//	public void hrAudit(AuditCommand ac, BusinessDataProcessCallBack businessDataProcessCallBack) {
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
//}

}
