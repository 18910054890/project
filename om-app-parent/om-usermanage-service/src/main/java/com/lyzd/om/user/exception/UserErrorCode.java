package com.lyzd.om.user.exception;

import com.lyzd.om.shared.exception.ErrorCode;

/**
 * @author Thinker
 *
 */
public enum UserErrorCode implements ErrorCode {
    USER_CANNOT_BE_MODIFIED(409, "用户无法变更"),
    USER_NOT_FOUND(404, "没有找到用户");
    private int status;
    private String message;

    UserErrorCode(int status, String message) {
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
