package es.muralla.ad.multimedia.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.muralla.ad.multimedia.entidades.Categoria;
import es.muralla.ad.multimedia.entidades.Pelicula;
import es.muralla.ad.multimedia.services.ICategoriasService;
import es.muralla.ad.multimedia.services.IPeliculasService;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	private IPeliculasService peliculasService;
	private ICategoriasService categoriasService;
	
	public PeliculasController(IPeliculasService peliculasService, ICategoriasService categoriasService) {
		this.peliculasService = peliculasService;
		this.categoriasService = categoriasService;
	}
	
	/*ANTES:@GetMapping("/crear")
	public String verFormCrearPelicula(Pelicula p) {
		return "form-pelicula";
	}*/
	
	//DESPUES:
	@GetMapping("/crear")
	public String verFormCrearPelicula(Model model) {
		model.addAttribute("pelicula", new Pelicula());
		List<Categoria> categorias = categoriasService.getAll();
		model.addAttribute("categorias",categorias);
		return "form-pelicula";
	}
	
	@PostMapping("/crear")
	public String crear(Pelicula p) {
		//aqui va en el body la pelicula 
		peliculasService.crear(p);
		return "redirect:/peliculas/getall"; //cada vez que recargas con f5 te hago un get a esto
	}
	
	@GetMapping("/getall")
	public String verTodasLasPelis(Model model) {
		model.addAttribute("peliculas", peliculasService.getAll());
		return "all-peliculas";
	}
	
	@PostMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id) {
		peliculasService.eliminar(id);
		return "redirect:/peliculas/getall";
	}
	
	@PutMapping("/editar/{id}")
	public String editar(Model model, @PathVariable int id) {
		model.addAttribute(peliculasService.getById(id));
		return "/peliculas/editar";
	}
	
	@GetMapping("/categoria/{id}")
	public String getPeliculasByCategoria(Model model, @PathVariable int id) {
		List<Pelicula> peliculaByCategoryId = peliculasService.getPeliculaByCategoryId(id);
		Pelicula categoriaDePelicula = peliculaByCategoryId.getFirst();
		model.addAttribute("peliculas", peliculaByCategoryId);
		model.addAttribute("categoriaDePelicula", categoriaDePelicula);
		return "pelicula-categoria";
	}

}
