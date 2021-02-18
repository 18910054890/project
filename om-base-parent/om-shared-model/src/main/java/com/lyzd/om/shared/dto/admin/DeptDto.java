package com.lyzd.om.shared.dto.admin;

import com.lyzd.om.shared.model.BaseEntity;

import lombok.Data;

/**
 * @author Thinker
 *
 */
@Data
public class DeptDto extends BaseEntity {


	private Integer id;

    private Integer parentId;

    private String checkArr = "0";

    private String title;
}
