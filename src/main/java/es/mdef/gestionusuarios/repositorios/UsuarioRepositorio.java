package es.mdef.gestionusuarios.repositorios;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.mdef.gestionusuarios.entidades.Usuario;
//import es.mdef.gestionusuarios.entidades.Usuario.Role;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
//	List<Usuario> findUsuarioByRole(Role role);
}
