package com.lyzd.om.user.exception;


import static com.lyzd.om.user.exception.UserErrorCode.USER_NOT_FOUND;
import static com.google.common.collect.ImmutableMap.of;

import com.lyzd.om.shared.exception.AppException;

public class UserNotFoundException extends AppException {
    public UserNotFoundException(String userId) {
        super(USER_NOT_FOUND, of("userId", userId));
    }
}
