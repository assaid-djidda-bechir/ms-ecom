package com.produits.mproduits.web.controller;

import com.produits.mproduits.dao.ProductDao;
import com.produits.mproduits.model.Produit;
import com.produits.mproduits.web.exceptions.ProductNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping(value = "/Produits")
    public List<Produit> listDesProduits(){
        List<Produit> produits = productDao.findAll();

        if(produits.isEmpty()) throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");

        return produits;
    }

    @GetMapping( value = "/Produits/{id}")
    public Optional<Produit> recupererUnProduit(@PathVariable int id){
        Optional<Produit> produit = productDao.findById(id);
        if(!produit.isPresent()) throw  new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");
        return produit;
    }
}
