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

import es.muralla.ad.multimedia.dao.UsuariosDao;
import es.muralla.ad.multimedia.entidades.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	private UsuariosDao usuariosDao;
	
	public UsuariosController(UsuariosDao usuariosDao) {
		this.usuariosDao = usuariosDao;
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
	
	@PostMapping("/crear")
	public String registrarUsuario(@ModelAttribute("usuario") Usuario u) {
		usuariosDao.add(u);
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
		model.addAttribute("usuario", u);
		return "form-iniciar-sesion";
	}
	
	@PostMapping("/iniciar")
	public String iniciarPerfilUsuario(@ModelAttribute("usuario") Usuario u) {
		//usuario encontrado = usuarioservice.buscarporemail(u.getemail())
		//if(u.getcontrasena.equals(encontrado.getcontrasena(()){ bien coincide la contra } else { //return "redirect:/usuarios/iniciar?error=!" en thymeleaf: <p th:text=${param.error}></p> }
		Optional<Usuario> encontrado = usuariosDao.getUserByUsername(u);
		if(u.getUsuario().equals(encontrado.get().getUsuario())) {
			return "SUCCESS";
		} else {
			return "redirect:/usuarios/iniciar";
		}
		
		
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
		u.setUsuario("Bob el constructor");
		u.setContrasena("yaga*10");
		u.setEmail("babayaga@yahoo.com");
		u.setNombre("Baba");
		u.setApellidos("Yaga");
		u.setFechaNac(LocalDate.of(2005, 10, 31));
		
		return usuariosDao.update(u); 
	}
	
	

}
