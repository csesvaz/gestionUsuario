package es.mdef.gestionusuarios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.mdef.gestionusuarios.entidades.Familia;
@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, Long> {

}
