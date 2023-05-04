package es.mdef.gestionusuarios.rest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
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
import es.mdef.gestionusuarios.repositorios.FamiliaRepositorio;
import es.mdef.gestionusuarios.repositorios.PreguntaRepositorio;

@RestController()
@RequestMapping("/familia")
public class FamiliaController {
	private final FamiliaRepositorio repositorio;
	private final FamiliaAssembler assembler;
	private final FamiliaListaAssembler listaAssembler;
	private final PreguntaRepositorio preguntaRepositorio;
	private final PreguntaListaAssembler preguntaListaAssembler;
	private final UsuarioListaAssembler usuarioListaAssembler;
//	private final Logger log;

	FamiliaController(FamiliaRepositorio repositorio, FamiliaAssembler assembler, 
			FamiliaListaAssembler listaAssembler,
			 PreguntaRepositorio preguntaRepositorio, PreguntaListaAssembler preguntaListaAssembler,
			 UsuarioListaAssembler usuarioListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.preguntaRepositorio = preguntaRepositorio;
		this.preguntaListaAssembler = preguntaListaAssembler;
		this.usuarioListaAssembler =usuarioListaAssembler;
//		this.log = GestionUsuariosApplication.log;
	}

	@GetMapping("{id}")
	public FamiliaModel one(@PathVariable Long id) {
		Familia familia = repositorio.findById(id).orElseThrow(() -> new RegisterNotFoundException(id, "Familia"));
//		log.info("Recuperado " + Familia);
		return assembler.toModel(familia);
	}

	@GetMapping("{id}/preguntas")
	public CollectionModel<PreguntaListaModel> pregunta(@PathVariable Long id) {
		return CollectionModel.of(
				repositorio.findById(id)
					.orElseThrow(() -> new RegisterNotFoundException(id, "Familia"))
					.getPreguntas().stream().map(pregunta -> preguntaListaAssembler.toModel(pregunta))
						.collect(Collectors.toList()),
				linkTo(methodOn(FamiliaController.class).pregunta(id)).withSelfRel());
	}
	
	@GetMapping("{id}/usuarios")
	public CollectionModel<UsuarioListaModel> usuariosFamilia(@PathVariable Long id) {
	    List<Usuario> usuarios = repositorio.findById(id)
	            .orElseThrow(() -> new RegisterNotFoundException(id, "Familia"))
	            .getPreguntas().stream().map(pregunta -> pregunta.getUsuario())
	            .collect(Collectors.toList());
	    
	    Set<UsuarioListaModel> usuarioListaModels = usuarios.stream()
	            .map(usuario-> usuarioListaAssembler.toModel(usuario))
	            .collect(Collectors.toSet());
	    
	    return CollectionModel.of(
	            usuarioListaModels,
	            linkTo(methodOn(FamiliaController.class).usuariosFamilia(id)).withSelfRel());
	}


	@GetMapping
	public CollectionModel<FamiliaListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}

	@PostMapping
	public FamiliaModel add(@RequestBody Familia Familia) {
		Familia nuevaFamilia = repositorio.save(Familia);
//		log.info("Añadido " + nuevaFamilia);;
		return assembler.toModel(nuevaFamilia);
	}

//	
	@PutMapping("{id}")
	public FamiliaModel edit(@PathVariable Long id, @RequestBody Familia Familia) {
		Familia nuevoFamilia = repositorio.findById(id).map(emp -> {
			emp.setEnunciado(Familia.getEnunciado());
			return repositorio.save(emp);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Familia"));
//		log.info("Actualizado " + nuevoFamilia);
		return assembler.toModel(nuevoFamilia);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
//		log.info("Borrado usuario " + id);
		repositorio.deleteById(id);
	}

}
