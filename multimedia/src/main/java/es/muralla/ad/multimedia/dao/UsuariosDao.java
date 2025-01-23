package es.muralla.ad.multimedia.dao;

import java.util.List;
import java.util.Optional;

import es.muralla.ad.multimedia.entidades.Usuario;

public interface UsuariosDao {
	
	public List<Usuario> getAll();
	
	public Optional<Usuario> getById(int id);
	
	public Usuario add(Usuario u);
	
	public Usuario deleteById(int id);
	
	public Usuario update(Usuario u);

}
