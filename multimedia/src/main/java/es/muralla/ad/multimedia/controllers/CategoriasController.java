package es.muralla.ad.multimedia.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.muralla.ad.multimedia.entidades.Categoria;
import es.muralla.ad.multimedia.entidades.Usuario;
import es.muralla.ad.multimedia.services.ICategoriasService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	private ICategoriasService categoriasService;
	
	public CategoriasController(ICategoriasService categoriasService) {
		this.categoriasService = categoriasService;
	}
	
	@GetMapping("/getall")
	public String verCategorias(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario"); 
		if (usuario == null) {
			return "redirect:/usuarios/iniciar";
		}
		model.addAttribute("categorias", categoriasService.getAll());
		return "categorias";
	}
	
	@GetMapping("/categoria/{id}")
	public String getPeliculasByCategoria(@PathVariable int id) {
		
		return "categoria-pelicula";
	}
	
	@GetMapping("/crear")
	public String verFormCrearPelicula(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario"); 
		if (usuario == null) {
			return "redirect:/usuarios/iniciar";
		}
		model.addAttribute("categoria", new Categoria());
		return "form-categoria";
	}
	
	@PostMapping("/crear")
	public String crear(Categoria c) {
		categoriasService.crear(c);
		return "redirect:/categorias/getall";
	}

}
