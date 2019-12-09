package com.sep.koncentratorPlacanja.service;

import com.sep.koncentratorPlacanja.model.MoguciTipoviPlacanja;
import com.sep.koncentratorPlacanja.repository.MoguciTipoviPlacanjaResository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoguciTipoviPlacanjaService {

    @Autowired
    private MoguciTipoviPlacanjaResository mtpr;

    public List<MoguciTipoviPlacanja> findAll()
    {
        return mtpr.findAll();
    }

    public void save(MoguciTipoviPlacanja m)
    {
        mtpr.save(m);
    }
}
