package co.edu.iudigital.app.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "casos")
public class Caso implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha_hora")
	private LocalDateTime fechaHora;
	
	private Float latitud;
	
	private Float longitud;
	
	private Float altitud;
	
	private Boolean visible;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "usuarios_id")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name = "delitos_id")
	private Delito delito;

	@PrePersist
	public void prePersist() {
		fechaHora = LocalDateTime.now();
	}
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
	 * @return the fechaHora
	 */
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return the latitud
	 */
	public Float getLatitud() {
		return latitud;
	}

	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	/**
	 * @return the longitud
	 */
	public Float getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	/**
	 * @return the altitud
	 */
	public Float getAltitud() {
		return altitud;
	}

	/**
	 * @param altitud the altitud to set
	 */
	public void setAltitud(Float altitud) {
		this.altitud = altitud;
	}

	/**
	 * @return the visible
	 */
	public Boolean getVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
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
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the delito
	 */
	public Delito getDelito() {
		return delito;
	}

	/**
	 * @param delito the delito to set
	 */
	public void setDelito(Delito delito) {
		this.delito = delito;
	}
		
	private static final long serialVersionUID = 1L;
}
