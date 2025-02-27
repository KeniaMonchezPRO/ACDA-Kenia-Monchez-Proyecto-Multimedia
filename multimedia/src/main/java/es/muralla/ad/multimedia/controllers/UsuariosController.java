package es.muralla.ad.multimedia.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.muralla.ad.multimedia.dao.PeliculasRepository;
import es.muralla.ad.multimedia.dao.UsuariosDao;
import es.muralla.ad.multimedia.entidades.Pelicula;
import es.muralla.ad.multimedia.entidades.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	private UsuariosDao usuariosDao;
	private final PeliculasRepository peliculasRepository;
	
	public UsuariosController(UsuariosDao usuariosDao, PeliculasRepository peliculasRepository) {
		this.usuariosDao = usuariosDao;
		this.peliculasRepository = peliculasRepository;
	}
	
	@GetMapping("/crear")
	public String verFormRegistro(Model model) {
		Usuario u = new Usuario();
		model.addAttribute("usuario", u);
		return "form-registro";
	}
	
	@PostMapping("/crear")
    public String addPelicula(@RequestParam int idPelicula, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario != null) {
            Optional<Pelicula> pelicula = peliculasRepository.findById(idPelicula);
            
            if (pelicula.isPresent()) {
                usuariosDao.addPeliculaToUsuario(usuario.getId(), pelicula.get().getId());
            }

            return "redirect:/peliculas/getall";
        }

        return "redirect:/usuarios/iniciar";
    }
	
	@GetMapping("/iniciar")
	public String verFormIniciarSesion(Model model) {
		Usuario u = new Usuario();
		model.addAttribute("username", u);
		return "form-iniciar-sesion";
	}
	
	@PostMapping("/iniciar")
	public String iniciarPerfilUsuario(@ModelAttribute("username") Usuario u, Model model, HttpSession session) {
		Optional<Usuario> encontrado = usuariosDao.getUserByUsername(u.getUsername());
		if (encontrado.isPresent()) {
			if (u.getContrasena().equals(encontrado.get().getContrasena())) {
	            session.setAttribute("usuario", encontrado.get());
	            return "redirect:/peliculas/getall"; 
	        } else {
	        	model.addAttribute("error","Contraseña incorrecta");
	            return "form-iniciar-sesion"; 
	        }
	    } else {
	    	model.addAttribute("error","Usuario no encontrado");
	        return "form-iniciar-sesion";
	    }
	}
	
	@GetMapping("/logout")
	public String cerrarSesion(HttpSession session) {
	    session.invalidate();
	    return "redirect:/usuarios/iniciar";
	}
	
	

}
