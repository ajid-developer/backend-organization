package com.bvk.test.backend_organization.enums;

public enum ResponseCode {

    SUCCESS("00", "Successfully"),
    VALIDATION_ERROR("16", "Request wrong"),
    SIGNATURE_ERROR("44", "Signature wrong"),
    JWT_EXPIRED("42", "JWT token expired"),
    INTERNAL_SERVER_ERROR("50", "Internal error"),
    ;

    private String code;
    private String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
