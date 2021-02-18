package com.lyzd.om.shared.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lyzd.om.shared.dao.admin.MenuDao;
import com.lyzd.om.shared.dto.admin.MenuIndexDto;
import com.lyzd.om.shared.entity.admin.MyRole;
import com.lyzd.om.shared.represation.MyRoleRepresation;
import com.lyzd.om.shared.represation.UserInfoRepresation;
import com.lyzd.om.spring.common.dto.JwtUserDto;

/**
 * @author Thinker
 *
 */
@Component
public class UserInfoQueryService {

	@Autowired
	private MenuDao menuDao;

	/**
	 * @param jwtUserDto
	 * @return
	 */
	public UserInfoRepresation getUserInfo(JwtUserDto jwtUserDto) {

		Integer userId = jwtUserDto.getMyUser().getUserId();

		UserInfoRepresation ur = new UserInfoRepresation();
		ur.setUserName(jwtUserDto.getMyUser().getUserName());

		// MyRoleRepresation
		List<MyRole> myRoles = jwtUserDto.getRoleInfo();
		List<MyRoleRepresation> myRoleRepresationList = new ArrayList<MyRoleRepresation>();
		for (MyRole myRole : myRoles) {
			MyRoleRepresation mr = new MyRoleRepresation();
			mr.setRoleId(myRole.getRoleId());
			mr.setRoleName(myRole.getRoleName());
			mr.setDataScope(myRole.getDataScope());
			mr.setDescription(myRole.getDescription());
			myRoleRepresationList.add(mr);
		}
		ur.setRoles(myRoleRepresationList);

		List<MenuIndexDto> menus = menuDao.listByUserId(userId);
		List<String> permission = new ArrayList<String>();
		for (MenuIndexDto menuIndexDto : menus) {
			if (menuIndexDto.getPermission() != null && menuIndexDto.getPermission().trim().length() > 0) {
				permission.add(menuIndexDto.getPermission());
			}
		}
		// List<String> permission =
		// menus.stream().map(MenuIndexDto::getPermission).collect(Collectors.toList());

		ur.setPermissions(permission);

		return ur;
	}
}
