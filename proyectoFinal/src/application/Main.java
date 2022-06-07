package application;
	
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import application.controller.AdminController;
import application.controller.ClientController;
import application.controller.EnvioController;
import application.controller.FormularioRegistroController;
import application.controller.LoginController;
import application.exception.AdminNoEncontradoException;
import application.exception.ClienteExisteException;
import application.exception.ClienteNoEncontradoException;
import application.exception.ProductoExisteException;
import application.model.Administrador;
import application.model.Ciudad;
import application.model.Cliente;
import application.model.Empresa;
import application.model.Producto;
import application.model.Sede;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private Stage secundatyStage;
	private Empresa empresa= new Empresa(new Administrador("root", "0000", "-|-", "....",null,null),"tecnologia","7755");
	
	@Override
	public void start(Stage primaryStage) {
		try {
			empresa.quemarDatosSedes();
			empresa.configurarAdmins();
			empresa.quemarDatosClientes();
			this.primaryStage = primaryStage;
			this.secundatyStage=new Stage();

			this.primaryStage.setTitle("Login");
//			mostrarVentanaFormularioRegistro();
//			mostrarVentanaEnvio();
//			mostrarVentanaAdmin();
//			mostraVentanaCliente();
			mostrarVentanaLogin();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarVentanaFormularioRegistro() {
		try {
			
			secundatyStage.setTitle("Formulario registro");
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/FormularioRegistroView.fxml"));
			AnchorPane rootLayout=(AnchorPane) loader.load();
			
			FormularioRegistroController controller=loader.getController();
			controller.setMain(this);
			Scene escena= new Scene(rootLayout);
			primaryStage.setScene(escena);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mostrarVentanaEnvio(Cliente cliente, int idFactura, Sede sede) {
		try {
			secundatyStage.setTitle("Gestion Envio");
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/EnvioView.fxml"));
			AnchorPane rootLayout=(AnchorPane) loader.load();
			
			EnvioController controller=loader.getController();
			controller.setMain(this,cliente,idFactura,sede);
			Scene escena= new Scene(rootLayout);
			primaryStage.setScene(escena);
			primaryStage.show();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mostrarVentanaAdmin(Administrador admin) {
		try {
			
			secundatyStage.setTitle("Gestion Administrador");
			
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/AdminView.fxml"));
			AnchorPane rootLayout=(AnchorPane) loader.load();
			
			AdminController controller=loader.getController();
			controller.setMain(this,admin);
			Scene escena= new Scene(rootLayout);
			primaryStage.setScene(escena);
			primaryStage.show();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		
		}
	}

	public void mostrarVentanaCliente(Cliente cliente) {
		try {
			secundatyStage.setTitle("Gestion Cliente");
			
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ClientView.fxml"));
			AnchorPane rootLayout=(AnchorPane) loader.load();
			
			ClientController controller=loader.getController();
			controller.setMain(this,cliente);
			Scene escena= new Scene(rootLayout);
			primaryStage.setScene(escena);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
	}

	public void mostrarVentanaLogin() throws IOException {
		
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/LoginView.fxml"));
		AnchorPane rootLayout=(AnchorPane) loader.load();
		
		LoginController controller=loader.getController();
		controller.setMain(this);
		Scene escena= new Scene(rootLayout);
		primaryStage.setScene(escena);
		primaryStage.show();
	
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void cargarAdmin(String user, String passwd) throws AdminNoEncontradoException {
		Administrador admin=obtenerAdmin(user,passwd);
		mostrarVentanaAdmin(admin);
		
	}

	private Administrador obtenerAdmin(String user, String passwd) throws AdminNoEncontradoException {
		return empresa.obtenerAdmin(user,passwd);
	}

	public void cargarCliente(String user, String passwd) throws ClienteNoEncontradoException {
		Cliente cliente=obtenerCliente(user,passwd);
		mostrarVentanaCliente(cliente);
		
	}

	/*
	 * metodo que obtiene un cliente con su user y clave
	 */
	private Cliente obtenerCliente(String user, String passwd) throws ClienteNoEncontradoException {
		return empresa.obtenerCliente(user,passwd);
	}

	public ArrayList<Cliente> obtenerClientesRegistrados() {
		return empresa.getListaClientes();
	}

	public ArrayList<Producto> obtenerProductosRegistrados(Sede sede) {
		return empresa.getListaProductos(sede);
	}

	public ArrayList<Sede> obtenerSedesRegistradas() {
		return empresa.getListaSedes();
	}

	public ArrayList<Ciudad> getCiudadesRegistradas() {
		return empresa.getCiudadesRegistradas();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Stage getSecundatyStage() {
		return secundatyStage;
	}

	public void setSecundatyStage(Stage secundatyStage) {
		this.secundatyStage = secundatyStage;
	}

	/*
	 * metodo que agrega un cliente a la empresa
	 */
	public void agregarCliente(Cliente cliente) throws ClienteExisteException {
		empresa.agregarCliente(cliente);
		
	}

	/*
	 * metodo que agrega un producto a la empresa
	 */
	public void agregarProducto(Producto producto, Sede sedeAdmin) throws ProductoExisteException {
		empresa.agregarProducto(producto,sedeAdmin);
		
	}

	public void actualizarCliente(Cliente cliente, Cliente clienteSeleccionado) throws ClienteExisteException {
		empresa.actualizarCliente(cliente,clienteSeleccionado);
		
	}

	public void actualizarProducto(Sede sedeAdmin, Producto producto, Producto productoSeleccionado) throws ProductoExisteException {
		empresa.actualizarProducto(sedeAdmin,producto,productoSeleccionado);
		
	}

	public void eliminarCliente(Cliente clienteSeleccionado) {
		empresa.eliminarCliente(clienteSeleccionado);
		
	}

	public boolean hasVentasAsociadas(Sede sede, Producto productoSeleccionado) {
		return empresa.hasVentasAsociadas(sede,productoSeleccionado);
	}

	public void eliminarProducto(Sede sede, Producto productoSeleccionado) {
		empresa.eliminarProducto(sede,productoSeleccionado);
		
	}

	public Sede obtenerSede(Ciudad ciudadSeleccionada) {
		return empresa.obtenerSede(ciudadSeleccionada);
		
	}

	public int obtenerCodigoFactura(Sede sede) {
		return empresa.obtenerCodigoFactura(sede);
	}
	
}
