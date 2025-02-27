package es.muralla.ad.multimedia.services;

import java.util.List;
import java.util.Optional;

import es.muralla.ad.multimedia.entidades.Pelicula;


public interface IPeliculasService {
	
	void crear(Pelicula p);
	
	void eliminar(int id);
	
	void modificar(Pelicula p);
	
	List<Pelicula> getAll();
	
	Optional<Pelicula> getById(int id);
	
	List<Pelicula> getPeliculaByCategoryId(int id);

}
