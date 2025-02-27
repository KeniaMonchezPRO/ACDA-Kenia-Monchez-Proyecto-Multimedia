package es.muralla.ad.multimedia.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import es.muralla.ad.multimedia.dao.UsuariosDao;
import es.muralla.ad.multimedia.entidades.Categoria;
import es.muralla.ad.multimedia.entidades.Pelicula;
import es.muralla.ad.multimedia.entidades.Usuario;
import es.muralla.ad.multimedia.services.ICategoriasService;
import es.muralla.ad.multimedia.services.IPeliculasService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	private IPeliculasService peliculasService;
	private ICategoriasService categoriasService;
	private UsuariosDao usuariosDao;
	
	public PeliculasController(IPeliculasService peliculasService, ICategoriasService categoriasService, UsuariosDao usuariosDao) {
		this.peliculasService = peliculasService;
		this.categoriasService = categoriasService;
		this.usuariosDao = usuariosDao;
	}
	
	@GetMapping("/crear")
	public String verFormCrearPelicula(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (usuario == null) {
	        return "redirect:/usuarios/iniciar";
	    }
		
		model.addAttribute("pelicula", new Pelicula());
		
		List<Categoria> categorias = categoriasService.getAll();
		model.addAttribute("categorias",categorias);
		
		return "form-pelicula";
	}
	
	@PostMapping("/crear")
	public String agregarPelicula(@ModelAttribute Pelicula pelicula, @SessionAttribute Usuario usuario, HttpSession session) {
	    peliculasService.crear(pelicula);
	    
	    usuario.getPeliculas().add(pelicula);
	    usuariosDao.update(usuario);
	    
	    usuario = usuariosDao.getById(usuario.getId()).get();
	    session.setAttribute("usuario", usuario);

	    return "redirect:/peliculas/getall";
	}
	
	@GetMapping("/getall")
	public String verPeliculasPorUsuario(Model model, HttpSession session) {
	    Usuario usuario = (Usuario) session.getAttribute("usuario");

	    if (usuario != null) {
	        List<Pelicula> peliculasDelUsuario = usuario.getPeliculas();
	        model.addAttribute("peliculas", peliculasDelUsuario);
	        
	        return "all-peliculas";
	    }
	    
	    return "redirect:/usuarios/iniciar";
	}
	
	@PostMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id) {
		peliculasService.eliminar(id);
		
		return "redirect:/peliculas/getall";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(Model model, @PathVariable int id) {
		Optional<Pelicula> p = peliculasService.getById(id);
		if(!p.isPresent()) {
			
			return "redirect:/peliculas/getall";
		} else {
			model.addAttribute("pelicula", p.get());
			model.addAttribute("categorias", categoriasService.getAll());
			
			return "form-pelicula";
		}
	}
	
	@PostMapping("/editar/{id}")
	public String editar(@PathVariable int id, @ModelAttribute Pelicula p) {
		peliculasService.crear(p);
		return "redirect:/peliculas/getall"; 
	}
	
	@GetMapping("/categoria/{id}")
	public String getPeliculasByCategoria(Model model, @PathVariable int id) {
		List<Pelicula> peliculaByCategoryId = peliculasService.getPeliculaByCategoryId(id);
		Pelicula categoriaDePelicula = peliculaByCategoryId.isEmpty() ? new Pelicula() : peliculaByCategoryId.getFirst();
	    Optional<Categoria> categoria = categoriasService.getById(id);

	    model.addAttribute("peliculas", peliculaByCategoryId);
	    model.addAttribute("categoriaDePelicula", categoriaDePelicula);
	    model.addAttribute("categoria", categoria.get());
		
		return "pelicula-categoria";
	}

}
