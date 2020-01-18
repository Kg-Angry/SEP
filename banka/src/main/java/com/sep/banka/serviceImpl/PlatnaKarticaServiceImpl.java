package com.sep.banka.serviceImpl;

import com.sep.banka.dto.PlatnaKarticaDTO;
import com.sep.banka.model.PlatnaKartica;
import com.sep.banka.model.Zahtev;
import com.sep.banka.repository.PlatnaKarticaRepository;
import com.sep.banka.repository.ZahtevRepository;
import com.sep.banka.service.PlatnaKarticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlatnaKarticaServiceImpl implements PlatnaKarticaService {

    @Autowired
    PlatnaKarticaRepository platnaKarticaRepository;
    @Autowired
    ZahtevRepository zahtevRepository;

    @Override
    public List<PlatnaKartica> getAllPlatneKartice() {
        return platnaKarticaRepository.findAll();
    }

    @Override
    public PlatnaKartica save(PlatnaKartica platnaKartica) {
        return platnaKarticaRepository.save(platnaKartica);
    }

    @Override
    public Boolean prodavacImaKarticu(String merchantUsername) {
        List<PlatnaKartica> platnaKarticas = platnaKarticaRepository.findAll();
        return  platnaKarticas.stream().anyMatch(x-> x.getMerchantUsername().equals(merchantUsername));
    }

    @Override
    public PlatnaKartica getProdavacKartica(PlatnaKarticaDTO platnaKarticaDTO) {
        List<Zahtev> zahtevi = zahtevRepository.findAll();
        Zahtev zahtev =  zahtevi.stream().filter(x-> x.getPaymentId().compareTo(platnaKarticaDTO.getPaymentId())==0).findAny().orElse(null);
        List<PlatnaKartica> kartice = getAllPlatneKartice();
        System.out.println(zahtev.getMerchantUsername());
        PlatnaKartica platnaKartica = kartice.stream().filter(x-> x.getMerchantUsername().equals(zahtev.getMerchantUsername()) && x.getMerchantUsername()!=null).findAny().orElse(null);
        return platnaKartica;

    }

    @Override
    public PlatnaKartica getKupacKartica(PlatnaKarticaDTO platnaKarticaDTO) {
        List<PlatnaKartica> kartice = getAllPlatneKartice();
        return kartice.stream().filter(x-> x.getCardHolderName().equals(platnaKarticaDTO.getCardHolderName()) &&
                x.getDatumVazenja().equals(platnaKarticaDTO.getDatumVazenja()) && x.getPan().equals(platnaKarticaDTO.getPan()) && x.getSecurityCode().equals(platnaKarticaDTO.getSecurityCode())).findAny().orElse(null);
    }

    @Override
    public Boolean proveraAutenticnsotiKupca(PlatnaKarticaDTO platnaKarticaDTO) {
        List<PlatnaKartica> kartice = getAllPlatneKartice();

        return kartice.stream().anyMatch(x-> x.getCardHolderName().equals(platnaKarticaDTO.getCardHolderName()) &&
                x.getDatumVazenja().equals(platnaKarticaDTO.getDatumVazenja()) && x.getPan().equals(platnaKarticaDTO.getPan()) && x.getSecurityCode().equals(platnaKarticaDTO.getSecurityCode()));

    }


}
