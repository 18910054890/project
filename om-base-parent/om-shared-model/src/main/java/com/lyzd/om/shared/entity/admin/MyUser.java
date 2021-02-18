package com.lyzd.om.shared.entity.admin;

import com.lyzd.om.shared.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Thinker
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MyUser extends BaseEntity{

	private static final long serialVersionUID = 773479832718009411L;

	private Integer userId;

    private Integer deptId;
    
    private String deptName;

    private String userName;

    private String password;

    private String nickName;

    private String phone;

    private String email;

    private Integer status;
    
    private String firstLogin;
//    private String firstLogin;

    public interface Status {
        int LOCKED = 0;
        int VALID = 1;
    }

    private Integer roleId;
    /** 岗位组 */
    private Integer[] jobIds;

    /**
     * 判断是否为admin用户
     * @return
     */
    public boolean isAdmin()
    {
        return isAdmin(this.getUserId());
    }

    public static boolean isAdmin(Integer userId)
    {
        return userId != null && 1L == userId;
    }

    public MyUser(Integer userId)
    {
        this.setUserId(userId);
    }
    
}
