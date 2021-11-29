package ar.edu.unju.fi.poo.grupo04.service;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unju.fi.poo.grupo04.dto.TurnoDTO;

public interface ITurnoService {
	public void agregarTurno(TurnoDTO turnoDTO) throws Exception;
	
	public TurnoDTO  getTurno();
	
	public void eliminarTurno(TurnoDTO turnoDTO);
	
	public List<TurnoDTO> listaTurnos();
	
	public boolean retrasarTurno(Long dni, Integer cantidadMinutos);
	
	public LocalDateTime buscarHorarioAtencionPaciente(Long dniPaciente);
	
	public TurnoDTO buscarTurnoPorDniPaciente(Long dniPaciente);
     
    public String tiempoRestanteTurnoPaciente(Long dni);
}
