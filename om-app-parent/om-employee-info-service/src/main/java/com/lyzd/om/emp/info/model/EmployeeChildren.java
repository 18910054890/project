package com.lyzd.om.emp.info.model;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeChildren {
	@NotNull(message = "子女姓名不能为空")
	private String childName;
	@NotNull(message = "子女性别不能为空")
	private String childSex;
	@NotNull(message = "子女出生日期不能为空")
	private String childBirthdate;
	@NotNull(message = "子女身份证号不能为空")
	private String childIdCard;
	private String id;
	@NotNull(message = "报销银行卡号不能为空")
	private String applyCardNo;
	private String userId;
	private String phone;
//	private String phone;
}
