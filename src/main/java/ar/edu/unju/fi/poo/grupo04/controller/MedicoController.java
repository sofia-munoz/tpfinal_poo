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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unju.fi.poo.grupo04.dto.MedicoDTO;
import ar.edu.unju.fi.poo.grupo04.service.IMedicoService;
import ar.edu.unju.fi.poo.grupo04.service.IObraSocialService;

@RestController
@RequestMapping("/api")
public class MedicoController {
	@Autowired
	IMedicoService medicoService;
	
	@Autowired
	IObraSocialService obraSocialService;
	
	@GetMapping("/v1/medicos")
	public ResponseEntity<?> listaMedicos(){
		Map<String, Object> response = new HashMap<>();
		List<MedicoDTO> listaMedicos = new ArrayList<MedicoDTO>();
		try {
			listaMedicos = medicoService.listaMedicos();
			response.put("Medicos: ", listaMedicos);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Ocurrió un error al intentar consultar la lista de médicos");
			response.put("Error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/v1/medico")
	public ResponseEntity<?> agregarMedico(@RequestBody MedicoDTO medicoDTO) {
		Map<String, Object> response = new HashMap<>();
		try {
			if(medicoDTO.getMatricula()!=null) {
				medicoService.agregarMedico(medicoDTO);
				response.put("Mensaje", "Se agregó el médico correctamente");
			}else {
				response.put("Mensaje", "No se puede agregar un médico sin matrícula");
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			response.put("Mensaje", "Ocurrio un error al intentar agregar al médico ");
			response.put("Error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/v1/medico/{matricula}")
	public ResponseEntity<?> buscarMedico(@PathVariable Integer matricula) {
		Map<String, Object> response = new HashMap<>();
		MedicoDTO medicoDTO = medicoService.buscarPorMatricula(matricula);
		if(medicoDTO != null) {
			response.put("Mensaje", medicoDTO);
		}else {
			response.put("Mensaje", "No se encontró al médico con matrícula " + matricula);
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@PutMapping("/v1/medicoss")
	public ResponseEntity<?> modificarMedico(@RequestBody MedicoDTO medicoDTO) {
		Map<String, Object> response = new HashMap<>();	
		try {
			MedicoDTO medico = medicoService.buscarPorMatricula(medicoDTO.getMatricula());
			if(medico != null) {
				medicoDTO.setId(medico.getId());
				medicoService.agregarMedico(medicoDTO);
				response.put("Mensaje", "Se actualizó con éxito el médico con matrícula " + medicoDTO.getMatricula());
				response.put("Medico: ", medicoService.buscarPorMatricula(medicoDTO.getMatricula()));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
			}else {
				if(medicoDTO.getMatricula()==null) {
					response.put("Mensaje", "Debe enviar un numero de matrícula válido");
					return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
				}else {
					response.put("Mensaje", "No se encontró ningun médico con matricula " + medicoDTO.getMatricula());
					return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
				}
			}
		} catch (DataAccessException e) {
			response.put("Mensaje", "Ocurrio un error al intentar actualizar el médico");
			response.put("Error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
