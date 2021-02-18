package com.lyzd.om.emp.info.sdk.commond;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author GM
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEducationCommand {
//	@NotNull(message = "姓名不能为空")
	private String userNo;

//    @NotNull(message = "请输入性别")
	private String schoolName;

	private String major;
	private String maxEducation;
	private String startTime;
	private String endTime;
}
