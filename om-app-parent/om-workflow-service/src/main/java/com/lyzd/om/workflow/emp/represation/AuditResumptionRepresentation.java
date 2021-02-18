package com.lyzd.om.workflow.emp.represation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * Employee's Resumption, display for audit.
 * 
 * @author Thinker
 *
 */

@Data
public class AuditResumptionRepresentation {
	
	/**工状态  正式/劳动合同**/
	@ApiModelProperty(value = "员工状态  正式/劳动合同")
	private String userStatus;

	/**类型:劳动合同、实习协议、劳务合同**/
	@ApiModelProperty(value = "类型:劳动合同、实习协议、劳务合同")
	private String contractStatus;
	
	/**工作区域：**/
	@ApiModelProperty(value = "工作区域")
	private String district;
	
}
