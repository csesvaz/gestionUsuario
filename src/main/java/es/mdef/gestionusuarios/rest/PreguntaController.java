package es.mdef.gestionusuarios.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
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

import es.mdef.gestionusuarios.entidades.Familia;
import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.repositorios.PreguntaRepositorio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/preguntas")
public class PreguntaController {
	private final PreguntaRepositorio repositorio;
	private final PreguntaAssembler assembler;
	private final PreguntaListaAssembler listaAssembler;
	private final PreguntaPostAssembler preguntaPostAssembler;
	private final UsuarioAssembler usuarioAssembler;
	private final FamiliaAssembler familiaAssembler;

	PreguntaController(PreguntaRepositorio repositorio, PreguntaAssembler assembler,FamiliaAssembler familiaAssembler,
			
			PreguntaListaAssembler listaAssembler, PreguntaPostAssembler preguntaPostAssembler, 
			UsuarioAssembler usuarioAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.preguntaPostAssembler = preguntaPostAssembler;
		this.usuarioAssembler = usuarioAssembler;
		this.familiaAssembler =familiaAssembler;

//		log = GestionPreguntasApplication.log;
	}

	@GetMapping("{id}")
	public PreguntaModel one(@PathVariable Long id) {
		Pregunta Pregunta = repositorio.findById(id).orElseThrow(() -> new RegisterNotFoundException(id, "Pregunta"));
//		log.info("Recuperado " + Pregunta);
		return assembler.toModel(Pregunta);
	}

	@GetMapping
	public CollectionModel<PreguntaListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}

	
	
	@PostMapping
	public PreguntaModel add(@Valid @RequestBody PreguntaPostModel model) {
		Pregunta Pregunta = repositorio.save(preguntaPostAssembler.toEntity(model));
//		log.info("Añadido " + Pregunta);
		return assembler.toModel(Pregunta);
	}
//	
	@PutMapping("{id}")
	public PreguntaModel edit(@PathVariable Long id, @RequestBody PreguntaModel model) {
		Pregunta Pregunta = repositorio.findById(id).map(ped -> {
			ped.setEnunciado(model.getEnunciado());
			return repositorio.save(ped);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Pregunta"));

		return assembler.toModel(Pregunta);
	}

//	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
//		log.info("Borrado Pregunta " + id);
		repositorio.deleteById(id);
	}

	
	@GetMapping("{id}/familia")
	public FamiliaModel getPreguntasFamilia(@PathVariable Long id) {
		Familia familia= repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "pregunta")).getFamilia();
		return familiaAssembler.toModel(familia);
	}
	@GetMapping("{id}/usuario")
	public UsuarioModel getPreguntasUsuario(@PathVariable Long id) {
		Usuario usuario= repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "pregunta")).getUsuario();
		return usuarioAssembler.toModel(usuario);
	}

}
