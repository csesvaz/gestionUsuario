package es.mdef.gestionusuarios.rest;


import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.NoAdministrador;
import es.mdef.gestionusuarios.entidades.Usuario;

@Component
public class UsuarioPostModelAssembler  implements RepresentationModelAssembler<UsuarioPostModel, UsuarioModel>{

	public UsuarioPostModel toModel(Usuario entity) {
		UsuarioPostModel model = new UsuarioPostModel();
		model.setNombre(entity.getNombre());
		model.setUsername(entity.getUsername());
		model.setContrasena(entity.getContrasena());
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

	public UsuarioModel toModel(UsuarioPostModel model) {
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
