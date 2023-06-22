package com.pru.token.app.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String errMsg;
    public ErrorResponse(int status, String errMsg) {
        this.status=status;
        this.errMsg=errMsg;
    }
}
