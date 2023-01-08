package com.tsubaki.exceptions;

import lombok.Getter;

@Getter
public class AlreadyTakenException extends RuntimeException { // TODO create one abstract class for all exceptions

    private final String code;

    private final int status;

    private final String logMessage;

    public AlreadyTakenException(String code, int status, String logMessage) {
        super(logMessage);
        this.code = code;
        this.status = status;
        this.logMessage = logMessage;
    }

    public AlreadyTakenException(GlobalError globalError) {
        this(globalError.getCode(), globalError.getStatus(), globalError.getMessage());
    }
}
