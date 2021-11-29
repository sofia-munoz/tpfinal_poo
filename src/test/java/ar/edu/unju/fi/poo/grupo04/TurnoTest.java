package ar.edu.unju.fi.poo.grupo04;

import static org.junit.jupiter.api.Assertions.*;

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
import ar.edu.unju.fi.poo.grupo04.service.IMedicoService;
import ar.edu.unju.fi.poo.grupo04.service.IObraSocialService;
import ar.edu.unju.fi.poo.grupo04.service.IPacienteService;
import ar.edu.unju.fi.poo.grupo04.service.ITurnoService;
import ar.edu.unju.fi.poo.grupo04.util.Util;

@SpringBootTest
class TurnoTest {
	static final Logger logger = Logger.getLogger(TurnoTest.class);
	
	@Autowired
	ObraSocialDTO obraSocialDTO;
	
	@Autowired
	PacienteDTO pacienteDTO;
	
	@Autowired
	MedicoDTO medicoDTO;
	
	@Autowired
	TurnoDTO turnoDTO;
	
	@Autowired
	ITurnoService turnoService;
	
	@Autowired
	IPacienteService pacienteService;
	
	@Autowired
	IMedicoService medicoService;
	
	@Autowired
	IObraSocialService obraSocialService;
	
	@Autowired
	TurnoDTO turnoDTO1, turnoDTO2, turnoDTO3, turnoDTO4;
	
	@Autowired
	PacienteDTO pacienteDTO1, pacienteDTO2, pacienteDTO3, pacienteDTO4, pacienteDTO5;
	
	@Autowired
	ObraSocialDTO obraSocialDTO1, obraSocialDTO2, obraSocialDTO3;
	
	@Autowired
	MedicoDTO medicoDTO1, medicoDTO2, medicoDTO3, medicoDTO4, medicoDTO5;
	
	@Autowired
	Util util;
	
	private List<ObraSocialDTO> obraSocialDTOList1 = new ArrayList<ObraSocialDTO>();
	private List<ObraSocialDTO> obraSocialDTOList2 = new ArrayList<ObraSocialDTO>();

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
		
		obraSocialDTOList1.add(obraSocialDTO1);
		obraSocialDTOList2.add(obraSocialDTO1);
		obraSocialDTOList2.add(obraSocialDTO2);

		pacienteDTO1 = new PacienteDTO("Pedro Alfonso", "cesarmercadook@gmail.com" , 27352634L, "Av. Libertador 123", 154723461L, 1L);
		pacienteDTO2 = new PacienteDTO("Alfonsina Guevara", "cesarmercadook@gmail.com", 36212743L, "Calle Falsa 123", 155675342L, 2L);
		pacienteDTO3 = new PacienteDTO("Isis Belizan", "cesarmercadook@gmail.com", 33263745L, "Estados Unidos 53", 154339285L, 3L);

		medicoDTO1 = new MedicoDTO("Juan Perez", "cesarmercadook@gmail.com", 1234, "Mañana",obraSocialDTOList1);
		medicoDTO2 = new MedicoDTO("Belén Rodrigez", "cesarmercadook@gmail.com", 2563, "Tarde",obraSocialDTOList2);


		LocalDateTime fechaHoraInicio = LocalDateTime.of(2021, 12, 10, 9, 00);
		turnoDTO1 = new TurnoDTO(pacienteDTO1.getNombre(), pacienteDTO1.getDni(), medicoDTO1.getNombre(),medicoDTO1.getMatricula(), fechaHoraInicio);
		
		fechaHoraInicio = LocalDateTime.of(2021, 12, 10, 9, 00);
		turnoDTO2 = new TurnoDTO(pacienteDTO2.getNombre(), pacienteDTO2.getDni(), medicoDTO2.getNombre(),medicoDTO2.getMatricula(), fechaHoraInicio);
		
		fechaHoraInicio = LocalDateTime.of(2021, 12, 10, 9, 30);
		turnoDTO3 = new TurnoDTO(pacienteDTO3.getNombre(), pacienteDTO3.getDni(), medicoDTO1.getNombre(),medicoDTO1.getMatricula(), fechaHoraInicio);
		
