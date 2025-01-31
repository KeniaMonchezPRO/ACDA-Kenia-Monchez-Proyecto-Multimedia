package es.muralla.ad.multimedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.muralla.ad.multimedia.entidades.Pelicula;
import es.muralla.ad.multimedia.services.IPeliculasService;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	private IPeliculasService peliculasService;
	
	public PeliculasController(IPeliculasService peliculasService) {
		this.peliculasService = peliculasService;
	}
	
	@GetMapping("/crear")
	public String verFormCrearPelicula(@ModelAttribute Pelicula p) {
		return "form-pelicula";
	}
	
	@PostMapping("/crear")
	public String crear(Pelicula p) {
		//aqui va en el body la pelicula 
		peliculasService.crear(p);
		return "redirect:/peliculas/crear"; //cada vez que recargas con f5 te hago un get a esto
	}
	
	@GetMapping("/getall")
	public String verTodasLasPelis(Model model) {
		model.addAttribute("peliculas", peliculasService.getAll());
		return "all-peliculas";
	}

}
