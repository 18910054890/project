package com.lyzd.om.shared.service;

import com.lyzd.om.shared.model.common.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(Long id);
}
