package ar.edu.unju.fi.poo.grupo04.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
Clase Entidad Médico,hereda de Persona
*/
@Component
@Entity
@Table(name = "medicos")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Medico extends Persona{
	@Column(name = "matricula")
	private Integer matricula;
	
	@Column(name = "turno_laboral")
	private String turnoLaboral;//mañana o tarde
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "medico_obrasocial",
	joinColumns = @JoinColumn(name = "id_medico"),
	inverseJoinColumns = @JoinColumn(name = "id_obra_social"))
	private List<ObraSocial> listaObrasSociales = new ArrayList<ObraSocial>();
	
	public Medico() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param matricula
	 * @param turnoLaboral
	 * @param listaObrasSociales
	 */
	public Medico(String nombre, String email, Integer matricula, String turnoLaboral, List<ObraSocial> listaObrasSociales) {
		super(nombre, email);
		this.matricula = matricula;
		this.turnoLaboral = turnoLaboral;
		this.listaObrasSociales = listaObrasSociales;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
	}

	@Override
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub
		super.setNombre(nombre);
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		super.setEmail(email);
	}

	/**
	 * @return the matricula
	 */
	public Integer getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the turnoLaboral
	 */
	public String getTurnoLaboral() {
		return turnoLaboral;
	}

	/**
	 * @param turnoLaboral the turnoLaboral to set
	 */
	public void setTurnoLaboral(String turnoLaboral) {
		this.turnoLaboral = turnoLaboral;
	}

	
	/**
	 * @return the listaObrasSociales
	 */
	
	public List<ObraSocial> getListaObrasSociales() {
		return listaObrasSociales;
	}

	/**
	 * @param listaObrasSociales the listaObrasSociales to set
	 */
	public void setListaObrasSociales(List<ObraSocial> listaObrasSociales) {
		this.listaObrasSociales = listaObrasSociales;
	}

	@Override
	public String toString() {
		return "Medico [nombre=" + getNombre()
				+ ", matricula=" + matricula
				+ ", email=" + getEmail()
				+ ", turnoLaboral=" + turnoLaboral ;
		//		+ ", listaObrasSociales=" + listaObrasSociales + "]";
	}
	
	
	
}
