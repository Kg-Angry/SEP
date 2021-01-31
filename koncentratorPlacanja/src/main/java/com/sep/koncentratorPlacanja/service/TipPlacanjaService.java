package com.sep.koncentratorPlacanja.service;

import com.sep.koncentratorPlacanja.model.TipPlacanjaModel;
import com.sep.koncentratorPlacanja.repository.TipPlacanjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipPlacanjaService {

    @Autowired
    private TipPlacanjaRepository tpr;

    public TipPlacanjaModel findId(Long id)
    {
        return tpr.getOne(id);
    }

    public TipPlacanjaModel findByCasopis(String naziv)
    {
        return tpr.findByNaziv(naziv);
    }

    public List<TipPlacanjaModel> findAll()
    {
        return tpr.findAll();
    }

    public TipPlacanjaModel save(TipPlacanjaModel t)
    {
        return tpr.save(t);
    }
}
