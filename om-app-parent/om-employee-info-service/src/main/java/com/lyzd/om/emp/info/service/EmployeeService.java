package com.lyzd.om.emp.info.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lyzd.om.emp.info.model.ContractInfo;
import com.lyzd.om.emp.info.model.Education;
import com.lyzd.om.emp.info.model.Employee;
import com.lyzd.om.emp.info.model.EmployeeChildren;
import com.lyzd.om.emp.info.model.EmployeeProject;
import com.lyzd.om.emp.info.model.InterViewInfo;
import com.lyzd.om.emp.info.model.LyWorkExperience;
import com.lyzd.om.emp.info.model.MyEmployee;
import com.lyzd.om.emp.info.model.Resumption;
import com.lyzd.om.emp.info.model.Skill;
import com.lyzd.om.emp.info.model.WorkExperience;
import com.lyzd.om.emp.info.model.eductionNewType;
import com.lyzd.om.emp.info.repository.EmployeeRepository;
import com.lyzd.om.emp.info.repository.LoginRepository;
import com.lyzd.om.emp.info.representation.ContractInfoRepresentation;
import com.lyzd.om.emp.info.representation.EducationRepresentation;
import com.lyzd.om.emp.info.representation.EductionNewTypeRepresentation;
import com.lyzd.om.emp.info.representation.EmpBaseInfoRepresentation;
import com.lyzd.om.emp.info.representation.EmployeeRepresentation;
import com.lyzd.om.emp.info.representation.ResumptionRepresentation;
import com.lyzd.om.emp.info.representation.SkillRepresentation;
import com.lyzd.om.emp.info.sdk.commond.CreateEducationCommand;
import com.lyzd.om.emp.info.sdk.commond.CreateEmployeeCommand;
import com.lyzd.om.emp.info.sdk.commond.CreateSkillCommand;
import com.lyzd.om.emp.info.sdk.commond.CreateWorkExperienceCommand;
import com.lyzd.om.emp.info.sdk.commond.InterViewInfoCommond;
import com.lyzd.om.emp.info.sdk.commond.ResumptionCommond;
import com.lyzd.om.shared.utils.PagedResource;
import com.lyzd.om.spring.common.dto.Result;
import com.rabbitmq.client.DnsSrvRecordAddressResolver.SrvRecord;

import lombok.extern.slf4j.Slf4j;

/**
 * @author GM
 *
 */
@Slf4j
@Component
public class EmployeeService {
	// 插入数据库,创建对象
	private EmployeeRepository employeeRepository;
    
	public EmployeeService() {
	}

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Transactional
	public String addEmployee(CreateEmployeeCommand employeecommand, CreateEducationCommand createEducationCommand,
			CreateWorkExperienceCommand createWorkExperienceCommand, CreateSkillCommand createSkillCommand) {
		Employee employee = null;//Employee.create(employeecommand.getname(), employeecommand.getSex());
		employee.createEducation(createEducationCommand.getUserNo(), createEducationCommand.getSchoolName(),
				createEducationCommand.getMajor(), createEducationCommand.getMaxEducation(),
				createEducationCommand.getStartTime(), createEducationCommand.getEndTime());
		employee.createWorkExperience(createWorkExperienceCommand.getCompany(),
				createWorkExperienceCommand.getPosition(), createWorkExperienceCommand.getDuty(),
				createWorkExperienceCommand.getDescription(), createWorkExperienceCommand.getStartTime(),
				createWorkExperienceCommand.getEndTime(), createWorkExperienceCommand.getUserNo());
//		employee.createSkill(createSkillCommand.getUserNo(), createSkillCommand.getSkillTree(),
//				createSkillCommand.getSonSkill(), createSkillCommand.getSkillName(), createSkillCommand.getOtherName(),
//				createSkillCommand.getUseTime(), createSkillCommand.getSkillType(), createSkillCommand.getSkillLevel());

		employeeRepository.save(employee);
		employeeRepository.save(employee);
		employeeRepository.save(employee);
		employeeRepository.save(employee);
		log.info("Created user[{}].", employee.getUserId());
		return null;
	}
	
	
	
	public void saveEmployee(Employee employee) {
		employeeRepository.saveEmployee(employee);
	}
	
	@Transactional
	public List<MyEmployee> listEmployee(String flag, String name) {
		// 改方法名
		return employeeRepository.byFlag(flag, name);
	}

	@Transactional
	public Employee byUserNo(String userNo) {
		return employeeRepository.byUserNo(userNo);
	}

	@Transactional
	public List<Education> employeeEducationById(String userId) {
		return employeeRepository.employeeEducationById(userId);
	}

