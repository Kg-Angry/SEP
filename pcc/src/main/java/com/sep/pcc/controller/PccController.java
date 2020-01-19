package com.sep.pcc.controller;

import com.sep.pcc.dto.PlatnaKarticaDTO;
import com.sep.pcc.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PccController.class);


    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/drugaBanka")
    public ResponseDTO drugaBanka(@RequestBody PlatnaKarticaDTO platnaKarticaDTO){
        logger.info("\n\t\t Preusmeren na drugu banku");
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlatnaKarticaDTO> entity = new HttpEntity<>(platnaKarticaDTO,header);
        PlatnaKarticaDTO karticaDTO = null;
        try {
            logger.info("\n\t\t Provera kartice korinika");
            karticaDTO = restTemplate.postForObject("https://BANKA-API/checkCard", entity, PlatnaKarticaDTO.class);
        }catch (Exception e){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setStatusTransakcije("https://localhost:4201//greska");
            logger.info("\n\t\t Greska prilikom provere kartice");
        }
        if(karticaDTO!=null){
            logger.info("\n\t\t Kartica je pronadjena, preusmeravanje na placanje");
            HttpEntity<PlatnaKarticaDTO> entityKartica = new HttpEntity<>(platnaKarticaDTO,header);
            try {
                ResponseDTO responseDTO = restTemplate.postForObject("https://BANKA-API/pay", entityKartica, ResponseDTO.class);
                return responseDTO;
            }catch (Exception  e){
                logger.info("\n\t\t Greska prilikom placanja");
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setStatusTransakcije("https://localhost:4201/greska");
                return responseDTO;
            }
        }else{
            logger.info("\n\t\t Kartica nije pronadjena");
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setStatusTransakcije("https://localhost:4201/neuspesno");
            return responseDTO;
        }

    }

}
