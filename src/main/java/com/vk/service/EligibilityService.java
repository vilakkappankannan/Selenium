package com.vk.service;

import com.vk.entity.Eligibility;
import com.vk.module.EligibilityRequest;
import com.vk.repository.EligibilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Base64Encryption;

import java.util.Base64;

@Service
public class EligibilityService {

    @Autowired
    private EligibilityRepo repo;

    private Base64Encryption base64Encryption = new Base64Encryption();
    public Eligibility saveEligibility (EligibilityRequest request) {

//        String name = null;
//        System.out.println(name.length());

        Eligibility eligibility = new Eligibility();
//        eligibility.setCardNumber(request.getCardNumber());
//        System.out.println(request.getCardNumber());
        eligibility.setCardNumber(base64Encryption.encrypt(request.getCardNumber()));
        eligibility.setCvv(request.getCvv());
        return repo.save(eligibility);

    }

}
