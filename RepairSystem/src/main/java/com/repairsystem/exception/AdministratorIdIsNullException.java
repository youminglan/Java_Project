package com.repairsystem.exception;

/**
 * @author youminglan
 * @date 2021/04/20
 * @time 22:18
 */
public class AdministratorIdIsNullException extends RuntimeException {

    public AdministratorIdIsNullException() {
        super();
    }

    public AdministratorIdIsNullException(String message) {
        super(message);
    }

    public AdministratorIdIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdministratorIdIsNullException(Throwable cause) {
        super(cause);
    }

    protected AdministratorIdIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
