package es.muralla.ad.multimedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.muralla.ad.multimedia.services.ICategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	private ICategoriasService categoriasService;
	
	public CategoriasController(ICategoriasService categoriasService) {
		this.categoriasService = categoriasService;
	}
	
	@GetMapping("/getall")
	public String verCategorias(Model model) {
		model.addAttribute("categorias", categoriasService.getAll());
		return "categorias";
	}
	
	@GetMapping("/categoria/{id}")
	public String getPeliculasByCategoria(@PathVariable int id) {
		
		return "categoria-pelicula";
	}

}
