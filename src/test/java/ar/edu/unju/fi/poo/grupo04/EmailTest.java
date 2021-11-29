package ar.edu.unju.fi.poo.grupo04;

import java.time.LocalDateTime;
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

import ar.edu.unju.fi.poo.grupo04.dto.MedicoDTO;
import ar.edu.unju.fi.poo.grupo04.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.grupo04.dto.PacienteDTO;
import ar.edu.unju.fi.poo.grupo04.dto.TurnoDTO;
import ar.edu.unju.fi.poo.grupo04.service.IJavaMailService;
import ar.edu.unju.fi.poo.grupo04.service.IMedicoService;
import ar.edu.unju.fi.poo.grupo04.service.IObraSocialService;
import ar.edu.unju.fi.poo.grupo04.service.IPacienteService;
import ar.edu.unju.fi.poo.grupo04.util.Util;

@SpringBootTest
class EmailTest {
	static final Logger logger = Logger.getLogger(EmailTest.class);
	
	@Autowired
	TurnoDTO turnoDTO1;
	
	@Autowired
	PacienteDTO pacienteDTO1;
	
	@Autowired
	MedicoDTO medicoDTO1;
	
	@Autowired
	ObraSocialDTO obraSocialDTO1;
	
	@Autowired
	IJavaMailService javaMailService;
	
	@Autowired
	IObraSocialService obraSocialService;
	
	@Autowired
	IPacienteService pacienteService;
	
	@Autowired
	IMedicoService medicoService;
	
	@Autowired
	Util util;
	
	private List<ObraSocialDTO> obraSocialDTOList1 = new ArrayList<ObraSocialDTO>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		obraSocialDTO1 = (util.fromObraSocialToObraSocialDTO(obraSocialService.buscarObraSocialPorId(1L)));
		obraSocialDTOList1.add(obraSocialDTO1);
		
		pacienteDTO1 = new PacienteDTO("Pedro Alfonzo", "38212366@fi.unju.edu.ar", 27352634L, "Av. Libertador 123", 154723461L, 1L);
		medicoDTO1 = new MedicoDTO("Juan Perez", "sofim.2803.1@gmail.com", 1234, "Mañana",obraSocialDTOList1);
				
		LocalDateTime fechaHoraInicio = LocalDateTime.of(2021, 10, 31, 9, 00);
		turnoDTO1 = new TurnoDTO(pacienteDTO1.getNombre(), pacienteDTO1.getDni(), medicoDTO1.getNombre(),medicoDTO1.getMatricula(), fechaHoraInicio);
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	@DisplayName("Enviar Email")
	void enviarEmailTest() throws Exception {
		logger.info("Ejecutando enviarEmailTest()");
		String asunto = "Registro de turno";
		String mensaje = util.getMensageTurno(turnoDTO1);
		//Envia el mail
		javaMailService.enviarMail(pacienteDTO1.getEmail(), asunto, mensaje);
		javaMailService.enviarMail(medicoDTO1.getEmail(), asunto, mensaje);
	}

}
