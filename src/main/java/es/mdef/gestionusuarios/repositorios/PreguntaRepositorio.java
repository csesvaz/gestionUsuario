package es.mdef.gestionusuarios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.mdef.gestionusuarios.entidades.Pregunta;

@Repository
public interface PreguntaRepositorio extends JpaRepository<Pregunta, Long> {

}
