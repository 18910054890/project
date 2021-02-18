package com.lyzd.om.shared.represation;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Thinker
 *
 */
@Data
public class MyRoleRepresation implements Serializable { 
	
	/****/
	private static final long serialVersionUID = 1L;
	private Integer roleId;
	private String roleName;
	private String dataScope;
	private String description;

}
