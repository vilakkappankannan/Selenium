package com.vk.controller;

import com.vk.module.EligibilityRequest;
import com.vk.module.EligibilityResponse;
import com.vk.module.EncryptedFields;
import com.vk.module.SecureContext;
import com.vk.service.EligibilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.Base64Encryption;
import utils.GenerateRandomValue;
import utils.Masking;


@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    @Autowired
    private EligibilityService service;
    private Masking masking = new Masking();
    private Base64Encryption base64Encryption = new Base64Encryption();
    private SecureContext secureContext = new SecureContext();
    private GenerateRandomValue generateRandomValue = new GenerateRandomValue();
    private EncryptedFields encryptedFields = new EncryptedFields();

    //Request
    @PostMapping(value = "/test")
    public EligibilityResponse eligibility(@Valid @RequestBody EligibilityRequest add) {
        service.saveEligibility(add);

        //Response
        EligibilityResponse response = new EligibilityResponse();
        response.setStatus(200);
        response.setMessage("success");
        //get the same value passed in request
//        response.setRequestId(add.getRequestId());
        //in response if we need random values we can generate
        response.setRequestId(generateRandomValue.randomValues());
        response.setCardNumber(masking.mask(add.getCardDetails().getCardNumber(), 4));
        response.setSecureContext(new SecureContext(base64Encryption.encrypt(add.getCardDetails().getCardNumber())));

        return response;
    }

    @GetMapping(value = "/test1", consumes = "text/plain")
    public String newMethod() {
        System.out.println("VK-Gatling");
        return "hello world";

    }
}
