package com.sep.banka.serviceImpl;

import com.sep.banka.dto.FormDTO;
import com.sep.banka.dto.FormFieldDTO;
import com.sep.banka.service.FormService;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl implements FormService {
    @Override
    public FormDTO getFormForSecret() {
        FormDTO formDTO = new FormDTO();
        formDTO.setNaziv("BankaSecret");
        FormFieldDTO formFieldDTO = new FormFieldDTO();
        formFieldDTO.setFieldLabel("CasopisUsername");
        formFieldDTO.setFieldTypeName("String");
        formDTO.getFields().add(formFieldDTO);
        FormFieldDTO formFieldDTO1 = new FormFieldDTO();
        formFieldDTO1.setFieldTypeName("String");
        formFieldDTO1.setFieldLabel("ClientId");
        formDTO.getFields().add(formFieldDTO1);
        FormFieldDTO formFieldDTO2 = new FormFieldDTO();
        formFieldDTO2.setFieldTypeName("Password");
        formFieldDTO1.setFieldLabel("ClientPassword");
        formDTO.getFields().add(formFieldDTO1);
        return formDTO;
    }
}
