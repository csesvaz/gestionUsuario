package es.mdef.gestionusuarios.rest;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import es.mdef.gestionusuarios.entidades.Familia;

@Component
public class FamiliaListaAssembler implements RepresentationModelAssembler<Familia, FamiliaListaModel> {

 
	public FamiliaListaModel toModel(Familia entity) {
		FamiliaListaModel model = new FamiliaListaModel();
		model.setEnunciado(entity.getEnunciado());
		model.add(
			linkTo(methodOn(FamiliaController.class).one(entity.getId())).withSelfRel()
		);
		return model;
	}
	
	public CollectionModel<FamiliaListaModel> toCollection(List<Familia> lista) {
		CollectionModel<FamiliaListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
		collection.add(
			linkTo(methodOn(FamiliaController.class).all()).withRel("Familias")
		);
		return collection;
	}

}


