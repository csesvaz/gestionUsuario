package es.mdef.gestionusuarios.entidades;

import es.mdef.gestionusuarios.entidades.Usuario.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("N")
public class NoAdministrador extends Usuario {
	public static enum Departamento {
		EMIES, CCESP
	}
	public static enum Tipo {
		Alumno, Docente , Administración
	}
	@NotBlank(message="departamento es un campo obligatorio de la clase NoAdministrador")
	private Departamento departamento;
	@NotBlank(message="tipo es un campo obligatorio de la clase NoAdministrador")
	private Role role;
	private Tipo tipo;
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public NoAdministrador(Departamento departamento, Tipo tipo) {
		super();
		this.departamento = departamento;
		this.tipo = tipo;
	}
	public NoAdministrador() {
		super();
	}

	
	
}
