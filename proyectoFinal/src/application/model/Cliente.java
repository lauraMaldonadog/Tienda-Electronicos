package application.model;

public class Cliente extends Persona{

	//ATRIBUTOS DE LA CLASE
	
	private String ciudadResidencia;
	private String deptoResidencia;
	private CarroCompras carroCompras;
	
	//CONSTRUCTOR DE LA CLASE
	public Cliente(String nombre, String identificacion, String direccion, String correoElectronico,
			String fechaNacimiento, String ciudadResisdencia, String deptoResidencia, CarroCompras carroCompras, String ciudadResidencia) {
		super(nombre, identificacion, direccion, correoElectronico, fechaNacimiento);
		this.ciudadResidencia = ciudadResidencia;
		this.deptoResidencia = deptoResidencia;
	}

	
	//GET Y SET DE LA CLASE
	
	public String getCiudadResidencia() {
		return ciudadResidencia;
	}


	public void setCiudadResidencia(String ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}
	public String getDeptoResidencia() {
		return deptoResidencia;
	}
	public void setDeptoResidencia(String deptoResidencia) {
		this.deptoResidencia = deptoResidencia;
	}
	public CarroCompras getCarroCompras() {
		return carroCompras;
	}
	public void setCarroCompras(CarroCompras carroCompras) {
		this.carroCompras = carroCompras;
	}
	@Override
	public String toString() {
		return "Cliente [ciudadResidencia=" + ciudadResidencia + ", deptoResidencia=" + deptoResidencia
				+ ", carroCompras=" + carroCompras + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carroCompras == null) ? 0 : carroCompras.hashCode());
		result = prime * result + ((ciudadResidencia == null) ? 0 : ciudadResidencia.hashCode());
		result = prime * result + ((deptoResidencia == null) ? 0 : deptoResidencia.hashCode());
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
		Cliente other = (Cliente) obj;
		if (carroCompras == null) {
			if (other.carroCompras != null)
				return false;
		} else if (!carroCompras.equals(other.carroCompras))
			return false;
		if (ciudadResidencia == null) {
			if (other.ciudadResidencia != null)
				return false;
		} else if (!ciudadResidencia.equals(other.ciudadResidencia))
			return false;
		if (deptoResidencia == null) {
			if (other.deptoResidencia != null)
				return false;
		} else if (!deptoResidencia.equals(other.deptoResidencia))
			return false;
		return true;
	}
	
	
	
}
