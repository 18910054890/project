package com.lyzd.om.spring.common.exception;

import com.lyzd.om.shared.exception.ErrorCode;

/**
 * @author Thinker
 *
 */
public enum SpringCommonErrorCode implements ErrorCode {
    REQUEST_VALIDATION_FAILED(400, "请求数据格式验证失败");
    private int status;
    private String message;

    SpringCommonErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
