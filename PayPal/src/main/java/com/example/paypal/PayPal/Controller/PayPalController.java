package com.example.paypal.PayPal.Controller;

import com.example.paypal.PayPal.DTO.PayPalProfileDTO;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="api3/paypal")
public class PayPalController {

    private static String clientId = "Ae_29b_0t76NauXNqN2GpLBl7CAR82-AoEOGpn4OpY29CBNLDVdD4QwKdheDsBHafoQvs_HLnCRGYSbm";
    private static String clientSecret = "EBtg38K9znkZNdNgrib5mZDdifDYzMqVYHLybGuaftjFd8Q76ag5tjZuxytET2DczXDXxWBP-vp2c97K";

    @RequestMapping(method = RequestMethod.POST, value="/startPayment")
    public String paypal()
    {
        return createPayment("12");
    }

    @PostMapping(value = "/completePayment")
    public ResponseEntity uspesnoPlaceno(@RequestBody PayPalProfileDTO payPalProfileDTO){

        if(completePayment(payPalProfileDTO))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    //metoda za pocetak placanja PAYPAL-a
    public String createPayment(String sum){
        Map<String, Object> response = new HashMap<String, Object>();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(sum);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://localhost:4200/cancel");
        redirectUrls.setReturnUrl("http://localhost:4200/paypal");
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
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        System.out.println("RESPONSE: "+ response.toString());
        return response.get("redirect_url").toString();
    }

    //potvrda klijenta o izvrsenom placanju
    public boolean completePayment(PayPalProfileDTO payPalProfileDTO){

        //System.out.println("PaymentID " + payPalProfileDTO.getPaymentId());
        //System.out.println("PayerID " + payPalProfileDTO.getPayerID());

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
                return true;
            }
            //System.out.println("RESPONSE: " + response);
            return false;
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
            return false;
        }
    }
}
