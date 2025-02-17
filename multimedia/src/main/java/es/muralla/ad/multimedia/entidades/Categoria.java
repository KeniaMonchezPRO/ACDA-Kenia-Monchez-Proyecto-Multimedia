package es.muralla.ad.multimedia.entidades;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "categoria")
	private Set<Pelicula> peliculas;
	
	public Set<Pelicula> getPeliculas() {
		return peliculas;
	}


	public void setPeliculas(Set<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}


	public Categoria() {
	}


	public Categoria(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		String builder = String.format("=== Categoría: %d ==="
				+ "\nNombre: %d"
				+ "\nPelículas: %s", id, nombre, peliculas);
		return builder;
	}

}
