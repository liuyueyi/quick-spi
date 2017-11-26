package com.yihui.silver.spi.exception;

/**
 * Created by yihui on 2017/5/25.
 */
public class SpiProxyCompileException extends Exception {

    public SpiProxyCompileException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
