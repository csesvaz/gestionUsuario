package es.mdef.gestionusuarios.rest;


import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.NoAdministrador;
import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.entidades.Usuario.Role;

@Component
public class UsuarioPostModelAssembler  implements RepresentationModelAssembler<Usuario, UsuarioPostModel>{

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

	public Usuario toEntity(UsuarioPostModel model) {
		Usuario usuario = new Usuario();
		
		switch (model.getRole()) {
        case administrador: {
                Administrador administrador = new Administrador();
                administrador.setTelefono(model.getTelefono());
                administrador.setRole(Role.administrador);
                usuario=administrador;
            
            break;
        }
        case noAdministrador: {
            
			NoAdministrador noAdministrador = new NoAdministrador();

                noAdministrador.setDepartamento(model.getDepartamento());
                noAdministrador.setTipo(model.getTipo());
                noAdministrador.setRole(Role.noAdministrador);
                usuario=noAdministrador;
            break;
        }
        default:
            throw new IllegalArgumentException("Unexpected value: " + model.getRole());
    }
		usuario.setNombre(model.getNombre());
		usuario.setUsername(model.getUsername());
		usuario.setContrasena(model.getContrasena());
		

		return usuario;
	}

	


}
