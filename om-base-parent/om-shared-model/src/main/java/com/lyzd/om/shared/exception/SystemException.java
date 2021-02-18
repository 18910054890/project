package com.lyzd.om.shared.exception;


import static com.google.common.collect.ImmutableMap.of;

/**
 * @author Thinker
 *
 */
public class SystemException extends AppException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7001312243116208496L;

	public SystemException(Throwable cause) {
        super(CommonErrorCode.SYSTEM_ERROR, of("detail", cause.getMessage()), cause);
    }
}
