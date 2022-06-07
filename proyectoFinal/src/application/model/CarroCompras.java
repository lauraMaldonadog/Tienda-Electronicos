package application.model;

import java.util.ArrayList;

public class CarroCompras {

	private ArrayList<Producto> listaProductosCarrosCompras;

	public CarroCompras() {
		super();
		this.listaProductosCarrosCompras= new ArrayList<>();
	}

	public ArrayList<Producto> getListaProductosCarrosCompras() {
		return listaProductosCarrosCompras;
	}

	public void setListaProductosCarrosCompras(ArrayList<Producto> listaProductosCarrosCompras) {
		this.listaProductosCarrosCompras = listaProductosCarrosCompras;
	}

	@Override
	public String toString() {
		return "CarroCompras [listaProductosCarrosCompras=" + listaProductosCarrosCompras + "]";
	}

	public void agregarProducto(Producto productoSeleccionado2) {
		boolean agregado=false;
			for (Producto producto : listaProductosCarrosCompras) {
				if(producto.validarIdentidad(productoSeleccionado2.getCodigo()))
				{
					producto.sumarExistencias(productoSeleccionado2.getExistencias());
					agregado=true;
					break;
				}
			}
			if(agregado==false)
				listaProductosCarrosCompras.add(productoSeleccionado2);
		
	}

	public void eliminarProducto(Producto productoSeleccionado2) {
		listaProductosCarrosCompras.remove(productoSeleccionado2);
		
	}

	public Double getSubtotal() {
		double subTotal=0.0;
		for (Producto producto : listaProductosCarrosCompras) {
			subTotal+=producto.getPrecio()*producto.getExistencias();
		}
		return subTotal;
	}

	public Integer getCantArticulos() {
		int cantTotalArticulos=0;
		for (Producto producto : listaProductosCarrosCompras) {
			cantTotalArticulos+=producto.getExistencias();
			
		}
		return cantTotalArticulos;
	}
	
	
}
