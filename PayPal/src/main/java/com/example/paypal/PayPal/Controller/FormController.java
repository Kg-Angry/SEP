package com.example.paypal.PayPal.Controller;

import com.example.paypal.PayPal.DTO.FormSubmitDTO;
import com.example.paypal.PayPal.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@CrossOrigin
@RequestMapping("api/form")
public class FormController {

    @Autowired
    private FormService formService;
    @Autowired
    private com.example.paypal.PayPal.Service.TopSecretDataService topSecretDataService;

    @GetMapping
    public ResponseEntity<?> getFormForSecret(){
        return ResponseEntity.ok(formService.getFormForSecret());
    }

    @PostMapping
    public ResponseEntity<?> submitForm(@RequestBody FormSubmitDTO formSubmitDTO){
        return ResponseEntity.ok(topSecretDataService.save(formSubmitDTO));
    }
}
