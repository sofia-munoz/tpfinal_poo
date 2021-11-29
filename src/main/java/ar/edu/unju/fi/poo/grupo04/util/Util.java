package ar.edu.unju.fi.poo.grupo04.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.unju.fi.poo.grupo04.dto.MedicoDTO;
import ar.edu.unju.fi.poo.grupo04.dto.ObraSocialDTO;
import ar.edu.unju.fi.poo.grupo04.dto.PacienteDTO;
import ar.edu.unju.fi.poo.grupo04.dto.TurnoDTO;
import ar.edu.unju.fi.poo.grupo04.entity.Medico;
import ar.edu.unju.fi.poo.grupo04.entity.ObraSocial;
import ar.edu.unju.fi.poo.grupo04.entity.Paciente;
import ar.edu.unju.fi.poo.grupo04.entity.Turno;
import ar.edu.unju.fi.poo.grupo04.repository.MedicoRepository;
import ar.edu.unju.fi.poo.grupo04.repository.ObraSocialRepository;
import ar.edu.unju.fi.poo.grupo04.repository.PacienteRepository;

@Component
public class Util {
	
	@Autowired
	private ObraSocialRepository obraSocialRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ObraSocialDTO obraSocialDTO;
	
	@Autowired
	TurnoDTO turnoDTO;
	
	public ObraSocialDTO fromObraSocialToObraSocialDTO(ObraSocial obrasocial) {
		obraSocialDTO = new ObraSocialDTO(obrasocial.getId(), obrasocial.getNombre());
		return obraSocialDTO;
	}
	
	public ObraSocial fromObraSocialDTOToObraSocial(ObraSocialDTO obrasocialDTO) {
		ObraSocial obraSocial = obraSocialRepository.findByNombre(obrasocialDTO.getNombre());
		return obraSocial;
	}
	
	public Paciente pacienteDTOtoPaciente(PacienteDTO pacienteDTO) {
		Paciente paciente = new Paciente();
		paciente.setId(pacienteDTO.getId());
		paciente.setNombre(pacienteDTO.getNombre());
		paciente.setEmail(pacienteDTO.getEmail());
		paciente.setDni(pacienteDTO.getDni());
		paciente.setDomicilio(pacienteDTO.getDomicilio());
		paciente.setNumeroCelular(pacienteDTO.getNumeroCelular());
		paciente.setObraSocial(obraSocialRepository.findById(pacienteDTO.getIdObraSocial()).get());
		return paciente;
	}
	
	public PacienteDTO pacientetoPacienteDTO(Paciente paciente) {
		PacienteDTO pacienteDTO = new PacienteDTO();
		pacienteDTO.setId(paciente.getId());
		pacienteDTO.setNombre(paciente.getNombre());
		pacienteDTO.setEmail(paciente.getEmail());
		pacienteDTO.setDni(paciente.getDni());
		pacienteDTO.setDomicilio(paciente.getDomicilio());
		pacienteDTO.setNumeroCelular(paciente.getNumeroCelular());
		pacienteDTO.setIdObraSocial(paciente.getObraSocial().getId());
		pacienteDTO.setNombreObraSocial(paciente.getObraSocial().getNombre());
		return pacienteDTO;
	}
	
	public Medico medicoDTOToMedico(MedicoDTO medicoDTO) {
		Medico medico = new Medico();
		medico.setId(medicoDTO.getId());
		medico.setNombre(medicoDTO.getNombre());
		medico.setEmail(medicoDTO.getEmail());
		medico.setMatricula(medicoDTO.getMatricula());
		medico.setTurnoLaboral(medicoDTO.getTurnoLaboral());
		List<ObraSocial> listaObrasSociales = new ArrayList<ObraSocial>();
		for (ObraSocialDTO osDTO : medicoDTO.getListaObrasSociales()) {
			listaObrasSociales.add(obraSocialRepository.findById(osDTO.getIdObraSocial()).get());
		    }
		medico.setListaObrasSociales(listaObrasSociales);
		return medico;
	}
	
