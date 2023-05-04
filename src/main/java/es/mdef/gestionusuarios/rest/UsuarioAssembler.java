package es.mdef.gestionusuarios.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.NoAdministrador;

@Component
public class UsuarioAssembler implements RepresentationModelAssembler<Usuario, UsuarioModel> {

	@Override
	public UsuarioModel toModel(Usuario entity) {
		UsuarioModel model = new UsuarioModel();
		model.setNombre(entity.getNombre());
		model.setUsername(entity.getUsername());
		switch (entity.getRole()) {
		case administrador: {
			Administrador administrador = (Administrador) entity;
			model.setRole(administrador.getRole());
			model.setTelefono(administrador.getTelefono());
			entity = administrador;
			break;
		}
		case noAdministrador: {
			NoAdministrador noAdministrador = (NoAdministrador) entity;
			model.setRole(noAdministrador.getRole());
			model.setDepartamento(noAdministrador.getDepartamento());
			model.setTipo(noAdministrador.getTipo());
			entity = noAdministrador;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + entity.getRole());
		}

		model.add(linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(UsuarioController.class).getPreguntasUsuario(entity.getId())).withRel("preguntas"));
		return model; 
	}

	public Usuario toEntity(UsuarioModel model) {
	    Usuario usuario;
	    switch (model.getRole()) {
	        case administrador: {
	            usuario = new Administrador();
	            ((Administrador) usuario).setTelefono(model.getTelefono());
	            break;
	        }
	        case noAdministrador: {
	            usuario = new NoAdministrador();
	            ((NoAdministrador) usuario).setDepartamento(model.getDepartamento());
	            ((NoAdministrador) usuario).setTipo(model.getTipo());
	            break;
	        }
	        default:
	            throw new IllegalArgumentException("Unexpected value: " + model.getRole());
	    }
	    usuario.setNombre(model.getNombre());
	    usuario.setUsername(model.getUsername());
	    usuario.setId(usuario.getId());
	    return usuario;
	}
}
