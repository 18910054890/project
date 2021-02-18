package com.lyzd.om.emp.info.model;

import com.lyzd.om.shared.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;
@Data
@EqualsAndHashCode(callSuper = true)
@Value
public class MyEmployee extends BaseEntity{

    /**员工姓名**/
    private String userName;
    /**部门名称**/
    private String deptName;
    /**技能**/
    private String skill;
    /**员工号**/
    private String userNo;
    
    
}
