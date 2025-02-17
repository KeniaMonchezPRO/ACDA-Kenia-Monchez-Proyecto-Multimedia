package es.muralla.ad.multimedia.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import es.muralla.ad.multimedia.entidades.Categoria;
import es.muralla.ad.multimedia.entidades.Pelicula;

public interface ICategoriasService {
	
	void crear(Categoria c);
	
	void eliminar(int id);
	
	void modificar(Categoria c);
	
	List<Categoria> getAll();
	
	Optional<Categoria> getById(int id);

}
