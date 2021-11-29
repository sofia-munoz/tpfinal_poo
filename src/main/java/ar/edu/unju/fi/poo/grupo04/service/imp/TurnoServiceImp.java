package ar.edu.unju.fi.poo.grupo04.service.imp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.poo.grupo04.dto.TurnoDTO;
import ar.edu.unju.fi.poo.grupo04.entity.Medico;
import ar.edu.unju.fi.poo.grupo04.entity.Paciente;
import ar.edu.unju.fi.poo.grupo04.entity.Turno;
import ar.edu.unju.fi.poo.grupo04.repository.MedicoRepository;
import ar.edu.unju.fi.poo.grupo04.repository.PacienteRepository;
import ar.edu.unju.fi.poo.grupo04.repository.TurnoRepository;
import ar.edu.unju.fi.poo.grupo04.service.IJavaMailService;
import ar.edu.unju.fi.poo.grupo04.service.ITurnoService;
import ar.edu.unju.fi.poo.grupo04.util.Util;

@Service("turnoServiceImp")
public class TurnoServiceImp implements ITurnoService {
	static final Logger logger = Logger.getLogger(TurnoServiceImp.class);
	
	@Autowired
	TurnoRepository turnoRepository;
	
	@Autowired
	MedicoRepository medicoRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Autowired
	IJavaMailService javaMailService;
	
	@Autowired
	Medico medico;
	
	@Autowired
	Util util;
		
	@Autowired
	Turno turno = new Turno();
	
	@Autowired
	TurnoDTO turnoDTO = new TurnoDTO();
	
	@Autowired
	Paciente paciente = new Paciente();
	
	/**
	 * Agrega un turno,
	 * comprobando con anterioridad que el turno 
	 * no se superponga con otro para el mismo médico,
	 * y envía un mail al paciente y al médico
	 * @throws Exception 
	 */
	public void agregarTurno(TurnoDTO turnoDTO) throws Exception { 
		turnoDTO.setFechaHoraFin(turnoDTO.getFechaHoraInicio().plusMinutes(Turno.DURACION_TURNO_EN_MINUTOS));
		if(turnoDisponible(turnoDTO)) {
			turno = util.turnoDTOToTurno(turnoDTO);
			turnoRepository.save(turno);
			logger.info("Se ha agregado un nuevo turno: " + turnoDTO);
			String mailPaciente = turno.getPaciente().getEmail();
			String mailMedico = turno.getMedico().getEmail();
			javaMailService.enviarMail(mailPaciente, "Registro de Turno", util.getMensageTurno(turnoDTO));
			javaMailService.enviarMail(mailMedico, "Registro de Turno", util.getMensageTurno(turnoDTO));
		}else {
			logger.info("No se ha podido agregar el turno solicitado");
		}
	}
	/**
	 * Comprueba si el horario del turnoDTO enviado por parámetro
	 * está disponible o no
	 * @param turnoDTO
	 * @return boolean
	 */
	public boolean turnoDisponible(TurnoDTO turnoDTO) {
		List<Turno> turnosAgendados = new ArrayList<Turno>();
		List<Turno> turnosAgendados2 = new ArrayList<Turno>();
		medico = medicoRepository.findByMatricula(turnoDTO.getMatriculaMedico());
		turnosAgendados = turnoRepository.findByMedicoAndFechaHoraInicioBetween(medico, turnoDTO.getFechaHoraInicio(), turnoDTO.getFechaHoraFin().minusMinutes(1));
		turnosAgendados2 = turnoRepository.findByMedicoAndFechaHoraFinBetween(medico, turnoDTO.getFechaHoraInicio().plusMinutes(1), turnoDTO.getFechaHoraFin());
		if(turnosAgendados.isEmpty() && turnosAgendados2.isEmpty()){
			return true;
		}else
			return false;
	}
	/**
	 * Comprueba si el horario del turno enviado por parámetro
	 * está disponible o no
	 * @param turno
	 * @return boolean
	 */
	public boolean turnoDisponible(Turno turno) {
		List<Turno> turnosAgendados = new ArrayList<Turno>();
		List<Turno> turnosAgendados2 = new ArrayList<Turno>();
		medico = medicoRepository.findByMatricula(turno.getMedico().getMatricula());
		turnosAgendados = turnoRepository.findByMedicoAndFechaHoraInicioBetween(medico, turno.getFechaHoraInicio(), turno.getFechaHoraFin().minusMinutes(1));
		turnosAgendados2 = turnoRepository.findByMedicoAndFechaHoraFinBetween(medico, turno.getFechaHoraInicio().plusMinutes(1), turno.getFechaHoraFin());
		logger.info("hizo las asignaciones a las listas");
		for (Turno t1 : turnosAgendados) {
			logger.info("entro al foreach 1");
			if(t1.getFechaHoraInicio() == turno.getFechaHoraInicio()) {
				turnosAgendados.remove(t1);
			}	
			if(turnosAgendados.size()==0)
				break;
		}

		for (Turno t2 : turnosAgendados2) {
			logger.info("entro al foreach 2");
			if(t2.getFechaHoraInicio() == turno.getFechaHoraInicio()) {
				turnosAgendados2.remove(t2);		
			}
			if(turnosAgendados2.size()==0)
				break;

		}
		if(turnosAgendados.isEmpty() && turnosAgendados2.isEmpty()){
			return true;
		}else {
			return false;
		}		
	}

