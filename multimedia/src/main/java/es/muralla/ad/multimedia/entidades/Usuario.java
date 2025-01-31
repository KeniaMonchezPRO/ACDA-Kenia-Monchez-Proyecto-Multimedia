package es.muralla.ad.multimedia.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table( name = "usuario")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "usuario")
	private String username;
	private String contrasena;
	private String email;
	private String nombre;
	private String apellidos;
	
	@Column(name = "fecha_nac")
	private LocalDate fechaNac;
	
	public Usuario() {
		
	}
	
	public Usuario(String username, String contrasena, String email, String nombre, String apellidos, LocalDate fechaNac) {
		this.username = username;
		this.contrasena = contrasena;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
	}

	public Usuario(int id, String username, String contrasena, String email, String nombre, String apellidos, LocalDate fechaNac) {
		this.id = id;
		this.username = username;
		this.contrasena = contrasena;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
				+ "\nFecha de Nacimiento: %s", getUsername(), id, email, nombre, apellidos, fechaNac);
		return builder;
	}

	
	
	

}
