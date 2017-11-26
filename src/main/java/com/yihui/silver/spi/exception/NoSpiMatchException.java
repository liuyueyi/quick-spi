package com.yihui.silver.spi.exception;

/**
 * Created by yihui on 2017/5/24.
 */
public class NoSpiMatchException extends Exception {

    public NoSpiMatchException() {
    }

    public NoSpiMatchException(String message) {
        super(message);
    }

    public NoSpiMatchException(Throwable cause) {
        super(cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
