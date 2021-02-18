package com.lyzd.om.emp.info.representation;

import org.springframework.beans.BeanUtils;

import lombok.Data;

@Data
public class ResumptionRepresentation {
	private String userNo;
	private String firstSocialSecurity;
	private String userStatus;
	private String district;
	private String contractStatus;
	private String employmentStatus;
	private String applicationSource;
	private String companyEmail;
	private String cardNo;
	private String openbankname;
	private String workingSeniority;
	private String officeDate;
	private String sendOfferTime;
	private String comInsideLevel;
	private String deptName;
	private String comInsidepost;
	private String csName;
	private String csProject;
	private String hxLevl;
	private String territory;
	private String headman;
	private String secondaryExamine;
	private String departmentHead;
	private String divisionManager;
	private String hr;
	private String cardFlag;//使用已有的华夏银行卡作为工资卡
	//毕业日期
	private String graduateDate;
	//参加工作时间
	private String officialWorkDate;
	//行内级别 
	private String  industryLevel;
 	//司龄
	private Integer companyWorkTime;


}
