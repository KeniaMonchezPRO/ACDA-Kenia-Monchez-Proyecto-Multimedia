package es.muralla.ad.multimedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.muralla.ad.multimedia.services.ICategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {
	
	private ICategoriasService categoriasService;
	
	public CategoriasController(ICategoriasService categoriasService) {
		this.categoriasService = categoriasService;
	}

}
