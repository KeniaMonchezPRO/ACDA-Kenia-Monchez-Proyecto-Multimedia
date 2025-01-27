package es.muralla.ad.multimedia.entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pelicula")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private LocalDate estreno;
	private int duracion;
	
	public Pelicula() {
		
	}

	public Pelicula(int id, String titulo, LocalDate estreno, int duracion) {
		this.id = id;
		this.titulo = titulo;
		this.estreno = estreno;
		this.duracion = duracion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getEstreno() {
		return estreno;
	}

	public void setEstreno(LocalDate estreno) {
		this.estreno = estreno;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	@Override
	public String toString() {
		String builder = String.format("=== Pelicula: %d ==="
				+ "\nTítulo: %d"
				+ "\nEstreno: %s"
				+ "\nDuración: %d", id, titulo, estreno, duracion);
		return builder;
	}

}
