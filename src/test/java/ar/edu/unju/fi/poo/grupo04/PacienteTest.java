package ar.edu.unju.fi.poo.grupo04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.edu.unju.fi.poo.grupo04.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.grupo04.dto.PacienteDTO;
import ar.edu.unju.fi.poo.grupo04.service.IObraSocialService;
import ar.edu.unju.fi.poo.grupo04.service.IPacienteService;
import ar.edu.unju.fi.poo.grupo04.util.Util;

@SpringBootTest
class PacienteTest {
	static final Logger logger = Logger.getLogger(PacienteTest.class);
	
	@Autowired
	ObraSocialDTO obraSocialDTO;
	
	@Autowired
	PacienteDTO pacienteDTO;
	
	@Autowired
	IPacienteService pacienteService;
	
	@Autowired
	IObraSocialService obraSocialService;
	
	@Autowired
	ObraSocialDTO obraSocialDTO1, obraSocialDTO2, obraSocialDTO3;

	@Autowired
	PacienteDTO pacienteDTO1, pacienteDTO2, pacienteDTO3, pacienteDTO4, pacienteDTO5;
	
	@Autowired
	Util util;

	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		obraSocialDTO1 = util.fromObraSocialToObraSocialDTO(obraSocialService.buscarObraSocialPorId(1L));
		obraSocialDTO2 = util.fromObraSocialToObraSocialDTO(obraSocialService.buscarObraSocialPorId(2L));
		obraSocialDTO3 = util.fromObraSocialToObraSocialDTO(obraSocialService.buscarObraSocialPorId(3L));
		
		pacienteDTO1 = new PacienteDTO("Pedro Alfonzo", "palfonzo@hotmail.com" , 27352634L, "Av. Libertador 123", 154723461L, 1L);
		pacienteDTO2 = new PacienteDTO("Alfonsina Guevara", "alfon@gmail.com", 36212743L, "Calle Falsa 123", 155675342L, 2L);
		pacienteDTO3 = new PacienteDTO("Isis Belizan", "ibel@yahoo.com", 33263745L, "Estados Unidos 53", 154339285L, 3L);
		pacienteDTO4 = new PacienteDTO("Agustina Aramayo", "agustinaara@gmail.com", 26371125L, "Rio de la Plata 1040", 155362788L, 1L);
		pacienteDTO5 = new PacienteDTO("Micaela Soriano", "msoriano@yahoo.com", 40284643L, "El Ceibal 423", 1557329845L, 1L);

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Agregar Paciente")
	//@Disabled
	void agregarPacienteTest() {
		logger.info("Ejecutando agregarPacienteTest()");
		// agrega 5 pacientes a la base de datos
		
		pacienteService.agregarPaciente(pacienteDTO1);
		pacienteService.agregarPaciente(pacienteDTO2);
		pacienteService.agregarPaciente(pacienteDTO3);
		pacienteService.agregarPaciente(pacienteDTO4);
		pacienteService.agregarPaciente(pacienteDTO5);
		
		assertTrue(pacienteService.listaPacientes().size() == 5);
		
		pacienteService.eliminarPaciente(pacienteDTO1);
		pacienteService.eliminarPaciente(pacienteDTO2);
		pacienteService.eliminarPaciente(pacienteDTO3);
		pacienteService.eliminarPaciente(pacienteDTO4);
		pacienteService.eliminarPaciente(pacienteDTO5);
		
	}
	
	@Test
	@DisplayName("Modificar Paciente")
	//@Disabled
	void modificarPacienteTest() {
		logger.info("Ejecutando modificarPacienteTest()");
		// Guardo un paciente DNI: 27352634
		pacienteService.agregarPaciente(pacienteDTO1);
		// Busco el paciente por el dni, modifico el dni y lo guardo
		pacienteDTO = pacienteService.buscarPacientePorDni(27352634L);
		pacienteDTO.setDni(38623754L);
		pacienteService.agregarPaciente(pacienteDTO);
		//Busco el paciente por el nuevo y viejo dni
		assertNotNull(pacienteService.buscarPacientePorDni(38623754L));
		assertNull(pacienteService.buscarPacientePorDni(27352634L));
		//Borro el paciente
		pacienteService.eliminarPaciente(pacienteDTO);
	}
	
	@Test
	@DisplayName("Buscar Paciente Por Nombre")
	//@Disabled
	void buscarPacientePorNombre() {
		logger.info("Ejecutando buscarPacientePorNombre()");
		List<PacienteDTO> listaPaciente = new ArrayList<PacienteDTO>();
		
		pacienteService.agregarPaciente(pacienteDTO1);
		pacienteService.agregarPaciente(pacienteDTO2);
		pacienteService.agregarPaciente(pacienteDTO3);
		pacienteService.agregarPaciente(pacienteDTO4);
		pacienteService.agregarPaciente(pacienteDTO5);
		
		listaPaciente = pacienteService.buscarPacientePorParteDelNombre("Alfon");
		assertTrue(listaPaciente.size()==2);
		
		pacienteService.eliminarPaciente(pacienteDTO1);
		pacienteService.eliminarPaciente(pacienteDTO2);
		pacienteService.eliminarPaciente(pacienteDTO3);
		pacienteService.eliminarPaciente(pacienteDTO4);
		pacienteService.eliminarPaciente(pacienteDTO5);
	}
}
