package com.lyzd.om.workflow.emp.represation;

import java.io.Serializable;
import java.util.List;

import com.lyzd.om.emp.file.service.FileInfoRepresentation;
import com.lyzd.om.emp.info.model.EmployeeChildren;
import com.lyzd.om.emp.info.model.EmployeeProject;
import com.lyzd.om.emp.info.model.LyWorkExperience;
import com.lyzd.om.emp.info.model.WorkExperience;
import com.lyzd.om.emp.info.representation.ContractInfoRepresentation;
import com.lyzd.om.emp.info.representation.EductionNewTypeRepresentation;
import com.lyzd.om.emp.info.representation.SkillRepresentation;
import com.lyzd.om.workflow.emp.representation.AuditEmpInfoRepresentation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Thinker
 *
 */
@Data
public class AuditContentRepresentation implements Serializable {
	
	/****/
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "返回员工基本信息")
	private AuditEmpInfoRepresentation baseInfo;
	
	@ApiModelProperty(value = "返回教育类型")
	private EductionNewTypeRepresentation eductionType;
	
	@ApiModelProperty(value = "返回Resumption")
	private AuditResumptionRepresentation resumption;
	
	@ApiModelProperty(value = "返回工作经历")
	private List<WorkExperience> workExperienceList;
	
	@ApiModelProperty(value = "返回项目经验")
	private List<EmployeeProject> employeeProjectList;
	
	@ApiModelProperty(value = "返回子女信息")
	private List<EmployeeChildren> employeeChildrenList;
	
	@ApiModelProperty(value = "返回龙盈工作经历")
	private List<LyWorkExperience> lyWorkExperience;
	
	@ApiModelProperty(value = "返回技能")
	private SkillRepresentation skillRepresentation;
	
	@ApiModelProperty(value = "返回合同信息")
	private ContractInfoRepresentation contractInfo;
	
	@ApiModelProperty(value = "返回内部项目经历")
	private List<EmployeeProject>  innerProjects;
	
	/**文件信息列表**/
	 @ApiModelProperty(value = "返回文件信息列表")
	private List<FileInfoRepresentation> fileInfoList;
	

}
