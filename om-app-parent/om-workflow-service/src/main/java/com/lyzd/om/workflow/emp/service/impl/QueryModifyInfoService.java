package com.lyzd.om.workflow.emp.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.file.service.FileInfoRepresentation;
import com.lyzd.om.emp.file.service.FileInfoService;
import com.lyzd.om.emp.info.model.EmployeeChildren;
import com.lyzd.om.emp.info.model.EmployeeProject;
import com.lyzd.om.emp.info.model.LyWorkExperience;
import com.lyzd.om.emp.info.model.WorkExperience;
import com.lyzd.om.emp.info.repository.InnerProjectRepository;
import com.lyzd.om.emp.info.representation.ContractInfoRepresentation;
import com.lyzd.om.emp.info.representation.EductionNewTypeRepresentation;
import com.lyzd.om.emp.info.representation.EmployeeRepresentation;
import com.lyzd.om.emp.info.representation.ResumptionRepresentation;
import com.lyzd.om.emp.info.representation.SkillRepresentation;
import com.lyzd.om.emp.info.service.EmployeeService;
import com.lyzd.om.workflow.emp.convert.AuditEmpInfoRepresentationCover;
import com.lyzd.om.workflow.emp.model.EmpInfoUpdateApplyTask;
import com.lyzd.om.workflow.emp.model.repository.EmployeeTaskRepository;
import com.lyzd.om.workflow.emp.represation.AuditContentRepresentation;
import com.lyzd.om.workflow.emp.represation.AuditResumptionRepresentation;
import com.lyzd.om.workflow.emp.representation.AuditEmpInfoRepresentation;

/**
 * Query info modified by employee.
 * 
 * @author Thinker
 *
 */
@Component
public class QueryModifyInfoService {

	@Autowired
	private EmployeeTaskRepository employeeTaskRepository;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private FileInfoService fileInfoService;
	
	@Autowired
	private InnerProjectRepository innerProjectRepository;

	/**
	 * Query info modified by employee.
	 * 
	 * @param id
	 */
	public AuditContentRepresentation queryAuditContent(String id) {

		EmpInfoUpdateApplyTask et = byId(id);
		// String applyType = et.getApplyType();

		AuditContentRepresentation ar = new AuditContentRepresentation();

		String userId = et.getUserId().toString();

		AuditEmpInfoRepresentation employeeRepresentation = toAuditEmpInfoRepresentation(employeeService.employeeBaseinfoById(userId));
		
		AuditResumptionRepresentation resumptionRepresentation = toAuditResumptionRepresentation(employeeService.employeeSumptionById(userId));
		
		
		EductionNewTypeRepresentation  eductionNewTypeRepresentation = employeeService.employeeMaxEduById(userId);
		
		List<WorkExperience> workExperienceList = employeeService.employeeWorkExperienceById(userId);
		
		List<EmployeeProject> employeeProjectList = employeeService.employeeProjectById(userId);
		
		List<EmployeeChildren> employeeChildrenList = employeeService.employeeChildrenById(userId);
		
		List<LyWorkExperience> lyWorkExperience = employeeService.employeeLyWorkExperienceById(userId);
		
		SkillRepresentation skillRepresentation = employeeService.employeeSkillById(userId);
		
		ContractInfoRepresentation contractInfo = employeeService.employeeContractById(userId);
		
		List<EmployeeProject>  innerProjects = innerProjectRepository.queryInnerProject(userId);

		ar.setBaseInfo(employeeRepresentation);
		ar.setResumption(resumptionRepresentation);
		ar.setEductionType(eductionNewTypeRepresentation);
		ar.setWorkExperienceList(workExperienceList);
		ar.setEmployeeProjectList(employeeProjectList);
		ar.setEmployeeChildrenList(employeeChildrenList);
		ar.setLyWorkExperience(lyWorkExperience);
		ar.setSkillRepresentation(skillRepresentation);
		ar.setContractInfo(contractInfo);
		ar.setInnerProjects(innerProjects);

		List<FileInfoRepresentation> fileInfoList = fileInfoService.queryFileInfo(et.getUserId() + "", null);
		ar.setFileInfoList(fileInfoList);

		return ar;

	}
	
	
    /**
     * Employee's base info in auditor's view.
     * @param employeeRepresentation
     * @return VO
     */
    public AuditEmpInfoRepresentation toAuditEmpInfoRepresentation(EmployeeRepresentation employeeRepresentation)  {
    	
    	//AuditEmpInfoRepresentation target = new AuditEmpInfoRepresentation();
		//BeanUtils.copyProperties(employeeRepresentation, target);
		//return target;
		
		return AuditEmpInfoRepresentationCover.INSTANCE.toVO(employeeRepresentation);
    	
    }
    
    /**
     * Employee's resumption
     * @param resumptionRepresentation
     * @return
     */
    public AuditResumptionRepresentation toAuditResumptionRepresentation(ResumptionRepresentation resumptionRepresentation) {
    	
    	AuditResumptionRepresentation target = new AuditResumptionRepresentation();
		BeanUtils.copyProperties(resumptionRepresentation, target);
		
		return target;
    	
    }
    

	/**
	 * Query by id
	 * 
	 * @param id
	 * @return
	 */
	public EmpInfoUpdateApplyTask byId(String id) {

		return employeeTaskRepository.byId(id);

	}
}
