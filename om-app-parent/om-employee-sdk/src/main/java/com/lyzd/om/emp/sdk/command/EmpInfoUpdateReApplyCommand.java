package com.lyzd.om.emp.sdk.command;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 员工信息更新，重新申请
 * 
 * @author Thinker
 *
 */
@Data
public class EmpInfoUpdateReApplyCommand extends EmployeeApplyCommand {
	
	
	  /**The key for querying last application **/
	  @NotNull(message = "businessId为空")
	   private String businessId;

}
