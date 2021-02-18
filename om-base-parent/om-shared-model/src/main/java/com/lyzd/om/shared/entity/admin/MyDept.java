package com.lyzd.om.shared.entity.admin;

import com.lyzd.om.shared.model.BaseEntity;

import lombok.Data;


/**
 * @author Thinker
 *
 */
@Data
public class MyDept extends BaseEntity {
    private static final long serialVersionUID = 8925514045582235633L;

    private Integer deptId;

    private Integer parentId;

    private String ancestors;

    private String deptName;

    private Integer sort;

    private Integer status;

}
