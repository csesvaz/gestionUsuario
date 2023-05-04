package es.mdef.gestionusuarios.rest;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation = "empleado", collectionRelation = "empleados")
public class FamiliaListaModel extends RepresentationModel<FamiliaListaModel>{

	private String enunciado;
	private Long tamano;
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public Long getTamano() {
		return tamano;
	}
	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}
	

}
