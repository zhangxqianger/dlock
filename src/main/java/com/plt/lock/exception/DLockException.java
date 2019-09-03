package com.plt.lock.exception;

/**
 * @author zxq
 */
public class DLockException extends RuntimeException {
    public DLockException() {
    }

    public DLockException(String message) {
        super(message);
    }

    public DLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public DLockException(Throwable cause) {
        super(cause);
    }

    public DLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
