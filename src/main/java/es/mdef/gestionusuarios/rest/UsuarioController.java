package es.mdef.gestionusuarios.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.NoAdministrador;
import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.entidades.Usuario.Role;
import es.mdef.gestionusuarios.repositorios.UsuarioRepositorio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioRepositorio repositorio;
	private final UsuarioAssembler assembler;
	private final UsuarioListaAssembler listaAssembler;
	private final UsuarioPostModelAssembler postAssembler;
	private PreguntaListaAssembler preguntaListaAssembler;
	private FamiliaListaAssembler familiaListaAssembler;
//	private final Logger log;

	UsuarioController(UsuarioRepositorio repositorio, UsuarioAssembler assembler, UsuarioListaAssembler listaAssembler,
			UsuarioPostModelAssembler postAssembler, PreguntaListaAssembler preguntaListaAssembler,FamiliaListaAssembler familiaListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.postAssembler = postAssembler;
		this.preguntaListaAssembler = preguntaListaAssembler;
		this.familiaListaAssembler = familiaListaAssembler;

//		log = GestionusuariosApplication.log;
	}

	@GetMapping("{id}")
	public UsuarioModel one(@PathVariable Long id) {
		Usuario Usuario = repositorio.findById(id).orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"));
//		log.info("Recuperado " + Usuario);
		return assembler.toModel(Usuario);
	}

	@GetMapping
	public CollectionModel<UsuarioListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}

	@PostMapping
	public UsuarioModel add(@Valid @RequestBody UsuarioPostModel model) {
		Usuario Usuario = repositorio.save(assembler.toEntity(postAssembler.toModel(model)));
//		log.info("Añadido " + Usuario);
		return assembler.toModel(Usuario);
	}

	@PutMapping("{id}")
	public UsuarioModel edit(@PathVariable Long id, @RequestBody UsuarioPutModel model) {
		Usuario usuario = repositorio.findById(id).map(ped -> {
			ped.setNombre(model.getNombre());
			ped.setUsername(model.getUsername());
			if (model.getRole() == Role.administrador) {
				Administrador administrador = (Administrador) ped;
				administrador.setTelefono(model.getTelefono());
				ped = administrador;
			} else if (model.getRole() == Role.noAdministrador) {
				NoAdministrador noAdministrador = (NoAdministrador) ped;
				noAdministrador.setDepartamento(model.getDepartamento());
				noAdministrador.setTipo(model.getTipo());
				ped = noAdministrador;
			}
			return repositorio.save(ped);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"));

		return assembler.toModel(usuario);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
//		log.info("Borrado Usuario " + id);
		repositorio.deleteById(id);
	}

	@GetMapping("{id}/preguntas")
	public CollectionModel<PreguntaListaModel> getPreguntasUsuario(@PathVariable Long id) {
			return CollectionModel.of(
					repositorio.findById(id)
						.orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"))
						.getPreguntas().stream().map(pregunta -> preguntaListaAssembler.toModel(pregunta))
							.collect(Collectors.toList()),
					linkTo(methodOn(UsuarioController.class).getPreguntasUsuario(id)).withSelfRel());
		}
	
	@GetMapping("{id}/familias")
	public CollectionModel<FamiliaListaModel> getFamiliasUsuario(@PathVariable Long id) {
		Set<FamiliaListaModel> familiaListaModels = repositorio.findById(id)
		        .orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"))
		        .getPreguntas().stream()
		        .map(pregunta -> pregunta.getFamilia())
		        .distinct()
		        .map(familia -> familiaListaAssembler.toModel(familia))
		        .collect(Collectors.toSet());
		

		return CollectionModel.of(
		        familiaListaModels,
		        linkTo(methodOn(UsuarioController.class).getFamiliasUsuario(id)).withSelfRel());

		}

}
