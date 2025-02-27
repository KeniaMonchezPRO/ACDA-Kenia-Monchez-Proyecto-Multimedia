package es.muralla.ad.multimedia.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	/**************************Para @Controller**********************************************/
	
	@GetMapping("/crear")
	public String verFormRegistro(Model model) {
		Usuario u = new Usuario();
		model.addAttribute("usuario", u);
		return "form-registro";
	}
	
//	Forma 2
//	@GetMapping("/crear")
//	public String verFormRegistro(@ModelAttribute Pelicula p) {
//		return "form-registro";
//	}
	
//	ANTES DE @PostMapping("/crear")
//	@PostMapping("/crear")
//	public String registrarUsuario(@ModelAttribute("usuario") Usuario u) {
//		usuariosDao.add(u);
//		return "redirect:/usuarios/iniciar";
//	}
	
	@PostMapping("/crear")
    public String addPelicula(@RequestParam int idPelicula, HttpSession session) {
        // Obtener el usuario desde la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        if (usuario != null) {
            // Buscar la película por ID
            Optional<Pelicula> pelicula = peliculasRepository.findById(idPelicula);
            
            if (pelicula.isPresent()) {
                // Llamar al método addPeliculaToUsuario en UsuarioDao
                usuariosDao.addPeliculaToUsuario(usuario.getId(), pelicula.get().getId());
            }

            // Redirigir al usuario a la página de películas (se recargan sus películas)
            return "redirect:/peliculas/getall";
        }

        // Si el usuario no está logueado, redirigir a la página de login
        return "redirect:/usuarios/iniciar";
    }
	
	/* forma de spring: 
	@PostMapping("/crear")
	public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
		model.addAttribute("greeting", usuario);
		return "form-iniciar-sesion";
	}*/
	
	@GetMapping("/iniciar")
	public String verFormIniciarSesion(Model model) {
		Usuario u = new Usuario();
		model.addAttribute("username", u);
		return "form-iniciar-sesion";
	}
	
	@PostMapping("/iniciar")
	public String iniciarPerfilUsuario(@ModelAttribute("username") Usuario u, Model model, HttpSession session) {
		//TODO: QUE VERIFICA TAMBIEN QUE LA CONTRASENA COINCIDA CON EL USUARIO
		//usuario encontrado = usuarioservice.buscarporemail(u.getemail())
		//if(u.getcontrasena.equals(encontrado.getcontrasena(()){ bien coincide la contra } else { //return "redirect:/usuarios/iniciar?error=!" en thymeleaf: <p th:text=${param.error}></p> }
		//Optional<Usuario> encontrado = usuariosDao.getUserByUsername(u);
		Optional<Usuario> encontrado = usuariosDao.getUserByUsername(u.getUsername());
		/*if(encontrado.isPresent()) {
			if(u.getUsername().equals(encontrado.get().getUsername())) {
				return "redirect:/peliculas/crear";
			}
		} else {
			return "redirect:/usuarios/iniciar";
		}
		return "";*/
		if (encontrado.isPresent()) {
	        // Verificar que la contraseña es correcta (comparar contraseñas)
			if (u.getContrasena().equals(encontrado.get().getContrasena())) {
				// Guardamos el usuario en la sesión
	            session.setAttribute("usuario", encontrado.get());
	            // Si la contraseña es correcta, redirigir al usuario a la página de películas
	            return "redirect:/peliculas/getall"; 
	        } else {
	            // Si la contraseña es incorrecta, que lo regrese al form
	        	model.addAttribute("error","Contraseña incorrecta");
	            return "form-iniciar-sesion"; 
	        }
	    } else {
	        // Si no se encuentra el usuario, que lo regrese al form
	    	model.addAttribute("error","Usuario no encontrado");
	        return "form-iniciar-sesion";
	    }
	}
	
	@GetMapping("/logout")
	public String cerrarSesion(HttpSession session) {
	    session.invalidate(); // Invalida la sesión actual
	    return "redirect:/usuarios/iniciar"; // Redirige a la página de login
	}
	
	
	
	
	
	/**************************Para @RestController**********************************************/
	
	@GetMapping("/all")
	public List<Usuario> getAll() {
		List<Usuario> usuarios = usuariosDao.getAll();
		return usuarios;
	}
	
	@GetMapping("/id/{id}")
	public Optional<Usuario> getById(@PathVariable int id) {
		Optional<Usuario> u = usuariosDao.getById(id);
		return u;
		
	}
	
	@GetMapping("/delete/{id}")
	public Usuario deleteById(@PathVariable int id) {
		return usuariosDao.deleteById(id); 
	}
	
	//Para probar hice que cuando cliente ejecute esta request en el Impl se crea un neuvo user "por default" desde aqui dentro, 
	//TODO: modificar estos 2 para que le lleve a un html con un formulario para ingresar nuevo usuario o modificarlo
	@GetMapping("/new")
	public Usuario add() {
		return usuariosDao.add(new Usuario("la piedra demoledora", "fantas_ma","ghost@abc123.net","Fede", "Lobo", LocalDate.of(2000, 04, 25)));
	}
	
	@GetMapping("/update")
	public Usuario update() {
		Usuario u = new Usuario();
		
		u.setId(3);
		u.setUsername("Bob el constructor");
		u.setContrasena("yaga*10");
		u.setEmail("babayaga@yahoo.com");
		u.setNombre("Baba");
		u.setApellidos("Yaga");
		u.setFechaNac(LocalDate.of(2005, 10, 31));
		
		return usuariosDao.update(u); 
	}
	
	

}
