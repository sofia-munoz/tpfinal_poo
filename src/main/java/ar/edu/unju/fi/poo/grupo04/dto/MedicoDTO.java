package ar.edu.unju.fi.poo.grupo04.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MedicoDTO {
	
	private Long id;
	private String nombre;
	private String email;
	private Integer matricula;
	private String turnoLaboral;
	private List<ObraSocialDTO> listaObrasSociales;
	
	public MedicoDTO() {
		super();
	}

	public MedicoDTO(String nombre, String email, Integer matricula, String turnoLaboral,
			List<ObraSocialDTO> listaObrasSocialesDTO) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.matricula = matricula;
		this.turnoLaboral = turnoLaboral;
		this.listaObrasSociales = listaObrasSocialesDTO;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getTurnoLaboral() {
		return turnoLaboral;
	}

	public void setTurnoLaboral(String turnoLaboral) {
		this.turnoLaboral = turnoLaboral;
	}

	public List<ObraSocialDTO> getListaObrasSociales() {
		return listaObrasSociales;
	}

	public void setListaObrasSociales(List<ObraSocialDTO> listaObrasSociales) {
		this.listaObrasSociales = listaObrasSociales;
	}

	@Override
	public String toString() {
		return "MedicoDTO [nombre=" + nombre + ", email=" + email + ", matricula=" + matricula + ", turnoLaboral="
				+ turnoLaboral + ", listaObrasSociales=" + listaObrasSociales + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
