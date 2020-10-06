package com.example.demo.entites;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

@Projection(name="p1",types = ProjectionFilm.class)
@CrossOrigin("*")
public interface ProjectionFilmProj {
	public Long getId();
	public Date getDateProjection();
	public double getPrix();
	public Salle getSalle();
	public Seance getSeance();
	public Film getFilm();
	public Collection<Ticket> getTickets();
}
