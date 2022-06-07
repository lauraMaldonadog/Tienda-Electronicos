package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.DatePicker;

public class Cliente extends Persona{

	//ATRIBUTOS DE LA CLASE
	

	private CarroCompras carroCompras;
	private ArrayList<Factura> listaFacturas;
	
	//CONSTRUCTOR DE LA CLASE
	public Cliente(String nombre, String identificacion, String direccion, String correoElectronico,
			LocalDate fechaNacimiento,String contrasenia) {
		super(nombre, identificacion, direccion, correoElectronico, fechaNacimiento,contrasenia);
		this.carroCompras=new CarroCompras();
		listaFacturas=new ArrayList<>();
	}
	public void actualizarDatos(Cliente cliente) {
		setNombre(cliente.getNombre());
		setIdentificacion(cliente.getIdentificacion());
		setDireccion(cliente.getDireccion());
		setCorreoElectronico(cliente.getCorreoElectronico());
		setFechaNacimiento(cliente.getFechaNacimiento());
		setContrasenia(cliente.getContrasenia());
		this.carroCompras=cliente.getCarroCompras();
		listaFacturas=cliente.getListaFacturas();
		
	}
	
	//GET Y SET DE LA CLASE
	
	public CarroCompras getCarroCompras() {
		return carroCompras;
	}
	public void setCarroCompras(CarroCompras carroCompras) {
		this.carroCompras = carroCompras;
	}
	
	
	public ArrayList<Factura> getListaFacturas() {
		return listaFacturas;
	}
	public void setListaFacturas(ArrayList<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}
	@Override
	public String toString() {
		return "Cliente [carroCompras=" + carroCompras + ", getNombre()=" + getNombre() + ", getContrasenia()="
				+ getContrasenia() + ", getIdentificacion()=" + getIdentificacion() + ", getDireccion()="
				+ getDireccion() + ", getCorreoElectronico()=" + getCorreoElectronico() + ", getFechaNacimiento()="
				+ getFechaNacimiento() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carroCompras == null) ? 0 : carroCompras.hashCode());
		return result;
	}

	/*
	 * metodo que valida si el usuario y contraseña que entran como parametro son los de este cliente
	 */
	public boolean validar(String user, String passwd) {
		if(getNombre().equals(user) && getContrasenia().equals(passwd))
			return true;
		return false;
	}


	public ArrayList<Producto> getProductosCarrito() {
		return carroCompras.getListaProductosCarrosCompras();
	}


	public boolean validarIdentidad(String identificacion) {
		if(getIdentificacion().equals(identificacion))
			return true;
		return false;
	}
	public boolean hasFacturas() {
		if(listaFacturas.size()!=0)
			return true;
		return false;
	}
	/*
	 * metodo que agrega un producto al carro de compras, en
	 * caso de que el producto exista solo suma las existencias
	 */
	public void agregarProductoCarrito(Producto productoSeleccionado2) {
		carroCompras.agregarProducto(productoSeleccionado2);
		
	}
	/*
	 * metodo que elimina un producto del carrito
	 */
	public void eliminarProductoCarrito(Producto productoSeleccionado2) {
		carroCompras.eliminarProducto(productoSeleccionado2);
		
	}
	public void agregarFactura(Factura facturaAux) {
		this.listaFacturas.add(facturaAux);
		
	}




	
	
	
}
