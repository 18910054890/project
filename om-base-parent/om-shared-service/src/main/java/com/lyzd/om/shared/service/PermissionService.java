package com.lyzd.om.shared.service;

import com.lyzd.om.shared.model.common.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
