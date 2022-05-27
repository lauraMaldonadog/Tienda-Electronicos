package application.model;

import java.util.ArrayList;

public class Envio {

	private String departamento;
	private String ciudad;
	private String direccion;
	private String personaRecibe;
	private ArrayList<String> listaEnvios;
	
	
	public Envio(String departamento, String ciudad, String direccion, String personaRecibe,
			ArrayList<String> listaEnvios) {
		super();
		this.departamento = departamento;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.personaRecibe = personaRecibe;
		this.listaEnvios = listaEnvios;
	}


	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getPersonaRecibe() {
		return personaRecibe;
	}


	public void setPersonaRecibe(String personaRecibe) {
		this.personaRecibe = personaRecibe;
	}


	public ArrayList<String> getListaEnvios() {
		return listaEnvios;
	}


	public void setListaEnvios(ArrayList<String> listaEnvios) {
		this.listaEnvios = listaEnvios;
	}


	@Override
	public String toString() {
		return "Envio [departamento=" + departamento + ", ciudad=" + ciudad + ", direccion=" + direccion
				+ ", personaRecibe=" + personaRecibe + ", listaEnvios=" + listaEnvios + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((listaEnvios == null) ? 0 : listaEnvios.hashCode());
		result = prime * result + ((personaRecibe == null) ? 0 : personaRecibe.hashCode());
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
		Envio other = (Envio) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (listaEnvios == null) {
			if (other.listaEnvios != null)
				return false;
		} else if (!listaEnvios.equals(other.listaEnvios))
			return false;
		if (personaRecibe == null) {
			if (other.personaRecibe != null)
				return false;
		} else if (!personaRecibe.equals(other.personaRecibe))
			return false;
		return true;
	}
	
	
}
