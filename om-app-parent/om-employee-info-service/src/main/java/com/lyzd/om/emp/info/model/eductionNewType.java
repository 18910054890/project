package com.lyzd.om.emp.info.model;

import org.springframework.beans.BeanUtils;

import com.lyzd.om.emp.info.representation.EductionNewTypeRepresentation;
import com.lyzd.om.emp.info.representation.ResumptionRepresentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class eductionNewType {
	private String maxEducation;
	private String educationType;
	
	
	public EductionNewTypeRepresentation toRepresentation() {
		EductionNewTypeRepresentation target = new EductionNewTypeRepresentation();
		BeanUtils.copyProperties(this, target);
		return target;
	}
}
