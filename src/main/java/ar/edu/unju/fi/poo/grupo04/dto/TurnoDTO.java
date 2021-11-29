package ar.edu.unju.fi.poo.grupo04.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.poo.grupo04.entity.Turno;

@Component
public class TurnoDTO {
	private Long idTurno;
	private String nombrePaciente;
	private Long dniPaciente;
	private String nombreMedico;
	private Integer matriculaMedico;
	private LocalDateTime fechaHoraInicio;
	private LocalDateTime fechaHoraFin;
	
	public TurnoDTO() {
		super();
	}

	
	
	/**
	 * @param nombrePaciente
	 * @param dniPaciente
	 * @param nombreMedico
	 * @param matriculaMedico
	 * @param fechaHoraInicio
	 * @param fechaHoraFin
	 */
	public TurnoDTO(String nombrePaciente, Long dniPaciente, String nombreMedico, Integer matriculaMedico,
			LocalDateTime fechaHoraInicio) {
		super();
		this.nombrePaciente = nombrePaciente;
		this.dniPaciente = dniPaciente;
		this.nombreMedico = nombreMedico;
		this.matriculaMedico = matriculaMedico;
		this.fechaHoraInicio = fechaHoraInicio;
	}



	public TurnoDTO(Turno turno) {
		super();
		this.nombrePaciente = turno.getPaciente().getNombre();
		this.dniPaciente = turno.getPaciente().getDni();
		this.nombreMedico = turno.getMedico().getNombre();
		this.matriculaMedico = turno.getMedico().getMatricula();
	}

	
	/**
	 * @return the idTurno
	 */
	public Long getIdTurno() {
		return idTurno;
	}

	/**
	 * @param idTurno the idTurno to set
	 */
	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public Long getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(Long dniPaciente) {
		this.dniPaciente = dniPaciente;
	}

	public String getNombreMedico() {
		return nombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public Integer getMatriculaMedico() {
		return matriculaMedico;
	}

	public void setMatriculaMedico(Integer matriculaMedico) {
		this.matriculaMedico = matriculaMedico;
	}

	/**
	 * @return the fechaHoraInicio
	 */
	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}



	/**
	 * @param fechaHoraInicio the fechaHoraInicio to set
	 */
	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}



	/**
	 * @return the fechaHoraFin
	 */
	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}



	/**
	 * @param fechaHoraFin the fechaHoraFin to set
	 */
	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}



	@Override
	public String toString() {
		return "TurnoDTO [idTurno=" + idTurno + ", nombrePaciente=" + nombrePaciente + ", dniPaciente=" + dniPaciente
				+ ", nombreMedico=" + nombreMedico + ", matriculaMedico=" + matriculaMedico + ", fechaHoraInicio="
				+ fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + "]";
	}
	
	
	
}
