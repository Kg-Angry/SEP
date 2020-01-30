package com.sep.koncentratorPlacanja.service;

import com.sep.koncentratorPlacanja.dto.*;
import com.sep.koncentratorPlacanja.model.BankaSecret;
import com.sep.koncentratorPlacanja.repository.BankaSecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BankaSecretService {
    @Autowired
    private BankaSecretRepository bankaSecretRepository;
    public BankaSecret save(BankaSecret bankaSecret){
        return bankaSecretRepository.save(bankaSecret);
    }
    public BankaSecret save(FormSubmitDTO formSubmitDTO){
        BankaSecret bankaSecret = new BankaSecret();
        bankaSecret.setCasopisUsername(formSubmitDTO.getCasopisUsername());
        for(FormSubmissionDTO dto : formSubmitDTO.getFields()){
            if(dto.getFieldId().equals("ClientId"))
                bankaSecret.setClientId(dto.getFieldValue());
            if(dto.getFieldId().equals("ClientPassword")){
                bankaSecret.setClientPassword(dto.getFieldValue());
            }
        }
        return bankaSecretRepository.save(bankaSecret);
    }

    public Set<BankaSecretDTO> findSecret(PlatilacBankaDTO platilacBankaDTO){
        Set<BankaSecretDTO> bankaSecretDTOS = new HashSet<>();
        for(NaucniCasopisDTO dto : platilacBankaDTO.getNazivi_casopisa()){
            BankaSecret bankaSecret = bankaSecretRepository.findByCasopisUsername(dto.getNaziv());
            bankaSecretDTOS.add(new BankaSecretDTO(bankaSecret));
        }
        return bankaSecretDTOS;
    }
}
