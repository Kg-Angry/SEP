package com.example.bitcoin.Bitcoin.Controller;

import com.example.bitcoin.Bitcoin.DTO.PlatilacDTO;
import com.example.bitcoin.Bitcoin.DTO.TransakcijeDTO;
import com.example.bitcoin.Bitcoin.Model.ResponseBitcoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "api1/bitcoin")
public class BitcoinController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/startPayment")
    public String startPayment(@RequestBody PlatilacDTO platilac){

        Map<String, Object> mapa = new HashMap<String,Object>();
        String order_id = UUID.randomUUID().toString();
        mapa.put("order_id", order_id);
        mapa.put("price_amount", platilac.getCena());
        mapa.put("price_currency","USD");
        mapa.put("receive_currency","USD");
        mapa.put("title", platilac.getNaziv_casopisa());
        mapa.put("description", "Placanje naucnog casopisa");
        mapa.put("callback_url", "https://api-sandbox.coingate.com/account/orders");
        mapa.put("success_url", "https://localhost:4200/uspesnoPlacanje/"+order_id);
        mapa.put("cancel_url", "https://localhost:4200/neuspesnoPlacanje/"+order_id);

        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization", "Token 8W2cFE2hUx55MHxxuisH9gigTzdP7pRjYmQsHH2V");
        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String,Object>>(mapa, headers);

        ResponseBitcoin response = client.postForObject("https://api-sandbox.coingate.com/v2/orders", entity, ResponseBitcoin.class);

        HttpHeaders noviHeaders = new HttpHeaders();
        noviHeaders.add("Authorization", "Token 8W2cFE2hUx55MHxxuisH9gigTzdP7pRjYmQsHH2V");
        noviHeaders.add("Location", response.getPayment_url()); //payment url
        noviHeaders.add("id", response.getId().toString()); //id inicirane transakcije
        noviHeaders.add("uuid", response.getPayment_url().split("invoice/")[1]);
        noviHeaders.add("title", platilac.getNaziv_casopisa()); //naziv casopisa koji se placa
        noviHeaders.add("created_at", response.getCreated_at());
        noviHeaders.add("status", response.getStatus()); //ovde ce uvek biti new
        noviHeaders.add("merchant_order_id", platilac.getKorisnicko_ime_platioca()); //uplatilac
        //System.out.println("\t\tnoviHeaders: " + noviHeaders.toString() + "\n\n");
        TransakcijeDTO trDTO = new TransakcijeDTO();
        trDTO.setIdTransakcije(response.getId().toString());
        trDTO.setOrderId(order_id);
        trDTO.setCena(platilac.getCena());
        trDTO.setUuid(response.getPayment_url().split("invoice/")[1]);
        trDTO.setNaziv(platilac.getNaziv_casopisa());
        trDTO.setVremeKreiranjaTransakcije(response.getCreated_at());
        trDTO.setStatus(response.getStatus());
        trDTO.setUplatilac(platilac.getKorisnicko_ime_platioca());
        String paymentUrl = response.getPayment_url();
        HttpHeaders h = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TransakcijeDTO> transakcija = new HttpEntity<>(trDTO,h);
        restTemplate.postForObject("https://koncentrator-placanja/api1/kp/kreiranaTransakcija",transakcija,String.class);

        //String retVal = paymentUrl + ", " + idIniciraneTransakcije + ", " + uuid + ", " + naziv + ", " + vremeKreiranja + ", " + status + ", " + uplatilac;
        //System.out.println("RETVAL" + retVal);
        return paymentUrl;
    }
}

