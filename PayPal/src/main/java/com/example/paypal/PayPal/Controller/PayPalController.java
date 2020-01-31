package com.example.paypal.PayPal.Controller;

import com.example.paypal.PayPal.DTO.PayPalProfileDTO;
import com.example.paypal.PayPal.DTO.PlatilacDTO;
import com.example.paypal.PayPal.DTO.TopSecretDataDTO;
import com.example.paypal.PayPal.DTO.TransakcijeDTO;
import com.example.paypal.PayPal.Model.TopSecretData;
import com.example.paypal.PayPal.Service.TopSecretDataService;
import com.paypal.api.payments.*;
import com.paypal.api.payments.Currency;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.sun.java.swing.plaf.windows.resources.windows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping(value="api3/paypal")
public class PayPalController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TopSecretDataService tsds;

//    private static String clientId = "Ae_29b_0t76NauXNqN2GpLBl7CAR82-AoEOGpn4OpY29CBNLDVdD4QwKdheDsBHafoQvs_HLnCRGYSbm";
//    private static String clientSecret = "EBtg38K9znkZNdNgrib5mZDdifDYzMqVYHLybGuaftjFd8Q76ag5tjZuxytET2DczXDXxWBP-vp2c97K";

    private static String clientId="";
    private static String clientSecret="";
    private static String planID="";

    @RequestMapping(method = RequestMethod.POST, value="/startPayment")
    public String paypal(@RequestBody PlatilacDTO platilac)
    {
        return createPayment(platilac);
    }

    @PostMapping(value = "/completePayment/{orderId}")
    public String uspesnoPlaceno(@RequestBody PayPalProfileDTO payPalProfileDTO, @PathVariable String orderId){

        if(completePayment(payPalProfileDTO,orderId))
            return "uspesno";
        else
            return "neuspesno";
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
            TopSecretData topSecretData = tsds.findByNazivCasopisa(platilac.getNaziv_casopisa());
            clientId=topSecretData.getClientId();
            clientSecret=topSecretData.getClientSecret();
            APIContext context = new APIContext(topSecretData.getClientId(), topSecretData.getClientSecret(), "sandbox");
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
                if(platilac.getNaziv_radova().equals(""))
                    trDTO.setNaziv(platilac.getNaziv_casopisa());
                else
                    trDTO.setNaziv(platilac.getNaziv_radova());
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
    public boolean completePayment(PayPalProfileDTO payPalProfileDTO, String orderId){
        Map<String, Object> response = new HashMap();
        Payment payment = new Payment();
        payment.setId(payPalProfileDTO.getPaymentId());
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payPalProfileDTO.getPayerID());

        boolean statusTransakcije = restTemplate.getForObject("https://koncentrator-placanja/api1/kp/transakcije/"+orderId,Boolean.class);
        if(statusTransakcije)
            return false;
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if(createdPayment!=null){
                response.put("status", "success");
                response.put("payment", createdPayment);

                return true;
            }

            return false;
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
            return false;
        }
    }

    @PostMapping(value="/pretplataPayPal")
    public String pretplatiSe(@RequestBody PlatilacDTO platilacDTO)
    {
        //pravljenje plana za pretplatu
        Plan p = new Plan();
        p.setName("Pretplata");
        p.setDescription("Pretplata korisnika na odgovarajuci casopis");
        p.setType("fixed");

        PaymentDefinition pd = new PaymentDefinition();
        pd.setName("Pretplata na casopis");
        pd.setType("REGULAR");
        //napraviti da bude dinamicko (nedelja/mesec/godina)
        pd.setFrequency(platilacDTO.getPeriod());
        pd.setFrequencyInterval("1");

        //na koliko krugova (ukoliko je na mesec dana onda 12 meseci zaredom)
        pd.setCycles(platilacDTO.getRate());

        Currency c = new Currency();
        c.setValue(platilacDTO.getCena().toString());
        c.setCurrency("USD");
        pd.setAmount(c);

        ChargeModels cm = new com.paypal.api.payments.ChargeModels();;
        cm.setType("SHIPPING");
        cm.setAmount(c);

        List<ChargeModels> list_chargeModels = new ArrayList<>();
        list_chargeModels.add(cm);
        pd.setChargeModels(list_chargeModels);

        List<PaymentDefinition> list_pd = new ArrayList<>();
        list_pd.add(pd);
        p.setPaymentDefinitions(list_pd);

        MerchantPreferences mp = new MerchantPreferences();
        mp.setSetupFee(c);
        mp.setReturnUrl("https://localhost:4200/paypal_pretplata/"+platilacDTO.getToken());
        mp.setCancelUrl("https://localhost:4200/neuspesnoPlacanje/"+platilacDTO.getToken());
        mp.setAutoBillAmount("YES");
        mp.setInitialFailAmountAction("CONTINUE");
        mp.setMaxFailAttempts("0");
        p.setMerchantPreferences(mp);

        try {
            TopSecretData topSecretData = tsds.findByNazivCasopisa(platilacDTO.getNaziv_casopisa());
            clientId=topSecretData.getClientId();
            clientSecret=topSecretData.getClientSecret();
            APIContext apiContext = new APIContext(clientId,clientSecret, "sandbox");
            // Create payment
            Plan createdPlan = p.create(apiContext);

            System.out.println("ID plana za verifikaciju " + createdPlan.getId());
            System.out.println("Stanje plana" + createdPlan.getState());

            ArrayList<Patch> lista_patch = new ArrayList<>();
            //sluzi za promenu stanja plana u CREATE
            Patch patch = new Patch();
            patch.setOp("replace");
            patch.setPath("/");
            Map<String,String> stanje_plana = new HashMap<>();
            stanje_plana.put("state","ACTIVE");
            patch.setValue(stanje_plana);
            lista_patch.add(patch);

            createdPlan.update(apiContext,lista_patch);

            System.out.println("Stanje nakon UPDATE: " + createdPlan.getState());

            //System.out.println("Vratio je: " + creiranje_ugovora_za_pretplatu(platilacDTO, createdPlan.getId(), apiContext));

            TransakcijeDTO trDTO = new TransakcijeDTO();

            trDTO.setIdTransakcije(platilacDTO.getId_porudzbine().toString());
            trDTO.setNaziv(platilacDTO.getNaziv_casopisa());
            trDTO.setOrderId(createdPlan.getId()); //stavio sa ID plana da se cuva kao transakcija OrderID
            planID = createdPlan.getId();
            trDTO.setStatus("kreirana");
            trDTO.setUplatilac(platilacDTO.getKorisnicko_ime_platioca());
            trDTO.setUuid(UUID.randomUUID().toString());
            trDTO.setCena(platilacDTO.getCena());
            trDTO.setVremeKreiranjaTransakcije(new Date().toString());
            HttpHeaders h = new HttpHeaders();
            HttpEntity<TransakcijeDTO> transakcija = new HttpEntity<>(trDTO,h);
            restTemplate.postForObject("https://koncentrator-placanja/api1/kp/kreiranaPayPalTransakcija",transakcija,String.class);

            return creiranje_ugovora_za_pretplatu(platilacDTO, createdPlan.getId(), apiContext);

        }catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }

        return null;
    }

    public String creiranje_ugovora_za_pretplatu(PlatilacDTO platilac, String id_plana, APIContext apiContext)
    {
        Agreement agree = new Agreement();
        agree.setName(platilac.getNaziv_casopisa()+" pretplata");
        agree.setDescription("Pretplata " + platilac.getNaziv_casopisa());
        String startDate = (new Date()).toInstant().plus(Duration.ofMinutes(2)).toString();
        agree.setStartDate(startDate);

        Plan plan = new Plan();
        plan.setId(id_plana);
        agree.setPlan(plan);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        agree.setPayer(payer);

        ShippingAddress address = new ShippingAddress();
        address.setLine1("111 First Street");
        address.setCity("Saratoga");
        address.setState("CA");
        address.setPostalCode("95070");
        address.setCountryCode("US");
        agree.setShippingAddress(address);

        try {
            agree = agree.create(apiContext);
            System.out.println("Slozio se: " + agree);
            for (Links links : agree.getLinks()) {
                if ("approval_url".equals(links.getRel())) {
                    URL url = new URL(links.getHref());

                    return url.toString();

                }
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value="/izvrsiPretplatu")
    public String izvrsi_pretplatu(@RequestBody PlatilacDTO platilacDTO)
    {
        Agreement agreement =  new Agreement();
        agreement.setToken(platilacDTO.getToken());

        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
            Agreement activeAgreement = agreement.execute(apiContext, agreement.getToken());
            System.out.println("Agreement: " + activeAgreement);
            for (Links links : activeAgreement.getLinks()) {
               URL url = new URL(links.getHref());
               TransakcijeDTO transakicje = new TransakcijeDTO();
               transakicje.setOrderId(planID);
                transakicje.setStatus("uspesno");

                boolean statusTransakcije = restTemplate.postForObject("https://koncentrator-placanja/api1/kp/izmenjenStatusTransakcije",transakicje,Boolean.class);

                return url.toString();

                }
            } catch (PayPalRESTException | MalformedURLException ex) {

            TransakcijeDTO transakicje = new TransakcijeDTO();
            transakicje.setOrderId(planID);
            transakicje.setStatus("neuspesno");

            boolean statusTransakcije = restTemplate.postForObject("https://koncentrator-placanja/api1/kp/izmenjenStatusTransakcije",transakicje,Boolean.class);
        }
        return null;
    }

    @PostMapping(value="/saveSecret")
    public ResponseEntity<?> saveSecret(@RequestBody TopSecretDataDTO t)
    {
        TopSecretData tsd = tsds.findByNazivCasopisa(t.getNazivCasopisa());

        if(tsd!=null)
        {
            TopSecretData tsd1 = new TopSecretData();
            tsd1.setNazivCasopisa(t.getNazivCasopisa());
            tsd1.setClientId(t.getClientId());
            tsd1.setClientSecret(t.getClientSecret());
            tsds.save(tsd1);
            return new ResponseEntity<>(HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
