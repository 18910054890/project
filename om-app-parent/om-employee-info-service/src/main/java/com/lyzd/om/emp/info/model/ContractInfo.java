package com.lyzd.om.emp.info.model;

import org.springframework.beans.BeanUtils;

import com.lyzd.om.emp.info.representation.ContractInfoRepresentation;
import com.lyzd.om.emp.info.representation.ResumptionRepresentation;
import com.lyzd.om.shared.utils.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ContractInfo {
	private String hireDate;
	private String officialDate;
	private String contractStartTime;
	private String employment;
	private String probationEndDate;
	private String createdAt;
	private String id;
	private String userId;
//	private String phone;
//	private String id;

	public ContractInfo() {
		super();
	}
	
	public ContractInfo(String hireDate, String officialDate, String contractStartTime, String employment,
			String probationEndDate) {
		super();
		this.hireDate = hireDate;
		this.officialDate = officialDate;
		this.contractStartTime = contractStartTime;
		this.employment = employment;
		this.probationEndDate = probationEndDate;
		this.createdAt = DateUtil.now();
	}
	
	
	public ContractInfoRepresentation toRepresentation() {
		ContractInfoRepresentation target = new ContractInfoRepresentation();
		BeanUtils.copyProperties(this, target);
		return target;
	}
}
