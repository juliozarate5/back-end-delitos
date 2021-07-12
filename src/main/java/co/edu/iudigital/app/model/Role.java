package co.edu.iudigital.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity // convierte en una entidad de Hibernate
@Table(name = "roles")// se coloca el nombre de la tabla en la BD
public class Role implements Serializable{
	
	@Id// llave primaria en la tabla
	@GeneratedValue(strategy = GenerationType.IDENTITY)//llave autoincrement
	private Long id;

	private String nombre;

	private String descripcion;
	
	//bidireccional (opcional)
	@ManyToMany(mappedBy="roles")//nombre atributo en la tabla relacionada
	private List<Usuario> usuarios;

	/*
	public TipoUsuario(){
	  		usuarios = new ArrayList<>();
	 * }
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	/**
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	/**
	 * serialVersionUID, que se utiliza durante la 
	 * deserialización para verificar que el remitente 
	 * y el receptor de un objeto serializado hayan cargado 
	 * clases para ese objeto que sean compatibles con 
	 * respecto a la serialización. Si el receptor ha 
	 * cargado una clase para el objeto que tiene un 
	 * serialVersionUID diferente al de la clase del 
	 * remitente correspondiente, la deserialización 
	 * dará como resultado una InvalidClassException. 
	 * Una clase serializable puede declarar su propio 
	 * serialVersionUID explícitamente declarando un 
	 * campo llamado serialVersionUID que debe ser estático, 
	 * final y de tipo long
	 * Si una clase serializable no declara explícitamente 
	 * un serialVersionUID, entonces el tiempo de ejecución 
	 * de serialización calculará un valor serialVersionUID 
	 * predeterminado para esa clase en base a varios aspectos 
	 * de la clase, como se describe en la Especificación de 
	 * serialización de objetos Java (TM). Sin embargo, se 
	 * recomienda encarecidamente que todas las clases 
	 * serializables declaren explícitamente los valores 
	 * serialVersionUID, ya que el cálculo predeterminado de 
	 * serialVersionUID es muy sensible a los detalles de la 
	 * clase que pueden variar según las implementaciones del 
	 * compilador y, por lo tanto, pueden generar excepciones 
	 * inesperadas de InvalidClassExceptions durante la 
	 * deserialización. Por lo tanto, para garantizar un valor 
	 * serialVersionUID consistente en diferentes 
	 * implementaciones del compilador java, una clase 
	 * serializable debe declarar un valor serialVersionUID 
	 * explícito. También se recomienda encarecidamente que 
	 * las declaraciones explícitas de serialVersionUID 
	 * utilicen el modificador privado siempre que sea posible, 
	 * ya que tales declaraciones se aplican solo a la clase de 
	 * declaración inmediata: los campos serialVersionUID no son 
	 * útiles como miembros heredados.
	 */
	private static final long serialVersionUID = 1L;
}
