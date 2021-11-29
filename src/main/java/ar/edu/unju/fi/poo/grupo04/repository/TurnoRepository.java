package ar.edu.unju.fi.poo.grupo04.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.grupo04.entity.Medico;
import ar.edu.unju.fi.poo.grupo04.entity.Paciente;
import ar.edu.unju.fi.poo.grupo04.entity.Turno;


@Repository
public interface TurnoRepository extends CrudRepository<Turno,Long> {
	List<Turno> findByMedico(Medico medico);
	List<Turno>findByMedicoAndFechaHoraInicioBetween(Medico medico, LocalDateTime fechaHoraInicio,LocalDateTime fechaHoraFin);
	List<Turno>findByMedicoAndFechaHoraFinBetween(Medico medico, LocalDateTime fechaHoraInicio,LocalDateTime fechaHoraFin);
	Turno findByPacienteAndFechaHoraInicio(Paciente paciente, LocalDateTime fechaHoraInicio);
	Turno findByPaciente(Paciente paciente);
}
