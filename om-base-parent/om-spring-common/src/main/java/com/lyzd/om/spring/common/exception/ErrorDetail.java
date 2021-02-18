package com.lyzd.om.spring.common.exception;

import static com.google.common.collect.Maps.newHashMap;
import static org.apache.commons.collections4.MapUtils.isEmpty;

import java.time.Instant;
import java.util.Map;

/**
 * @author Thinker
 *
 */
public class ErrorDetail {

	private final String code;
	private final int status;
	private final String message;
	private final String path;
	private final Instant timestamp;
	private final Map<String, Object> data = newHashMap();

	public ErrorDetail(String code, int status, String message, String path, Map<String, Object> data) {
		this.code = code;
		this.status = status;
		this.message = message;
		this.path = path;
		this.timestamp = Instant.now();
		if (!isEmpty(data)) {
			this.data.putAll(data);
		}
	}

	public int getStatus() {
		return status;
	}
}