	@Override
	public TurnoDTO getTurno() {
		return turnoDTO;
	}

	/**
	 * Elimina un turno enviado por parámetro
	 */
	@Override
	public void eliminarTurno(TurnoDTO turnoDTO) {
		Paciente paciente = pacienteRepository.findByDni(turnoDTO.getDniPaciente());
		turno = turnoRepository.findByPacienteAndFechaHoraInicio(paciente, turnoDTO.getFechaHoraInicio());
		turnoRepository.delete(turno);
		logger.info("Se ha eliminado un turno: " + turnoDTO);
	}
	
	@Override
	public List<TurnoDTO> listaTurnos() {
		List<TurnoDTO> listaTurnosDTO = new ArrayList<TurnoDTO>();
		List<Turno> listaTurnos = (List<Turno>)turnoRepository.findAll();
		for (Turno turno : listaTurnos) {
			listaTurnosDTO.add(util.turnoToTurnoDTO(turno));
		}
		return listaTurnosDTO;
	}
	/**
	 * Retrasa un turno, la cantidad de minutos
	 * enviados por parámetros, recuperandolo de la bd
	 * con el dni tambien enviado por parámetro, 
	 */
	@Override
	public boolean retrasarTurno(Long dni, Integer cantidadMinutos) {
		logger.info("entro");
		paciente = pacienteRepository.findByDni(dni);
		logger.info("busco paciente");
		turno = turnoRepository.findByPaciente(paciente);
		logger.info("busco turno");
		turno.setFechaHoraInicio(turno.getFechaHoraInicio().plusMinutes(cantidadMinutos));
		logger.info("asigno hora inicio");
		turno.setFechaHoraFin(turno.getFechaHoraInicio().plusMinutes(Turno.DURACION_TURNO_EN_MINUTOS));
		logger.info("asigno hora fin");
		
		if(turnoDisponible(turno)){
			logger.info("Se ha podido retrasar el turno solicitado");
			turnoRepository.delete(turnoRepository.findByPaciente(paciente));
			try {
				agregarTurno(util.turnoToTurnoDTO(turno));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}else {
			logger.info("NO se ha podido retrasar el turno solicitado");
			return false;
			}
	}
	/**
	 * Devuelve un string con la cantidad de tiempo restante
	 * para el turno asignado a un paciente,
	 * buscandolo con el dni enviado por parámento
	 */
	@Override
	public String tiempoRestanteTurnoPaciente(Long dni) {
    	Paciente paciente = this.pacienteRepository.findByDni(dni);
        Turno turno=turnoRepository.findByPaciente(paciente);
        LocalDateTime ahora = LocalDateTime.now();
        if(turno.getFechaHoraInicio().isAfter(ahora)){
        	long minutos = ChronoUnit.MINUTES.between(ahora, turno.getFechaHoraInicio());
        	if(minutos>1440) {
        		long dias = minutos/60/24;
        		minutos = minutos - 1440 * dias;
        		long hours = minutos / 60;
        		minutos = minutos - hours *60;
        		return dias + " dias " + hours + " horas " + minutos + " minutos";
        	}else {
        		long hours = minutos / 60;
            	long dias = minutos/60/24;
            	minutos = minutos - hours *60;
                return dias + " dias " + hours + " horas " + minutos + " minutos";
        	}	
        }
       return null;
             
    }

	@Override
	public LocalDateTime buscarHorarioAtencionPaciente(Long dniPaciente) {
		 Paciente paciente = this.pacienteRepository.findByDni(dniPaciente);
         Turno t=turnoRepository.findByPaciente(paciente);
     
		return t.getFechaHoraInicio();
	}
	
	@Override
	public TurnoDTO buscarTurnoPorDniPaciente(Long dniPaciente) {
		Paciente paciente = this.pacienteRepository.findByDni(dniPaciente);
        turno = turnoRepository.findByPaciente(paciente);
		return util.turnoToTurnoDTO(turno);
	}

}

