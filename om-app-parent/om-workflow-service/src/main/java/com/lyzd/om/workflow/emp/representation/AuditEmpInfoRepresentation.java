package com.lyzd.om.workflow.emp.representation;


import lombok.Data;

/**
 * Employee info displayed for auditor.
 * 
 * @author Thinker
 *
 */
@Data
public class AuditEmpInfoRepresentation{
	
	
	private String registeredType;// 户籍性质
	private String familyLive;// 家庭地址
	private String familyPhone;// 家庭电话
	private String postalCode;// 邮编
	private String nation;// 民族
	private String sOSPersonName;// 紧急联系人
	private String sosPersonRelation;// 紧急联系人关系
	private String sOSPersonPhone   ;// 紧急联系人电话
	
	/**生育状况**/
	private String giveBirth;
}
