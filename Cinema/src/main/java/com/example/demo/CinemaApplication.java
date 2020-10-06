package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entites.Film;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner{

	@Autowired
	private ICinemaService iCinemaService;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Film.class);
		iCinemaService.initVille();
		iCinemaService.initCinema();
		iCinemaService.initSalle();
		iCinemaService.initPlace();
		iCinemaService.initCategorie();
		iCinemaService.initFilm();
		iCinemaService.initSeance();
		iCinemaService.initProjection();
		iCinemaService.initTicket();
	}

	
		
}
