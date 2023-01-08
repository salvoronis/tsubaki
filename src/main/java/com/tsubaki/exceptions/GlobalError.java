package com.tsubaki.exceptions;

import lombok.Getter;

@Getter
public enum GlobalError {

    USERNAME_ALREADY_TAKEN("AUTHEX01", 400, "username already taken"),
    EMAIL_ALREADY_TAKEN("AUTHEX02", 400, "email already taken");

    private final String code;
    private final int status;
    private final String message;

    GlobalError(String code, int status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
