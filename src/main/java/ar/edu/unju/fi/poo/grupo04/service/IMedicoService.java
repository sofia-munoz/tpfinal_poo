package ar.edu.unju.fi.poo.grupo04.service;

import java.util.List;

import ar.edu.unju.fi.poo.grupo04.dto.MedicoDTO;

public interface IMedicoService {
	
	public void agregarMedico(MedicoDTO medicoDTO);
	
	public MedicoDTO getMedico();
	
	public void eliminarMedico(MedicoDTO medicoDTO);
	
	public List<MedicoDTO> listaMedicos();
	
	public List<MedicoDTO> buscarPorMatriculaONombre(Integer matricula, String nombre);
	
	public MedicoDTO buscarPorMatricula(Integer matricula);
	
}
