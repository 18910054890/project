package com.lyzd.om.emp.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.repository.ContractInfoRepository;
import com.lyzd.om.emp.info.representation.ContractInfoRepresentation;
import com.lyzd.om.emp.info.representation.SkillRepresentation;

@Component
public class ContractInfoService {
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	public ContractInfoRepresentation employeeContractByUserId(String userId) {
		return contractInfoRepository.employeeContractByUserId(userId).toRepresentation();
	}

}
