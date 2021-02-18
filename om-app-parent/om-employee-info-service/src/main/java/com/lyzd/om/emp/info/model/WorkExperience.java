package com.lyzd.om.emp.info.model;

import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.lyzd.om.emp.info.representation.ResumptionRepresentation;
import com.lyzd.om.emp.info.representation.WorkExperienceRepresentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author GM
 *
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience {
//	/** 电话号码 **/
//	private String phone;
//  @NotNull(message = "请输入性别")
	/** 公司名称 **/

	private String company;
	/** 职位 **/

	private String position;
	/** 所属行业 **/
	@NotNull(message = "外部工作经历的所属行业不能为空")
	private String companyType;
	/** 描述 **/

	private String description;
	/** 开始时间 **/
	
	private String startTime;
	/** 结束时间 **/
	
	private String endTime;
	/** 唯一标识uuid **/
	private String id;
	private String userId;
	private String phone;

	public static WorkExperience create(String phone, String company, String position, String companyType, String description,
			String startTime, String endTime) {
		WorkExperience workExperience = WorkExperience.builder().id(newUuid()).company(company)
				.companyType(companyType).description(description).startTime(startTime).endTime(endTime).build();
		return workExperience;
	}
	
//	public List<WorkExperienceRepresentation> toRepresentation() {
//		WorkExperienceRepresentation target = new WorkExperienceRepresentation();
//		BeanUtils.copyProperties(this, target);
//		return target;
//	}
}