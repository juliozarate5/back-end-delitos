package co.edu.iudigital.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import co.edu.iudigital.app.model.Role;

public class UsuarioDto {
	
	private Long id;
	
	@NotEmpty(message = "Email requerido")
	private String username;
	
	@NotEmpty(message = "Password requerido")
	private String password;
	
	private LocalDate fechaNacimiento;
	
	@NotEmpty(message = "Nombre requerido")
	private String nombre;
	
	private String image;
	
	@NotEmpty(message = "tipo usuario requerido")
	private Role rol;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the rol
	 */
	public Role getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(Role rol) {
		this.rol = rol;
	}

	/**
	 * @return the tipoUsuario
	 */
	public Role getTipoUsuario() {
		return rol;
	}

	/**
	 * @param tipoUsuario the tipoUsuario to set
	 */
	public void setTipoUsuario(Role rol) {
		this.rol = rol;
	}
	
}
