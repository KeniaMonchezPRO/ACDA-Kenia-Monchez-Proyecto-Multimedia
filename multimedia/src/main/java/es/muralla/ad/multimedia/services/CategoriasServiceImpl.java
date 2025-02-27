package es.muralla.ad.multimedia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.muralla.ad.multimedia.dao.CategoriasRepository;
import es.muralla.ad.multimedia.entidades.Categoria;

@Service
public class CategoriasServiceImpl implements ICategoriasService {
	
	private CategoriasRepository categoriasRepository;
	
	public CategoriasServiceImpl(CategoriasRepository categoriasRepository) {
		this.categoriasRepository = categoriasRepository;
	}
	
	@Override
	public void crear(Categoria c) {
		categoriasRepository.save(c);
	}

	@Override
	public void eliminar(int id) {
		categoriasRepository.deleteById(id);
	}

	@Override
	public void modificar(Categoria c) {
		
	}

	@Override
	public List<Categoria> getAll() {
		return categoriasRepository.findAll();
	}

	@Override
	public Optional<Categoria> getById(int id) {
		return categoriasRepository.findById(id);
	}
}
