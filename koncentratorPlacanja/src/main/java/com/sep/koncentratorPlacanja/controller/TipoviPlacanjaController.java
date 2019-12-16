package com.sep.koncentratorPlacanja.controller;

import com.sep.koncentratorPlacanja.dto.MoguciTipoviPlacanjaDTO;
import com.sep.koncentratorPlacanja.dto.TipPlacanjaDTO;
import com.sep.koncentratorPlacanja.model.MoguciTipoviPlacanja;
import com.sep.koncentratorPlacanja.model.TipPlacanjaModel;
import com.sep.koncentratorPlacanja.service.MoguciTipoviPlacanjaService;
import com.sep.koncentratorPlacanja.service.TipPlacanjaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api1/tipPlacanja")
public class TipoviPlacanjaController {

    @Autowired
    private TipPlacanjaService tps;
    @Autowired
    private MoguciTipoviPlacanjaService mtps;

    private static final Logger logger = LoggerFactory.getLogger(TipoviPlacanjaController.class);

    @RequestMapping(value = "/tip", method = RequestMethod.POST)
    public ResponseEntity<?> tipPlacanjaCasopisa(@RequestBody TipPlacanjaDTO tpm)
    {
        TipPlacanjaModel t = tps.findByCasopis(tpm.getNaziv());

        if(t==null)
        {
            TipPlacanjaModel tp = new TipPlacanjaModel();
            tp.setNaziv(tpm.getNaziv());
            tp.setTipoviPlacanja(tpm.getTipoviPlacanja());

            tps.save(tp);
            logger.info("\n\t\tDodat je novi tip placanja " + tpm.getNaziv() + " na sistem koncentratora placanja.\n");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        logger.info("\n\t\tDodavanje novog tip placanja nije moguce izvrsiti.\n");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/sviTipovi", method = RequestMethod.GET)
    public ResponseEntity<List<MoguciTipoviPlacanjaDTO>> sviTipoviPlacanja()
    {
        List<MoguciTipoviPlacanja> tpm = mtps.findAll();
        List<MoguciTipoviPlacanjaDTO> mtpDTO = new ArrayList<>();

        for(MoguciTipoviPlacanja t: tpm)
        {
            mtpDTO.add(new MoguciTipoviPlacanjaDTO(t));
        }

        return new ResponseEntity<>(mtpDTO,HttpStatus.OK);
    }

    @RequestMapping(value="/tipoviPlacanjaZaSveCasopise", method=RequestMethod.GET)
    public ResponseEntity<List<TipPlacanjaModel>> tipoviPlacanjaCasopisa()
    {
        List<TipPlacanjaModel> tpm = tps.findAll();

        if(tpm != null)
        {
            return new ResponseEntity<>(tpm,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
