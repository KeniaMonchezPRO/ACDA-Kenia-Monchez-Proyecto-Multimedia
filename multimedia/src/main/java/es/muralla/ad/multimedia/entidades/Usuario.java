package es.muralla.ad.multimedia.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String usuario;
	private String contrasena;
	private String email;
	private String nombre;
	private String apellidos;
	
	@Column(name = "fecha_nac")
	private LocalDate fechaNac;
	
	public Usuario() {
		
	}
	
	public Usuario(String usuario, String contrasena, String email, String nombre, String apellidos, LocalDate fechaNac) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
	}

	public Usuario(int id, String usuario, String contrasena, String email, String nombre, String apellidos, LocalDate fechaNac) {
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	@Override
	public String toString() {
		String builder = String.format("=== Usuario: %s ==="
				+ "\nId: %d"
				+ "\nEmail: %s"
				+ "\nNombre: %s"
				+ "\nApellidos: %s"
				+ "\nFecha de Nacimiento: %s", usuario, id, email, nombre, apellidos, fechaNac);
		return builder;
	}
	
	

}
