package com.sep.banka.controller;

import com.sep.banka.dto.FormDTO;
import com.sep.banka.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/form")
public class FormController {

    @Autowired
    private FormService formService;

    @GetMapping
    public ResponseEntity<?> getFormForSecret(){
        return ResponseEntity.ok(formService.getFormForSecret());
    }

}
