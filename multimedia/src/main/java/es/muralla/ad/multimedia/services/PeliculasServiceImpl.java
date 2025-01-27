package es.muralla.ad.multimedia.services;

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

}
