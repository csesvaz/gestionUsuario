package es.mdef.gestionusuarios.rest;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Relation(itemRelation = "pregunta")
public class PreguntaModel extends RepresentationModel<PreguntaModel>{

	private String enunciado;

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	
}
