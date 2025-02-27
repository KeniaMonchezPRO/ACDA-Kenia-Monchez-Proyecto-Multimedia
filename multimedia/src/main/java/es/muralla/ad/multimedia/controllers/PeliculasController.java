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
	
	/************** PARA PRUEBAS *****************/
	
	@GetMapping("/prueba")
	public String prueba() {
		return "page-template";
	}
	
	@GetMapping("/prueba2")
	public String prueba2( ) {
		return"/fragments/card-layout";
	}
	
	/*********************************************/
	
	/*ANTES:@GetMapping("/crear")
	public String verFormCrearPelicula(Pelicula p) {
		return "form-pelicula";
	}*/
	
	//DESPUES:
	@GetMapping("/crear")
	public String verFormCrearPelicula(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null) {
	        return "redirect:/usuarios/iniciar";  // Si no hay sesión, redirigir al login
	    }
		model.addAttribute("pelicula", new Pelicula());
		List<Categoria> categorias = categoriasService.getAll();
		model.addAttribute("categorias",categorias);
		return "form-pelicula";
	}
	
//	@PostMapping("/crear")
//	public String crear(Pelicula p) {
//		//aqui va en el body la pelicula 
////		System.out.println(p.getId());
////		System.out.println(p.getTitulo());
////		System.out.println(p.getEstreno());
//		peliculasService.crear(p);
//		return "redirect:/peliculas/getall"; //cada vez que recargas con f5 te hago un get a esto
//	}
	
	@PostMapping("/crear")
	public String agregarPelicula(@ModelAttribute Pelicula pelicula, @SessionAttribute Usuario usuario, HttpSession session) {
	    
		// Guardamos la película en la base de datos
	    peliculasService.crear(pelicula);
	    
	    // Asociamos la película al usuario
	    usuario.getPeliculas().add(pelicula);
	    usuariosDao.update(usuario);
	    
	    // Forzar actualización en sesión
	    usuario = usuariosDao.getById(usuario.getId()).get(); // Recargar usuario desde la BD
	    session.setAttribute("usuario", usuario); // Actualizar la sesión con los nuevos datos

	    
	    // Redirigimos a la página donde se muestran sus películas
	    return "redirect:/peliculas/getall";
	}
	
//	@GetMapping("/getall")
//	public String verTodasLasPelis(Model model) {
//		model.addAttribute("peliculas", peliculasService.getAll());
//		return "all-peliculas";
//	}
	
	@GetMapping("/getall")
	public String verPeliculasPorUsuario(Model model, HttpSession session) {
	    // Obtener el usuario desde la sesión
	    Usuario usuario = (Usuario) session.getAttribute("usuario"); // El usuario debe estar guardado en la sesión

	    if (usuario != null) {
	        // Obtener las películas del usuario desde la relación Many-to-Many
	        List<Pelicula> peliculasDelUsuario = usuario.getPeliculas(); // Las películas relacionadas con el usuario
	        
	        model.addAttribute("peliculas", peliculasDelUsuario); // Pasamos las películas al modelo
	        return "all-peliculas"; // Vista donde se muestran las películas
	    }
	    
	    // Si no hay usuario en la sesión, redirigir a la página de login
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
			System.out.println("Fecha de estreno: " + p.get().getEstreno());
			
//			// Formatea la fecha en el formato 'yyyy-MM-dd' (por ejemplo, '2014-12-17')
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	        String fechaFormateada = p.get().getEstreno().format(formatter);
			model.addAttribute("pelicula", p.get());
//			model.addAttribute("fechaEstreno", p.get().getEstreno());
			model.addAttribute("categorias", categoriasService.getAll());
			
//			System.out.println("Fecha en el modelo: " + model.getAttribute("fechaEstreno"));
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
//		List<Pelicula> peliculaByCategoryId = peliculasService.getPeliculaByCategoryId(id);
//		if(peliculaByCategoryId != null) {
//			Pelicula categoriaDePelicula = peliculaByCategoryId.getFirst();
//			model.addAttribute("peliculas", peliculaByCategoryId);
//			model.addAttribute("categoriaDePelicula", categoriaDePelicula);
//		} else {
//			model.addAttribute("peliculas", peliculaByCategoryId);
//		}
		List<Pelicula> peliculaByCategoryId = peliculasService.getPeliculaByCategoryId(id);
		Pelicula categoriaDePelicula = peliculaByCategoryId.isEmpty() ? new Pelicula() : peliculaByCategoryId.getFirst();
	    Optional<Categoria> categoria = categoriasService.getById(id); // Asegúrate de tener este método en tu servicio

	    model.addAttribute("peliculas", peliculaByCategoryId);
	    model.addAttribute("categoriaDePelicula", categoriaDePelicula);
	    model.addAttribute("categoria", categoria.get()); // Pasamos la categoría por separado
		
		return "pelicula-categoria";
	}

}
