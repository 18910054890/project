package com.lyzd.om.shared.entity.admin;

import lombok.Data;

import java.io.Serializable;


/**
 * @author Thinker
 *
 */
@Data
public class MyRoleMenu implements Serializable {

    private static final long serialVersionUID = 8925514045582235875L;

    private Integer roleId;

    private Integer permissionId;
}
