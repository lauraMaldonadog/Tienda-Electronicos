package application.model;

public class Persona {

	/*
	 * ATRIBUTOS DE LA CLASE 
	 */
	private String nombre;
	private String identificacion;
	private String direccion;
	private String correoElectronico;
	private String fechaNacimiento;
	
	
	/*
	 * CONSTRUCTOR DE LA CLASE
	 */
	public Persona(String nombre, String identificacion, String direccion, String correoElectronico,
			String fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * GET Y SET DE LA CLASE
	 * @return
	 */

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getIdentificacion() {
		return identificacion;
	}


	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * TO STRING DE LA CLASE 
	 */

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", identificacion=" + identificacion + ", direccion=" + direccion
				+ ", correoElectronico=" + correoElectronico + ", fechaNacimiento=" + fechaNacimiento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((correoElectronico == null) ? 0 : correoElectronico.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((identificacion == null) ? 0 : identificacion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (correoElectronico == null) {
			if (other.correoElectronico != null)
				return false;
		} else if (!correoElectronico.equals(other.correoElectronico))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (identificacion == null) {
			if (other.identificacion != null)
				return false;
		} else if (!identificacion.equals(other.identificacion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	
	
}
