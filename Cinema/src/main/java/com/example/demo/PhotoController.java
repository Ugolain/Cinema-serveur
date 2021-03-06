package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.FilmRepository;
import com.example.demo.dao.TicketRepository;
import com.example.demo.entites.Film;
import com.example.demo.entites.Ticket;

import lombok.Data;

@RestController
@CrossOrigin("*")
public class PhotoController {
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping(path="/imageFilm/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable (name="id") Long id) throws Exception {
		Film f=filmRepository.findById(id).get();
		String photoName=f.getPhoto();
		File file=new File(System.getProperty("user.home")+"/cinema/"+photoName+".jpg");
		Path path=Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}
	
	@PostMapping("/payerTicket")
	@Transactional
	public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm){
		List<Ticket> listTickets=new ArrayList<>();
		ticketForm.getTickets().forEach(idticket->{
			Optional<Ticket> tic = ticketRepository.findById(idticket);
			tic.get().setReserve(true);
			tic.get().setNomClient(ticketForm.getNomClient());
			tic.get().setCodePaiement(ticketForm.getCodepayement());
			ticketRepository.save(tic.get());
			listTickets.add(tic.get());
		});
		return listTickets;
	}
	
	
}

@Data
class TicketForm{
	private String nomClient;
	private int codepayement;
	private List<Long> tickets = new ArrayList<>();
	
}
