package com.tsubaki.exceptions;

import lombok.Getter;

@Getter
public class NoSuchUserException extends RuntimeException {

    private final String code;

    private final int status;

    private final String logMessage;

    public NoSuchUserException(String code, int status, String logMessage) {
        super(logMessage);
        this.code = code;
        this.status = status;
        this.logMessage = logMessage;
    }

    public NoSuchUserException(GlobalError globalError) {
        this(globalError.getCode(), globalError.getStatus(), globalError.getMessage());
    }

}
