package co.edu.iudigital.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "usuarios")
// serializable como buena práctica para serializar de java a estructura json
// y permite almacenar el objeto en una sesión http
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Email is mandatory")
	@Email(message = "Debe ser un email válido")//TODO: validar
	@Column(unique = true, length = 120)
	private String username;
	
	@NotEmpty(message = "Nombre obligatorio")
	private String nombre;
	
	@NotEmpty(message = "Apellido obligatorio")
	private String apellido;
	
	@NotEmpty(message = "Password obligatorio")
	private String password;
	
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	private Boolean enabled;
	
	@Column(name = "red_social")
	private Boolean redSocial;
	
	private String image;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable (name = "roles_usuarios", 
				joinColumns = {@JoinColumn(name = "usuarios_id")},
				inverseJoinColumns = {@JoinColumn(name = "roles_id")})
	private List<Role> roles;
	
	/*TODO: relacion OneToMany delitos
	 * relacion OneToMany casos
	 * */
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
	 * @return the email
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param email the email to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}


	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	/**
	 * @return the contrasena
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param contrasena the contrasena to set
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
	 * @return the estado
	 */
	public Boolean getEnabled() {
		return enabled;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	/**
	 * @return the redSocial
	 */
	public Boolean getRedSocial() {
		return redSocial;
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
	 * @param redSocial the redSocial to set
	 */
	public void setRedSocial(Boolean redSocial) {
		this.redSocial = redSocial;
	}


	/**
	 * @return the tipoUsuario
	 */
	public List<Role> getRoles() {
		return roles;
	}


	/**
	 * @param tipoUsuario the tipoUsuario to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
