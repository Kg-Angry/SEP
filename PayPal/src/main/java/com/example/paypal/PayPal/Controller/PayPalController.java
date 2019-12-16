package com.example.paypal.PayPal.Controller;

import com.example.paypal.PayPal.DTO.PayPalProfileDTO;
import com.example.paypal.PayPal.DTO.PlatilacDTO;
import com.example.paypal.PayPal.DTO.TransakcijeDTO;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

@RestController
@RequestMapping(value="api3/paypal")
public class PayPalController {

    @Autowired
    RestTemplate restTemplate;

    private static String clientId = "Ae_29b_0t76NauXNqN2GpLBl7CAR82-AoEOGpn4OpY29CBNLDVdD4QwKdheDsBHafoQvs_HLnCRGYSbm";
    private static String clientSecret = "EBtg38K9znkZNdNgrib5mZDdifDYzMqVYHLybGuaftjFd8Q76ag5tjZuxytET2DczXDXxWBP-vp2c97K";

    @RequestMapping(method = RequestMethod.POST, value="/startPayment")
    public String paypal(@RequestBody PlatilacDTO platilac)
    {
        return createPayment(platilac);
    }

    @PostMapping(value = "/completePayment")
    public ResponseEntity uspesnoPlaceno(@RequestBody PayPalProfileDTO payPalProfileDTO){

        if(completePayment(payPalProfileDTO))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    //metoda za pocetak placanja PAYPAL-a
    public String createPayment(PlatilacDTO platilac){
        String orderId = UUID.randomUUID().toString();
        Map<String, Object> response = new HashMap<String, Object>();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(platilac.getCena().toString());
        Transaction transaction = new Transaction();
        System.out.println("Transakcija: "+ transaction);
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        System.out.println("Payments: " + payment);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://localhost:4200/neuspesnoPlacanje/"+orderId);
        redirectUrls.setReturnUrl("https://localhost:4200/paypal/"+orderId);
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if(createdPayment!=null){
                List<Links> links = createdPayment.getLinks();
                for (Links link:links) {
                    if(link.getRel().equals("approval_url")){
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
                //kreiranje transakcije za back
                TransakcijeDTO trDTO = new TransakcijeDTO();

                trDTO.setIdTransakcije(platilac.getId_porudzbine().toString());
                trDTO.setNaziv(platilac.getNaziv_casopisa());
                trDTO.setOrderId(orderId);
                trDTO.setStatus("kreirana");
                trDTO.setUplatilac(platilac.getKorisnicko_ime_platioca());
                trDTO.setUuid(UUID.randomUUID().toString());
                trDTO.setCena(platilac.getCena());
                trDTO.setVremeKreiranjaTransakcije(new Date().toString());
                HttpHeaders h = new HttpHeaders();
                HttpEntity<TransakcijeDTO> transakcija = new HttpEntity<>(trDTO,h);
                restTemplate.postForObject("https://koncentrator-placanja/api1/kp/kreiranaPayPalTransakcija",transakcija,String.class);

            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        System.out.println("RESPONSE: "+ response.toString());
        return response.get("redirect_url").toString();
    }

    //potvrda klijenta o izvrsenom placanju
    public boolean completePayment(PayPalProfileDTO payPalProfileDTO){
        Map<String, Object> response = new HashMap();
        Payment payment = new Payment();
        payment.setId(payPalProfileDTO.getPaymentId());
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payPalProfileDTO.getPayerID());
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if(createdPayment!=null){
                response.put("status", "success");
                response.put("payment", createdPayment);
                //System.out.println("RESPONSE COMPLETE: " + response);
                return true;
            }

            return false;
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
            return false;
        }
    }
}
