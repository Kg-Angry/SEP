package com.sep.koncentratorPlacanja.service;

import com.sep.koncentratorPlacanja.model.Transakcije;
import com.sep.koncentratorPlacanja.repository.TransakcijeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransakcijeService {

    @Autowired
    private TransakcijeRepository tr;

    public void save(Transakcije t)
    {
        tr.save(t);
    }

    public Transakcije findByNaziv(String naziv)
    {
        return tr.findByNaziv(naziv);
    }

    public Transakcije findByOrderId(String order_id)
    {
        return tr.findByOrderId(order_id);
    }

    public List<Transakcije> findByTransakcije(String status)
    {
        return tr.findAllByStatus(status);
    }
}
