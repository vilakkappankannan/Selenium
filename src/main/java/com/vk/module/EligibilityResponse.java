package com.vk.module;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EligibilityResponse {

    private String message;
    private int status;
    private String cardNumber;
    private String requestId;
    private SecureContext secureContext;

    public void setErrors(List<Map<String, String>> errorList) {
    }
}
