package com.example.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public interface ICinemaService {

	public void initVille();
	public void initCinema();
	public void initSalle();
	public void initPlace();
	public void initCategorie();
	public void initFilm();
	public void initSeance();
	public void initProjection();
	public void initTicket();
	
	}
