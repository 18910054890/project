package com.lyzd.om.workflow.emp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.sdk.audit.representation.TaskIdAware;
import com.lyzd.om.emp.sdk.audit.support.BusinessDataQueryCallBack;
import com.lyzd.om.workflow.emp.model.repository.EmployeeTaskRepository;

/**
 * Query employee's updated info.
 * @author Thinker
 *
 */
@Component
public class EmployeeTaskQueryService implements BusinessDataQueryCallBack {

	@Autowired
	EmployeeTaskRepository employeeTaskRepository;
	@Override
	public TaskIdAware call(String businesskey) {
		
		return employeeTaskRepository .byId(businesskey).toRepresentation();
	}

}
