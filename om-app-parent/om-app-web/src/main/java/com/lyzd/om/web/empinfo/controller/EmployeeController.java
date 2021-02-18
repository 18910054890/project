package com.lyzd.om.web.empinfo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyzd.om.emp.file.service.FileInfoRepresentation;
import com.lyzd.om.emp.file.service.FileInfoService;
import com.lyzd.om.emp.info.model.Employee;
import com.lyzd.om.emp.info.model.EmployeeProject;
import com.lyzd.om.emp.info.model.MyEmployee;
import com.lyzd.om.emp.info.model.Skill;
import com.lyzd.om.emp.info.repository.InnerProjectRepository;
import com.lyzd.om.emp.info.sdk.commond.CreateEmployeeCommand;
import com.lyzd.om.emp.info.service.ContractInfoService;
import com.lyzd.om.emp.info.service.EmployeeService;
import com.lyzd.om.emp.info.service.SaveEmployeeInfoService;
import com.lyzd.om.emp.info.service.UpdateEmployeeInfoService;
import com.lyzd.om.emp.sdk.audit.support.AuditableSevice;
import com.lyzd.om.emp.sdk.command.EmployeeApplyCommand;
import com.lyzd.om.spring.common.dto.JwtUserDto;
import com.lyzd.om.spring.common.dto.Result;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/talenSearch")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private FileInfoService fileInfoService;

	@Autowired
	private SaveEmployeeInfoService saveEmployeeInfoService;
	@Autowired
	private InnerProjectRepository innerProjectRepository;
	@Autowired
	private ContractInfoService contractInfoService;
	@GetMapping
	@ResponseBody
	@ApiOperation(value = "用户列表")
	@PreAuthorize("hasAnyAuthority('MyEmployee:list')")
	// @MyLogA("查询") 操作类纪录日志
	public Result<MyEmployee> userList(@PathVariable(name = "flag") String flag,
			@PathVariable(name = "name") String name) {
//        pageTableRequest.countOffset();List<MyEmployee>
//        return employeeService.getAllUsersByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit(),myEmployee);
		return Result.ok().data(employeeService.listEmployee(flag, name));
	}

//	@GetMapping(value = "/{userNo}")
//	public Employee byUserNo(@PathVariable(name = "userNo") String userNo) {
//		return employeeService.byUserNo(userNo);
//	}

	@GetMapping(value = "/userInfo")
	@ResponseBody
	@ApiOperation(value = "个人信息查询")
//	@PreAuthorize("hasAnyAuthority('employee:list')")
	public Result<Employee> byUserId() {
		JwtUserDto user = this.getJwtUserDto();
		List<Map<String, Object>> List = new ArrayList();
		Map map = new HashMap();
		Map map1 = new HashMap();
		List List1 = new ArrayList();
		String userId = user.getMyUser().getUserId().toString();
		map1.put("educationList", employeeService.employeeEducationById(userId));
		map1.put("educationType", employeeService.employeeMaxEduById(userId).getEducationType());
		map1.put("maxEducation", employeeService.employeeMaxEduById(userId).getMaxEducation());
		map.put("education", map1);
		map.put("jbxx", employeeService.employeeBaseinfoById(userId));
		map.put("lzxx", employeeService.employeeSumptionById(userId));
		map.put("workExperienceList", employeeService.employeeWorkExperienceById(userId));
		map.put("projectList", employeeService.employeeProjectById(userId));
		List<EmployeeProject> EmployeeProjectList=innerProjectRepository.queryInnerProject(userId);
		map.put("innerProjectList",EmployeeProjectList );
		//map.put("childrenList", employeeService.employeeChildrenById(userId));
		map.put("employeeChildren", employeeService.queryEmployeeChildrenById(userId));
		map.put("lyworkExperienceList", employeeService.employeeLyWorkExperienceById(userId));
		map.put("htxx", contractInfoService.employeeContractByUserId(userId));
		map.put("zcll", employeeService.employeeSkillById(userId));
		map.put("emStatus", employeeService.seelctEmployeeByUserId(userId).getEmStatus());

		List<FileInfoRepresentation> fileInfoRepresentationList = fileInfoService.queryFileInfo(userId, null);
		map.put("fileInfoList", fileInfoRepresentationList);
		List.add(map);

//           employeeService.toEmpBaseInfoRepresentation(phone);
		return Result.ok().data(List);
	}

	private JwtUserDto getJwtUserDto() {
		return (JwtUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@PostMapping(value = "/updateEmp")
	@ResponseBody
	public Result updateEmpInfo(@RequestBody @Valid CreateEmployeeCommand createEmployeeCommand) {
		JwtUserDto user = this.getJwtUserDto();
		//List<String> messageList;
		saveEmployeeInfoService.saveEmpInfo(user, createEmployeeCommand);
		return Result.ok().message("保存成功");
	}
}
