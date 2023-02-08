package com.vk.controller;

import com.vk.module.EligibilityRequest;
import com.vk.module.EligibilityResponse;
import com.vk.module.SecureContext;
import com.vk.service.EligibilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.Base64Encryption;
import utils.Masking;


@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    @Autowired
    private EligibilityService service;
    private Masking masking = new Masking();
    private Base64Encryption base64Encryption = new Base64Encryption();
    private SecureContext secureContext = new SecureContext();
    //Request
    @PostMapping(value = "/test")
    public EligibilityResponse eligibility(@Valid  @RequestBody EligibilityRequest add) {
        service.saveEligibility(add);

        //Response
        EligibilityResponse response = new EligibilityResponse();
        response.setStatus(200);
        response.setMessage("Success");
        response.setCardNumber(masking.mask(add.getCardNumber()));
//        response.setEncrypt(base64Encryption.encrypt("Test"));
//        response.getSecureContext().getEncrypt(response.setSecureContext());
        secureContext.getEncrypt();

        return response;
    }
    @PostMapping(value="/test1",consumes = "text/plain")
    public String newMethod(String word)
    {
        return word;

    }
}
