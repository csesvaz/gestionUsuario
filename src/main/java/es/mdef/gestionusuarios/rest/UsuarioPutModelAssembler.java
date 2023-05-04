package es.mdef.gestionusuarios.rest;


import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.NoAdministrador;
import es.mdef.gestionusuarios.entidades.Usuario;

@Component
public class UsuarioPutModelAssembler  implements RepresentationModelAssembler<UsuarioPutModel, UsuarioModel>{

	public UsuarioPutModel toModel(Usuario entity) {
		UsuarioPutModel model = new UsuarioPutModel();
		model.setNombre(entity.getNombre());
		model.setUsername(entity.getUsername());
		switch (entity.getRole()) {
        case administrador: {
            if (entity instanceof Administrador) {
                Administrador administrador = (Administrador) entity;
                model.setRole(administrador.getRole());
                model.setTelefono(administrador.getTelefono());
            }
            break;
        }
        case noAdministrador: {
            if (entity instanceof NoAdministrador) {
                NoAdministrador noAdministrador = (NoAdministrador) entity;
                model.setRole(noAdministrador.getRole());
                model.setDepartamento(noAdministrador.getDepartamento());
                model.setTipo(noAdministrador.getTipo());
            }
            break;
        }
        default:
            throw new IllegalArgumentException("Unexpected value: " + entity.getRole());
    }

		return model;
	}

	@Override
	public UsuarioModel toModel(UsuarioPutModel model) {
		UsuarioModel usuario = new UsuarioModel();
		usuario.setNombre(model.getNombre());
		usuario.setUsername(model.getUsername());
		usuario.setRole(model.getRole());
		usuario.setDepartamento(model.getDepartamento());
		usuario.setTipo(model.getTipo());
		usuario.setTelefono(model.getTelefono());
		return usuario;
	}

}
