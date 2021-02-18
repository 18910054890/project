package com.lyzd.om.spring.common.exception;


import com.lyzd.om.shared.exception.AppException;
import com.lyzd.om.shared.exception.ErrorCode;

/**
 * @author Thinker
 *
 */
public class ErrorRepresentation {
	
    private final ErrorDetail error;

    public ErrorRepresentation(AppException ex, String path) {
        ErrorCode error = ex.getError();
        this.error = new ErrorDetail(error.getCode(), error.getStatus(), error.getMessage(), path, ex.getData());
    }

    public ErrorRepresentation(ErrorDetail error) {
        this.error = error;
    }

    public ErrorDetail getError() {
        return error;
    }

  
}
