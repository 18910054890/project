package com.lyzd.om.emp.info.model;

import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.catalina.LifecycleListener;
import org.springframework.beans.BeanUtils;

import com.lyzd.om.emp.info.representation.ResumptionRepresentation;
import com.lyzd.om.emp.info.representation.SkillRepresentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
//语言能力languageLvl   技术特长skillType   业务类别businessType   爱好特长hobby  专业技术资格名称certificate
//	/** 编号 **/
//	private String phone;
//  @NotNull(message = "请输入性别")
	/** 语言能力 **/
	private String languageLvl;
	/** 技术特长 **/
	@NotNull(message = "技术特长不能为空")
	private List<String> skillType;
	/** 业务类别 **/
	@NotNull(message = "业务类别不能为空")
	private List<String> businessType;
	/** 其他技能 **/
	@NotNull(message = "爱好不能为空")
	private String hobby;
	/** 使用时长 **/
	@NotNull(message = "专业技术资格名称不能为空")
	private String certificate;
	/** 使用时长 **/
	private String id;
	private String userId;
	private String phone;

//	public static Skill create(String userNo, String skillTree, String sonSkill, String skillName, String otherName,
//			String useTime, String[] skillType, String skillLevel) {
//		Skill skill = Skill.builder().id(newUuid()).languageLvl(sonSkill)
//				.hobby(skillName)
//				.build();
//		return skill;
//	}
	
	
	public SkillRepresentation toRepresentation() {
		SkillRepresentation target = new SkillRepresentation();
		BeanUtils.copyProperties(this, target);
		return target;
	}
}
