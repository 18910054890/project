package com.lyzd.om.workflow.listener;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author Thinker
 *
 */
@Service
public class HRRejectListener implements ExecutionListener {

	/****/    
	private static final long serialVersionUID = 2628249542324947276L;

	@Override
	public void notify(DelegateExecution execution) {
		
		ProcessEngine services = ProcessEngines.getDefaultProcessEngine();
		ExecutionEntity entity = (ExecutionEntity) execution;
		String processInstId = entity.getProcessInstanceId(); // 流程实例Id
		// 根据审批传入的参数判断是否打回
		Object value = execution.getVariableInstance("isSuccess").getValue();
		if (ObjectUtils.isEmpty(value)) {
			value = execution.getParent().getVariableInstance("isSuccess").getValue();
		}
		if (!ObjectUtils.isEmpty(value) && (boolean) (value) == true) {
			return;
		}
		List<HistoricTaskInstance> list = services.getHistoryService().createHistoricTaskInstanceQuery()
				.orderByTaskCreateTime().asc().processInstanceId(processInstId).list();
		if (list != null) {
			String user = list.get(0).getAssignee(); // 获取最新的一个责任人信息回退给他
			execution.setVariable("applyUser", user);
		}


	}

}