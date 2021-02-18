package com.lyzd.om.workflow.emp.model;

import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import com.lyzd.om.shared.model.BaseAggregate;
import com.lyzd.om.shared.utils.DateUtil;

import lombok.Data;

/**
 * Audit-related task model.
 * 
 * @author Thinker
 *
 */
@Data
public class TaskEntity extends BaseAggregate {

	/**business id**/
	protected String id;

	protected Integer userId;
	
	/** 是否通过审核, 1: 通过， 2: 驳回 **/
	protected String isPass;
	
	/** 审核意见 **/
	protected String auditOpinion;
	
	protected String createdAt;
	
	public TaskEntity() {
		super();
	}

	public TaskEntity(Integer userId,  String isPass) {
		super();
		this.id = newUuid();
		this.userId = userId;
		this.isPass = isPass;
		this.createdAt = DateUtil.now();
	}
	
}
