package com.lyzd.om.emp.info.sdk.commond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSkillCommand {
	/**编号**/
	private String userNo;
//  @NotNull(message = "请输入性别")
	/**主技能**/
	private String skillTree;
	/**子技能**/
	private String sonSkill;
	/**技能名称**/
	private String skillName;
	/**其他技能**/
	private String otherName;
	/**使用时长**/
	private String useTime;
	/**技能类型**/
	private String skillType;
	/**技能等级**/
	private String skillLevel;
}
