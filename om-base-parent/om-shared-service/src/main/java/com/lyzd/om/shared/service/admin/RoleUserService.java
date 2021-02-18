package com.lyzd.om.shared.service.admin;

import java.util.List;

import com.lyzd.om.shared.entity.admin.MyRoleUser;

/**
 * @author Thinker
 *
 */
public interface RoleUserService {
    /**
     * 返回用户拥有的角色
     * @param userId
     * @return
     */
    List<MyRoleUser> getMyRoleUserByUserId(Integer userId);
}
