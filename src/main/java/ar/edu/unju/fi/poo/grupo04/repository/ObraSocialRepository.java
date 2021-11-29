package ar.edu.unju.fi.poo.grupo04.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.poo.grupo04.entity.ObraSocial;

@Repository
public interface ObraSocialRepository extends CrudRepository<ObraSocial, Long> {

	ObraSocial findByNombre(String nombre);
	Optional<ObraSocial> findById(Long id);
}
