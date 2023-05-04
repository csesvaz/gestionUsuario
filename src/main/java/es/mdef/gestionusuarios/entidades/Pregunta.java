package es.mdef.gestionusuarios.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "PREGUNTAS")
public class Pregunta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@NotBlank(message="enunciado es un campo obligatorio de la clase Pregunta")
	private String enunciado;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="UsuarioId")
	@NotBlank(message="usuario es un campo obligatorio de la clase Pregunta")
	private Usuario usuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="FamiliaId")
	@NotBlank(message="familia es un campo obligatorio de la clase Pregunta")
	private Familia familia;

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}


	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Familia getFamilia() {
		return familia;
	}


	public void setFamilia(Familia familia) {
		this.familia = familia;
	}


	public Pregunta(String enunciado) {
		this.enunciado = enunciado;
	}


	public Pregunta() {
		// TODO Auto-generated constructor stub
	}


	


	

}
