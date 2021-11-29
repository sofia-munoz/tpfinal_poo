package ar.edu.unju.fi.poo.grupo04.dto;

import org.springframework.stereotype.Component;

@Component
public class PacienteDTO{

	private Long id;
	private String nombre;
	private String email;
	private Long dni;
	private String domicilio;
	private Long numeroCelular;
	private Long idObraSocial;
	private String nombreObraSocial;
	
	public PacienteDTO() {
		super();
	}
	
	
	public PacienteDTO(String nombre, String email, Long dni, String domicilio, Long numeroCelular,
			Long idObraSocial) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.dni = dni;
		this.domicilio = domicilio;
		this.numeroCelular = numeroCelular;
		this.idObraSocial = idObraSocial;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the dni
	 */
	public Long getDni() {
		return dni;
	}


	/**
	 * @param dni the dni to set
	 */
	public void setDni(Long dni) {
		this.dni = dni;
	}


	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}


	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	/**
	 * @return the numeroCelular
	 */
	public Long getNumeroCelular() {
		return numeroCelular;
	}


	/**
	 * @param numeroCelular the numeroCelular to set
	 */
	public void setNumeroCelular(Long numeroCelular) {
		this.numeroCelular = numeroCelular;
	}


	/**
	 * @return the idObraSocial
	 */
	public Long getIdObraSocial() {
		return idObraSocial;
	}


	/**
	 * @param idObraSocial the idObraSocial to set
	 */
	public void setIdObraSocial(Long idObraSocial) {
		this.idObraSocial = idObraSocial;
	}


	/**
	 * @return the nombreObraSocial
	 */
	public String getNombreObraSocial() {
		return nombreObraSocial;
	}


	/**
	 * @param nombreObraSocial the nombreObraSocial to set
	 */
	public void setNombreObraSocial(String nombreObraSocial) {
		this.nombreObraSocial = nombreObraSocial;
	}


	@Override
	public String toString() {
		return "PacienteDTO [id=" + id + ", nombre=" + nombre + ", email=" + email + ", dni=" + dni + ", domicilio="
				+ domicilio + ", numeroCelular=" + numeroCelular + ", idObraSocial=" + idObraSocial
				+ ", nombreObraSocial=" + nombreObraSocial + "]";
	}

	
	
}
