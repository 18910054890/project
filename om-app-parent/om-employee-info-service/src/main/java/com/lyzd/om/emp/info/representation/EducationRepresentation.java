package com.lyzd.om.emp.info.representation;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class EducationRepresentation implements Serializable {
	private List educationList;
	private String maxEducation;
	private String educationType;
}
