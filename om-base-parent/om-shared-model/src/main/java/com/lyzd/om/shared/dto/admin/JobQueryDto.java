package com.lyzd.om.shared.dto.admin;

import lombok.Data;

import java.io.Serializable;


/**
 * @author Thinker
 *
 */
@Data
public class JobQueryDto implements Serializable {
    private String queryName;

    private Integer queryStatus;
}
