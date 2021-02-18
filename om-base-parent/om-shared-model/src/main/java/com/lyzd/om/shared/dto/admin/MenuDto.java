package com.lyzd.om.shared.dto.admin;

import lombok.Data;

import java.io.Serializable;


/**
 * @author Thinker
 *
 */
@Data
public class MenuDto implements Serializable {

    private Integer id;

    private Integer parentId;

    private String checkArr = "0";

    private String title;
}
