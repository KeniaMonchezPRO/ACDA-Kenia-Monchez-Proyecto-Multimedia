package es.muralla.ad.multimedia.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.muralla.ad.multimedia.entidades.Usuario;
import jakarta.persistence.EntityManager;
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
		//si hago el metodo de https://www.bezkoder.com/jpa-entitymanager-spring-boot/ no funciona, me da el error org.hibernate.UnknownEntityTypeException: Unable to locate persister: java.util.Optional (sen resolver)
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
	
	
	
	

}
