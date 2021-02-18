package com.lyzd.om.emp.sdk.command;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工个人申请信息
 * 
 * @author Thinker
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeApplyCommand {
	
	/**businessKey 后台生成**/
	private String id;

	/** For security, we should get userId from session(user object). **/
	private Integer userId;

	private String name;

	//@NotNull(message = "applyType为空")
	private String applyType;

	private String deptName;
	
	private Object businessData;

}
