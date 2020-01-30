package com.example.paypal.PayPal.Service;

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
}
