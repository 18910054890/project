package com.lyzd.om.shared.entity.admin;

import lombok.Data;

import java.io.Serializable;


/**
 * @author Thinker
 *
 */
@Data
public class MyRoleDept implements Serializable {

    private static final long serialVersionUID = 8925514042332235875L;

    private Integer roleId;

    private Integer deptId;
}
