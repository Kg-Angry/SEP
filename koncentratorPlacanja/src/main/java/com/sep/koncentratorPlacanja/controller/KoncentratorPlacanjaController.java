package com.sep.koncentratorPlacanja.controller;

import com.sep.koncentratorPlacanja.dto.PlatilacDTO;
import com.sep.koncentratorPlacanja.dto.TipPlacanjaDTO;
import com.sep.koncentratorPlacanja.dto.TransakcijeDTO;
import com.sep.koncentratorPlacanja.model.TipPlacanja;
import com.sep.koncentratorPlacanja.model.TipPlacanjaModel;
import com.sep.koncentratorPlacanja.model.Transakcije;
import com.sep.koncentratorPlacanja.service.TipPlacanjaService;
import com.sep.koncentratorPlacanja.service.TransakcijeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "api1/kp")
public class KoncentratorPlacanjaController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TransakcijeService ts;

    private static final Logger logger = LoggerFactory.getLogger(KoncentratorPlacanjaController.class);

    @PostMapping(value="/pretplata")
    public String pretplata(@RequestBody PlatilacDTO platilacDTO)
    {
        String retval="";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlatilacDTO> entity = new HttpEntity<>(platilacDTO,headers);
        System.out.println("USAO U METODU  " + platilacDTO.getToken());
        if(platilacDTO.getToken() == null)
        {
            retval = restTemplate.postForObject("https://paypal-api/"
                    +"api3/paypal/pretplataPayPal",entity,String.class);

            return retval;
        }else
        {
            retval = restTemplate.postForObject("https://paypal-api/"
                    +"api3/paypal/izvrsiPretplatu",entity,String.class);

            System.out.println("Vratio mi je: " + retval);
            return retval;
        }

    }

    //nacin = bitcoin-api,paypal-api
    @PostMapping(value = "/{nacin}")
    public String payment(@RequestBody PlatilacDTO platilacDTO,@PathVariable String nacin){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlatilacDTO> entity = new HttpEntity<>(platilacDTO,headers);
        Transakcije t = new Transakcije();
        String retval="";
        if(nacin.equals("bitcoin-api"))
        {
            retval = restTemplate.postForObject("https://"+nacin+"/"
                    +"api1/bitcoin/startPayment",entity,String.class);
            logger.info("\n\t\tRedirekcija na adresu: " + retval + "\n");
            return retval;
        }else if(nacin.equals("paypal-api"))
        {
            if(platilacDTO.getPayerID()==null && platilacDTO.getPaymentId()==null)
            {
                retval = restTemplate.postForObject("https://"+nacin+"/"
                    +"api3/paypal/startPayment",entity,String.class);
                logger.info("\n\t\tRedirekcija na adresu: " + retval + " , za podatke o placanju\n");
                return retval;
            }else {
                retval = restTemplate.postForObject("https://" + nacin + "/"
                        + "api3/paypal/completePayment/", entity, String.class);
                logger.info("\n\t\tRedirekcija na adresu: " + retval + " , za zavrsetak placanja.\n");
                return retval;
            }
        }else if(nacin.equals("banka-api")){
            retval = restTemplate.postForObject("https://"+nacin+"/"
                    +"startPayment",entity,String.class);
            logger.info("\n\t\tRedirekcija na adresu: " + retval + " , za podatke o placanju\n");
            return retval;
        }
        logger.info("\n\t\tRedirekcija nije uspela.\n");
        return retval;
    }

    @PostMapping(value="/kreiranaTransakcija")
    public ResponseEntity kreiranaTransakcijaBitcoin(@RequestBody TransakcijeDTO transakcije)
    {
        //find by naziv nemere nikako!!
        Transakcije t = ts.findByOrderId(transakcije.getOrderId());
        if(t==null)
        {
            t = new Transakcije();
            t.setNaziv(transakcije.getNaziv());
            t.setIdTransakcije(transakcije.getIdTransakcije());
            t.setStatus(transakcije.getStatus());
            t.setUplatilac(transakcije.getUplatilac());
            t.setUuid(transakcije.getUuid());
            t.setOrderId(transakcije.getOrderId());
            t.setCena(transakcije.getCena());
            t.setTipPlacanja("BITCOIN");
            t.setVremeKreiranjaTransakcije(transakcije.getVremeKreiranjaTransakcije());
            ts.save(t);
            logger.info("\n\t\tUspesno je kreirana transakcija.\n");
            return new ResponseEntity(HttpStatus.OK);

        }
        logger.info("\n\t\tTransakcija nije uspesno sacuvana.\n");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/izmenjenStatusTransakcije")
    private ResponseEntity izmenjenStatusTransakcije(@RequestBody TransakcijeDTO transakcije)
    {

        Transakcije t = ts.findByOrderId(transakcije.getOrderId());
        if(t!=null)
        {
            if(transakcije.getStatus().equals("uspesno")) {
                t.setStatus(transakcije.getStatus());
                String tipPlacanja = t.getTipPlacanja();
                ts.save(t);
                logger.info("\n\t\tUspesno je placeno preko "+tipPlacanja+"-a .");
                return new ResponseEntity(HttpStatus.OK);
            }else if(transakcije.getStatus().equals("neuspesno"))
            {
                t.setStatus(transakcije.getStatus());
                String tipPlacanja = t.getTipPlacanja();
                ts.save(t);
                logger.info("\n\t\tNeuspesno placanje preko "+tipPlacanja+"-a .");
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        logger.info("\n\t\tTransakcija nije kreirana.");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/kreiranaPayPalTransakcija")
    public ResponseEntity kreirajTransakciju(@RequestBody TransakcijeDTO transakcije)
    {
        Transakcije t = ts.findByOrderId(transakcije.getOrderId());
        if(t==null)
        {
            t = new Transakcije();
            t.setNaziv(transakcije.getNaziv());
            t.setIdTransakcije(transakcije.getIdTransakcije());
            t.setStatus(transakcije.getStatus());
            t.setUplatilac(transakcije.getUplatilac());
            t.setUuid(transakcije.getUuid());
            t.setOrderId(transakcije.getOrderId());
            t.setCena(transakcije.getCena());
            t.setTipPlacanja("PAYPAL");
            t.setVremeKreiranjaTransakcije(transakcije.getVremeKreiranjaTransakcije());
            ts.save(t);
            logger.info("\n\t\tUspesno je kreirana transakcija.\n");
            return new ResponseEntity(HttpStatus.OK);

        }
        logger.info("\n\t\tTransakcija nije uspesno sacuvana.\n");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
