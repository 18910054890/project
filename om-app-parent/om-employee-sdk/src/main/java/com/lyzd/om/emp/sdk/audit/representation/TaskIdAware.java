package com.lyzd.om.emp.sdk.audit.representation;

import java.io.Serializable;

/**
 * 
 * @author Thinker
 *
 */
public interface TaskIdAware extends Serializable {
	
	public void setTaskId(String taskId);
	
	public void setNo(int no);


}
