package com.sep.NoviServis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api1/noviservis")
public class NoviServicController {

    @RequestMapping(value="/proba")
    public ResponseEntity<?> proba()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
