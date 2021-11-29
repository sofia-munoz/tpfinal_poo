package ar.edu.unju.fi.poo.grupo04.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "turnos")
public class Turno {
	public static Integer DURACION_TURNO_EN_MINUTOS = 60;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTurno;
	
	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;
	
	@Column(name = "fecha_hora_inicio")
	private LocalDateTime fechaHoraInicio;
	
	@Column(name = "fecha_hora_fin")
	private LocalDateTime fechaHoraFin;
	
	public Turno() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param idTurno
	 * @param medico
	 * @param paciente
	 * @param fechaHoraInicio
	 * @param fechaHoraFin
	 */
	public Turno(Medico medico, Paciente paciente, LocalDateTime fechaHoraInicio) {
		super();
		this.medico = medico;
		this.paciente = paciente;
		this.fechaHoraInicio = fechaHoraInicio;
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

	/**
	 * @return the medico
	 */
	public Medico getMedico() {
		return medico;
	}

	/**
	 * @param medico the medico to set
	 */
	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	/**
	 * @return the paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
		return "Turno [idTurno=" + idTurno + ", medico=" + medico + ", paciente=" + paciente + ", fechaHoraInicio="
				+ fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + "]";
	}
	
	
}

