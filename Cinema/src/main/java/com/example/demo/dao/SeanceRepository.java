package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entites.Salle;
import com.example.demo.entites.Seance;

@RepositoryRestResource
@CrossOrigin("*")
public interface  SeanceRepository extends JpaRepository<Seance, Long>{

}
