package com.sep.banka.service;

import com.sep.banka.dto.PlatnaKarticaDTO;
import com.sep.banka.model.PlatnaKartica;

import java.util.List;

public interface PlatnaKarticaService {

    public List<PlatnaKartica> getAllPlatneKartice();
    public PlatnaKartica save(PlatnaKartica platnaKartica);
    public Boolean prodavacImaKarticu(String merchantUsername);
    public PlatnaKartica getProdavacKartica(PlatnaKarticaDTO platnaKarticaDTO);
    public PlatnaKartica getKupacKartica(PlatnaKarticaDTO platnaKarticaDTO);
    public Boolean proveraAutenticnsotiKupca(PlatnaKarticaDTO platnaKarticaDTO);
}
