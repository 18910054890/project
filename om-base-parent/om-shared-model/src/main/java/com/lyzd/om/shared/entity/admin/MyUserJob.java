package com.lyzd.om.shared.entity.admin;

import lombok.Data;

import java.io.Serializable;


/**
 * @author Thinker
 *
 */
@Data
public class MyUserJob implements Serializable {

    private static final long serialVersionUID = 8925514045582235321L;

    private Integer userId;

    private Integer jobId;
}
