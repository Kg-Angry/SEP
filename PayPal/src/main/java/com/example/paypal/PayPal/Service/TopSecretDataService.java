package com.example.paypal.PayPal.Service;

import com.example.paypal.PayPal.DTO.FormSubmissionDTO;
import com.example.paypal.PayPal.DTO.FormSubmitDTO;
import com.example.paypal.PayPal.Model.TopSecretData;
import com.example.paypal.PayPal.Repository.TopSecretDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopSecretDataService {

    @Autowired
    private TopSecretDataRepository tsdr;

    public TopSecretData findByNazivCasopisa(String naziv)
    {
        return tsdr.findByNazivCasopisa(naziv);
    }

    public void save(TopSecretData t)
    {
        tsdr.save(t);
    }

    public TopSecretData save(FormSubmitDTO formSubmitDTO){
        TopSecretData payPalSecret = new TopSecretData();
        payPalSecret.setNazivCasopisa(formSubmitDTO.getCasopisUsername());
        for(FormSubmissionDTO dto : formSubmitDTO.getFields()){
            if(dto.getFieldId().equals("ClientId"))
                payPalSecret.setClientId(dto.getFieldValue());
            if(dto.getFieldId().equals("ClientSecret")){
                payPalSecret.setClientSecret(dto.getFieldValue());
            }
        }
        return tsdr.save(payPalSecret);
    }

}
