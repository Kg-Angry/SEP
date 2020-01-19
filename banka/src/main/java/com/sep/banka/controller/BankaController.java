package com.sep.banka.controller;

import com.sep.banka.dto.PlatilacDTO;
import com.sep.banka.dto.PlatnaKarticaDTO;
import com.sep.banka.dto.ResponseDTO;
import com.sep.banka.dto.TransakcijeDTO;
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

import java.sql.Timestamp;
import java.util.Date;
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
    public String startPayment(@RequestBody PlatilacDTO platilacDTO){
        logger.info("\n\t\tPlacanje preko banke zapoceto");
        platilacDTO.setUspesnaRedirekcija("https://localhost:4201/uspesno");
        platilacDTO.setNeuspesnaRedirekcija("https://localhost:4201/neuspesno");
        platilacDTO.setPogresnaRedirekcija("https://localhost:4201/greska");

        if(platnaKarticaService.prodavacImaKarticu(platilacDTO.getNaziv_casopisa())){
            Payment payment = new Payment("https://localhost:4201/banka");
            payment = paymentService.save(payment);
            payment.setPaymentUrl("https://localhost:4201/banka/"+payment.getIdPayment());
            logger.info("\n\t\t Kreiranje payment sa id-jem"+payment.getIdPayment());
            zahtevService.save(platilacDTO,payment.getIdPayment());
            logger.info("\n\t\t Kartica prodavca postoji, preusmeravnje na formu za unos podataka platioca");

            TransakcijeDTO transakcijeDTO = new TransakcijeDTO();
            transakcijeDTO.setIdTransakcije(platilacDTO.getId_porudzbine().toString());
            transakcijeDTO.setNaziv(platilacDTO.getNaziv_casopisa());
            transakcijeDTO.setOrderId(UUID.randomUUID().toString());
            transakcijeDTO.setStatus("kreirana");
            transakcijeDTO.setUplatilac(platilacDTO.getKorisnicko_ime_platioca());
            transakcijeDTO.setUuid(UUID.randomUUID().toString());
            transakcijeDTO.setTipPlacanja("BANKA");
            transakcijeDTO.setCena((platilacDTO.getCena()).doubleValue());
            transakcijeDTO.setVremeKreiranjaTransakcije(new Date().toString());
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<TransakcijeDTO> transakcija = new HttpEntity<>(transakcijeDTO,headers);
            restTemplate.postForObject("https://koncentrator-placanja/api1/kp/kreiranaTransakcija",transakcija,String.class);
            payment.setPaymentUrl(payment.getPaymentUrl()+"/"+transakcijeDTO.getOrderId());
            return payment.getPaymentUrl();
        }else{
            logger.info("\n\t\t Kartica prodavca ne postoji");
            return platilacDTO.getNeuspesnaRedirekcija();
        }
    }

    @PostMapping(value = "/checkPan")
    public ResponseEntity<?> checkPan(@RequestBody PlatnaKarticaDTO platnaKarticaDTO){
        PlatnaKartica karticaCasopisa = platnaKarticaService.getProdavacKartica(platnaKarticaDTO);
        logger.info("\n\t\t Pokrenuta provera kartice platioca");
        if(platnaKarticaDTO.getPan().substring(0,5).equals(karticaCasopisa.getPan().substring(0,5))){
            logger.info("\n\t\t Kartica platioca je u istoj banci");
            ResponseDTO responseDTO = pay(platnaKarticaDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }else{
            logger.info("\n\t\t Kartica platioca je u drugoj banci,preusmeravanje na pcc");
            platnaKarticaDTO.setAcquierID((int)(Math.random()*100000));
            platnaKarticaDTO.setAcquirerTimestamp(new Timestamp(System.currentTimeMillis()));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<PlatnaKarticaDTO> entity = new HttpEntity<>(platnaKarticaDTO,headers);
            ResponseDTO responseDTO = restTemplate.postForObject("https://PCC-API/drugaBanka",entity,ResponseDTO.class);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
    }

    @PostMapping(value = "/pay")
    public ResponseDTO pay(@RequestBody PlatnaKarticaDTO platnaKarticaDTO){
        if(!platnaKarticaService.proveraAutenticnsotiKupca(platnaKarticaDTO)){
            logger.info("\n\t\t Ne postoji kartica platioca");
            //kupac nije uneo dobre podatke kartic,transakcije neuspela ili da vratimo da unosi ponovo ?
            return null;
        }
        logger.info("\n\t\t Pokrenuto placanje");
        PlatnaKartica karticaKupca = platnaKarticaService.getKupacKartica(platnaKarticaDTO);
        PlatnaKartica karticaProdavca = platnaKarticaService.getProdavacKartica(platnaKarticaDTO);
        Zahtev zahtev = zahtevService.getByPaymentId(platnaKarticaDTO.getPaymentId());

        if(zahtev!=null){
            if(karticaKupca.getAmount().compareTo(zahtev.getAmount())>0){
                karticaKupca.setAmount(karticaKupca.getAmount().subtract(zahtev.getAmount()));
                karticaKupca = platnaKarticaService.save(karticaKupca);
                karticaProdavca.setAmount(karticaProdavca.getAmount().add(zahtev.getAmount()));
                karticaProdavca = platnaKarticaService.save(karticaProdavca);
                TransakcijeDTO transakcijeDTO = new TransakcijeDTO();
                transakcijeDTO.setOrderId(platnaKarticaDTO.getOrderId());
                transakcijeDTO.setStatus("uspesno");
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<TransakcijeDTO> transakcija = new HttpEntity<>(transakcijeDTO,headers);
                headers.setContentType(MediaType.APPLICATION_JSON);
                logger.info("\n\t\t Menjanje statusa transakcije");
                restTemplate.postForObject("https://koncentrator-placanja/api1/kp/izmenjenStatusTransakcije",transakcija,String.class);

                logger.info("\n\t\t Placanje je uspesno izvrseno");

                return paymentService.getResponse(platnaKarticaDTO,"https://localhost:4201/uspesno");
            }
        }
        logger.info("\n\t\t Placanje nije uspesno izvrseno");

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