	@Transactional(readOnly = true)
	public EmployeeRepresentation employeeBaseinfoById(String userId) {
		return employeeRepository.employeeBaseinfoById(userId).toRepresentation();
	}

	@Transactional(readOnly = true)
	public Employee seelctEmployeeByUserId(String userId) {
		return employeeRepository.employeeBaseinfoById(userId);
	}
	
	public ResumptionRepresentation employeeSumptionById(String userId) {
		return employeeRepository.employeeSumptionById(userId).toRepresentation();
	}

	
	public List<WorkExperience> employeeWorkExperienceById(String userId) {
		return employeeRepository.employeeWorkExperienceById(userId);
	}

	
	public List<EmployeeProject> employeeProjectById(String userId) {
		return employeeRepository.employeeProjectById(userId);
	}

	
	public List<EmployeeChildren> employeeChildrenById(String userId) {
		return employeeRepository.employeeChildrenById(userId);
	}

	public EmployeeChildren queryEmployeeChildrenById(String userId) {
		return employeeRepository.queryEmployeeChildrenById(userId);
	}
	
	public List<LyWorkExperience> employeeLyWorkExperienceById(String userId) {
		return employeeRepository.employeeLyWorkExperienceById(userId);
	}

	
	public ContractInfoRepresentation employeeContractById(String userId) {
		return employeeRepository.employeeContractById(userId).toRepresentation();
	}


	public SkillRepresentation employeeSkillById(String userId) {
		return employeeRepository.employeeSkillById(userId).toRepresentation();
	}

	
	public EductionNewTypeRepresentation employeeMaxEduById(String userId) {
		return employeeRepository.employeeMaxEduById(userId).toRepresentation();
	}

	public List<EmpBaseInfoRepresentation> toEmpBaseInfoRepresentation(String userId) {
		EmpBaseInfoRepresentation er = new EmpBaseInfoRepresentation();
		List<EmpBaseInfoRepresentation> List = new ArrayList();
		EducationRepresentation ep = new EducationRepresentation();
//		  List<Map<String, Object>>  List = new ArrayList();
//	    	Map map = new HashMap();
//	    	Map map1 = new HashMap();
//	    	List List1 =new ArrayList();
		er.setChildrenList(employeeChildrenById(userId));// 子女信息集合
//		  er.setEducationList(employeeEducationByPhone(phone));//教育经历集合
		er.setHtxx(employeeContractById(userId).toString());// 合同信息
		er.setJbxx(employeeBaseinfoById(userId).toString());// 基本信息
		er.setLyworkExperienceList(employeeLyWorkExperienceById(userId));// 内部工作经历集合
		er.setProjectList(employeeProjectById(userId));// 项目经历集合
		er.setWorkExperienceList(employeeWorkExperienceById(userId));// 工作经历集合
		er.setLzxx(employeeSumptionById(userId).toString());// 履职信息
		er.setZcll(employeeSkillById(userId).toString());// 技术特长
		er.setMaxEdu(employeeMaxEduById(userId).toString());
		er.setEmStatus(seelctEmployeeByUserId(userId).getEmStatus());
		ep.setEducationList(employeeEducationById(userId));
		ep.setEducationType(employeeMaxEduById(userId).getEducationType());
		ep.setEducationType(employeeMaxEduById(userId).getMaxEducation());

		return List;
	}


	public boolean updateUserStatus(String userId, String isPass) {

		return employeeRepository.updateUserStatus(userId, isPass);
	}
	
	public boolean updateChildren(CreateEmployeeCommand createEmployeeCommand, String userId) {
		return employeeRepository.updateChildren(createEmployeeCommand, userId);
	}

//	public boolean updateLyWorkExperience(CreateEmployeeCommand command, String userId) {
//		return employeeRepository.updateLyWorkExperience(command,  userId);
//	}

//	public boolean updateWorkExperience(CreateEmployeeCommand command, String userId) {
//		return employeeRepository.updateWorkExperience(command,userId);
//		
//	}

//	public boolean updateProject(CreateEmployeeCommand command, String userId) {
//		return employeeRepository.updateProject(command,userId);
//		
//	}

//	public boolean updateSkill(CreateEmployeeCommand command, String userId) {
//		return employeeRepository.updateSkill(command,userId);
//		
//	}
//	
	public boolean updateShanghuiResumption(Resumption resumption, String userId ) {
		return employeeRepository.updateShanghuiResumption(resumption,userId );
	}
	
	
	public void updateResumption(CreateEmployeeCommand createEmployeeCommand, String userId) {
		employeeRepository.updateResumption(createEmployeeCommand,userId );	
	}

	public void saveSkill(Skill zcll) {
		employeeRepository.saveSkill(zcll);
		
	}
}
