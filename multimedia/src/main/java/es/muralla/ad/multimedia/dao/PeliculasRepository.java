package es.muralla.ad.multimedia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.muralla.ad.multimedia.entidades.Pelicula;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {
//	@Query("from Pelicula p order by p.titulo")
//	public List<Pelicula> findByTituloSortByTitulo();
//	public List<Pelicula> findAllByIdAsc();

}
