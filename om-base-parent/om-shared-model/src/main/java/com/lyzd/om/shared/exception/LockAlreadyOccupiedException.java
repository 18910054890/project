package com.lyzd.om.shared.exception;

import static com.google.common.collect.ImmutableMap.of;

/**
 * @author Thinker
 *
 */
public class LockAlreadyOccupiedException extends AppException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 586279485202943633L;

	public LockAlreadyOccupiedException(String lockKey) {
        super(CommonErrorCode.LOCK_OCCUPIED, of("lockKey", lockKey));
    }
}
