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

import ar.edu.unju.fi.poo.grupo04.dto.MedicoDTO;
import ar.edu.unju.fi.poo.grupo04.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.grupo04.service.IMedicoService;
import ar.edu.unju.fi.poo.grupo04.service.IObraSocialService;
import ar.edu.unju.fi.poo.grupo04.util.Util;

@SpringBootTest
class MedicoTest {
	static final Logger logger = Logger.getLogger(MedicoTest.class);
	
	@Autowired
	IMedicoService medicoService;
	
	@Autowired
	IObraSocialService obraSocialService;
	
	@Autowired
	Util util;
	
	private List<ObraSocialDTO> obraSocialDTOList1 = new ArrayList<ObraSocialDTO>();
	private List<ObraSocialDTO> obraSocialDTOList2 = new ArrayList<ObraSocialDTO>();
	private List<ObraSocialDTO> obraSocialDTOList3 = new ArrayList<ObraSocialDTO>();
	
	@Autowired
	MedicoDTO medicoDTO1, medicoDTO2, medicoDTO3, medicoDTO4, medicoDTO5;

	@Autowired
	ObraSocialDTO obraSocialDTO1, obraSocialDTO2, obraSocialDTO3;

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
		obraSocialDTOList3.add(obraSocialDTO2);
		obraSocialDTOList3.add(obraSocialDTO3);
		
		medicoDTO1 = new MedicoDTO("Juan Perez", "jperez@hotmail.com", 1234, "Mañana",obraSocialDTOList1);
		medicoDTO2 = new MedicoDTO("Belén Rodrigez", "belenr@gmail.com", 2563, "Tarde",obraSocialDTOList2);
		medicoDTO3 = new MedicoDTO("Magalí Lara", "mlara20@yahoo.com", 7452, "Mañana",obraSocialDTOList3);
		medicoDTO4 = new MedicoDTO("Constanza Campos", "conycampos@gmail.com", 3514, "Tarde", obraSocialDTOList1);
		medicoDTO5 = new MedicoDTO("Hector Farjat", "farjath@hotmail.com", 2263, "Tarde", obraSocialDTOList2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Agregar Medico")
	//@Disabled
	void agregarMedicoTest() {
		logger.info("Ejecutando agregarMedicoTest()");

		medicoService.agregarMedico(medicoDTO1);
		medicoService.agregarMedico(medicoDTO2);
		medicoService.agregarMedico(medicoDTO3);
		medicoService.agregarMedico(medicoDTO4);
		medicoService.agregarMedico(medicoDTO5);
		
		assertTrue(medicoService.listaMedicos().size()==5);
		
		medicoService.eliminarMedico(medicoDTO1);
		medicoService.eliminarMedico(medicoDTO2);
		medicoService.eliminarMedico(medicoDTO3);
		medicoService.eliminarMedico(medicoDTO4);
		medicoService.eliminarMedico(medicoDTO5);
	}
	
	@Test
	@DisplayName("Buscar Medico por Matricula o Nombre")
	//@Disabled
	void buscarMedicoPorMatriculaONombreTest() {
		logger.info("Ejecutando buscarMedicoPorMatriculaONombreTest()");
		
		medicoService.agregarMedico(medicoDTO1);
		medicoService.agregarMedico(medicoDTO2);
		medicoService.agregarMedico(medicoDTO3);
		
		List<MedicoDTO> resultadoMedicos = new ArrayList<MedicoDTO>();
		
		resultadoMedicos = medicoService.buscarPorMatriculaONombre(null, "Juan Perez");
		assertTrue(resultadoMedicos.size()==1);
				
		medicoService.eliminarMedico(medicoDTO1);
		medicoService.eliminarMedico(medicoDTO2);
		medicoService.eliminarMedico(medicoDTO3);
	}

}
