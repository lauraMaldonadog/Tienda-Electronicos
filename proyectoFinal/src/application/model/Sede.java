package application.model;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import application.exception.ProductoExisteException;
import javafx.scene.image.Image;

public class Sede {

	String nombre;
	Administrador administrador;
	ArrayList<Factura> listaFacturas;
	ArrayList<Producto> listaProductos;
	ArrayList<Envio> listaEnvios;
	Ciudad ciudad;
	Integer numFactura;
	public Sede(String nombre, Administrador administrador,  Ciudad ciudad) {
		super();
		this.nombre = nombre;
		this.administrador = administrador;
		this.listaFacturas = new ArrayList<>();
		this.listaProductos = new ArrayList<>();
		this.listaEnvios =  new ArrayList<>();
		this.numFactura=100000;
		this.ciudad = ciudad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	public ArrayList<Factura> getListaFacturas() {
		return listaFacturas;
	}
	public void setListaFacturas(ArrayList<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public ArrayList<Envio> getListaEnvios() {
		return listaEnvios;
	}
	public void setListaEnvios(ArrayList<Envio> listaEnvios) {
		this.listaEnvios = listaEnvios;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	@Override
	public String toString() {
		return "Sede [nombre=" + nombre + ", administrador=" + administrador + ", listaFacturas=" + listaFacturas
				+ ", listaProductos=" + listaProductos + ", listaEnvios=" + listaEnvios + ", ciudad=" + ciudad + "]";
	}
	public void quemarObjetos() {
			listaProductos.add(new Producto("celular", "1234",25000.0, "es un celular", new Image("application/resources/png/productos/celular.png"), 15, TipoProducto.TECNOLOGIA_MOVIL));
			listaProductos.add(new Producto("computador", "5555",35000.0, "es un computador", new Image("application/resources/png/productos/computador.png"), 10, TipoProducto.TECNOLOGIA_COMPUTACIONAL));
			listaProductos.add(new Producto("bafles", "3555",15000.0, "es un bafle", new Image("application/resources/png/productos/bafles.png"), 10, TipoProducto.MUSICA));
			listaProductos.add(new Producto("cama", "5585",105000.0, "es una cama", new Image("application/resources/png/productos/cama.png"), 10, TipoProducto.HOGAR));
			listaProductos.add(new Producto("excel", "5586",5000.0, "es una licencia", new Image("application/resources/png/productos/excel.png"), 3, TipoProducto.EMPRESARIALES));
		
	}
	public void setearAdmin(Sede sedeAux) {
		this.administrador.setSede(sedeAux);
		
	}
	public boolean validarAdmin(String user, String passwd) {
		if(administrador.validarDatos(user,passwd))
			return true;
		return false;
	}
	/*
	 * metodo que agrega un producto
	 */
	public void agregarProducto(Producto productoAux) throws ProductoExisteException {
		for (Producto producto : listaProductos) {
			if(producto.verificar(productoAux.getNombre(),productoAux.getCodigo()))
				throw new ProductoExisteException("el producto de nombre "+productoAux.getNombre()+"ya se encuentra registrado");
		}
		listaProductos.add(productoAux);
		
	}
	/*
	 * metodo que actualiza un producto
	 */
	public void actualizarProducto(Producto producto, Producto productoSeleccionado) throws ProductoExisteException {
		if(!producto.validarIdentidad(productoSeleccionado.getCodigo())){
			for (Producto productoLista : listaProductos) {
				if(productoLista.validarIdentidad(producto.getCodigo()))
					throw new ProductoExisteException("El codigo ya está registrado");
			}			
		}

		for (Producto productoLista : listaProductos) {
			if(productoLista.equals(productoSeleccionado))
				productoLista.actualizarDatos(producto);
		}

		
	}
	public boolean hasVentasAsociadas(Producto productoSeleccionado) {
		for (Factura fact :listaFacturas) {
			if(fact.contiene(productoSeleccionado))
				return true;
		}
		return false;
	}

	public void eliminarProducto(Producto productoSeleccionado) {
		listaProductos.remove(productoSeleccionado);
		
	}
	/*
	 * metodo que valida si la ciudad que entra como parametro es la misma que la de la instancia
	 */
	public boolean validarCiudad(Ciudad ciudadSeleccionada) {
		if(this.ciudad.equals(ciudadSeleccionada))
			return true;
		return false;
	}
	/*
	 * metodo que hace el reintegro de un producto
	 */
	public void reintegro(String codigoAux, Integer existencias) {
		for (Producto producto : listaProductos) {
			if(producto.validarIdentidad(codigoAux))
				producto.sumarExistencias(existencias);
		}
		
	}
	public Integer getNumFactura() {
		return numFactura;
	}
	public void setNumFactura(Integer numFactura) {
		this.numFactura = numFactura;
	}
	public void agregarFactura(Factura facturaAux) {
		this.listaFacturas.add(facturaAux);
		this.numFactura++;
		
	}
	public void agregarEnvio(Envio envio) {
		this.listaEnvios.add(envio);
		
	}
	public ArrayList<Factura> getFacturasPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		ArrayList<Factura> listaFacturasFiltradas=new ArrayList<>();
		for (Factura factura : listaFacturas) {
				if(fechaInicio.compareTo(factura.getFecha())<0 && fechaFin.compareTo(factura.getFecha())>0 )
				listaFacturasFiltradas.add(factura);
			
		}
		return listaFacturasFiltradas;
	}
	public Cliente getclienteEstrella() {
		Cliente clienteEstrella=null;
		ArrayList<Cliente> listaClientesFactura=obtenerClientesFactura();
		ArrayList<Integer> listaTotalPorCliente=obtenerTotalPorCliente(listaClientesFactura);
		
		for (int i = 0; i < listaTotalPorCliente.size()-1; i++) {
			if(listaTotalPorCliente.get(i)>listaTotalPorCliente.get(i+1))
				clienteEstrella=listaClientesFactura.get(i);
		}
		
		return clienteEstrella ;
	}
	private ArrayList<Integer> obtenerTotalPorCliente(ArrayList<Cliente> listaClientesFactura) {
		ArrayList<Integer> listaTotalPorCliente= new ArrayList<>();
		
		for (Cliente cliente : listaClientesFactura) {
			int total=0;
			for (Factura factura :listaFacturas) {
				if(factura.validarClienteAsosiado(cliente))
					total+=factura.getTotal();
			}
			listaTotalPorCliente.add(total);
		}
		
		return listaTotalPorCliente;
	}
	private ArrayList<Cliente> obtenerClientesFactura() {
		ArrayList<Cliente> listaClientesFactura=new ArrayList<>();
		for (Factura factura : listaFacturas) {
			Cliente clienteAsociado=factura.getClienteAsosiado();
			if(!listaClientesFactura.contains(clienteAsociado))
				listaClientesFactura.add(clienteAsociado);
			
		}
		return listaClientesFactura;
	}
	public LocalDate obtenerfechaProductoMasVendido(String idProducto) {
		LocalDate fechaProductoMasVendido=null;
		int  cantidadProductoMasVendido=-1;
		int  cantidadAux=-1;
		for (Factura factura : listaFacturas) {
			if(factura.containsProductoID(idProducto)){
				cantidadAux=factura.getCantidadProducto(idProducto);
				if(cantidadProductoMasVendido<cantidadAux){
					fechaProductoMasVendido=factura.getFecha();
					cantidadProductoMasVendido=cantidadAux;
				}
			}
		}
		return fechaProductoMasVendido;
	}
	public ArrayList<Producto> getProductosMasVendidos() {
		ArrayList<Producto> listaProductosMasVendidos= new ArrayList<>();

		ArrayList<Producto> listaProductosFactura=obtenerProductosFactura();
		ArrayList<Integer>   listaCantidades=obtenerCantidadesProductoFactura(listaProductosFactura);
		
		listaProductosMasVendidos=filtrarProductosMasVendidos(listaProductosFactura,listaCantidades);
		return listaProductosMasVendidos;
	}
	private ArrayList<Producto> filtrarProductosMasVendidos(ArrayList<Producto> listaProductosFactura,
			ArrayList<Integer> listaCantidades) {

		ArrayList<Producto> listaProductosMasVendidos=new ArrayList<>();
		Producto productoAux=null;
		int cantAux;
		
		for (int i = 0; i < 3; i++) {
			cantAux=-1;
			for (int j = 0; j < listaCantidades.size()-1 ; j++) {
				if(!listaProductosMasVendidos.contains(listaProductosFactura.get(j)) && !listaProductosMasVendidos.contains(listaProductosFactura.get(j+1)) ){
					if(listaCantidades.get(j)>cantAux){
						productoAux=listaProductosFactura.get(j);
						cantAux=listaCantidades.get(i);

					}
				}
			}			
			listaProductosMasVendidos.add(productoAux);
		}
		return listaProductosMasVendidos;
	}
	private ArrayList<Integer> obtenerCantidadesProductoFactura(ArrayList<Producto> listaProductosFactura) {
		ArrayList<Integer> listaCantidades= new ArrayList<>();
		for (Producto producto : listaProductosFactura) {
			int cantidad=0;
			for (Factura factura : listaFacturas) {
				if(factura.containsProductoID(producto.getCodigo())){
					cantidad+=factura.getCantidadProducto(producto.getCodigo());
				}
			}
			listaCantidades.add(cantidad);
		}
		return listaCantidades;
	}
	private ArrayList<Producto> obtenerProductosFactura() {
		ArrayList<Producto> listaProductosFacturaFiltrados=new ArrayList<>();
		for (Factura factura :listaFacturas) {
			ArrayList<Producto> listaProductosFactura=factura.getListaProductos();
			for (Producto producto : listaProductosFactura) {
				if(!listaProductosFacturaFiltrados.contains(producto))
					listaProductosFacturaFiltrados.add(producto);
			}
			
		}
		return listaProductosFacturaFiltrados;
	}
}
