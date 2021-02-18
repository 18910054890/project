package com.lyzd.om.emp.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.sdk.commond.CreateEmployeeCommand;
import com.lyzd.om.emp.sdk.audit.support.BusinessDataProcessCallBack;
import com.lyzd.om.emp.sdk.command.AuditCommand;
import com.lyzd.om.emp.sdk.command.EmployeeApplyCommand;

@Component
public class UpdateEmployeeInfoService implements BusinessDataProcessCallBack{

	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private SaveEmployeeInfoService saveEmployeeInfoService;
	@Override
	public void call(EmployeeApplyCommand command) {
		
		CreateEmployeeCommand createEmployeeCommand =  (CreateEmployeeCommand)command.getBusinessData();
		//employeeService.updateEmployee(createEmployeeCommand, command.getUserId()+"");
		String userId=createEmployeeCommand.getUserId();
		saveEmployeeInfoService.saveEmployeeInfo(createEmployeeCommand, userId);
		saveEmployeeInfoService.saveResumptions(createEmployeeCommand, userId);
		//更新子女
		String bearStatu=createEmployeeCommand.getBearStatus();
		if("1".equals(bearStatu)) {  //有子女
			saveEmployeeInfoService.saveChildInfo(createEmployeeCommand, userId);
		}
		saveEmployeeInfoService.saveLyWorkExperience(createEmployeeCommand, userId);
		saveEmployeeInfoService.saveWorkExperience(createEmployeeCommand, userId);
		saveEmployeeInfoService.saveInnerProject(createEmployeeCommand, userId);
		saveEmployeeInfoService.saveProject(createEmployeeCommand, userId);
		saveEmployeeInfoService.saveSkill(createEmployeeCommand, userId);
	}

	@Override
	public void call(AuditCommand command) {
		String isPass = command.getIsPass();
		String userId = command.getUserId().toString();
		employeeService.updateUserStatus(userId,isPass);
	}

	
		

}
