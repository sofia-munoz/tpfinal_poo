package ar.edu.unju.fi.poo.grupo04.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.grupo04.dto.PacienteDTO;
import ar.edu.unju.fi.poo.grupo04.entity.Paciente;
import ar.edu.unju.fi.poo.grupo04.repository.ObraSocialRepository;
import ar.edu.unju.fi.poo.grupo04.repository.PacienteRepository;
import ar.edu.unju.fi.poo.grupo04.service.IPacienteService;
import ar.edu.unju.fi.poo.grupo04.util.Util;

@Service("pacienteServiceImp")
public class PacienteServiceImp implements IPacienteService{
	static final Logger logger = Logger.getLogger(PacienteServiceImp.class);
	
	@Autowired
	ObraSocialRepository obraSocialRepository;
	
	@Autowired
	private PacienteDTO pacienteDTO = new PacienteDTO();

	@Autowired
	private Paciente paciente = new Paciente();
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	Util util;
	
	@Override
	public void agregarPaciente(PacienteDTO pacienteDTO) {
		paciente = util.pacienteDTOtoPaciente(pacienteDTO);
		pacienteRepository.save(paciente);
		logger.info("Se ha agregado un nuevo paciente: " + paciente);
	}

	@Override
	public PacienteDTO getPaciente() {
		return pacienteDTO;
	}

	@Override
	public PacienteDTO buscarPacientePorDni(Long dni) {
		paciente = pacienteRepository.findByDni(dni);//entidad
		if(paciente != null) {
			pacienteDTO = util.pacientetoPacienteDTO(paciente);
			return pacienteDTO;
		}
		return null;
	}

	@Override
	public List<PacienteDTO> listaPacientes() {
		List<PacienteDTO> listaDTO = new ArrayList<PacienteDTO>();
		List<Paciente> listaPacientes = (List<Paciente>)pacienteRepository.findAll();
		for (Paciente paciente : listaPacientes) {
		      listaDTO.add(util.pacientetoPacienteDTO(paciente));
		    }
		return listaDTO;
	}

	@Override
	public void eliminarPaciente(PacienteDTO pacienteDTO) {
		paciente = pacienteRepository.findByDni(pacienteDTO.getDni());
		pacienteRepository.delete(paciente);
		logger.info("Se ha eliminado un paciente: " + paciente);
	}
	
	@Override
	public List<PacienteDTO> buscarPacientePorParteDelNombre(String nombre) {
		List<PacienteDTO> listaDTO = new ArrayList<PacienteDTO>();
		List<Paciente> listaPacientes = (List<Paciente>)pacienteRepository.findByNombreContaining(nombre);
		for (Paciente paciente : listaPacientes) {
		      listaDTO.add(util.pacientetoPacienteDTO(paciente));
		    }
		return listaDTO;
	}

}

