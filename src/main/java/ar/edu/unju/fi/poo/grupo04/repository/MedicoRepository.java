package ar.edu.unju.fi.poo.grupo04.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.grupo04.entity.Medico;

@Repository
public interface MedicoRepository extends CrudRepository<Medico, Long>{
	List<Medico> findByMatriculaOrNombre(Integer matricula, String nombre);
	Medico findByMatricula(Integer matricula);
}
