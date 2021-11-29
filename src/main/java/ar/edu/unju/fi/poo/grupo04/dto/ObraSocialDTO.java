package ar.edu.unju.fi.poo.grupo04.dto;

import org.springframework.stereotype.Component;

@Component
public class ObraSocialDTO {
	private Long idObraSocial;
	private String nombre;

	public ObraSocialDTO() {
		super();
	}

	public ObraSocialDTO(Long idObraSocial, String nombre) {
		super();
		this.idObraSocial = idObraSocial;
		this.nombre = nombre;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "ObraSocialDTO [nombre=" + nombre + "]";
	}
	
}
