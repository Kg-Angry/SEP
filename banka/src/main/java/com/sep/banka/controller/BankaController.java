package com.sep.banka.controller;

import com.sep.banka.dto.*;
import com.sep.banka.model.Payment;
import com.sep.banka.model.PlatnaKartica;
import com.sep.banka.model.Zahtev;
import com.sep.banka.service.PaymentService;
import com.sep.banka.service.PlatnaKarticaService;
import com.sep.banka.service.ZahtevService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class BankaController {
    private static final Logger logger = LoggerFactory.getLogger(BankaController.class);

    @Autowired
    PlatnaKarticaService platnaKarticaService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    ZahtevService zahtevService;
    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/startPayment")
    public String startPayment(@RequestBody PlatilacBankaDTO platilacBankaDTO){
        logger.info("\n\t\tPlacanje preko banke zapoceto");
        platilacBankaDTO.setUspesnaRedirekcija("https://localhost:4201/uspesno");
        platilacBankaDTO.setNeuspesnaRedirekcija("https://localhost:4201/neuspesno");
        platilacBankaDTO.setPogresnaRedirekcija("https://localhost:4201/greska");

        TransakcijeDTO transakcijeDTO = new TransakcijeDTO();
        transakcijeDTO.setIdTransakcije(platilacBankaDTO.getId_porudzbine().toString());
        String nazivi = "";
        double cena = 0;
        for (NaucniCasopisDTO nc : platilacBankaDTO.getNazivi_casopisa()){
            nazivi+= nc.getNaziv() + ",";
            cena+=nc.getCena().doubleValue();
        }

        transakcijeDTO.setNaziv(nazivi.substring(0,nazivi.length()-1));
        transakcijeDTO.setOrderId(UUID.randomUUID().toString());
        transakcijeDTO.setStatus("kreirana");
        transakcijeDTO.setUplatilac(platilacBankaDTO.getKorisnicko_ime_platioca());
        transakcijeDTO.setUuid(UUID.randomUUID().toString());
        transakcijeDTO.setTipPlacanja("BANKA");
        transakcijeDTO.setCena(cena);
        transakcijeDTO.setVremeKreiranjaTransakcije(new Date().toString());
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TransakcijeDTO> transakcija = new HttpEntity<>(transakcijeDTO,headers);
        restTemplate.postForObject("https://koncentrator-placanja/api1/kp/kreiranaTransakcija",transakcija,String.class);
        if(platnaKarticaService.prodavciImajuKarticu(platilacBankaDTO.getNazivi_casopisa())){
            Payment payment = new Payment("https://localhost:4201/banka");
            payment = paymentService.save(payment);
            payment.setPaymentUrl("https://localhost:4201/banka/"+payment.getIdPayment());
            logger.info("\n\t\t Kreiranje payment sa id-jem"+payment.getIdPayment());
            zahtevService.save(platilacBankaDTO,payment.getIdPayment());
            logger.info("\n\t\t Kartica prodavca postoji, preusmeravnje na formu za unos podataka platioca");

            payment.setPaymentUrl(payment.getPaymentUrl()+"/"+transakcijeDTO.getOrderId());
            return payment.getPaymentUrl();
        }else{
            logger.info("\n\t\t Kartica prodavca ne postoji");
            return platilacBankaDTO.getNeuspesnaRedirekcija();
        }


    }

    @PostMapping(value = "/checkPan")
    public ResponseEntity<?> checkPan(@RequestBody PlatnaKarticaDTO platnaKarticaDTO){
        List<PlatnaKartica> karticeCasopisa = platnaKarticaService.getProdavacKartica(platnaKarticaDTO);
        logger.info("\n\t\t Pokrenuta provera kartice platioca");
        boolean flag = false;
        ResponseDTO response = new ResponseDTO();
        for(PlatnaKartica platnaKarticaCasopisa: karticeCasopisa) {
            platnaKarticaDTO.setMerchantUsername(platnaKarticaCasopisa.getMerchantUsername());
            if (platnaKarticaDTO.getPan().substring(0, 5).equals(platnaKarticaCasopisa.getPan().substring(0, 5))) {

                logger.info("\n\t\t Kartica platioca je u istoj banci");
                response = pay(platnaKarticaDTO);
                if(response.getStatusTransakcije().equals("https://localhost:4201/uspesno"))
                    flag=true;
                else
                    flag=false;
//                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                logger.info("\n\t\t Kartica platioca je u drugoj banci,preusmeravanje na pcc");
                platnaKarticaDTO.setAcquierID((int) (Math.random() * 100000));
                platnaKarticaDTO.setAcquirerTimestamp(new Timestamp(System.currentTimeMillis()));
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<PlatnaKarticaDTO> entity = new HttpEntity<>(platnaKarticaDTO, headers);
                response = restTemplate.postForObject("https://PCC-API/drugaBanka", entity, ResponseDTO.class);
                if(response.getStatusTransakcije().equals("https://localhost:4201/uspesno"))
                    flag=true;
                else
                    flag=false;
            }
        }
        if(flag){
            response.setStatusTransakcije("https://localhost:4201/uspesno");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.setStatusTransakcije("https://localhost:4201/neuspesno");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/pay")
    public ResponseDTO pay(@RequestBody PlatnaKarticaDTO platnaKarticaDTO){
        if(!platnaKarticaService.proveraAutenticnsotiKupca(platnaKarticaDTO)){
            logger.info("\n\t\t Ne postoji kartica platioca");
            //kupac nije uneo dobre podatke kartic,transakcije neuspela ili da vratimo da unosi ponovo ?
            return null;
        }
        TransakcijeDTO transakcijeDTO = new TransakcijeDTO();
        transakcijeDTO.setOrderId(platnaKarticaDTO.getOrderId());
        transakcijeDTO.setTipPlacanja("BANKA");
        logger.info("\n\t\t Pokrenuto placanje");
        PlatnaKartica karticaKupca = platnaKarticaService.getKupacKartica(platnaKarticaDTO);
        PlatnaKartica karticaProdavca = platnaKarticaService.getKartica(platnaKarticaDTO);
        Zahtev zahtev = zahtevService.getByPaymentId(platnaKarticaDTO.getPaymentId());

        if(zahtev!=null){
            if(karticaKupca.getAmount().compareTo(zahtev.getAmount())>0){
                karticaKupca.setAmount(karticaKupca.getAmount().subtract(zahtev.getAmount()));
                platnaKarticaService.save(karticaKupca);
                karticaProdavca.setAmount(karticaProdavca.getAmount().add(zahtev.getAmount()));
                platnaKarticaService.save(karticaProdavca);

                transakcijeDTO.setStatus("uspesno");
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<TransakcijeDTO> transakcija = new HttpEntity<>(transakcijeDTO,headers);
                headers.setContentType(MediaType.APPLICATION_JSON);
                logger.info("\n\t\t Menjanje statusa transakcije uspesno");
                restTemplate.postForEntity("https://koncentrator-placanja/api1/kp/statusTransakcije",transakcija,String.class);

                logger.info("\n\t\t Placanje je uspesno izvrseno");

                return paymentService.getResponse(platnaKarticaDTO,"https://localhost:4201/uspesno");
            }
        }
        logger.info("\n\t\t Placanje nije uspesno izvrseno");
        transakcijeDTO.setStatus("neuspesno");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TransakcijeDTO> transakcija = new HttpEntity<>(transakcijeDTO,headers);
        headers.setContentType(MediaType.APPLICATION_JSON);

        logger.info("\n\t\t Menjanje statusa transakcije neuspesno");
        restTemplate.postForEntity("https://koncentrator-placanja/api1/kp/statusTransakcije",transakcija,String.class);

        return paymentService.getResponse(platnaKarticaDTO,"https://localhost:4201/neuspesno");
    }

    @PostMapping(value="/neuspesno")
    public ResponseDTO neuspesno(@RequestBody PlatnaKarticaDTO platnaKarticaDTO){
        TransakcijeDTO transakcijeDTO = new TransakcijeDTO();
        transakcijeDTO.setOrderId(platnaKarticaDTO.getOrderId());
        transakcijeDTO.setTipPlacanja("BANKA");
        transakcijeDTO.setStatus("neuspesno");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<TransakcijeDTO> transakcija = new HttpEntity<>(transakcijeDTO,headers);
        headers.setContentType(MediaType.APPLICATION_JSON);

        logger.info("\n\t\t Menjanje statusa transakcije neuspesno");
        restTemplate.postForEntity("https://koncentrator-placanja/api1/kp/statusTransakcije",transakcija,String.class);
        return paymentService.getResponse(platnaKarticaDTO,"https://localhost:4201/neuspesno");
    }

    @PostMapping(value = "/checkCard")
    public PlatnaKarticaDTO checkCard(@RequestBody PlatnaKarticaDTO platnaKarticaDTO){
        if(platnaKarticaService.proveraAutenticnsotiKupca(platnaKarticaDTO)){
            PlatnaKartica kartica = platnaKarticaService.getKupacKartica(platnaKarticaDTO);
            if(zahtevService.getByPaymentId(platnaKarticaDTO.getPaymentId())!=null){
                if(kartica.getAmount().compareTo(zahtevService.getByPaymentId(platnaKarticaDTO.getPaymentId()).getAmount())>0){
                    platnaKarticaDTO.setIssuerId((int)(Math.random()*100000));
                    platnaKarticaDTO.setIssuerTimestamp(new Timestamp(System.currentTimeMillis()));
                    return platnaKarticaDTO;
                }
            }
        }
        return null;
    }

}
