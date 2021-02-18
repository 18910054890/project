package com.lyzd.om.workflow.emp.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * 员工信息更新审核
 * 
 * @author Thinker
 *
 */
@Data
public class HrAuditTask extends TaskEntity {
	
	private String taskId;
	
	public static String TASKDEFINITIONKEY = "sid-D52BAEC0-7E8D-40FC-B7F7-7452542B69EA";
	
	public  HrAuditTask() {
		super();
	}
	public HrAuditTask(String businessKey, Integer userId, String isPass,
			String auditOpinion) {
		this.id = businessKey;
		this.userId = userId;
		this.isPass = isPass;
		this.isPass = isPass;
		this.auditOpinion = auditOpinion;
	}

	
	public HrAuditTask(Integer userId,  String taskId, String isPass,
			String auditOpinion) {
		super(userId, isPass);
		this.isPass = isPass;
		this.taskId = taskId;
		this.auditOpinion = auditOpinion;
	}


	/**
	 * 工作流流程变量
	 * @return
	 */
	public Map getHrAuditMap() {
		Map<String, Object> variables = new HashMap<>();
		String isSuccess = "2".equals(isPass) ? "1" : "0";
		variables.put("isSuccess", isSuccess);
		//task.setAssignee("hr")
		return variables;
	}
}
