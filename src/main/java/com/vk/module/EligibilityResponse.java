package com.vk.module;

import lombok.Data;

@Data
public class EligibilityResponse {

    private String message;
    private String cardNumber;
    private int status;
    private SecureContext secureContext;

}
