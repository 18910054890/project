package com.lyzd.om.emp.sdk.audit.support;

import com.lyzd.om.emp.sdk.audit.representation.TaskIdAware;

/**
 * 查询工作流中任务对应的具体业务数据
 * 
 * @author Thinker
 *
 */
public interface BusinessDataQueryCallBack {
	
	/**
	 * @param businesskey 业务数据主键
	 * @return
	 */
	public TaskIdAware call(String businesskey);

}
