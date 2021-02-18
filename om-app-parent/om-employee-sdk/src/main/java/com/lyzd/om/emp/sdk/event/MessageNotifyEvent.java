package com.lyzd.om.emp.sdk.event;

import java.beans.ConstructorProperties;

import com.lyzd.om.shared.event.DomainEvent;
import com.lyzd.om.shared.utils.DateUtil;

import lombok.Getter;

/**
 * 员工信息更新事件
 * 
 * @author Thinker
 *
 */
@Getter
public class MessageNotifyEvent extends DomainEvent {
	
	/**发起人用户ID**/
	private Integer initUserId;
	/**普通用户ID，HR，或部门经理等**/
	private String reader;
	private String bussinesId;
    private String applyType;
    private String createdAt;


    @ConstructorProperties({"initUserId", "readers",  "bussinesId", "applyType", "createdAt"})
    public MessageNotifyEvent(Integer initUserId, String reader,String bussinesId, String applyType) {
    	this.initUserId = initUserId;
    	this.reader = reader;
    	this.bussinesId = bussinesId;
        this.applyType = applyType;
        this.createdAt = DateUtil.now();
    }
}
