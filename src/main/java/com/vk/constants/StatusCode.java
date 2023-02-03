//package com.vk.constants;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.client.HttpClientErrorException;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class StatusCode {
//
//    MANDATORY_FIELD_MISSING(errorCode:"100", BADREQUEST, errorMessage:"mandatory field missing")
//
//        private HttpStatus httpsStatusCode;
//        private String errorCode;
//        private String errorMessage;
//
//    private StatusCode(HttpStatus httpStatusCode, String errorCode, String errorMessage){
//        this.httpsStatusCode = httpStatusCode;
//        this.errorCode = errorCode;
//        this.errorMessage = errorMessage;
//    }
//    public String getErrorMessage() {return  errorMessage;}
//    public String getErrorCode(){return errorCode;}
//}
//
//
//
//    public String getErrorCode() {
//        return errorCode;
//    }
//
//    public HttpStatus getHttpStatusCode() {
//        return httpStatusCode;
//    }
//
//
//    public Map<String, String> getJsonError() {
//        Map<String, String> error = new LinkedHashMap<>();
//
//        error.put("errorCode", this.getErrorCode());
//        error.put("errorMessage", this.getMessage());
//
//        return error;
//    }
