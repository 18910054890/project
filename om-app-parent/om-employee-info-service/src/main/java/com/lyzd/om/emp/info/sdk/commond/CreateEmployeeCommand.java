package com.lyzd.om.emp.info.sdk.commond;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.lyzd.om.emp.info.model.Education;
import com.lyzd.om.emp.info.model.EmployeeChildren;
import com.lyzd.om.emp.info.model.EmployeeProject;
import com.lyzd.om.emp.info.model.LyWorkExperience;
import com.lyzd.om.emp.info.model.Skill;
import com.lyzd.om.emp.info.model.WorkExperience;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {
    //基本信息
	@NotNull(message = "出生日期不能为空")
	private String birthDate;// 出生日期
	@NotNull(message = "户籍所在地省不能为空")
	private String domicile;// 户籍所在地省
	@NotNull(message = "户籍所在地地址不能为空")
	private String domicileAddr;// 户籍所在地地址
	@NotNull(message = "户籍性质不能为空")
	private String registeredType;// 户籍性质
	@NotNull(message = "家庭地址不能为空")
	private String familyLive;// 家庭地址
	
	private String familyPhone;// 家庭电话
	@NotNull(message = "邮编不能为空")
	private String postalCode;// 邮编
	@NotNull(message = "民族不能为空")
	private String nation;// 民族
	@NotNull(message = "紧急联系人不能为空")
	private String sOSPersonName;// 紧急联系人
	@NotNull(message = "紧急联系人关系不能为空")
	private String sosPersonRelation;// 紧急联系人关系
	@NotNull(message = "紧急联系人电话不能为空")
	private String sOSPersonPhone   ;// 紧急联系人电话
	private String emStatus;// 员工信息审核状态     0 
	private String saveFlag;//提交或保存标识    0:：提交            1：保存
	@NotNull(message = "生育状况不能为空")
	private String bearStatus;
	//履职信息
	@NotNull(message = "华夏银行工资卡号不能为空")
	private String cardNo;//华夏银行工资卡号
	@NotNull(message = "是否在北京首次参保不能为空")
	private String firstSocialSecurity;//是否在北京首次参保
	@NotNull(message = "员工状态不能为空")
	private String userStatus;//员工状态
	@NotNull(message = "地域不能为空")
	private String district;//地域
	@NotNull(message = "类型不能为空")
	private String contractStatus;//类型
	@NotNull(message = "开户行不能为空")
	private String openbankname;//开户行
	@NotNull(message = "是否使用已有的华夏银行卡作为工资卡不能为空")
	private String cardFlag;//使用已有的华夏银行卡作为工资卡
	//合同信息
	private String contractStartTime;//劳动合同开始日期
	private String name;// 姓名
	private String userId;
	private String idcard;// 身份证
	@NotNull(message = "最高学历分类不能为空")
	private String educationType ; //最高学历分类
	private EmployeeChildren employeeChildren;
	@Valid
	private Skill zcll;
	@NotNull(message = "毕业时间不能为空")
	private String graduateDate;
	@NotNull(message = "参加工作时间不能为空")
	private String officialWorkDate;
	private  List<Education> educationList;//教育经历集合
	private  List<WorkExperience> workExperienceList;//工作经历集合
	//private  List<EmployeeChildren> childrenList;//子女集合
	//@Valid
	private  EmployeeChildren children;
	private  List<EmployeeProject> projectList;//项目集合
	private  List<EmployeeProject> innerProjectList;//内部项目集合
	private  List<LyWorkExperience> lyworkExperienceList;//内部工作经历集合
}
