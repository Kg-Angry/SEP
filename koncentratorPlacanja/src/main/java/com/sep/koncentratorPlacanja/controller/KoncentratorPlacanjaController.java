package com.sep.koncentratorPlacanja.controller;

import com.sep.koncentratorPlacanja.dto.*;
import com.sep.koncentratorPlacanja.model.*;
import com.sep.koncentratorPlacanja.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping(value = "api1/kp")
public class KoncentratorPlacanjaController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TransakcijeService ts;
    @Autowired
    private BankaSecretService bankaSecretService;
    @Autowired
    private PayPalSecretService payPalSecretService;
    @Autowired
    private BitCoinSecretService bitCoinSecretService;


    private static final Logger logger = LoggerFactory.getLogger(KoncentratorPlacanjaController.class);

//    tipPlacanja = BANKA, Paypal, Bitcoin
    @GetMapping("/form/{tipPlacanja}")
    public ResponseEntity<?> getFormForSecret(@PathVariable String tipPlacanja){

        FormDTO formDTO = restTemplate.getForObject("https://"+tipPlacanja+"-api/api/form",FormDTO.class);
        return ResponseEntity.ok(formDTO);
    }

    @PostMapping("/form/submit")
    public ResponseEntity<?> submitFormSecret(@RequestBody FormSubmitDTO formSubmitDTO){
        if(formSubmitDTO.getNaziv().equals("banka")){
            bankaSecretService.save(formSubmitDTO);
        }else if(formSubmitDTO.getNaziv().equals("paypal")){
            payPalSecretService.save(formSubmitDTO);
        }else if(formSubmitDTO.getNaziv().equals("bitcoin")){
            System.out.println("BITCOIN");
        }else{
            System.out.println("Novi api");
        }

        return ResponseEntity.ok(formSubmitDTO);
    }


    @PostMapping(value="/pretplata")
    public String pretplata(@RequestBody PlatilacDTO platilacDTO)
    {
        String retval="";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlatilacDTO> entity = new HttpEntity<>(platilacDTO,headers);
//        System.out.println("USAO U METODU  " + platilacDTO.getToken());
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
        System.out.println("Order ID: "+platilacDTO.getOrderId());
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
                        + "api3/paypal/completePayment/"+platilacDTO.getOrderId(), entity, String.class);
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
            t.setTipPlacanja(transakcije.getTipPlacanja());
            t.setVremeKreiranjaTransakcije(transakcije.getVremeKreiranjaTransakcije());
            ts.save(t);
            logger.info("\n\t\tUspesno je kreirana transakcija.\n");
            return new ResponseEntity(HttpStatus.OK);

        }
        logger.info("\n\t\tTransakcija nije uspesno sacuvana.\n");
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/statusTransakcije")
    public ResponseEntity<?> statusTransakcije(@RequestBody TransakcijeDTO transakcijeDTO){
        Transakcije t = ts.findByOrderId(transakcijeDTO.getOrderId());
        if(t!=null){
            t.setStatus(transakcijeDTO.getStatus());
            ts.save(t);
            logger.info("\n\t\t Transakcija preko "+ transakcijeDTO.getTipPlacanja() +" je izvrsena "+ transakcijeDTO.getStatus());
            return  ResponseEntity.ok("uspesno");
        }else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/izmenjenStatusTransakcije")
    public ResponseEntity izmenjenStatusTransakcije(@RequestBody TransakcijeDTO transakcije)
    {
        System.out.println("OrderId: " + transakcije.getOrderId());

        Transakcije t = ts.findByOrderId(transakcije.getOrderId());
        if(t!=null)
        {
            if(transakcije.getStatus().equals("uspesno")) {
                t.setStatus(transakcije.getStatus());
                String tipPlacanja = t.getTipPlacanja();
                ts.save(t);
                logger.info("\n\t\tUspesno je placeno preko "+tipPlacanja+"-a .");
                return ResponseEntity.ok(true);
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

    @Scheduled(fixedRate = 30000)
    public void checkStatus() throws ParseException {
        List<Transakcije> lista_transakcija = ts.findByTransakcije("kreirana");

        Date today = Calendar.getInstance().getTime();
        long minuts=0;

        for(Transakcije t : lista_transakcija)
        {
            SimpleDateFormat s = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            Date d = s.parse(t.getVremeKreiranjaTransakcije());
            if (d.before(today)){
                minuts= ((today.getTime() - d.getTime())/ (1000*60)%60);
            }
            if(minuts == 3)
            {
             t.setStatus("neuspesno");
             ts.save(t);
            }
        }
        logger.info("\n\t\tProvera statusa transakcije.\n");
    }

    @GetMapping(value="/transakcije/{order_id}")
    public boolean transakcijeNeuspesna(@PathVariable String order_id)
    {
        Transakcije t = ts.findByOrderId(order_id);
        if(t.getStatus().equals("neuspesno")) {
            return true;
        }
        return false;
    }

    @PostMapping(value="/bankaPayment")
    public String bankaPayment(@RequestBody PlatilacBankaDTO platilacBankaDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PlatilacBankaDTO> entity = new HttpEntity<>(platilacBankaDTO,headers);
        Set<BankaSecretDTO> bankaSecretDTOS = bankaSecretService.findSecret(platilacBankaDTO);
        platilacBankaDTO.setBankaSecret(bankaSecretDTOS);
        String retval = restTemplate.postForObject("https://BANKA-API/"
                +"startPayment",entity,String.class);
        logger.info("\n\t\tRedirekcija na adresu: " + retval + " , za podatke o placanju\n");
        return retval;
    }
}
