package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.DatePicker;

public class Administrador extends Persona  {
	ArrayList<Sede> listaSedes;
	
	
	/**
	 * CONSTRUCTOR DE LA CLASE
	 * @param nombre
	 * @param identificacion
	 * @param direccion
	 * @param correoElectronico
	 * @param fechaNacimiento
	 */
	public Administrador(String nombre, String identificacion, String direccion, String correoElectronico,
			LocalDate fechaNacimiento, String contrasenia) {
		super(nombre, identificacion, direccion, correoElectronico, fechaNacimiento,contrasenia);
		this.listaSedes= new ArrayList<>();
	}


	


	@Override
	public String toString() {
		return "Administrador [listaSedes=" + listaSedes + ", getNombre()=" + getNombre() + ", getContrasenia()="
				+ getContrasenia() + ", getIdentificacion()=" + getIdentificacion() + ", getDireccion()="
				+ getDireccion() + ", getCorreoElectronico()=" + getCorreoElectronico() + ", getFechaNacimiento()="
				+ getFechaNacimiento() + ", toString()=" + super.toString() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + "]";
	}





	public ArrayList<Sede> getListaSedes() {
		return listaSedes;
	}





	public void setListaSedes(ArrayList<Sede> listaSedes) {
		this.listaSedes = listaSedes;
	}

	/*
	 * metodo que valida si el usuario y contraseña ingresados son los del admin
	 */
	public boolean validarDatos(String user, String passwd) {
		if(getNombre().equals(user) && getContrasenia().equals(passwd))
			return true;
		return false;
	}
	/*
	 * metodo que agrega una sede a la lista de sedes del admin
	 */
	public void setSede(Sede sedeAux) {
		if(!listaSedes.contains(sedeAux))
			listaSedes.add(sedeAux);
		
	}


	


	
	
}
