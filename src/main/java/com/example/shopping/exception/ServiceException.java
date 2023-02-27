package com.example.shopping.exception;

import static java.lang.String.format;

public class ServiceException extends RuntimeException {
    public ServiceException(String message, Object... arguments) {
        super(format(message, arguments));
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
    }
}
