package ar.edu.unju.fi.poo.grupo04.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.poo.grupo04.dto.PacienteDTO;
import ar.edu.unju.fi.poo.grupo04.dto.TurnoDTO;
import ar.edu.unju.fi.poo.grupo04.service.IMedicoService;
import ar.edu.unju.fi.poo.grupo04.service.IPacienteService;
import ar.edu.unju.fi.poo.grupo04.service.ITurnoService;

@RestController
@RequestMapping("/api")
public class TurnoController {
	
	@Autowired
	ITurnoService turnoService;
	
	@Autowired
	IMedicoService medicoService;
	
	@Autowired
	IPacienteService pacienteService;
	
	@GetMapping("/v1/turnos")
	public ResponseEntity<?> listaTurnos(){
		Map<String, Object> response = new HashMap<>();
		List<TurnoDTO> listaTurnos = new ArrayList<TurnoDTO>();
		try {
			listaTurnos = turnoService.listaTurnos();
			response.put("Turnos: ", listaTurnos);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Ocurrió un error al intentar consultar la lista de turnos");
			response.put("Error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/v1/turno")
	public ResponseEntity<?> agregarTurno(@RequestBody TurnoDTO turnoDTO) throws Exception {
		Map<String, Object> response = new HashMap<>();
		try {
			pacienteService.buscarPacientePorDni(turnoDTO.getDniPaciente());
			medicoService.buscarPorMatricula(turnoDTO.getMatriculaMedico());
			if(turnoDTO.getFechaHoraInicio()!=null) {
				turnoService.agregarTurno(turnoDTO);
				response.put("Mensaje", "Se agregó el turno correctamente");
			}else {
				response.put("Mensaje", "No se puede agregar un turno sin fecha y hora de inicio");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			response.put("Mensaje", "Ocurrio un error al intentar agregar el turno");
			response.put("Error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@PatchMapping("/v1/turno/{dniPaciente}")
	public ResponseEntity<?> retrasarTurno(@PathVariable Long dniPaciente) throws Exception {
		Map<String, Object> response = new HashMap<>();
		TurnoDTO turno = new TurnoDTO();
		try {
			if(pacienteService.buscarPacientePorDni(dniPaciente)!=null) {
				turno = turnoService.buscarTurnoPorDniPaciente(dniPaciente);
				boolean resultado = turnoService.retrasarTurno(dniPaciente, 30);
				if (resultado) {
					response.put("Mensaje", "Se retrasó el turno correctamente");
					turno = turnoService.buscarTurnoPorDniPaciente(dniPaciente);
					response.put("Turno: ", turno);
				}else {
					response.put("Mensaje", "No se ha podido retrasar el turno");
					turnoService.agregarTurno(turno);
					return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CONFLICT);
				}
			}else {
				response.put("Mensaje", "No se encontró ningún turno asignado al dni " + dniPaciente);
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put("Mensaje", "Ocurrio un error al intentar agregar el turno");
			response.put("Error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/v1/turno/atencion/{dniPaciente}")
	public ResponseEntity<?> horaAtencionPorDni(@PathVariable Long dniPaciente) {
		Map<String, Object> response = new HashMap<>();
		PacienteDTO pacienteDTO = pacienteService.buscarPacientePorDni(dniPaciente);
		if(pacienteDTO != null) {
			LocalDateTime horario = turnoService.buscarHorarioAtencionPaciente(dniPaciente);
			response.put("Turno: ", DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(horario) + " hs.");
		}else {
			response.put("Mensaje", "No se encontró ningún turno asignado al dni " + dniPaciente);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/v1/turno/tiempo/{dniPaciente}")
	public ResponseEntity<?> tiempoParaTurno(@PathVariable Long dniPaciente) {
		Map<String, Object> response = new HashMap<>();
		PacienteDTO pacienteDTO = pacienteService.buscarPacientePorDni(dniPaciente);
		if(pacienteDTO != null) {
			String tiempo = turnoService.tiempoRestanteTurnoPaciente(dniPaciente);
			response.put("Tiempo restante: ", "Faltan " + tiempo + " para su turno");
		}else {
			response.put("Mensaje", "No se encontró ningún turno asignado al dni " + dniPaciente);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
}