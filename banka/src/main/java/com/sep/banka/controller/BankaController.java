package com.sep.banka.controller;

import com.sep.banka.dto.PlatilacDTO;
import com.sep.banka.dto.PlatnaKarticaDTO;
import com.sep.banka.dto.ResponseDTO;
import com.sep.banka.model.Payment;
import com.sep.banka.model.PlatnaKartica;
import com.sep.banka.model.Zahtev;
import com.sep.banka.service.PaymentService;
import com.sep.banka.service.PlatnaKarticaService;
import com.sep.banka.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BankaController {

    @Autowired
    PlatnaKarticaService platnaKarticaService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    ZahtevService zahtevService;

    @PostMapping(value = "/startPayment")
    public String startPayment(@RequestBody PlatilacDTO platilacDTO){
        platilacDTO.setUspesnaRedirekcija("https://localhost:4201/uspesno");
        platilacDTO.setNeuspesnaRedirekcija("https://localhost:4201/neuspesno");
        platilacDTO.setPogresnaRedirekcija("https://localhost:4201/greska");

        if(platnaKarticaService.prodavacImaKarticu(platilacDTO.getNaziv_casopisa())){
            Payment payment = new Payment("https://localhost:4201/banka");
            payment = paymentService.save(payment);
            payment.setPaymentUrl("https://localhost:4201/banka/"+payment.getIdPayment());
            zahtevService.save(platilacDTO,payment.getIdPayment());
            return payment.getPaymentUrl();
        }else{
            return platilacDTO.getNeuspesnaRedirekcija();
        }
    }

    @PostMapping(value = "/checkPan")
    public ResponseEntity<?> checkPan(@RequestBody PlatnaKarticaDTO platnaKarticaDTO){
        PlatnaKartica karticaCasopisa = platnaKarticaService.getProdavacKartica(platnaKarticaDTO);

        if(platnaKarticaDTO.getPan().substring(0,5).equals(karticaCasopisa.getPan().substring(0,5))){
            //Ista je banka
            ResponseDTO responseDTO = pay(platnaKarticaDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }else{
            System.out.println("Preumeriti na pcc");
        }

        return null;
    }

    @PostMapping(value = "/pay")
    public ResponseDTO pay(@RequestBody PlatnaKarticaDTO platnaKarticaDTO){
        if(!platnaKarticaService.proveraAutenticnsotiKupca(platnaKarticaDTO)){
            //kupac nije uneo dobre podatke kartic,transakcije neuspela ili da vratimo da unosi ponovo ?
            return null;
        }
        PlatnaKartica karticaKupca = platnaKarticaService.getKupacKartica(platnaKarticaDTO);
        PlatnaKartica karticaProdavca = platnaKarticaService.getProdavacKartica(platnaKarticaDTO);
        Zahtev zahtev = zahtevService.getByPaymentId(platnaKarticaDTO.getPaymentId());

        if(zahtev!=null){
            if(karticaKupca.getAmount().compareTo(zahtev.getAmount())>0){
                karticaKupca.setAmount(karticaKupca.getAmount().subtract(zahtev.getAmount()));
                karticaKupca = platnaKarticaService.save(karticaKupca);
                karticaProdavca.setAmount(karticaProdavca.getAmount().add(zahtev.getAmount()));
                karticaProdavca = platnaKarticaService.save(karticaProdavca);
                //LOGER TRANSAKCIJA USPESNA

                return paymentService.getResponse(platnaKarticaDTO,"https://localhost:4201/uspesno");
            }
        }
        //LOGER TRANSAKCIJA USPESNA
        return paymentService.getResponse(platnaKarticaDTO,"https://localhost:4201/neuspesno");
    }

}
