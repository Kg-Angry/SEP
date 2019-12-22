package com.sep.banka.serviceImpl;

import com.sep.banka.dto.PlatilacDTO;
import com.sep.banka.dto.ZahtevDTO;
import com.sep.banka.model.Zahtev;
import com.sep.banka.repository.ZahtevRepository;
import com.sep.banka.service.ZahtevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        zahtev.setMercantUsername(zahtevDTO.getMerchantUsername());
        zahtev.setMerchantPassword(zahtevDTO.getMerchantPassword());
        zahtev.setSuccessUrl(zahtevDTO.getSuccessUrl());
        return zahtevRepository.save(zahtev);
    }

    @Override
    public Zahtev save(Zahtev zahtev) {
        return zahtevRepository.save(zahtev);
    }

    @Override
    public Zahtev save(PlatilacDTO platilacDTO,Long paymentId) {
        Zahtev zahtev = new Zahtev();
        zahtev.setPaymentId(paymentId);
        //OVDE TREBA ID MERCHANTA!!
        zahtev.setMercantUsername(platilacDTO.getNaziv_casopisa());
        zahtev.setSuccessUrl(platilacDTO.getUspesnaRedirekcija());
        zahtev.setMerchantPassword(platilacDTO.getLozinka_platioca());
        zahtev.setFailedUrl(platilacDTO.getNeuspesnaRedirekcija());
        zahtev.setErrorUrl(platilacDTO.getPogresnaRedirekcija());
        zahtev.setAmount(platilacDTO.getCena());
        zahtev.setMerchantOrderId(platilacDTO.getId_porudzbine());
        zahtev.setMerchantTimestamp(platilacDTO.getVremensko_ogranicenje());
        return zahtevRepository.save(zahtev);
    }

    @Override
    public Zahtev getByPaymentId(Long paymentId) {
        return zahtevRepository.findByPaymentId(paymentId);
    }
}
