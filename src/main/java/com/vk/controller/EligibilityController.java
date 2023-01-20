package com.vk.controller;

import com.vk.module.EligibilityRequest;
import com.vk.module.EligibilityResponse;
import com.vk.service.EligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eligibility")
public class EligibilityController {

    @Autowired
    private EligibilityService service;

    //Request
    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public @ResponseBody EligibilityResponse eligibility(@RequestBody EligibilityRequest add) {
        service.saveEligibility(add);

        //Response
        EligibilityResponse response = new EligibilityResponse();
        response.setMessage("Success");
        response.setStatus(200);
        return response;

    }
    @RequestMapping(path="/test1",method=RequestMethod.POST,consumes = "text/plain")
    public String newMethod(String word)
    {
        return word;

    }
}
