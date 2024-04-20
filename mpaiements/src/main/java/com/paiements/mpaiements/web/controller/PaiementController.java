package com.paiements.mpaiements.web.controller;

import com.paiements.mpaiements.dao.PaiementDao;
import com.paiements.mpaiements.model.Paiement;
import com.paiements.mpaiements.web.exceptions.PaiementExistantException;
import com.paiements.mpaiements.web.exceptions.PaiementImpossibleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaiementController {

    PaiementDao paiementDao;

    public PaiementController(PaiementDao paiementDao) {
        this.paiementDao = paiementDao;
    }

    @PostMapping(value = "/paiement")
    public ResponseEntity<Paiement> payerUneCommande(@RequestBody Paiement paiement){
        Paiement paiementExistant = paiementDao.findByidCommand(paiement.getIdCommande());
        if(paiementExistant != null) throw new PaiementExistantException("Cette commande est déjà payée");

        Paiement nouveauPaiement = paiementDao.save(paiement);

        if(nouveauPaiement == null) throw new PaiementImpossibleException("Erreur, impossible d'établir le paiement, réessayez plus tard");

      return new ResponseEntity<Paiement>(nouveauPaiement, HttpStatus.CREATED);
    }
}
