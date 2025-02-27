package es.muralla.ad.multimedia.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.muralla.ad.multimedia.entidades.Pelicula;
import es.muralla.ad.multimedia.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class UsuariosMySqlDaoImpl implements UsuariosDao {
	
	private EntityManager entityManager;
	
	public UsuariosMySqlDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Usuario> getAll() {
		TypedQuery<Usuario> query = entityManager.createQuery("from Usuario", Usuario.class);
		List<Usuario> usersList = query.getResultList();
		return usersList;
	}

	@Override
	public Optional<Usuario> getById(int id) {
		Usuario u = entityManager.find(Usuario.class, id);
		return Optional.ofNullable(u);
	}

	@Override
	@Transactional
	public Usuario add(Usuario u) {
		entityManager.persist(u);
		return u;
	}

	@Override
	@Transactional
	public Usuario deleteById(int id) {
		Usuario u = entityManager.find(Usuario.class, id);
		if(u != null) {
			entityManager.remove(u);
		}
		return u;
	}

	@Override
	@Transactional
	public Usuario update(Usuario u) {
		entityManager.merge(u);
		return u;
	}

	@Override
	public Optional<Usuario> getUserByUsername(String username) {
		TypedQuery<Usuario> query = entityManager.createQuery("from Usuario where username = :username", Usuario.class);
		query.setParameter("username", username);
		Optional<Usuario> x = query.getResultList().stream().findFirst();
		return x;
	}

	@Override
	@Transactional
	public void addPeliculaToUsuario(int idUsuario, int idPelicula) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
	    Pelicula pelicula = entityManager.find(Pelicula.class, idPelicula);
	    
	    if (usuario != null && pelicula != null) {
	        if (!usuario.getPeliculas().contains(pelicula)) {
	            usuario.getPeliculas().add(pelicula);
	            entityManager.merge(usuario);
	        }
	    }
	}

}
