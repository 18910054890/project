package com.lyzd.om.shared.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyzd.om.shared.dao.admin.RoleUserDao;
import com.lyzd.om.shared.entity.admin.MyRoleUser;
import com.lyzd.om.shared.service.admin.RoleUserService;

/**
 * @author Thinker
 *
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;
    @Override
    public List<MyRoleUser> getMyRoleUserByUserId(Integer userId) {
       return roleUserDao.getMyRoleUserByUserId(userId);

    }
}
