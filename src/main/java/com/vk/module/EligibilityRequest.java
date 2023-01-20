package com.vk.module;

import lombok.Data;

@Data
public class EligibilityRequest {
    private String cardNumber;
    private int cvv;
}
