package es.muralla.ad.multimedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.muralla.ad.multimedia.dao.PeliculasRepository;
import es.muralla.ad.multimedia.entidades.Pelicula;

@Service
public class PeliculasServiceImpl implements IPeliculasService {
	
	private PeliculasRepository peliculasRepository;
	
	public PeliculasServiceImpl(PeliculasRepository peliculasRepository) {
		this.peliculasRepository = peliculasRepository;
	}

	@Override
	public void crear(Pelicula p) {
		peliculasRepository.save(p);
	}

	@Override
	public void eliminar(int id) {
		peliculasRepository.deleteById(id);
	}

	@Override
	public void modificar(Pelicula p) {
	
	}

	@Override
	public List<Pelicula> getAll() {
		return peliculasRepository.findAll();
	}

	@Override
	public Optional<Pelicula> getById(int id) {
		return peliculasRepository.findById(id);
	}

}
