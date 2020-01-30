package com.example.paypal.PayPal.service;

import com.example.paypal.PayPal.DTO.FormDTO;
import com.example.paypal.PayPal.DTO.FormFieldDTO;
import org.springframework.stereotype.Service;

@Service
public class FormService {
    public FormDTO getFormForSecret() {
        FormDTO formDTO = new FormDTO();
        formDTO.setNaziv("PayPalSecret");
        FormFieldDTO formFieldDTO = new FormFieldDTO();
        formFieldDTO.setFieldLabel("CasopisUsername");
        formFieldDTO.setFieldTypeName("String");
        formDTO.getFields().add(formFieldDTO);
        FormFieldDTO formFieldDTO1 = new FormFieldDTO();
        formFieldDTO1.setFieldTypeName("String");
        formFieldDTO1.setFieldLabel("ClientId");
        formDTO.getFields().add(formFieldDTO1);
        FormFieldDTO formFieldDTO2 = new FormFieldDTO();
        formFieldDTO2.setFieldTypeName("String");
        formFieldDTO1.setFieldLabel("ClientSecret");
        formDTO.getFields().add(formFieldDTO1);
        return formDTO;
    }
}
