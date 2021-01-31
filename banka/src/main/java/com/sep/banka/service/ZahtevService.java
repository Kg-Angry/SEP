package com.sep.banka.service;

import com.sep.banka.dto.PlatilacBankaDTO;
import com.sep.banka.dto.PlatilacDTO;
import com.sep.banka.dto.ZahtevDTO;
import com.sep.banka.model.Zahtev;

import java.util.List;

public interface ZahtevService {
    public List<Zahtev> getAll();
    public Zahtev save(ZahtevDTO zahtevDTO);
    public Zahtev save(Zahtev zahtev);
    public Zahtev save(PlatilacBankaDTO platilacBankaDTO, Long idPayment);
    public Zahtev getByPaymentId(Long paymenId);
}
