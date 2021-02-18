package com.lyzd.om.emp.info.service;

import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.util.List;

import org.apache.catalina.LifecycleListener;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.lyzd.om.emp.info.model.ContractInfo;
import com.lyzd.om.emp.info.model.Employee;
import com.lyzd.om.emp.info.model.EmployeeChildren;
import com.lyzd.om.emp.info.model.EmployeeProject;
import com.lyzd.om.emp.info.model.LyWorkExperience;
import com.lyzd.om.emp.info.model.Resumption;
import com.lyzd.om.emp.info.model.Skill;
import com.lyzd.om.emp.info.model.WorkExperience;
import com.lyzd.om.emp.info.repository.ContractInfoRepository;
import com.lyzd.om.emp.info.repository.EmployeeChildrenRepository;
import com.lyzd.om.emp.info.repository.EmployeeProjectRepository;
import com.lyzd.om.emp.info.repository.EmployeeRepository;
import com.lyzd.om.emp.info.repository.InnerProjectRepository;
import com.lyzd.om.emp.info.repository.LyWorkExperienceRepository;
import com.lyzd.om.emp.info.repository.ResumptionRepository;
import com.lyzd.om.emp.info.repository.SkillRepository;
import com.lyzd.om.emp.info.repository.WorkExperienceRepository;
import com.lyzd.om.emp.info.sdk.commond.CreateEmployeeCommand;
import com.lyzd.om.emp.sdk.audit.support.AuditableSevice;
import com.lyzd.om.emp.sdk.command.EmployeeApplyCommand;
import com.lyzd.om.spring.common.dto.JwtUserDto;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class SaveEmployeeInfoService  {

	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AuditableSevice employeeApplyService;
	
	@Autowired
	private UpdateEmployeeInfoService updateEmployeeInfoService;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private InnerProjectRepository innerProjectRepository;
	@Autowired
	private LyWorkExperienceRepository lyWorkExperienceRepository;
	@Autowired
	private  ResumptionRepository  resumptionRepository;
	@Autowired
	private EmployeeChildrenRepository employeeChildrenRepository;
	@Autowired
	private WorkExperienceRepository workExperienceRepository;
	@Autowired
	private EmployeeProjectRepository  employeeProjectRepository;
	@Autowired
	private SkillRepository  skillRepository;
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Transactional
	public void save(CreateEmployeeCommand command,String userId) {

		saveEmployeeInfo(command, userId);
		saveResumptions(command, userId);
		//更新子女
		String bearStatu=command.getBearStatus();
		if("1".equals(bearStatu)) {  //有子女
			saveChildInfo(command, userId);
		}
		saveLyWorkExperience(command, userId);
		saveWorkExperience(command, userId);
		saveInnerProject(command, userId);
		saveProject(command, userId);
		saveSkill(command, userId);
	}
	
	public boolean saveEmpInfo(JwtUserDto user,CreateEmployeeCommand createEmployeeCommand) {
		EmployeeApplyCommand command = new EmployeeApplyCommand();
		String userId=user.getMyUser().getUserId().toString();
		command.setUserId(user.getMyUser().getUserId());
		createEmployeeCommand.setUserId((user.getMyUser().getUserId().toString()));
		command.setBusinessData(createEmployeeCommand);
		String saveFlag=createEmployeeCommand.getSaveFlag();
		
		if("1".equals(saveFlag)) {  //保存
			save(createEmployeeCommand,user.getMyUser().getUserId().toString());
		}else if("0".equals(saveFlag)) {
			command.setUserId(user.getMyUser().getUserId());
			command.setName(user.getUsername());
			command.setDeptName(user.getMyUser().getDeptName());
			command.setApplyType("员工信息更新");
			employeeApplyService.apply(command, updateEmployeeInfoService);
    	}
		return true;
	} 
	
	public boolean saveEmployeeInfo(CreateEmployeeCommand createEmployeeCommand, String userId) {
		Employee employee=employeeRepository.employeeBaseinfoById(userId);    //看id
		ContractInfo contractInfo=contractInfoRepository.employeeContractByUserId(userId);
	
//		if(contractInfoList!=null) {
//			contractInfo=contractInfoList.get(0);
//		}
		if(contractInfo!=null) {
			contractInfo.setContractStartTime(createEmployeeCommand.getContractStartTime());
			contractInfo.setUserId(userId);
			contractInfoRepository.saveContractById(contractInfo);
		}
		try {
			if(employee!=null) {
				if ("0".equals(createEmployeeCommand.getSaveFlag())) {
					employee.setEmStatus("1");
				}
				employee.setBearStatus(createEmployeeCommand.getBearStatus());
				employee.setBirthDate(createEmployeeCommand.getBirthDate());
				employee.setDomicile(createEmployeeCommand.getDomicile());
				employee.setDomicileAddr(createEmployeeCommand.getDomicileAddr());
				employee.setFamilyPhone(createEmployeeCommand.getFamilyPhone());
				employee.setFamilyLive(createEmployeeCommand.getFamilyLive());
				employee.setPostalCode(createEmployeeCommand.getPostalCode());
				employee.setNation(createEmployeeCommand.getNation());
				employee.setSOSPersonName(createEmployeeCommand.getSOSPersonName());
				employee.setSosPersonRelation(createEmployeeCommand.getSosPersonRelation());
				employee.setSOSPersonPhone(createEmployeeCommand.getSOSPersonPhone());
				//employee.setEmStatus(createEmployeeCommand.getEmStatus());
				employee.setEducationType(createEmployeeCommand.getEducationType());
				employee.setUserId(userId);
				
				employeeService.saveEmployee(employee);
			}
		} catch (Exception e) {
			log.error("个人信息页面保存用户基本信息失败！");
			e.printStackTrace();
		}
		log.info("个人信息页面成功保存用户基本信息！");
		return true;
	}
	
	
	/**
	 * 个人信息页面保存履历信息
	 * @param resumption
	 * @param userId
	 */
	public boolean saveResumptions(CreateEmployeeCommand createEmployeeCommand, String userId) {
		try {
			Resumption resumption=resumptionRepository.employeeSumptionById(userId);
			resumption.setFirstSocialSecurity(createEmployeeCommand.getFirstSocialSecurity());
			resumption.setDistrict(createEmployeeCommand.getDistrict());
			resumption.setContractStatus(createEmployeeCommand.getContractStatus());
			resumption.setUserStatus(createEmployeeCommand.getUserStatus());
			resumption.setCardFlag(createEmployeeCommand.getCardFlag());
			resumption.setCardNo(createEmployeeCommand.getCardNo());
			resumption.setOpenbankname(createEmployeeCommand.getOpenbankname());
			resumption.setGraduateDate(createEmployeeCommand.getGraduateDate());
			resumption.setOfficialWorkDate(createEmployeeCommand.getOfficialWorkDate());
			resumption.setUserId(userId);
			resumptionRepository.saveResumption(resumption);
		} catch (Exception e) {
			log.error("保存履历信息失败！");
			e.printStackTrace();
		}
		log.info("保存履历信息成功！");
		return true;
	}

	/**
	 * lj编辑个人信息页面，保存子女信息
	 * @param createEmployeeCommand
	 * @param userId
	 * @return
	 */
	public String saveChildInfo(CreateEmployeeCommand createEmployeeCommand, String userId) {
		String message="";
		try {
			String id=employeeChildrenRepository.queryEmployeeChildrenById(userId).getId();
			EmployeeChildren newEmployeeChildren=createEmployeeCommand.getEmployeeChildren();
			if(newEmployeeChildren!=null) {
				newEmployeeChildren.setId(id);
				newEmployeeChildren.setUserId(userId);
				employeeChildrenRepository.saveEmployeeChildree(newEmployeeChildren);
			}
			//message=checkEmployChildren(newEmployeeChildren);
			if(!"".equals(message)) {
				return message;
			}
//			if(oldEmployeeChildren!=null) {
//				oldEmployeeChildren.setChildName(newEmployeeChildren.getChildName());
//				oldEmployeeChildren.setChildSex(newEmployeeChildren.getChildSex());
//				oldEmployeeChildren.setChildBirthdate(newEmployeeChildren.getChildBirthdate());
//				oldEmployeeChildren.setChildIdCard(newEmployeeChildren.getChildIdCard());
//				oldEmployeeChildren.setApplyCardNo(newEmployeeChildren.getApplyCardNo());
//				oldEmployeeChildren.setUserId(userId);
//				employeeChildrenRepository.saveEmployeeChildree(oldEmployeeChildren);
//			}
		} catch (Exception e) {
			log.error("保存子女信息失败!");
			e.printStackTrace();
		}
		log.info("保存子女信息成功！");
		return message;
	}
	
	
	public String checkEmployChildren(EmployeeChildren newEmployeeChildren) {
		String message="";
		if(Strings.isBlank(newEmployeeChildren.getChildName())||Strings.isEmpty(newEmployeeChildren.getChildName())) {
			message="子女信息的子女姓名不能为空";
		}
		if(Strings.isBlank(newEmployeeChildren.getChildSex())||Strings.isEmpty(newEmployeeChildren.getChildSex())) {
			message="子女信息的子女性别不能为空";
		}
		if(Strings.isBlank(newEmployeeChildren.getChildBirthdate())||Strings.isEmpty(newEmployeeChildren.getChildBirthdate())) {
			message="子女信息的子女出生日期不能为空";
		}
		if(Strings.isBlank(newEmployeeChildren.getChildIdCard())||Strings.isEmpty(newEmployeeChildren.getChildIdCard())) {
			message="子女信息的子女身份证号不能为空";
		}
		if(Strings.isBlank(newEmployeeChildren.getApplyCardNo())||Strings.isEmpty(newEmployeeChildren.getApplyCardNo())) {
			message="子女信息的子女报销卡号不能为空";
		}
		return message;
	
	}
	
	/**
	 * 保存个人信息页面的内部工作经验
	 * @param createEmployeeCommand
	 * @param userId
	 * @return
	 */
	public boolean saveLyWorkExperience(CreateEmployeeCommand createEmployeeCommand, String userId) {
		List<LyWorkExperience> newLyWorkExperienceList =createEmployeeCommand.getLyworkExperienceList();
		
		try {
			if(newLyWorkExperienceList!=null) {
				//lyWorkExperienceRepository.deleteLyWorkExperienceByUserId(userId);
				lyWorkExperienceRepository.deleteLyWorkExperienceByUserId(userId);
				for(int i=0;i<newLyWorkExperienceList.size();i++) {
					LyWorkExperience newLyWorkExperience=newLyWorkExperienceList.get(i);
					if(newLyWorkExperience!=null) {
						
						if(Strings.isEmpty(newLyWorkExperience.getId()) ||Strings.isBlank(newLyWorkExperience.getId())) {
							newLyWorkExperience.setUserId(userId);
							newLyWorkExperience.setId(newUuid());
							lyWorkExperienceRepository.saveLyWorkExperience(newLyWorkExperience);
						}else {
						
							//LyWorkExperience oldLyWorkExperience=lyWorkExperienceRepository.lyWorkExperienceById(newLyWorkExperience.getId());
//							oldLyWorkExperience.setStartTime(newLyWorkExperience.getStartTime());
//							oldLyWorkExperience.setEndTime(newLyWorkExperience.getEndTime());
//							oldLyWorkExperience.setDepartName(newLyWorkExperience.getDepartName());
//							oldLyWorkExperience.setOffices(newLyWorkExperience.getOffices());
//							oldLyWorkExperience.setPosition(newLyWorkExperience.getPosition());
//							oldLyWorkExperience.setPositions(newLyWorkExperience.getPositions());
//							oldLyWorkExperience.setUserId(userId);
							//lyWorkExperienceRepository.saveLyWorkExperience(oldLyWorkExperience);
							
							newLyWorkExperience.setUserId(userId);
							
							lyWorkExperienceRepository.saveLyWorkExperience(newLyWorkExperience);
						}
					}else {
						log.error("外部工作经验为空！");
					}
						log.info("外部工作经验更新成功！");
				}
			}
		} catch (Exception e) {
			log.error("外部工作经验更新失败！");
			e.printStackTrace();
		}
		return true;
	}
	
	
	/**
	 * 保存个人信息页面的工作经验
	 * @param command
	 * @param userId
	 * @return
	 */
	public boolean saveWorkExperience(CreateEmployeeCommand command, String userId) {
		List<WorkExperience> newWorkExperienceList =command.getWorkExperienceList();
		
		try {
			if(newWorkExperienceList!=null) {
				for(int i=0;i<newWorkExperienceList.size();i++) {
					WorkExperience newWorkExperience=newWorkExperienceList.get(i);
//					if(Strings.isEmpty(newWorkExperience.getId()) ||Strings.isBlank(newWorkExperience.getId())) {
//						newWorkExperience.setUserId(userId);	
//						workExperienceRepository.saveNewWorkExperience(newWorkExperience);
//					}else {
//						WorkExperience oldWorkExperience=employeeRepository.workExperienceById(newWorkExperience.getId());
//						oldWorkExperience.setStartTime(newWorkExperience.getStartTime());
//						oldWorkExperience.setEndTime(newWorkExperience.getEndTime());
//						oldWorkExperience.setCompany(newWorkExperience.getCompany());
//						oldWorkExperience.setCompanyType(newWorkExperience.getCompanyType());
//						oldWorkExperience.setPosition(newWorkExperience.getPosition());
//						oldWorkExperience.setDescription(newWorkExperience.getDescription());
//						oldWorkExperience.setUserId(command.getUserId());
						newWorkExperience.setUserId(userId);
						workExperienceRepository.saveWorkExperience(newWorkExperience);
					//}	
				}
				log.info("外部工作经验更新成功！");
			}
		} catch (Exception e) {
			log.error("外部工作经验更新失败！");
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean saveProject(CreateEmployeeCommand command, String userId) {
		List<EmployeeProject> newProjectList = command.getProjectList();
		try {
			if(newProjectList!=null) {
				for(int i=0;i<newProjectList.size();i++) {
					EmployeeProject newEmployeeProject=newProjectList.get(i);	
//					if(Strings.isEmpty(newEmployeeProject.getId()) ||Strings.isBlank(newEmployeeProject.getId())) {
//						newEmployeeProject.setUserId(userId);
//						employeeProjectRepository.saveNewEmployeeProject(newEmployeeProject);
//					}else {
//						EmployeeProject oldInnerProject=(employeeRepository.queryEmployeeProjectById(newEmployeeProject.getId()));
//						oldInnerProject.setStartTime(newEmployeeProject.getStartTime());
//						oldInnerProject.setEndTime(newEmployeeProject.getEndTime());
//						oldInnerProject.setProjectName(newEmployeeProject.getProjectName());
//						oldInnerProject.setRoleName(newEmployeeProject.getRoleName());
//						oldInnerProject.setDescription(newEmployeeProject.getDescription());
//						oldInnerProject.setUserId(userId);
						newEmployeeProject.setUserId(userId);
						employeeProjectRepository.saveEmployeeProject(newEmployeeProject);
					//}
				}
				 log.info("项目经验更新成功！");
			}
		} catch (Exception e) {
			log.error("项目经验更新失败！");
			e.printStackTrace();
		}
		return true;
	}

	public boolean saveInnerProject(CreateEmployeeCommand command, String userId) {
		List<EmployeeProject> newProjectList = command.getInnerProjectList();
		try {
			if(newProjectList!=null) {
				innerProjectRepository.deleteInnerProjectByUserId(userId);
				//innerProjectRepository.deleteInnerProjectByUserId(userId);
				for(int i=0;i<newProjectList.size();i++) {
					EmployeeProject newEmployeeProject=newProjectList.get(i);
					if(Strings.isEmpty(newEmployeeProject.getId()) ||Strings.isBlank(newEmployeeProject.getId())) {
						newEmployeeProject.setUserId(userId);
						newEmployeeProject.setId(newUuid());
						innerProjectRepository.saveInnerProject(newEmployeeProject);
					}else {
//						EmployeeProject oldInnerProject=innerProjectRepository.queryInnerProjectById(newEmployeeProject.getId());
//						
//						oldInnerProject.setStartTime(newEmployeeProject.getStartTime());
//						oldInnerProject.setEndTime(newEmployeeProject.getEndTime());
//						oldInnerProject.setProjectName(newEmployeeProject.getProjectName());
//						oldInnerProject.setRoleName(newEmployeeProject.getRoleName());
//						oldInnerProject.setDescription(newEmployeeProject.getDescription());
//						oldInnerProject.setUserId(userId);
//						innerProjectRepository.saveInnerProject(oldInnerProject);
						newEmployeeProject.setUserId(userId);
						
						innerProjectRepository.saveInnerProject(newEmployeeProject);
					}
				}
				log.info("内部项目经验更新成功！");
			}
		} catch (Exception e) {
			log.error("内部项目经验更新失败！");
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	/**
	 * 保存个人信息页的专长技能
	 * @param command
	 * @param userId
	 * @return
	 */
	public boolean saveSkill(CreateEmployeeCommand command, String userId) {
		Skill oldSkill =skillRepository.employeeSkillById(userId);
		Skill newSkill = command.getZcll();
		newSkill.setLanguageLvl(oldSkill.getLanguageLvl());
		newSkill.setId(oldSkill.getId());
		newSkill.setUserId(userId);
		if(newSkill!=null){
			skillRepository.saveSkill(newSkill);
			log.info("专长技能更新成功！");
		}else {
			log.error("专长技能为空 ！");
		}
			
	//	}
		return true;
	}
	
}
