package com.example.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategorieRepository;
import com.example.demo.dao.CinemaRepository;
import com.example.demo.dao.FilmRepository;
import com.example.demo.dao.PlaceRepository;
import com.example.demo.dao.ProjectionFilmRepository;
import com.example.demo.dao.SalleRepository;
import com.example.demo.dao.SeanceRepository;
import com.example.demo.dao.TicketRepository;
import com.example.demo.dao.VilleRepository;
import com.example.demo.entites.Categorie;
import com.example.demo.entites.Cinema;
import com.example.demo.entites.Film;
import com.example.demo.entites.Place;
import com.example.demo.entites.ProjectionFilm;
import com.example.demo.entites.Salle;
import com.example.demo.entites.Seance;
import com.example.demo.entites.Ticket;
import com.example.demo.entites.Ville;

@Service
@Transactional
public class CinemaServiceImp implements ICinemaService {

	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private SeanceRepository seanceRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private ProjectionFilmRepository projectionFilmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public void initVille() {
		Stream.of("Toulouse","Carcassonne","Narbonne","Montpellier").forEach(Nomville ->{
			Ville ville = new Ville();
			ville.setName(Nomville);
			villeRepository.save(ville);
		});
	}
	@Override
	public void initCinema() {
		villeRepository.findAll().forEach(ville->{ 
			Stream.of("MegaCGR","CapCinema","Pathé").forEach(Nomcinema->{
				Cinema cinema = new Cinema();
				cinema.setName(Nomcinema);
				cinema.setNombreSalles(3+(int)(Math.random()*7));
				cinema.setVille(ville);
				cinemaRepository.save(cinema);
			});
		});

	}

	@Override
	public void initSalle() {
		cinemaRepository.findAll().forEach(cinema->{
			for(int i=1;i<cinema.getNombreSalles()+1;i++){
				Salle salle = new Salle();
				salle.setCinema(cinema);
				salle.setName("Salle "+i);
				salle.setNombrePlaces(30+(int)(Math.random()*10));
				salleRepository.save(salle);
			}
		});
	}
	
	@Override
	public void initPlace() {
		salleRepository.findAll().forEach(salle->{
			for(int i=1;i<salle.getNombrePlaces()+1;i++){
				Place place = new Place();
				place.setSalle(salle);
				place.setNumero(i);
				placeRepository.save(place);
			}
		});
		
	}

	@Override
	public void initCategorie() {
		Stream.of("Drama","Action","Comedie","Documentaire").forEach(nomCategorie->{
			Categorie categorie = new Categorie();
			categorie.setName(nomCategorie);
			categorieRepository.save(categorie);
		});

	}

	@Override
	public void initFilm() {
		double[] durees = new double[] {
				1,1.5,2,2.5,3
		};
		List<Categorie> categories = categorieRepository.findAll();
		Stream.of("Star Wars 1","Taxi 3","The Social Network","titanic").forEach(titre->{
			Film film = new Film();
			film.setTitre(titre);
			film.setDuree(durees[new Random().nextInt(durees.length)]);
			film.setPhoto(titre.replaceAll(" ","_"));
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmRepository.save(film);
		});

	}

	@Override
	public void initSeance() {
		DateFormat df = new SimpleDateFormat("HH:mm");
		Stream.of("12:00","15:30","17:00","19:00","21:00").forEach(heure->{
			Seance seance = new Seance();
			try {
				seance.setHeureDebut(df.parse(heure));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initProjection() {
		villeRepository.findAll().forEach(ville->{
			ville.getCinemas().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					filmRepository.findAll().forEach(film->{
						seanceRepository.findAll().forEach(seance->{
							ProjectionFilm projectionFilm = new ProjectionFilm();
							projectionFilm.setPrix(7.00);
							projectionFilm.setSalle(salle);
							projectionFilm.setFilm(film);
							projectionFilm.setSeance(seance);
							projectionFilm.setDateProjection(new Date());
							projectionFilmRepository.save(projectionFilm);
							
						});
						
					});
					
				});
			});
		});
		
	}

	@Override
	public void initTicket() {
		projectionFilmRepository.findAll().forEach(projection->{
			projection.getSalle().getPlaces().forEach(place->{
				Ticket ticket = new Ticket();
				ticket.setPlace(place);
				ticket.setProjectionFilm(projection);
				ticket.setPrix(projection.getPrix());
				ticket.setReserve(false);
				ticketRepository.save(ticket);
			});
		});
		
	}
	

}
