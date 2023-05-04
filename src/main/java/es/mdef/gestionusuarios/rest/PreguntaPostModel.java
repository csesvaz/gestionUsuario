package es.mdef.gestionusuarios.rest;

import org.springframework.hateoas.RepresentationModel;

//import es.mdef.gestionusuarios.entidades.Familia;
import es.mdef.gestionusuarios.entidades.Usuario;

public class PreguntaPostModel extends RepresentationModel<PreguntaPostModel> {
private String enunciado;
private Usuario usuario;
//private Familia familia;
public String getEnunciado() {
	return enunciado;
}
public void setEnunciado(String enunciado) {
	this.enunciado = enunciado;
}
public Usuario getUsuario() {
	return usuario;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
//public Familia getFamilia() {
//	return familia;
//}
//public void setFamilia(Familia familia) {
//	this.familia = familia;
//}

}
