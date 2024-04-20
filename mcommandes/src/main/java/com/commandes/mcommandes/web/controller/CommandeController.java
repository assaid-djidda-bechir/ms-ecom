package com.commandes.mcommandes.web.controller;


import com.commandes.mcommandes.dao.CommandeDao;
import com.commandes.mcommandes.model.Commande;
import com.commandes.mcommandes.web.exceptions.CommandeNotFoundException;
import com.commandes.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CommandeController {

    CommandeDao commandeDao;

    @PostMapping(value = "/commandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande) {
        Commande nouvelleCommande = commandeDao.save(commande);

        if(nouvelleCommande == null) throw  new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }

    @GetMapping(value = "/commandes/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable int id){
        Optional<Commande> commande = commandeDao.findById(id);

        if(!commande.isPresent()) throw new CommandeNotFoundException("Cette commande n'existe pas");

        return commande;

    }

}
