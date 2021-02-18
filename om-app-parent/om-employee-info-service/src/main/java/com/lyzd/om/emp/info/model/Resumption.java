package com.lyzd.om.emp.info.model;

import org.springframework.beans.BeanUtils;

import com.lyzd.om.emp.info.representation.ResumptionRepresentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resumption {
	private String userNo;
	private String firstSocialSecurity;
	private String userStatus;
	private String district;
	private String contractStatus;
	private String employmentStatus;
	private String applicationSource;
	private String companyEmail ;
	private String cardNo;
	private String openbankname;
	private String workingSeniority;
	private String officeDate;
	private String sendOfferTime;
	private String comInsideLevel;//公司内部级别
	private String deptName ;
	private String comInsidepost;//公司内部岗位
	private String csName;
	private String csProject;
	private String hxLevl;
	private String territory;//领域
	private String headman;
	private String secondaryExamine ;
	private String departmentHead;
	private String divisionManager;
	private String hr;
	private String cardFlag;//使用已有的华夏银行卡作为工资卡
	//毕业日期
	private String graduateDate;
	//参加第一份工作的入职时间
	private String officialWorkDate;
	//行内级别 
	private String  industryLevel;
	 //司龄 
	private Integer companyWorkTime;
	private String id;
	private String userId;
	private String phone;
	public ResumptionRepresentation toRepresentation() {
		ResumptionRepresentation target = new ResumptionRepresentation();
		BeanUtils.copyProperties(this, target);
		return target;
	}
}
