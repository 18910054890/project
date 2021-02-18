package com.lyzd.om.shared.entity.admin;

import lombok.Data;

import java.io.Serializable;


/**
 * @author Thinker
 *
 */
@Data
public class MyRoleUser implements Serializable {

    private static final long serialVersionUID = 8545514045582235838L;

    private Integer userId;

    private Integer roleId;
}
