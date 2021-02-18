package com.lyzd.om.emp.sdk.audit.support;

import com.lyzd.om.emp.sdk.command.AuditCommand;
import com.lyzd.om.emp.sdk.command.EmployeeApplyCommand;

/**
 * 回调处理业务数据
 * @author Thinker
 *
 */
public interface BusinessDataProcessCallBack {
	
	
	/**
	 * 员工修改个人信息申请时回调，如，员工提交修改个人信息申请后，保存最新数据到相应的员工信息表。
	 * @param command
	 */
	public void call(EmployeeApplyCommand command);
	
	
	/**
	 * 审核时回调，如，回写审核意见，重置审核标志
	 * 
	 * @param command
	 */
	public void call(AuditCommand command);

}
