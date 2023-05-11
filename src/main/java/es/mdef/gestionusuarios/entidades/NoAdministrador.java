package es.mdef.gestionusuarios.entidades;

public class NoAdministrador extends Usuario {
	public static enum Departamento {
		EMIES, CCESP
	}
	public static enum Tipo {
		Alumno, Docente , Administración
	}
	private Departamento departamento;
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
