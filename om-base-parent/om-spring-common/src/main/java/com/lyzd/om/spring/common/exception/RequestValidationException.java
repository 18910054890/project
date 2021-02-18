package com.lyzd.om.spring.common.exception;

import com.lyzd.om.shared.exception.AppException;

import java.util.Map;

/**
 * @author Thinker
 *
 */
public class RequestValidationException extends AppException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1731210953272105052L;

	public RequestValidationException(Map<String, Object> data) {
        super(SpringCommonErrorCode.REQUEST_VALIDATION_FAILED, data);
    }
}
