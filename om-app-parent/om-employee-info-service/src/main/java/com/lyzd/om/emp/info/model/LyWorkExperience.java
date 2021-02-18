package com.lyzd.om.emp.info.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LyWorkExperience {
//	private String phone;
	//@NotNull(message = "内部工作经历的部门名称不能为空")
	private String departName;
	//@NotNull(message = "内部工作经历的科室不能为空")
	private String offices;
	//@NotNull(message = "内部工作经历的岗位不能为空")
	private String position;
	//@NotNull(message = "内部工作经历的开始时间不能为空")
	private String startTime;
	//@NotNull(message = "内部工作经历的结束时间不能为空")
	private String endTime;
	//@NotNull(message = "内部工作经历的职级不能为空")
	private String positions;
	private String id;
	private String userId;
	private String phone;
	
	
}
