package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entites.Categorie;
import com.example.demo.entites.Salle;

@RepositoryRestResource
@CrossOrigin("*")
public interface  CategorieRepository extends JpaRepository<Categorie, Long>{

}
