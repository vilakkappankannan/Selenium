package com.vk.service;

import com.vk.entity.Eligibility;
import com.vk.module.EligibilityRequest;
import com.vk.repository.EligibilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Base64Encryption;

@Service
public class EligibilityService {

    @Autowired
    private EligibilityRepo repo;
    private Base64Encryption base64Encryption = new Base64Encryption();
    public Eligibility saveEligibility (EligibilityRequest request) {

        Eligibility eligibility = new Eligibility();
        eligibility.setCardNumber(request.getRequestId());
//        eligibility.setCardNumber(base64Encryption.encrypt(request.getCardDetails().getCardNumber()));
        eligibility.setCardNumber(request.getCardDetails().getCardNumber());
        eligibility.setCvv(request.getCardDetails().getCvv());
        return repo.save(eligibility);

    }

}
