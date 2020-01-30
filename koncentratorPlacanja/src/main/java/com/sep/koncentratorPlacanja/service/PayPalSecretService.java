package com.sep.koncentratorPlacanja.service;

import com.sep.koncentratorPlacanja.dto.FormSubmissionDTO;
import com.sep.koncentratorPlacanja.dto.FormSubmitDTO;
import com.sep.koncentratorPlacanja.model.PayPalSecret;
import com.sep.koncentratorPlacanja.repository.PayPalSecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayPalSecretService {
    @Autowired
    PayPalSecretRepository payPalSecretRepository;

    PayPalSecret save(PayPalSecret payPalSecret){
        return payPalSecretRepository.save(payPalSecret);
    }
    public PayPalSecret save(FormSubmitDTO formSubmitDTO){
        PayPalSecret payPalSecret = new PayPalSecret();
        payPalSecret.setCasopisUsername(formSubmitDTO.getCasopisUsername());
        for(FormSubmissionDTO dto : formSubmitDTO.getFields()){
            if(dto.getFieldId().equals("ClientId"))
                payPalSecret.setClientId(dto.getFieldValue());
            if(dto.getFieldId().equals("ClientSecret")){
                payPalSecret.setClientSecret(dto.getFieldValue());
            }
        }
        return payPalSecretRepository.save(payPalSecret);
    }
}
