package com.sep.pcc.controller;

import com.sep.pcc.dto.PlatnaKarticaDTO;
import com.sep.pcc.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class PccController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/drugaBanka")
    public ResponseDTO drugaBanka(@RequestBody PlatnaKarticaDTO platnaKarticaDTO){

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlatnaKarticaDTO> entity = new HttpEntity<>(platnaKarticaDTO,header);
        PlatnaKarticaDTO karticaDTO = null;
        try {
            karticaDTO = restTemplate.postForObject("https://localhost:8088/checkCard", entity, PlatnaKarticaDTO.class);
        }catch (Exception e){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setStatusTransakcije("https://localhost:4201//greska");
        }
        if(karticaDTO!=null){
            HttpEntity<PlatnaKarticaDTO> entityKartica = new HttpEntity<>(platnaKarticaDTO,header);
            try {
                ResponseDTO responseDTO = restTemplate.postForObject("https://localhost:8088/pay", entityKartica, ResponseDTO.class);
                return responseDTO;
            }catch (Exception  e){
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setStatusTransakcije("https://localhost:4201/greska");
                return responseDTO;
            }
        }else{
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setStatusTransakcije("https://localhost:4201/neuspesno");
            return responseDTO;
        }

    }

}
