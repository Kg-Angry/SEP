package com.sep.NoviServis.service.serviceImpl;

import com.sep.NoviServis.dto.FormDTO;
import com.sep.NoviServis.dto.FormFieldDTO;
import com.sep.NoviServis.service.FormService;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl implements FormService {
    @Override
    public FormDTO getFormForSecret() {
        FormDTO formDTO = new FormDTO();
        formDTO.setNaziv("NoviServis");
        FormFieldDTO formFieldDTO1 = new FormFieldDTO();
        formFieldDTO1.setFieldTypeName("String");
        formFieldDTO1.setFieldLabel("ClientId");
        formDTO.getFields().add(formFieldDTO1);
        FormFieldDTO formFieldDTO2 = new FormFieldDTO();
        formFieldDTO2.setFieldTypeName("Image");
        formFieldDTO2.setFieldLabel("ClientImage");
        formDTO.getFields().add(formFieldDTO2);
        return formDTO;
    }
}
