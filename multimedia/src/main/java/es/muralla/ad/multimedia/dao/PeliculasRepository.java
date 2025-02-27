package es.muralla.ad.multimedia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.muralla.ad.multimedia.entidades.Pelicula;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {
	
	@Query(value = "Select * from pelicula where id_categoria = :categoria", nativeQuery = true)
	List<Pelicula> getPeliculaByCategoryId(@Param("categoria") int id);

}
