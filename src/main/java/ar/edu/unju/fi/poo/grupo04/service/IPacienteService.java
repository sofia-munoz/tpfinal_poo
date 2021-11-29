package ar.edu.unju.fi.poo.grupo04.service;

import java.util.List;

import ar.edu.unju.fi.poo.grupo04.dto.PacienteDTO;

public interface IPacienteService {
	
public void agregarPaciente(PacienteDTO pacienteDTO);
	
	public PacienteDTO getPaciente();
	
	public PacienteDTO buscarPacientePorDni(Long dni);
	
	public List<PacienteDTO> listaPacientes();
	
	public void eliminarPaciente(PacienteDTO pacienteDTO);
	
	public List<PacienteDTO> buscarPacientePorParteDelNombre(String nombre);
}
