package es.mdef.gestionusuarios.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Pregunta;
@Component
public class PreguntaAssembler implements RepresentationModelAssembler<Pregunta, PreguntaModel> {

	public Pregunta toEntity(PreguntaModel model) {
		Pregunta pregunta = new Pregunta();
		pregunta.setEnunciado(model.getEnunciado());
		return pregunta;
	}

	@Override
	public PreguntaModel toModel(Pregunta entity) {
		PreguntaModel model = new PreguntaModel();
		model.setEnunciado(entity.getEnunciado());
		model.add(linkTo(methodOn(PreguntaController.class).one(entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(UsuarioController.class).one(entity.getUsuario().getId())).withRel("usuario"));
		model.add(linkTo(methodOn(FamiliaController.class).one(entity.getUsuario().getId())).withRel("familia"));
		return model;
	}

}
