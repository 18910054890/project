package com.lyzd.om.emp.info.impl;

import com.lyzd.om.emp.info.repository.LoginRepository;
import com.lyzd.om.emp.info.sdk.commond.CheckPwdCommand;
import com.lyzd.om.emp.info.service.EmployeeService;
import com.lyzd.om.emp.info.service.PwdService;

public class Test {
public static void main(String[] args) {
	PwdService pwdService = new PwdService();
	EmployeeService EmployeeService = new EmployeeService();
//	EmployeeRepository employeeRepository  = new EmployeeRepository();
	CheckPwdCommand checkPwdCommand = new CheckPwdCommand();
	
//	String newPwd = "123456wwwwwww";
//	String phoneNo = "13556336212";
//	checkPwdCommand.setNewPwd(newPwd);
//	checkPwdCommand.setPhoneNo(phoneNo);
	String phone = "13556336255";
	//System.out.println(EmployeeService.byUsePhone(phone));
	
//	CheckPwd  checkPwd =CheckPwd.s;
//	System.out.println(pwdService.checkPwd());
}
}