		fechaHoraInicio = LocalDateTime.of(2021, 12, 10, 11, 00);
		turnoDTO4 = new TurnoDTO(pacienteDTO3.getNombre(), pacienteDTO3.getDni(), medicoDTO1.getNombre(),medicoDTO1.getMatricula(), fechaHoraInicio);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("AgregarTurno")
	//@Disabled
	void agregarTurnoTest() throws Exception {
		logger.info("Ejecutando agregarTurnoTest()");
		
		medicoService.agregarMedico(medicoDTO1);
		medicoService.agregarMedico(medicoDTO2);
		
		pacienteService.agregarPaciente(pacienteDTO1);
		pacienteService.agregarPaciente(pacienteDTO2);
		pacienteService.agregarPaciente(pacienteDTO3);
		
		turnoService.agregarTurno(turnoDTO1);
		turnoService.agregarTurno(turnoDTO2);
		//No se debe agregar, porque se superpone con turnoDTO1
		turnoService.agregarTurno(turnoDTO3);
		
		assertTrue(turnoService.listaTurnos().size() == 2);
		
		turnoService.eliminarTurno(turnoDTO1);
		turnoService.eliminarTurno(turnoDTO2);

		medicoService.eliminarMedico(medicoDTO1);
		medicoService.eliminarMedico(medicoDTO2);
		pacienteService.eliminarPaciente(pacienteDTO1);
		pacienteService.eliminarPaciente(pacienteDTO2);
		pacienteService.eliminarPaciente(pacienteDTO3);
		
		
	}
	
	@Test
	@DisplayName("RetrasarTurno")
	//@Disabled
	void retrasarTurnoTest() throws Exception {
		logger.info("Ejecutando retrasarTurnoTest()");
		
		medicoService.agregarMedico(medicoDTO1);
		medicoService.agregarMedico(medicoDTO2);
		
		pacienteService.agregarPaciente(pacienteDTO1);
		pacienteService.agregarPaciente(pacienteDTO2);
		pacienteService.agregarPaciente(pacienteDTO3);
		
		turnoService.agregarTurno(turnoDTO1);
		turnoService.agregarTurno(turnoDTO4);
		
		
		//No se puede retrasar. Se superpone con turnoDTO4
		assertFalse(turnoService.retrasarTurno(turnoDTO1.getDniPaciente(), 90));
		//Se puede retrasar
		assertTrue(turnoService.retrasarTurno(turnoDTO1.getDniPaciente(), 60));
		
		turnoDTO1.setFechaHoraInicio(turnoDTO1.getFechaHoraInicio().plusMinutes(60));
		
		turnoService.eliminarTurno(turnoDTO1);
		turnoService.eliminarTurno(turnoDTO4);

		medicoService.eliminarMedico(medicoDTO1);
		medicoService.eliminarMedico(medicoDTO2);
		pacienteService.eliminarPaciente(pacienteDTO1);
		pacienteService.eliminarPaciente(pacienteDTO2);
		pacienteService.eliminarPaciente(pacienteDTO3);
		
		
	}
	
	@Test
	@DisplayName("Tiempo Restante Para El Turno")
	//@Disabled
	void tiempoRestanteTurnoTest() throws Exception {
		logger.info("Ejecutando tiempoRestanteTurnoTest()");
		
		medicoService.agregarMedico(medicoDTO1);
		pacienteService.agregarPaciente(pacienteDTO1);
		turnoService.agregarTurno(turnoDTO1);
		
		assertNotNull(turnoService.tiempoRestanteTurnoPaciente(pacienteDTO1.getDni()));
		
		turnoService.eliminarTurno(turnoDTO1);
		medicoService.eliminarMedico(medicoDTO1);
		pacienteService.eliminarPaciente(pacienteDTO1);	
		
	}
	
	@Test
	@DisplayName("Horario de atencion por dni")
	//@Disabled
	void horarioAtencionPorDniTest() throws Exception {
		medicoService.agregarMedico(medicoDTO1);
		pacienteService.agregarPaciente(pacienteDTO1);
		turnoService.agregarTurno(turnoDTO1);
		
		LocalDateTime hora = turnoService.buscarHorarioAtencionPaciente(pacienteDTO1.getDni());
		String horaTurno = "Hora atencion: " + hora;
		logger.info(horaTurno);
		assertNotNull(hora);
		
		turnoService.eliminarTurno(turnoDTO1);
		medicoService.eliminarMedico(medicoDTO1);
		pacienteService.eliminarPaciente(pacienteDTO1);
	}

}
