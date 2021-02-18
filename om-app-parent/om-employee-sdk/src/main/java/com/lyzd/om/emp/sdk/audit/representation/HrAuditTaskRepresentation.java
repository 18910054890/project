package com.lyzd.om.emp.sdk.audit.representation;

import java.io.Serializable;


import lombok.Data;

/**
 * hr审核，返回前端展现
 * 
 * @author Thinker
 *
 */
@Data
public class HrAuditTaskRepresentation implements TaskIdAware, Serializable {
	
	/****/
	private static final long serialVersionUID = 4460830787148246017L;
	
	private String taskId;
	/**序号**/
	private int no;
	
	/**bussinessId**/
	private String id;
	
	/**提交内容**/
	private String applyType;
	
	/**提交人**/
	//private String userId;
	
	private String userName;
	
	/**提交时间**/
	private String createdAt;
	
}
