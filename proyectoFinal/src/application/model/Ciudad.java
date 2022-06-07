package application.model;

public class Ciudad {
	String nombre;
	String codigo;
	String departamento;
	public Ciudad(String nombre, String codigo, String departamento) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.departamento = departamento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	@Override
	public String toString() {
		return "Ciudad [nombre=" + nombre + ", codigo=" + codigo + ", departamento=" + departamento + "]";
	}
	

}
