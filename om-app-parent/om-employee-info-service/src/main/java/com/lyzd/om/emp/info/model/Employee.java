package com.lyzd.om.emp.info.model;

import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.lyzd.om.emp.info.representation.EmployeeRepresentation;
import com.lyzd.om.emp.info.sdk.commond.CreateEmployeeCommand;
import com.lyzd.om.shared.model.BaseAggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author GM ALT+shift+j
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseAggregate {
	private String userId;
	private String name;// 姓名
	private String sex;// 性别
	private String phone;//手机号
	private String email;// 邮箱
	private String idcard;// 身份证
	private String politicsStatus;// 政治面貌
	private String isMarried;// 婚姻状况
	private String birthPlace;// 出生地
	private String birthDate;// 出生日期
	private String age;// 年龄
	private String domicile;// 户籍所在地省
	private String domicileAddr;// 户籍所在地地址
	private String registeredType;// 户籍性质
	private String familyLive;// 家庭地址
	private String familyPhone;// 家庭电话
	private String postalCode;// 邮编
	private String nation;// 民族
	private String sOSPersonName;// 紧急联系人
	private String sosPersonRelation;// 紧急联系人关系
	private String sOSPersonPhone   ;// 紧急联系人电话
	private String emStatus;// 员工信息提交状态
//	private Instant createdAt;
	private String id;
	/***生育状况  0:无子女     1：有子女 */
	private String bearStatus;  
	private String educationType;
	private String maxEducation;
	/***目前居住地 */
	private String familyAddr;
	
	private transient List<Education> educationList;//教育经历集合
	private transient List<WorkExperience> workExperienceList;//工作经历集合
	//private transient List<EmployeeChildren> childrenList;//子女集合
	private transient EmployeeChildren employeeChildren;//子女
	private transient List<EmployeeProject> projectList;//项目集合
	private transient List<LyWorkExperience> lyworkExperienceList;//内部工作经历集合

	
	public static Employee create(String userId,String userName,String idcard) {

		Employee employee = Employee.builder().userId(userId).name(userName).idcard(idcard)
				.build();

//		Object t = "EEE", target = null;
//		BeanUtils.copyProperties(t, target);
		return employee;
	}
	
	public static Employee create(String userName,String idcard) {

		Employee employee = Employee.builder().name(userName).idcard(idcard)
				.build();

//		Object t = "EEE", target = null;
//		BeanUtils.copyProperties(t, target);
		return employee;
	}
	
	


	//public  Employee create(CreateEmployeeCommand createEmployeeCommand) {
//
////		Employee employee = null; // TODO...
// 
////		Employee employee = new Employee();
////		BeanUtils.copyProperties(createEmployeeCommand, employee);
////		Education education = new Education();
//////		BeanUtils.copyProperties(createEducationCommand, education);
////		employee.createEducation(education.getPhone(), education.getSchoolName(), education.getMajor(),
////				education.getEducation(), education.getStartTime(), education.getEndTime());
////		return employee;
//	}


	/**
	 * @author gangming
	 * 
	 */
	public void createEducation(String userNo, String schoolName, String major, String maxEducation, String startTime,
			String endTime) {

		Education education = Education.create(userNo, schoolName, major, maxEducation, startTime, endTime);

		if (educationList == null) {
			educationList = new ArrayList<Education>();
		}
		educationList.add(education);
	}

	public void createWorkExperience(String userNo, String company, String position, String duty, String description,
			String startTime, String endTime) {

		WorkExperience workExperience = WorkExperience.create(userNo, company, position, duty, description, startTime,endTime);

		if (workExperienceList == null) {
			workExperienceList = new ArrayList<WorkExperience>();
		}
		workExperienceList.add(workExperience);
	}


	public EmployeeRepresentation toRepresentation() {
		  EmployeeRepresentation target = new EmployeeRepresentation();
	      BeanUtils.copyProperties(this, target);
	        return target;
	}
	
//	public void createSkill(String userNo, String skillTree, String sonSkill, String skillName, String otherName,
//			String useTime, String skillType,String skillLevel) {
//
//		Skill skill = Skill.create(userNo, skillTree, sonSkill, skillName, otherName, useTime,skillType,skillLevel);
//
//		if (workExperienceList == null) {
//			workExperienceList = new ArrayList<WorkExperience>();
//		}
//		skillList.add(skill);
//	}

	public static Employee create(CreateEmployeeCommand command) {

		Employee employee = Employee.builder().id(newUuid()).userId(command.getUserId()).bearStatus(command.getBearStatus()).
				birthDate(command.getBirthDate()).domicile(command.getDomicile()).domicileAddr(command.getDomicileAddr()).
				familyPhone(command.getFamilyPhone()).registeredType(command.getRegisteredType()).familyLive(command.getFamilyLive()).
				postalCode(command.getPostalCode()).nation(command.getNation()).sOSPersonName(command.getSOSPersonName()).
				sosPersonRelation(command.getSosPersonRelation()).sOSPersonPhone(command.getSOSPersonPhone()).
				emStatus(command.getEmStatus()).educationType(command.getEducationType()).build();
		
		
//		Employee employee =  CommandToEmployeeCoverter.INSTANCE.toEmployee(command);
//		employee.setId(newUuid());
		return employee;
//		Object t = "EEE", target = null;
//		BeanUtils.copyProperties(t, target);
		//return employee;
	}
	
	public static Employee commondToEmployee(CreateEmployeeCommand command) { 
	
		Employee employee = null;
		
		try {
			//employee =  CommandToEmployeeCoverter.INSTANCE.toEmployee(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
      //employee.setId(newUuid());
		return employee;
	}
}
