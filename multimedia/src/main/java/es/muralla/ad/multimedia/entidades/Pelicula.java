package es.muralla.ad.multimedia.entidades;

import java.time.LocalDate;

public class Pelicula {

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

}
