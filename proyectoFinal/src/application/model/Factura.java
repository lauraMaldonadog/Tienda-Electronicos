package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.DatePicker;

public class Factura {

	private String codigo;
	private LocalDate fecha;
	private double total; 
	private double subtotal;
	private double iva;
	private String direccion;
	ArrayList<Producto> listaProductos;
	private Cliente clienteAsosiado;
	
	public Factura(String codigo, LocalDate fecha, double total, double subtotal, double iva, String direccion,ArrayList<Producto> listaProductos,
				  Cliente cliente) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.total = total;
		this.subtotal = subtotal;
		this.iva = iva;
		this.direccion = direccion;
		this.listaProductos=listaProductos;
		this.clienteAsosiado=cliente;
	}
	
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Cliente getClienteAsosiado() {
		return clienteAsosiado;
	}

	public void setClienteAsosiado(Cliente clienteAsosiado) {
		this.clienteAsosiado = clienteAsosiado;
	}

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Factura [codigo=" + codigo + ", fecha=" + fecha + ", total=" + total + ", subtotal=" + subtotal
				+ ", iva=" + iva + ", direccion=" + direccion + ", listaProductos=" + listaProductos + "]";
	}

	public boolean contiene(Producto productoSeleccionado) {
		for (Producto producto : listaProductos) {
			if(producto.equals(productoSeleccionado))
				return true;
			
		}
		return false;
	}

	public boolean validarClienteAsosiado(Cliente cliente) {
		if(this.clienteAsosiado.equals(cliente))
			return true;
		return false;
	}

	public boolean containsProductoID(String idProducto) {
		for (Producto producto : listaProductos) {
			if(producto.validarIdentidad(idProducto))
				return true;
		}
		return false;
	}

	public int getCantidadProducto(String idProducto) {
		for (Producto producto : listaProductos) {
			if(producto.validarIdentidad(idProducto))
				return producto.getExistencias();
		}
		return 0;
	}

	
}
