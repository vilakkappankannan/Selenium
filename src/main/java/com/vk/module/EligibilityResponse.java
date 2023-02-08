package com.vk.module;

import lombok.Data;

@Data
public class EligibilityResponse {

    private String message;
    private int status;
    private String cardNumber;
    private SecureContext secureContext;

}
