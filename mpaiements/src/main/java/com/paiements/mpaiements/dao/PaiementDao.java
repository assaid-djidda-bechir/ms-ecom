package com.paiements.mpaiements.dao;

import com.paiements.mpaiements.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface PaiementDao extends JpaRepository<Paiement,Integer> {
    Paiement findByidCommand(int idCommande);
}
