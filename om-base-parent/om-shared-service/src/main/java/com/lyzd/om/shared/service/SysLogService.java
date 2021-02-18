package com.lyzd.om.shared.service;

import com.lyzd.om.shared.model.common.SysLogs;

/**
 * 日志service
 * 
 */
public interface SysLogService {

	void save(SysLogs sysLogs);

	void save(Long userId, String module, Boolean flag, String remark);

	void deleteLogs();
}
