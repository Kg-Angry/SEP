package com.sep.banka.serviceImpl;

import com.sep.banka.dto.PlatnaKarticaDTO;
import com.sep.banka.dto.ResponseDTO;
import com.sep.banka.model.Payment;
import com.sep.banka.repository.PaymentRepository;
import com.sep.banka.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public ResponseDTO getResponse(PlatnaKarticaDTO platnaKarticaDTO, String url) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setAcquirerId(platnaKarticaDTO.getAcquieID());
        responseDTO.setAcquirerTimestamp(platnaKarticaDTO.getAcquirerTimestamp());
        responseDTO.setMerchantUsername(platnaKarticaDTO.getMerchantUsername());
        responseDTO.setPaymentId(platnaKarticaDTO.getPaymentId());
        responseDTO.setStatusTransakcije(url);
        return responseDTO;
    }
}