	public MedicoDTO medicoToMedicoDTO(Medico medico) {
		MedicoDTO medicoDTO = new MedicoDTO();
		medicoDTO.setId(medico.getId());
		medicoDTO.setNombre(medico.getNombre());
		medicoDTO.setEmail(medico.getEmail());
		medicoDTO.setMatricula(medico.getMatricula());
		medicoDTO.setTurnoLaboral(medico.getTurnoLaboral());
		List<ObraSocialDTO> listaObrasSociales = new ArrayList<ObraSocialDTO>();
		for (ObraSocial os : medico.getListaObrasSociales()) {
			listaObrasSociales.add(new ObraSocialDTO(os.getId(), os.getNombre()));
		    }
		medicoDTO.setListaObrasSociales(listaObrasSociales);
		return medicoDTO;
	}
	
	
	public Turno turnoDTOToTurno(TurnoDTO turnoDTO) {
		Turno turno = new Turno();
		turno.setMedico(medicoRepository.findByMatricula(turnoDTO.getMatriculaMedico()));
		turno.setPaciente(pacienteRepository.findByDni(turnoDTO.getDniPaciente()));
		turno.setFechaHoraInicio(turnoDTO.getFechaHoraInicio());
		turno.setFechaHoraFin(turnoDTO.getFechaHoraFin());
		return turno;
	}

	
	public TurnoDTO turnoToTurnoDTO(Turno turno) {
		TurnoDTO turnoDTO = new TurnoDTO();
		turnoDTO.setIdTurno(turno.getIdTurno());
		turnoDTO.setDniPaciente(turno.getPaciente().getDni());
		turnoDTO.setNombrePaciente(turno.getPaciente().getNombre());
		turnoDTO.setMatriculaMedico(turno.getMedico().getMatricula());
		turnoDTO.setNombreMedico(turno.getMedico().getNombre());
		turnoDTO.setFechaHoraInicio(turno.getFechaHoraInicio());
		turnoDTO.setFechaHoraFin(turno.getFechaHoraFin());
		
		return turnoDTO;
	}
	
	public static String saludoMail() {
		LocalTime hora = LocalTime.now();
		String saludo = "";
		if(hora.isBefore(LocalTime.parse("13:00:00"))){
			saludo = "Buenos dias!";
		}else {
			if (hora.isBefore(LocalTime.parse("19:00:00"))) {
				saludo = "Buenas tardes!";
			}else {
				saludo = "Buenas noches!";
			}
		}
		return saludo;
	}
	
	public String getMensageTurno(TurnoDTO turnoDTO){
		return
    			"<head>\n"
    		+ "	<meta name=\"viewport\" content=\"user-scalable=no, width=device-width, initial-scale=1\">\n"
    		+ "</head>\n"
    		+ "<body>\n"
    		+ "	\n"
    		+ "		<div style=\"background: white;\">\n"
    		+ "			<center>\n"
    		+ "				<img src='https://covadongah.com/assets/base/img/layout/logos/oftal.jpg' width='25%'>\n"
    		+ "			</center>\n"
    		+ "			<center>\n"
    		+ "				<h1 style=\"color: #1E429B; font-family: century gothic\\\";>Clinica Oftalmologica</h1>\n"
    		+ "			        <b>" + Util.saludoMail() + "</b> <br><br> Este correo ha sido enviado desde la clínica oftalmológica <b><i><font color='#252850'>LA SALUD</font><i></b>. <br>\n"
    		+ "			        	Le comunicamos que su turno ha sido registrado con exito. <br><br>\n"
    		+ "			        	<u>Detalles del turno: </u><br> <br>\n"
    		+ "			        	<table border=1 bordercolor='#252850' style='font-size:14px; border-collapse: collapse;border-color:#252850;'>\n"
    		+ "				        	<tr><td><b>Paciente:</b> </td><td>" +  turnoDTO.getNombrePaciente() + "\n"
    		+ "				        	<tr><td><b>Médico:</b> </td><td>" + turnoDTO.getNombreMedico() + "</td></tr>\n"
    		+ "				        	<tr><td style='padding-right: 5%'><b>Fecha y Hora del turno: </b></td><td>" + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(turnoDTO.getFechaHoraInicio()) + " hs." + "</td></tr>\n"
    		+ "			        	</table><br><i>Recuerde asistir con 15 minutos de anticipación. </i><br> <br>\n"
    		+ "			        	\\Ante cualquier inconveniente, no dude en comunicarse. <br><br>\n"
    		+ "			        	<b><strong>Saludos.</strong></b><br>\n"
    		+ "			        <img src='https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Miguel_D%C3%ADaz-Canel_firma.png/800px-Miguel_D%C3%ADaz-Canel_firma.png' width='10%'>\n"
    		+ "			</center>\n"
    		+ "\n"
    		+ "		</div>\n"
    		+ "	\n"
    		+ "	\n"
    		+ "	\n"
    		+ "	        \n"
    		+ "</body>\n"
    		+ "</html>";
	}
}
