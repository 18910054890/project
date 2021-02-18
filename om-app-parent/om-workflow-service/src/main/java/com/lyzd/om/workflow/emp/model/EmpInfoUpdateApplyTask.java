package com.lyzd.om.workflow.emp.model;

import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.lyzd.om.emp.sdk.audit.representation.HrAuditTaskRepresentation;
import com.lyzd.om.emp.sdk.event.MessageNotifyEvent;

import lombok.Data;

/**
 * 员工信息更新申请
 * 
 * @author Thinker
 *
 */
@Data
public class EmpInfoUpdateApplyTask extends TaskEntity {
	
    public static String taskDefinitionKey = "sid-11C4CF2C-B2DB-4CA4-9758-46715CF178A0";
	 
    private String name;
    
    /**申请类型（内容）**/
    private String applyType;
    
    /**申请人部门**/
    private String deptName;
    
    /**审核人**/
    private String auditor;
	
	public static String PROCESSINSTANCEBYKEY = "employeeInfoAudit";

	public EmpInfoUpdateApplyTask() {
		super();
	}
	public EmpInfoUpdateApplyTask(Integer userId, String name, String reader, String applyType, String isPass,String deptName) {
		super(userId, isPass);
		this.name = name;
		this.applyType = applyType;
		this.deptName = deptName;
		raiseEvent(new MessageNotifyEvent(userId, reader,this.getId(), this.getApplyType()));
	}
	
	
	/**
	 * 注册消息通知事件
	 * @param initUserId
	 * @param reader
	 * @param bussinesId
	 * @param applyType
	 */
	public void raiseEvent(Integer initUserId, String reader,String bussinesId, String applyType) {
		raiseEvent(new MessageNotifyEvent(userId, reader,this.getId(), this.getApplyType()));
	}
	 
	/**
	 * 前端展现
	 * @return
	 */
	public HrAuditTaskRepresentation toRepresentation() {
		
		HrAuditTaskRepresentation target = new HrAuditTaskRepresentation();
		BeanUtils.copyProperties(this, target);

		return target;
	}

	/**
	 * 工作流流程变量
	 * @return
	 */
	public Map getApplyViablesMap() {
		Map<String, Object> variables = new HashMap<>();
		variables.put("applyUser", userId);
		variables.put("hr", "hr");

		return variables;
	}
}
