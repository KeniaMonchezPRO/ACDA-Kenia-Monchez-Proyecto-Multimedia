package es.muralla.ad.multimedia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.muralla.ad.multimedia.entidades.Pelicula;

@Repository //inyecta dependencia en spring para que no tengamos que hacer new Repository, le dice a spring que le dé un new o un objeto de x tipo. le dice a spring que es un componente Bean
public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {
	//interfaz que tiene las operaciones JDBC por detrás, no tenemos necesidad de escribirlas
//	@Query("from Pelicula p order by p.titulo")
//	public List<Pelicula> findByTituloSortByTitulo();
//	public List<Pelicula> findAllByIdAsc();

}
