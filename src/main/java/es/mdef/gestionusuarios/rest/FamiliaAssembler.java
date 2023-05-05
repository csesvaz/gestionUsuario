package es.mdef.gestionusuarios.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;

import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Familia;



@Component
public class FamiliaAssembler implements RepresentationModelAssembler<Familia, FamiliaModel> {

	public Familia toEntity(FamiliaModel model) {
		Familia Familia = new Familia();
		Familia.setEnunciado(model.getEnunciado());
		return Familia;
	}
	@Override
	public FamiliaModel toModel(Familia entity) {
		FamiliaModel model = new FamiliaModel();
		model.setEnunciado(entity.getEnunciado());
		model.add(linkTo(methodOn(FamiliaController.class).one(entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(FamiliaController.class).pregunta(entity.getId())).withRel("preguntas"));
		model.add(linkTo(methodOn(FamiliaController.class).usuariosFamilia(entity.getId())).withRel("usuarios"));
		return model;
	}



}
