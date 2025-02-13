package es.muralla.ad.multimedia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.muralla.ad.multimedia.entidades.*;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Integer>{
}
