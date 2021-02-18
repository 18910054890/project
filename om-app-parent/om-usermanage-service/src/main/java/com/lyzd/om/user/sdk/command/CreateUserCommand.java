package com.lyzd.om.user.sdk.command;

//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thinker
 *
 */
//@Value
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCommand {

    @NotNull(message = "姓名不能为空")
    private String name;
    
    @NotNull(message = "请输入年龄")
    private Integer age;

}
