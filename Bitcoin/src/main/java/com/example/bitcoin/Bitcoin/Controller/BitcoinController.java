package com.example.bitcoin.Bitcoin.Controller;

import com.example.bitcoin.Bitcoin.DTO.PlatilacDTO;
import com.example.bitcoin.Bitcoin.Model.ResponseBitcoin;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @RequestMapping(value = "/proba", method = RequestMethod.GET)
//    public ResponseEntity<?> proba()
//    {
//        System.out.println("USAO");
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @RequestMapping(value = "/startPayment")
    //@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public String startPayment(@RequestBody PlatilacDTO platilac){


        Map<String, Object> mapa = new HashMap<String,Object>();
        mapa.put("order_id", UUID.randomUUID().toString());
        mapa.put("price_amount", platilac.getCena());
        mapa.put("price_currency","USD");
        mapa.put("receive_currency","USD");
        mapa.put("title", platilac.getNaziv_casopisa());
        mapa.put("description", "Placanje naucnog casopisa");
        mapa.put("callback_url", "https://api-sandbox.coingate.com/account/orders");
        mapa.put("success_url", "http://localhost:4200/uspesnoPlacanje");
        mapa.put("cancel_url", "http://localhost:4200/");

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

        String paymentUrl = response.getPayment_url();
        String idIniciraneTransakcije = response.getId().toString();
        String uuid = response.getPayment_url().split("invoice/")[1];
        String naziv = platilac.getNaziv_casopisa();
        String vremeKreiranja = response.getCreated_at();
        String status = response.getStatus();
        String uplatilac = platilac.getKorisnicko_ime_platioca();
        String retVal = paymentUrl + ", " + idIniciraneTransakcije + ", " + uuid + ", " + naziv + ", " + vremeKreiranja + ", " + status + ", " + uplatilac;
        //System.out.println("RETVAL" + paymentUrl);
        return paymentUrl;
    }
}

