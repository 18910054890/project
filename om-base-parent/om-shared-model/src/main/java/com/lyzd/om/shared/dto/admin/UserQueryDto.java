package com.lyzd.om.shared.dto.admin;

import lombok.Data;

import java.io.Serializable;


/**
 * @author Thinker
 *
 */
@Data
public class UserQueryDto implements Serializable {

    private String nickName;

    private String userName;

    private Integer deptId;
}
