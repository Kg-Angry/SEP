package com.sep.koncentratorPlacanja.service;

import com.sep.koncentratorPlacanja.repository.TipPlacanjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipPlacanjaService {

    @Autowired
    private TipPlacanjaRepository tpr;


}
