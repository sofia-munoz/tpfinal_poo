package ar.edu.unju.fi.poo.grupo04.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unju.fi.poo.grupo04.dto.PacienteDTO;
import ar.edu.unju.fi.poo.grupo04.service.IObraSocialService;
import ar.edu.unju.fi.poo.grupo04.service.IPacienteService;

@RestController
@RequestMapping("/api")
public class PacienteController {

	@Autowired
	IPacienteService pacienteService;
	
	@Autowired
	IObraSocialService obraSocialService;
	
	
	@GetMapping("/v1/pacientes")
	public ResponseEntity<?> listaPacientes(){
		Map<String, Object> response = new HashMap<>();
		List<PacienteDTO> listaPacientes = new ArrayList<PacienteDTO>();
		try {
			listaPacientes = pacienteService.listaPacientes();
			response.put("Pacientes: ", listaPacientes);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Ocurrió un error al intentar consultar la lista de pacientes");
			response.put("Error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	} 
	
	
	@PostMapping("/v1/paciente")
	public ResponseEntity<?> agregarPaciente(@RequestBody PacienteDTO pacienteDTO) {
		Map<String, Object> response = new HashMap<>();
		try {
			obraSocialService.buscarObraSocialPorId(pacienteDTO.getIdObraSocial());
			if(pacienteDTO.getDni()!=null) {
				pacienteService.agregarPaciente(pacienteDTO);
				response.put("Mensaje", "Se agregó el paciente correctamente");
			}else {
				response.put("Mensaje", "No se puede agregar un paciente sin dni");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			response.put("Mensaje", "Ocurrio un error al intentar agregar al paciente ");
			response.put("Error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/v1/paciente/{dni}")
	public ResponseEntity<?> buscarPaciente(@PathVariable Long dni) {
		Map<String, Object> response = new HashMap<>();
		
		PacienteDTO pacienteDTO = pacienteService.buscarPacientePorDni(dni);
		if(pacienteDTO != null) {
			response.put("Mensaje", pacienteDTO);
		}else {
			response.put("Mensaje", "No se encontró al paciente con el dni " + dni);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
				
	}
	
	@PutMapping("/v1/pacientess")
	public ResponseEntity<?> modificarPaciente(@RequestBody PacienteDTO pacienteDTO) {
		Map<String, Object> response = new HashMap<>();	
		try {
			PacienteDTO paciente = pacienteService.buscarPacientePorDni(pacienteDTO.getDni());
			if(paciente != null) {
				pacienteDTO.setId(paciente.getId());
				pacienteService.agregarPaciente(pacienteDTO);
				response.put("Mensaje", "Se actualizó con éxito el paciente con el dni " + pacienteDTO.getDni());
				response.put("Paciente: ", pacienteService.buscarPacientePorDni(pacienteDTO.getDni()));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
			}else {
				if(pacienteDTO.getDni()==null) {
					response.put("Mensaje", "Debe enviar un numero de dni válido");
					return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
				}else {
					response.put("Mensaje", "No se encontró ningun paciente con el dni " + pacienteDTO.getDni());
					return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
				}
			}
		} catch (DataAccessException e) {
			response.put("Mensaje", "Ocurrio un error al intentar actualizar el paciente");
			response.put("Error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PatchMapping("/v1/paciente/{dni}")
	public ResponseEntity<?> modificarAtributoPaciente(@RequestBody PacienteDTO paciente, @PathVariable Long dni) {
		Map<String, Object> response = new HashMap<>();
		try {
			PacienteDTO pacienteDTO = pacienteService.buscarPacientePorDni(dni);
			if(pacienteDTO != null) {
				if(paciente.getNombre()!=null) {
					pacienteDTO.setNombre(paciente.getNombre());
				}
				if(paciente.getEmail()!=null) {
					pacienteDTO.setEmail(paciente.getEmail());
				}
				if(paciente.getDomicilio()!=null) {
					pacienteDTO.setDomicilio(paciente.getDomicilio());
				}
				if(paciente.getNumeroCelular()!=null) {
					pacienteDTO.setNumeroCelular(paciente.getNumeroCelular());
				}
				if(paciente.getIdObraSocial()!=null) {
					pacienteDTO.setIdObraSocial(paciente.getIdObraSocial());
				}
				pacienteService.agregarPaciente(pacienteDTO);
				response.put("Mensaje", "Se actualizó el paciente con el dni " + pacienteDTO.getDni());
				response.put("Paciente: ", pacienteDTO);
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
			}else {
				response.put("Mensaje", "No se encontró ningun paciente con el dni " + dni);
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			response.put("Mensaje", "Ocurrio un error al intentar actualizar el paciente");
			response.put("Error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
