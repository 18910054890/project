package com.lyzd.om.emp.sdk.command;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 个人信息修改审核，有员工发起，Hr审核。
 * 
 * @author Thinker
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditCommand {

	/** bussinessId **/
	@NotNull(message = "id未传")
	private String id;

	private Integer userId;

	//@NotNull(message = "taskId不能为空")
	private String taskId;

	/**是否通过**/
	@NotNull(message = "请填是否通过")
	private String isPass;
	
	/*审核人***/
	private String auditor;

	@NotNull(message = "请填写审核意见")
	private String auditOpinion;
	
	private Object businessData;

}
