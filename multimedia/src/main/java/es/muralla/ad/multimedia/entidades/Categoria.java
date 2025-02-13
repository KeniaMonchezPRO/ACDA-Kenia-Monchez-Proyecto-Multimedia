package es.muralla.ad.multimedia.entidades;

import java.util.Set;

import jakarta.persistence.OneToMany;

public class Categoria {
	
	private int id;
	private String nombre;
	
	@OneToMany
	private Set<Pelicula> peliculas;
	
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

}
