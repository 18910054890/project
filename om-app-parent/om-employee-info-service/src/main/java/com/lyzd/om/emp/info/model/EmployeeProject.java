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
public class EmployeeProject {
	//@NotNull(message = "项目经历项目名称不能为空")
	private String projectName;
	@NotNull(message = "项目经历角色不能为空")
	private String roleName;
	//@NotNull(message = "项目经历描述不能为空")
	private String description;
	//@NotNull(message = "项目经历开始时间不能为空")
	private String startTime;
	//@NotNull(message = "项目经历结束时间不能为空")
	private String endTime;
	private String id;
	private String userId;
	private String phone;
//	private String phone;

}
