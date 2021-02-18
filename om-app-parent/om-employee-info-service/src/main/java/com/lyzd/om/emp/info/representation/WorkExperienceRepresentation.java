package com.lyzd.om.emp.info.representation;

import lombok.Data;

@Data
public class WorkExperienceRepresentation {
	/** 公司名称 **/
	private String company;
	/** 职位 **/
	private String position;
	/** 所属行业 **/
	private String companyType;
	/** 描述 **/
	private String description;
	/** 开始时间 **/
	private String startTime;
	/** 结束时间 **/
	private String endTime;
	/** 唯一标识uuid **/
	private String id;
}
