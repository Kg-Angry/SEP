package com.sep.banka.serviceImpl;

import com.sep.banka.dto.NaucniCasopisDTO;
import com.sep.banka.dto.PlatilacBankaDTO;
import com.sep.banka.dto.PlatilacDTO;
import com.sep.banka.dto.ZahtevDTO;
import com.sep.banka.model.Zahtev;
import com.sep.banka.repository.ZahtevRepository;
import com.sep.banka.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


@Service
@Transactional
public class ZahtevServiceImpl implements ZahtevService {

    @Autowired
    ZahtevRepository zahtevRepository;

    @Override
    public List<Zahtev> getAll() {
        return zahtevRepository.findAll();
    }

    @Override
    public Zahtev save(ZahtevDTO zahtevDTO) {
        Zahtev zahtev = new Zahtev();
        zahtev.setAmount(zahtevDTO.getAmount());
        zahtev.setErrorUrl(zahtevDTO.getErrorUrl());
        zahtev.setFailedUrl(zahtevDTO.getFailedUrl());
        zahtev.setMerchantUsername(zahtevDTO.getMerchantUsername());
        zahtev.setMerchantPassword(zahtevDTO.getMerchantPassword());
        zahtev.setSuccessUrl(zahtevDTO.getSuccessUrl());
        return zahtevRepository.save(zahtev);
    }

    @Override
    public Zahtev save(Zahtev zahtev) {
        return zahtevRepository.save(zahtev);
    }

    @Override
    public Zahtev save(PlatilacBankaDTO platilacBankaDTO, Long paymentId) {
        Zahtev zahtev = new Zahtev();
        zahtev.setPaymentId(paymentId);
        //OVDE TREBA ID MERCHANTA!!

        String nazivi = "";
        double cena = 0;
        for (NaucniCasopisDTO nc : platilacBankaDTO.getNazivi_casopisa()){
            nazivi += nc.getNaziv() + ",";
            cena += nc.getCena().doubleValue();
        }
        zahtev.setMerchantUsername(nazivi.substring(0,nazivi.length()-1));
        zahtev.setSuccessUrl(platilacBankaDTO.getUspesnaRedirekcija());
        zahtev.setMerchantPassword(platilacBankaDTO.getLozinka_platioca());
        zahtev.setFailedUrl(platilacBankaDTO.getNeuspesnaRedirekcija());
        zahtev.setErrorUrl(platilacBankaDTO.getPogresnaRedirekcija());
        zahtev.setAmount(new BigDecimal(cena));
        zahtev.setMerchantOrderId(platilacBankaDTO.getId_porudzbine());
        zahtev.setMerchantTimestamp(platilacBankaDTO.getVremensko_ogranicenje());
        return zahtevRepository.save(zahtev);
    }

    @Override
    public Zahtev getByPaymentId(Long paymentId) {
        return zahtevRepository.findByPaymentId(paymentId);
    }
}
