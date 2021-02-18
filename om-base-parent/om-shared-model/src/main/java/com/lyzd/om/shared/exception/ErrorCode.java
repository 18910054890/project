package com.lyzd.om.shared.exception;

/**
 * This is to be subclassed by concrete enums.
 * 
 * @author Thinker
 *
 */
public interface ErrorCode {
    int getStatus();

    String getMessage();

    String getCode();
}
