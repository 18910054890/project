package com.lyzd.om.emp.sdk.audit.representation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * View object
 * 
 * @author Thinker
 *
 */
@Data
public class EmployeeApplyRepresentation {
	
	/**bussinessId**/
	@ApiModelProperty(value = "返回id")
	private String id;
	@ApiModelProperty(value = "返回申请人")
	private String name;
	@ApiModelProperty(value = "返回部门名称")
	private String deptName;
	@ApiModelProperty(value = "返回申请内容")
	private String applyType;
	
	private String isPass;
	@ApiModelProperty(value = "返回创建日期")
	private String createdAt;
	   
	/**审核意见**/
	private String auditOpinion;

}
