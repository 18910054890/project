package com.lyzd.om.emp.info.sdk.commond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GM
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkExperienceCommand {
	/**编号**/
	private String userNo;
//  @NotNull(message = "请输入性别")
	/**公司名称**/
	private String company;
	/**职位**/
	private String position;
	/**职责**/
	private String duty;
	/**描述**/
	private String description;
	/**开始时间**/
	private String startTime;
	/**结束时间**/
	private String endTime;
}
