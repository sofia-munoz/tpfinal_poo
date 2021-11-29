package ar.edu.unju.fi.poo.grupo04.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
Clase Entidad Paciente
*/
@Component
@Entity
@Table(name = "pacientes")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Paciente extends Persona{
	@Column(name = "dni")
	private Long dni;
	
	@Column(name = "domicilio")
	private String domicilio;
	
	@Column(name = "numero_celular")
	private Long numeroCelular;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_obra_social")
	private ObraSocial obraSocial;
	
	public Paciente() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param nombre
	 * @param email
	 */
	public Paciente(String nombre, String email, Long dni, String domicilio, Long numeroCelular, ObraSocial obraSocial) {
		super(nombre, email);
		this.dni = dni;
		this.domicilio = domicilio;
		this.numeroCelular = numeroCelular;
		this.obraSocial = obraSocial;
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
	 * @return the dni
	 */
	public Long getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(Long dni) {
		this.dni = dni;
	}

	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return the numeroCelular
	 */
	public Long getNumeroCelular() {
		return numeroCelular;
	}

	/**
	 * @param numeroCelular the numeroCelular to set
	 */
	public void setNumeroCelular(Long numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	/**
	 * @return the obraSocial
	 */
	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	/**
	 * @param obraSocial the obraSocial to set
	 */
	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	@Override
	public String toString() {
		return "Paciente [nombre=" + getNombre() 
				+ ", dni=" + dni 
				+ ", email=" + getEmail()
				+ ", domicilio=" + domicilio 
				+ ", numeroCelular=" + numeroCelular
				+ ", obraSocial=" + obraSocial + "]";
	}

	
	
}
