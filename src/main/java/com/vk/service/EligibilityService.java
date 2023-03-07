package com.vk.service;

import com.vk.entity.Eligibility;
import com.vk.module.EligibilityRequest;
import com.vk.repository.EligibilityRepo;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Base64Encryption;
import utils.GenerateRandomValue;

@Service
public class EligibilityService {

    @Autowired
    private EligibilityRepo repo;
    private GenerateRandomValue generateRandomValue = new GenerateRandomValue();
    private Base64Encryption base64Encryption = new Base64Encryption();
    public Eligibility saveEligibility (EligibilityRequest request) {

        Eligibility eligibility = new Eligibility();
//        eligibility.setRequestId(generateRandomValue.randomValues(request.getRequestId()));

//        eligibility.setCardNumber(base64Encryption.encrypt(request.getCardDetails().getCardNumber()));
        eligibility.setCardNumber(request.getCardDetails().getCardNumber());
        eligibility.setCvv(request.getCardDetails().getCvv());
        eligibility.setRequestId(generateRandomValue.randomValues());
        return repo.save(eligibility);

    }

}
