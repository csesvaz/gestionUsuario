package es.mdef.gestionusuarios.entidades;

import java.util.List;


public class Familia extends es.mdef.support.Familia   {

	private List <Pregunta> preguntas; 
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}


}
