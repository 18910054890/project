package com.lyzd.om.emp.info.representation;

import java.util.List;

import lombok.Data;

@Data
public class SkillRepresentation {
	/** 语言能力 **/
	private String languageLvl;
	/** 技术特长 **/
	private List<String> skillType;
	/** 业务类别 **/
	private List<String> businessType;
	/** 其他技能 **/
	private String hobby;
	/** 使用时长 **/
	private String certificate;
	/** 使用时长 **/
	private String id;
	private String userId;
}
