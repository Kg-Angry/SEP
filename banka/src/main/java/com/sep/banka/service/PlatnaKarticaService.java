package com.sep.banka.service;

import com.sep.banka.dto.BankaSecretDTO;
import com.sep.banka.dto.NaucniCasopisDTO;
import com.sep.banka.dto.PlatnaKarticaDTO;
import com.sep.banka.model.PlatnaKartica;

import java.util.List;
import java.util.Set;

public interface PlatnaKarticaService {

    public List<PlatnaKartica> getAllPlatneKartice();
    public PlatnaKartica save(PlatnaKartica platnaKartica);
    public Boolean prodavacImaKarticu(String clientId,String clientPass);
    public Boolean prodavciImajuKarticu(Set<BankaSecretDTO> bankaSecretDTOS);
    public List<PlatnaKartica> getProdavacKartica(PlatnaKarticaDTO platnaKarticaDTO);
    public PlatnaKartica getKartica(PlatnaKarticaDTO platnaKarticaDTO);
    public PlatnaKartica getKupacKartica(PlatnaKarticaDTO platnaKarticaDTO);
    public Boolean proveraAutenticnsotiKupca(PlatnaKarticaDTO platnaKarticaDTO);
}
