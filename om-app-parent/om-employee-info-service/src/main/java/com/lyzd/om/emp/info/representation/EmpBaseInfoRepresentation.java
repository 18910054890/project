package com.lyzd.om.emp.info.representation;

import java.io.Serializable;
import java.util.List;

import com.lyzd.om.emp.info.model.ContractInfo;

import lombok.Data;

/**
 * @author GM
 *
 */
@Data
public class EmpBaseInfoRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4443041346344391692L;

	private List<EducationRepresentation>  education;
    
	private String jbxx;
	//履职信息
	private String lzxx;
	private List workExperienceList;
	private List projectList;
	private List childrenList;
	private List lyworkExperienceList;
	private String htxx;
	private String zcll;
	private String maxEdu;
	private String emStatus;

}
