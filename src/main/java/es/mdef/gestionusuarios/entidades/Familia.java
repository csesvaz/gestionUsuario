package es.mdef.gestionusuarios.entidades;

import java.util.List;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.Escape;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="familia")
public class Familia extends es.mdef.support.Familia   {
	@Column(unique=true, name = "enunciado")
	@NotBlank(message="enunciado es un campo obligatorio de la clase Familia")
	private String enunciado;
	@OneToMany(mappedBy = "familia")
	private List <Pregunta> preguntas; 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

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

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}


}
