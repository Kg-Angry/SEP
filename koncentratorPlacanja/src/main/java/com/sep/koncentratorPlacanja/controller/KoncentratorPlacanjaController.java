package com.sep.koncentratorPlacanja.controller;

import com.sep.koncentratorPlacanja.dto.PlatilacDTO;
import com.sep.koncentratorPlacanja.dto.TipPlacanjaDTO;
import com.sep.koncentratorPlacanja.model.TipPlacanja;
import com.sep.koncentratorPlacanja.model.TipPlacanjaModel;
import com.sep.koncentratorPlacanja.service.TipPlacanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "api1/kp")
public class KoncentratorPlacanjaController {

    @Autowired
    RestTemplate restTemplate;


//    @GetMapping(value = "/{nacin}")
//    public String proba(@PathVariable String nacin){
//        String url= "http://"+ nacin+"/"
//                +"api1/bitcoin/proba";
//        System.out.println(url);
//        String retval = restTemplate.getForObject("http://"+ nacin+"/"
//                +"api1/bitcoin/proba",String.class);
//        return retval;
//    }

    //nacin = bitcoin-api,paypal-api
    @PostMapping(value = "/{nacin}")
    public String payment(@RequestBody PlatilacDTO platilacDTO,@PathVariable String nacin){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("USO OVDE");
        HttpEntity<PlatilacDTO> entity = new HttpEntity<>(platilacDTO,headers);
        String retval = restTemplate.postForObject("https://"+nacin+"/"
                +"api1/bitcoin/startPayment",entity,String.class);

        return retval;
    }
}
