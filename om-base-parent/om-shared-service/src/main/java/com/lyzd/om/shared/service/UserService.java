package com.lyzd.om.shared.service;

import com.lyzd.om.shared.model.common.SysUser;
import com.lyzd.om.shared.model.common.UserDto;

public interface UserService {

	SysUser saveUser(UserDto userDto);

	SysUser updateUser(UserDto userDto);

	SysUser getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
