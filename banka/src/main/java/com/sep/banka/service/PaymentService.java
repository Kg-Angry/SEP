package com.sep.banka.service;

import com.sep.banka.dto.PlatnaKarticaDTO;
import com.sep.banka.dto.ResponseDTO;
import com.sep.banka.model.Payment;

import java.util.List;

public interface PaymentService {
    public List<Payment> getAll();

    public Payment save(Payment payment);

    public ResponseDTO getResponse(PlatnaKarticaDTO platnaKarticaDTO, String url);
}
