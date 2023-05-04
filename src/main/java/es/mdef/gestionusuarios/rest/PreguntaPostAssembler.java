package es.mdef.gestionusuarios.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Pregunta;

@Component
public class PreguntaPostAssembler implements RepresentationModelAssembler<Pregunta, PreguntaPostModel> {
	@Override
	public PreguntaPostModel toModel(Pregunta entity) {
		PreguntaPostModel model = new PreguntaPostModel();
		model.setEnunciado(entity.getEnunciado());
		model.add(linkTo(methodOn(PreguntaController.class).one(entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(PreguntaController.class).getPreguntasFamilia(entity.getId())).withRel("preguntas"));
		
		return model;
	}

	public Pregunta toEntity(PreguntaPostModel model) {
		Pregunta pregunta = new Pregunta();
		pregunta.setEnunciado(model.getEnunciado());
		pregunta.setUsuario(model.getUsuario());
		
		return pregunta;
	}

}
