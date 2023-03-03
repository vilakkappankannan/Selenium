package com.vk.constants;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.LinkedHashMap;
import java.util.Map;

public enum StatusCode {

    MANDATORY_FIELD_MISSING("1000", BAD_REQUEST, "Mandatory field missing"),
    INVALID_FIELD_VALUE("2000", BAD_REQUEST, "Invalid field value");

    private String errorCode;
    private HttpStatus httpsStatusCode;
    private String errorMessage;

    private StatusCode(String errorCode, HttpStatus httpStatusCode, String errorMessage) {
        this.httpsStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() { return errorMessage; }

    public String getErrCode() { return errorCode; }

    public HttpStatus getHttpsStatusCode() { return httpsStatusCode; }

    public Map<String, String> getJsonError() {
        Map<String, String> error = new LinkedHashMap<>();

        error.put("errorCode", this.getErrCode());
        error.put("errorMessage", this.getErrorMessage());

        return error;
    }

    public String getLogMessage() {
        StringBuilder message = new StringBuilder();

        message.append(this.getErrCode());
        message.append(": ");
        message.append(this.getErrorMessage());
        message.append(": ");
        message.append(this.getHttpsStatusCode());
        message.append(": ");

        return message.toString();

    }

}
