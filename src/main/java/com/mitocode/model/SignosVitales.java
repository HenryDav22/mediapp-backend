package com.mitocode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "signosvitales") // , schema = "m01")
public class SignosVitales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSignos;

	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_signos_paciente"))
	private Paciente paciente;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	@Column(name = "temperatura", nullable = false)
	private String temperatura;

	@Column(name = "pulso", nullable = false)
	private String pulso;

	@Column(name = "ritmo", nullable = false)
	private String ritmo;

	/**
	 * @return the idSignos
	 */
	public Integer getIdSignos() {
		return idSignos;
	}

	/**
	 * @param idSignos
	 *            the idSignos to set
	 */
	public void setIdSignos(Integer idSignos) {
		this.idSignos = idSignos;
	}

	/**
	 * @return the paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente
	 *            the paciente to set
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the fecha
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the temperatura
	 */
	public String getTemperatura() {
		return temperatura;
	}

	/**
	 * @param temperatura
	 *            the temperatura to set
	 */
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	/**
	 * @return the pulso
	 */
	public String getPulso() {
		return pulso;
	}

	/**
	 * @param pulso
	 *            the pulso to set
	 */
	public void setPulso(String pulso) {
		this.pulso = pulso;
	}

	/**
	 * @return the ritmo
	 */
	public String getRitmo() {
		return ritmo;
	}

	/**
	 * @param ritmo
	 *            the ritmo to set
	 */
	public void setRitmo(String ritmo) {
		this.ritmo = ritmo;
	}

}
