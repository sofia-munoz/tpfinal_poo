package ar.edu.unju.fi.poo.grupo04.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.grupo04.dto.MedicoDTO;
import ar.edu.unju.fi.poo.grupo04.entity.Medico;
import ar.edu.unju.fi.poo.grupo04.repository.MedicoRepository;
import ar.edu.unju.fi.poo.grupo04.repository.ObraSocialRepository;
import ar.edu.unju.fi.poo.grupo04.service.IMedicoService;
import ar.edu.unju.fi.poo.grupo04.util.Util;

@Service("medicoServiceImp")
public class MedicoServiceImp implements IMedicoService{
	static final Logger logger = Logger.getLogger(MedicoServiceImp.class);
	
	@Autowired
	private MedicoDTO medicoDTO = new MedicoDTO();

	@Autowired
	private Medico medico = new Medico();
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	ObraSocialRepository obraSocialRepository;
	
	@Autowired
	Util util;
	
	@Override
	public void agregarMedico(MedicoDTO medicoDTO) {
		medico = util.medicoDTOToMedico(medicoDTO);
		medicoRepository.save(medico);
		logger.info("Se ha agregado un nuevo médico: " + medico);
	}

	@Override
	public MedicoDTO getMedico() {
		return medicoDTO;
	}

	@Override
	public void eliminarMedico(MedicoDTO medicoDTO) {
		medico = medicoRepository.findByMatricula(medicoDTO.getMatricula());
		medicoRepository.delete(medico);
		logger.info("Se ha eliminado un médico: " + medico);
	}

	@Override
	public List<MedicoDTO> listaMedicos() {
		List<MedicoDTO> listaDTO = new ArrayList<MedicoDTO>();
		List<Medico> listaMedicos = (List<Medico>)medicoRepository.findAll();
		for (Medico medico : listaMedicos) {
		      listaDTO.add(util.medicoToMedicoDTO(medico));
		    }
		return listaDTO;
	}

	@Override
	public List<MedicoDTO> buscarPorMatriculaONombre(Integer matricula, String nombre) {
		List<MedicoDTO> listaDTO = new ArrayList<MedicoDTO>();
		List<Medico> listaMedicos = (List<Medico>)medicoRepository.findByMatriculaOrNombre(matricula, nombre);
		for (Medico medico : listaMedicos) {
		      listaDTO.add(util.medicoToMedicoDTO(medico));
		    }
		return listaDTO;
	}

	@Override
	public MedicoDTO buscarPorMatricula(Integer matricula) {
		medico = medicoRepository.findByMatricula(matricula);//entidad
		if(medico != null) {
			medicoDTO = util.medicoToMedicoDTO(medico);
			return medicoDTO;
		}
		return null;
	}

}
