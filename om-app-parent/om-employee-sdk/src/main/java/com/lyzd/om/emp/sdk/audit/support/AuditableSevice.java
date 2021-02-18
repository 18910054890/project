package com.lyzd.om.emp.sdk.audit.support;

import com.lyzd.om.emp.sdk.command.EmployeeApplyCommand;

/**
 * @author Thinker
 *
 */
public interface AuditableSevice {
	
	
	/**
	 * @param command
	 * @param businessDataProcessCallBack
	 */
    public void apply(EmployeeApplyCommand command, BusinessDataProcessCallBack businessDataProcessCallBack);

}
