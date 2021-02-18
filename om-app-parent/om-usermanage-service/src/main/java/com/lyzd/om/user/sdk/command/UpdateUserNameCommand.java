package com.lyzd.om.user.sdk.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Thinker
 *
 */
//@Value

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserNameCommand {
	
	
	 //@NotBlank(message = "id不能为空")
	@NotNull(message = "id不能为空")
	 private String id;

    @NotNull(message = "姓名不能为空")
    private String newName;

}
