package application.model;

import java.util.ArrayList;

public class CarroCompras {

	private ArrayList<String> listaProductosCarrosCompras;

	public CarroCompras(ArrayList<String> listaProductosCarrosCompras) {
		super();
		this.listaProductosCarrosCompras = listaProductosCarrosCompras;
	}

	public ArrayList<String> getListaProductosCarrosCompras() {
		return listaProductosCarrosCompras;
	}

	public void setListaProductosCarrosCompras(ArrayList<String> listaProductosCarrosCompras) {
		this.listaProductosCarrosCompras = listaProductosCarrosCompras;
	}

	@Override
	public String toString() {
		return "CarroCompras [listaProductosCarrosCompras=" + listaProductosCarrosCompras + "]";
	}
	
	
}
