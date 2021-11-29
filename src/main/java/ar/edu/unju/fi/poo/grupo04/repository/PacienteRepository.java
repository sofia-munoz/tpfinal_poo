package ar.edu.unju.fi.poo.grupo04.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.grupo04.entity.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Long>{
	/**
	 * Buscar por dni del paciente
	 * @param dni
	 * @return Paciente
	 */
	public Paciente findByDni(Long dni);
	List<Paciente> findByNombreContaining(String nombre);
}
