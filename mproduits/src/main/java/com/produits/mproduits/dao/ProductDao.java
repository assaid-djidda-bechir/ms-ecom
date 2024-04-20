package com.produits.mproduits.dao;

import com.produits.mproduits.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Produit,Integer> {
}
