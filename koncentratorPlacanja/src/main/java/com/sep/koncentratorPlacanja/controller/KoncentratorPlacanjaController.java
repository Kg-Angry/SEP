package com.sep.koncentratorPlacanja.controller;

import com.sep.koncentratorPlacanja.dto.PlatilacDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "kp")
public class KoncentratorPlacanjaController {

    @Autowired
    RestTemplate restTemplate;

//    @GetMapping(value = "/{nacin}")
//    public String proba(@PathVariable String nacin){
//        String url= "http://localhost:8762/" + nacin+"/"
//                +"api1/bitcoin/pro";
//        System.out.println(url);
//        String retval = restTemplate.getForObject("http://localhost:8762/" + nacin+"/"
//                +"api1/bitcoin/pro",String.class);
//        return retval;
//    }

//    nacin = bitcoin-api,paypal-api
    @PostMapping(value = "/{nacin}")
    public String payment(@RequestBody PlatilacDTO platilacDTO,@PathVariable String nacin){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PlatilacDTO> entity = new HttpEntity<>(platilacDTO,headers);
        String retval = restTemplate.postForObject("http://localhost:8762/" + nacin+"/"
                +"api1/bitcoin/pro",entity,String.class);

        return retval;
    }

}
