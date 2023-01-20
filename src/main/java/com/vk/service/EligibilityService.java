package com.vk.service;

import com.vk.entity.Eligibility;
import com.vk.module.EligibilityRequest;
import com.vk.repository.EligibilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class EligibilityService {

    @Autowired
    private EligibilityRepo repo;
    public Eligibility saveEligibility (EligibilityRequest request) {
        Eligibility eligibility = new Eligibility();
        eligibility.setCardNumber(request.getCardNumber());
        eligibility.setCvv(request.getCvv());
        return repo.save(eligibility);
    }
}
