package es.mdef.gestionusuarios.entidades;

public class Administrador extends Usuario {
	
	private String telefono;
	private Role role;
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public Administrador(String telefono) {
		super();
		this.telefono = telefono;
	}

	public Administrador() {
		super();
	}
	


}
