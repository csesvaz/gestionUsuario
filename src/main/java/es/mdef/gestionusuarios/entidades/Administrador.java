package es.mdef.gestionusuarios.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("A")
public class Administrador extends Usuario {
	@NotBlank(message="telefono es un campo obligatorio de la clase Administrador")
	private String telefono;
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Role getRole() {
		return Role.administrador;
	}

	public Administrador(String telefono) {
		super();
		this.telefono = telefono;
	}

	public Administrador() {
		super();
	}
	


}
