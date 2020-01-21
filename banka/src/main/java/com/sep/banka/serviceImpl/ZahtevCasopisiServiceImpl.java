package com.sep.banka.serviceImpl;

import com.sep.banka.model.ZahtevCasopisi;
import com.sep.banka.repository.ZahtevCasopisiRepository;
import com.sep.banka.service.ZahtevCasopisiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ZahtevCasopisiServiceImpl implements ZahtevCasopisiService {

    @Autowired
    ZahtevCasopisiRepository zahtevCasopisiRepository;

    @Override
    public ZahtevCasopisi save(ZahtevCasopisi zahtevCasopisi) {
        return zahtevCasopisiRepository.save(zahtevCasopisi);
    }
}
