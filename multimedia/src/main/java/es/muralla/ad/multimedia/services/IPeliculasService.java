package es.muralla.ad.multimedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.muralla.ad.multimedia.entidades.Pelicula;
import es.muralla.ad.multimedia.entidades.Usuario;


public interface IPeliculasService {
	
	void crear(Pelicula p);
	
	void eliminar(int id);
	
	void modificar(Pelicula p);
	
	List<Pelicula> getAll();

}
