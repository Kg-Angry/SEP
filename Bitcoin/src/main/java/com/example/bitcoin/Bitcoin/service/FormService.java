package com.example.bitcoin.Bitcoin.service;

import com.example.bitcoin.Bitcoin.DTO.FormDTO;
import com.example.bitcoin.Bitcoin.DTO.FormFieldDTO;
import org.springframework.stereotype.Service;

@Service
public class FormService {
    public FormDTO getFormForSecret() {
        FormDTO formDTO = new FormDTO();
        formDTO.setNaziv("BitcoinSecret");
        FormFieldDTO formFieldDTO1 = new FormFieldDTO();
        formFieldDTO1.setFieldTypeName("String");
        formFieldDTO1.setFieldLabel("Token");
        formDTO.getFields().add(formFieldDTO1);
        return formDTO;
    }
}
