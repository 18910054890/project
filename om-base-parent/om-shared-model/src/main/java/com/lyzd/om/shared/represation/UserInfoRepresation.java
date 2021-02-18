package com.lyzd.om.shared.represation;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Thinker
 *
 */
@Data
public class UserInfoRepresation implements Serializable{
	
	/****/
	private static final long serialVersionUID = 6859679957474294561L;

	/**用户头像**/
   @ApiModelProperty(value = "返回用户头像")
	private String avatar;
	
	/**用户名**/
   @ApiModelProperty(value = "返回用户名")
	private String userName;
	
	/**用户角色**/
   @ApiModelProperty(value = "返回用户角色")
	List<MyRoleRepresation> roles;
	
	/**用户菜单权限**/
   @ApiModelProperty(value = "返回用户菜单权限")
	private List<String> permissions;
	

}
