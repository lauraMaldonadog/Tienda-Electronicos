package application.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import application.exception.AdminNoEncontradoException;
import application.exception.ClienteExisteException;
import application.exception.ClienteNoEncontradoException;
import application.exception.ProductoExisteException;

public class Empresa {

	//ATRIBUTOS DE LA CLASE
	
	private Administrador root;
	private String nombre;
	private String nit;
	private ArrayList<Sede> listaSedes;
	private ArrayList<Producto> listaGenralProductos;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Ciudad> listaCiudadesRegistradas;

	public Empresa(Administrador root,String nombre,String nit) {
		super();
		this.root = root;
		this.nombre = nombre;
		this.nit = nit;
		this.listaSedes = new ArrayList<>();
		this.listaGenralProductos = new ArrayList<>();
		this.listaClientes = new ArrayList<>();
	}
	
	public ArrayList<Ciudad> getListaCiudadesRegistradas() {
		return listaCiudadesRegistradas;
	}

	public void setListaCiudadesRegistradas(ArrayList<Ciudad> listaCiudadesRegistradas) {
		this.listaCiudadesRegistradas = listaCiudadesRegistradas;
	}

	public Administrador getRoot() {
		return root;
	}
	public void setRoot(Administrador root) {
		this.root = root;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public ArrayList<Sede> getListaSedes() {
		return listaSedes;
	}
	public void setListaSedes(ArrayList<Sede> listaSedes) {
		this.listaSedes = listaSedes;
	}

	public ArrayList<Producto> getListaGenralProductos() {
		return listaGenralProductos;
	}
	public void setListaGenralProductos(ArrayList<Producto> listaGenralProductos) {
		this.listaGenralProductos = listaGenralProductos;
	}
	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	@Override
	public String toString() {
		return "Empresa [root=" + root + ", nombre=" + nombre + ", nit=" + nit + ", listaSedes=" + listaSedes
				+ ", listaGenralProductos=" + listaGenralProductos + ", listaClientes=" + listaClientes + "]";
	}
	/*
	 * metodo que quema los datos de las sedes(una por ciudad)
	 */
	public void quemarDatosSedes() {
		try {
			int contador=1;
			this.listaCiudadesRegistradas=obtenerCiudades();
			for (Ciudad ciudad : listaCiudadesRegistradas) {
				Sede sedeAux= new Sede(ciudad.getNombre(), new Administrador(ciudad.getNombre(),"899"+contador, "cra -22"+contador,""+ciudad.getNombre()+"@gmail.com",null,"1234"), ciudad);
				if(contador<4)
					sedeAux.quemarObjetos();
				contador++;
				listaSedes.add(sedeAux);
			}
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private ArrayList<Ciudad> obtenerCiudades() throws IOException {
		ArrayList<Ciudad> listaCiudades= new ArrayList<Ciudad>();


		String[]  contenido;
		FileReader fr=new FileReader("src/application/resources/ciudades/ciudades.txt");
		BufferedReader bfr=new BufferedReader(fr);
		String linea="";
		while((linea = bfr.readLine())!=null) 
		{
			contenido=linea.split(",");
			listaCiudades.add(new Ciudad(contenido[2], contenido[1], contenido[0]));
		}
		bfr.close();
		fr.close();
		return listaCiudades;
	}
	public void configurarAdmins() {
			for (Sede sedeAux : listaSedes) {
				sedeAux.setearAdmin(sedeAux);
			}		
	}
	/*
	 * metodo que quema los datos de los clientes
	 */
	public void quemarDatosClientes() {
		listaClientes.add(new Cliente("selvas", "14378", "cr 234 ", "selvas@gmail.com", null, "1234"));
		listaClientes.add(new Cliente("laura", "12378", "cr 634 ", "laura@gmail.com", null, "44234"));
		listaClientes.add(new Cliente("raquel", "92378", "cr 664 ", "raquel@gmail.com", null,"49234"));
		
	}
	/*
	 * metodo que busca los datos de un administrador en todas las sedes registradas
	 */
	public Administrador obtenerAdmin(String user, String passwd) throws AdminNoEncontradoException {
		for (Sede sede : listaSedes) {
			if(sede.validarAdmin(user,passwd))
				return sede.getAdministrador();
		}
		throw new AdminNoEncontradoException("el admin "+user+" no ha sido encontrado");
	}
	/*
	 * metodo que obtiene todos los productos de una sede en particular
	 */
	public ArrayList<Producto> getListaProductos(Sede sede) {
		for (Sede sedeAux : listaSedes) {
			if(sedeAux.equals(sede))
				return sedeAux.getListaProductos();
		}
		return null;
	}
	/*
	 * metodo que busca un cliente
	 */
	public Cliente obtenerCliente(String user, String passwd) throws ClienteNoEncontradoException {
		for (Cliente cliente : listaClientes) {
			if(cliente.validar(user,passwd))
				return cliente;
			
		}
		throw new ClienteNoEncontradoException("el cliente "+user+" no ha sido encontrado");
	}
	public ArrayList<Ciudad> getCiudadesRegistradas() {
		return listaCiudadesRegistradas;
	}

	/*
	 * metodo que agrega un cliente a la lista de clientes, el metodo revisa si el cliente ya exite en la empresa, si existe 
	 * genera una excepcion, si  no lo agrega
	 */
	public void agregarCliente(Cliente cliente) throws ClienteExisteException {
		for (Cliente clienteAux : listaClientes) {
			if(clienteAux.validar(cliente.getNombre(), cliente.getContrasenia()))
			{
				throw new ClienteExisteException("El cliente ya está registrado");
			}
		}
		listaClientes.add(cliente);
		
	}

	public void agregarProducto(Producto producto, Sede sedeAdmin) throws ProductoExisteException {
		for (Sede sede : listaSedes) {
			if(sede.equals(sedeAdmin))
				sede.agregarProducto(producto);
		}
	}

	public void actualizarCliente(Cliente cliente, Cliente clienteSeleccionado) throws ClienteExisteException {
		if(!cliente.validarIdentidad(clienteSeleccionado.getIdentificacion())){
			for (Cliente clienteLista : listaClientes) {
				if(clienteLista.validarIdentidad(cliente.getIdentificacion()))
					throw new ClienteExisteException("la identifiacion ya está registrada");
			}			
		}

		for (Cliente clienteLista : listaClientes) {
			if(clienteLista.equals(clienteSeleccionado))
				clienteLista.actualizarDatos(cliente);
		}

	}

	public void actualizarProducto(Sede sedeAdmin, Producto producto, Producto productoSeleccionado) throws ProductoExisteException {
		for (Sede sede : listaSedes) {
			if(sede.equals(sedeAdmin))
				sede.actualizarProducto(producto,productoSeleccionado);
		}
		
	}

	public void eliminarCliente(Cliente clienteSeleccionado) {
		listaClientes.remove(clienteSeleccionado);
	}

	public boolean hasVentasAsociadas(Sede sede, Producto productoSeleccionado) {
		for (Sede sedeLista : listaSedes) {
			if(sedeLista.equals(sede))
			{
				return sedeLista.hasVentasAsociadas(productoSeleccionado);
			}
		}
		return false;
	}

	public void eliminarProducto(Sede sede, Producto productoSeleccionado) {
		for (Sede sedeLista : listaSedes) {
			if(sedeLista.equals(sede))
			{
				sedeLista.eliminarProducto(productoSeleccionado);
			}
		}

	}

	public Sede obtenerSede(Ciudad ciudadSeleccionada) {
		ArrayList<Sede> listaCoincidencias= new ArrayList<>();
		String lsitaString="";
		int contador=0;

		for (Sede sede : listaSedes) {
			if(sede.validarCiudad(ciudadSeleccionada))
			{
				listaCoincidencias.add(sede);
			}
		}

		if(listaCoincidencias.size()==1)
			return listaCoincidencias.get(0);
		
		for (Sede sede : listaCoincidencias) {
			lsitaString+="nombre sede:"+sede.getNombre()+" admin :"+sede.getAdministrador()+" pos :"+contador;
			contador++;
		}
		
		while(true){
			
			try {
				int seleccion=Integer.parseInt(JOptionPane.showInputDialog(lsitaString));
				if(seleccion<=contador && seleccion>0)
					return listaCoincidencias.get(seleccion);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
	}

	public int obtenerCodigoFactura(Sede sedeAux) {
		for (Sede sede : listaSedes) {
			if(sede.equals(sedeAux))
				return sede.getNumFactura();		
		}
		return 0;
	}


}
