package application.controller;



import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Main;
import application.exception.AdminNoEncontradoException;
import application.model.Ciudad;
import application.model.Cliente;
import application.model.Factura;
import application.model.Producto;
import application.model.Sede;
import application.model.TipoProducto;
import application.model.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ClientController implements Initializable {
	
	Main main;
	Cliente cliente;
	Sede sede;
	//booleano que indica que hay que actualizar la tabla producto
	boolean actualizar=false;
	// objetos seleccionados de las tablas
	Ciudad ciudadSeleccionada=null;
	Producto productoSeleccionado=null;
	Producto productoCarritoSeleccionado=null;

	//listas filtradas
	
	FilteredList<Ciudad> filteredCiudadesData;
	FilteredList<Producto> filteredProductoData;
	FilteredList<Producto> filteredProductoCarritoData;
	
	ObservableList<Ciudad> listaCiudadesRegistradasData = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosRegistradosData = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosCarritoData = FXCollections.observableArrayList();
	ObservableList<Factura> listaFacturasData= FXCollections.observableArrayList();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Producto, Integer> columnCantidadMiCarrito;

    @FXML
    private TableColumn<Sede, String> columnCodigoMiTienda;

    @FXML
    private TextField txtBuscarProducto;

    @FXML
    private TableColumn<Factura, String> columnCodigo;

    @FXML
    private TextField txtTotalMiCarrito;
    
    @FXML
    private TextField txtNombreMiCarrito;
    
    @FXML
    private Button btnCerrarSesion;

    @FXML
    private TableColumn<Producto, TipoProducto> columnCategoriaProducto;

    @FXML
    private TableColumn<Producto, Integer> columnCantidadProducto;


    @FXML
    private TableColumn<Ciudad, String> columnCiudadMiTienda;

    @FXML
    private TextField txtArticulosMiCarrito;

    @FXML
    private TableColumn<Producto, Double> columnPrecioMiCarrito;

    @FXML
    private TextField txtFiltroCiudad;

    @FXML
    private Label lblSedeActualProductos;

    @FXML
    private TableColumn<Factura, Double> columnTotalFactura;

    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblNombreUsuarioCuenta;
    @FXML
    private Label lblDireccionClienteCuenta;
    @FXML
    private Label lblCiudadResidenciaCuenta;
    
    @FXML
    private Label lblIdentificacionCuenta;
    @FXML
    private Label lblCorreoCuenta;

    @FXML
    private Label lblSedeActual;

    @FXML
    private TableColumn<Ciudad, String> columnDepartamentoMiTienda;

    @FXML
    private TableColumn<Producto, Image> columnImgenProducto;

    @FXML
    private TableColumn<Producto, String> columnNombreProducto;


    @FXML
    private TableColumn<Factura, DatePicker> coumnFecha;

    @FXML
    private TableColumn<Producto, Double> columnPrecioProducto;

    @FXML
    private TableView<Ciudad> tableCiudades;
    
    @FXML
    private TableView<Producto> tableProductos;

    @FXML
    private TableColumn<Factura, String> columnDireccion;
    
    @FXML
    private TableView<Producto> tableProductosCarrito;

    @FXML
    private TableView<Factura> tablaFacturas;
    
    @FXML
    private Button btnComprar;

    @FXML
    private TableColumn<Producto, String> columnNombreMiCarrito;


    @FXML
    void comprarAction(ActionEvent event) {
    	comprar();

    }


	@FXML
    void cerrarSesionAction(ActionEvent event) {
    	try {
			main.mostrarVentanaLogin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//configuramos las columnas de la tabla  mi tienda
		columnCiudadMiTienda.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnDepartamentoMiTienda.setCellValueFactory(new PropertyValueFactory<>("departamento"));
		columnCodigoMiTienda.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		//configuramos las columnas de la tabla productos
		columnImgenProducto.setCellValueFactory(new PropertyValueFactory<>("fotoProducto"));
		columnNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnCantidadProducto.setCellValueFactory(new PropertyValueFactory<>("existencias"));
		columnCategoriaProducto.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		//configuramos las columnas de la tabla mi carrito
		columnNombreMiCarrito.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnCantidadMiCarrito.setCellValueFactory(new PropertyValueFactory<>("existencias"));
		columnPrecioMiCarrito.setCellValueFactory(new PropertyValueFactory<>("precio"));
		//configuramos las columnas de la tabla mi cuenta
		coumnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		columnTotalFactura.setCellValueFactory(new PropertyValueFactory<>("total"));
		
		//seteamos los objetos seleccionados en la seccion mi tienda
		tableCiudades.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			ciudadSeleccionada = newSelection;
			if(ciudadSeleccionada!=null)
			{
				if(mostrarMensajeConfirmacion("desea actualizar su sede?")){
									
					actualizarSede();
					mostrarDatosSede();
				
				}
				ciudadSeleccionada=null;
				
			}
			
		});
		tableProductos.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			productoSeleccionado = newSelection;
			if(productoSeleccionado!=null){
				agregarProductoCarrito(productoSeleccionado);
				refrescarMiCarrito();
				tableProductos.refresh();
				tableProductosCarrito.refresh();
				actualizarResumen();
				productoSeleccionado=null;
			}

		});

		tableProductosCarrito.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			
		});
		
		//seccion donde se configuran los bindings para la busqueda automatica de gestion cliente
		filteredCiudadesData = new FilteredList<>(listaCiudadesRegistradasData, p -> true);

		txtFiltroCiudad.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredCiudadesData.setPredicate(ciudad-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (ciudad.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (ciudad.getDepartamento().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		//seccion donde se configuran los bindings para la busqueda automatica de gestion producto 
		filteredProductoData = new FilteredList<>(listaProductosRegistradosData, p -> true);

		txtBuscarProducto.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredProductoData.setPredicate(producto-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (producto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if ((""+producto.getCategoria()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				} else if ((""+producto.getPrecio()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				} 
				return false; // Does not match.
			});
		});
		//seccion donde se configuran los bindings para la busqueda automatica de gestion producto carro compra 
		filteredProductoCarritoData = new FilteredList<>(listaProductosCarritoData, p -> true);

		txtNombreMiCarrito.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredProductoCarritoData.setPredicate(producto-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (producto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (producto.getCodigo().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});

	}

	


	private void actualizarResumen() {
		Double subTotal=cliente.getCarroCompras().getSubtotal();
		Integer cantArticulos=cliente.getCarroCompras().getCantArticulos();
		
		txtTotalMiCarrito.setText(""+subTotal);
		txtArticulosMiCarrito.setText(""+cantArticulos);
	}


	/*
	 * metodo que elimina un producto del carrito
	 */
	private void eliminarProductoCarrito(Producto productoSeleccionado2) {
			try {
					sede.reintegro(productoSeleccionado2.getCodigo(),productoSeleccionado2.getExistencias());
					cliente.eliminarProductoCarrito(productoSeleccionado2);

			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	@FXML
    void eliminarProductoCarritoAction(MouseEvent event) {
		
		productoCarritoSeleccionado = tableProductosCarrito.getSelectionModel().getSelectedItem();
		if(productoCarritoSeleccionado!=null){
			if(Utilidades.mostrarMensajeConfirmacion("Desea eliminar este producto a su carrito?\n nombre: "+productoCarritoSeleccionado.getNombre())){

				eliminarProductoCarrito(productoCarritoSeleccionado);
				refrescarMiCarrito();
				tableProductosCarrito.refresh();
				tableProductos.refresh();
				actualizarResumen();
				productoCarritoSeleccionado=null;

			}
		}
    }
	

	private void agregarProductoCarrito(Producto productoSelect) {
		
		if(Utilidades.mostrarMensajeConfirmacion("Desea agregar este producto a su carrito?\n"+productoSelect.getNombre())){
			try {
				int cantidad=obtenerCantidadProducto("Ingrese la cantidad ");
				if(cantidad<=productoSelect.getExistencias() && cantidad>0){
					Producto productoAux= new Producto(productoSelect.getNombre(),
							productoSelect.getCodigo(), productoSelect.getPrecio(), "", productoSelect.getFotoProducto(),
							cantidad, productoSelect.getCategoria());
					cliente.agregarProductoCarrito(productoAux);
					productoSelect.reducirExistencias(cantidad);
					
					
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	private int obtenerCantidadProducto(String string) {
		int cantidad=Integer.parseInt(JOptionPane.showInputDialog(string));
		return cantidad;
	}

	public void setMain(Main main, Cliente cliente) {
		this.main=main;
		this.cliente=cliente;
		lblUsuario.setText("Usuario : "+cliente.getNombre());
		setearDatosUsuario(cliente);
		refrescarMiCarrito();
		//configuramos las tablas para que autoseteen los valores filtrados

		//tabla ciudades de la seccion mi tienda 
    	SortedList<Ciudad> sortedDataCiudades = new SortedList<>(filteredCiudadesData);

    	sortedDataCiudades.comparatorProperty().bind(tableCiudades.comparatorProperty());
    	tableCiudades.setItems(sortedDataCiudades);
    	//tabla productos de la seccion productos 
    	SortedList<Producto> sortedDataProductos = new SortedList<>(filteredProductoData);

    	sortedDataProductos.comparatorProperty().bind(tableProductos.comparatorProperty());
    	tableProductos.setItems(sortedDataProductos);
    	
    	//tabla productos de la seccion productos  carrito compra
    	SortedList<Producto> sortedDataProductosCarrito = new SortedList<>(filteredProductoCarritoData);

    	sortedDataProductosCarrito.comparatorProperty().bind(tableProductosCarrito.comparatorProperty());
    	tableProductosCarrito.setItems(sortedDataProductosCarrito);
	
    	refrescarTablaCiudades();
    	refrescarTablaFacturas();
    	



		
	}


	private void refrescarTablaFacturas() {
	
		listaFacturasData.clear();
		listaFacturasData.addAll(cliente.getListaFacturas());
		tablaFacturas.getItems().clear();
		tablaFacturas.setItems(listaFacturasData);
	}


	private void refrescarMiCarrito() {
		listaProductosCarritoData.clear();
		listaProductosCarritoData.addAll(cliente.getProductosCarrito());
	}



	private void refrescarTablaCiudades() {		
		listaCiudadesRegistradasData.clear();
		listaCiudadesRegistradasData.addAll(main.getCiudadesRegistradas());

		listaFacturasData.clear();
		listaFacturasData.addAll(cliente.getListaFacturas());
			
		
	}

	/*
	 * metodo de confirmacion
	 */
	private boolean mostrarMensajeConfirmacion(String mensaje) {
		Alert alert  = new Alert (Alert.AlertType.CONFIRMATION);
		alert.setTitle(null);
		alert.setHeaderText("Confirmacion");
		alert.setContentText(mensaje);
		Optional<ButtonType> action = alert.showAndWait();

		if(action.get() == ButtonType.OK){
			return true;

		}else{
			return false;
		}
	}

	
	private void actualizarSede() {
		sede=main.obtenerSede(ciudadSeleccionada);
	}
	private void mostrarDatosSede() {
		lblSedeActual.setText(sede.getNombre());
		lblSedeActualProductos.setText(sede.getNombre());
	
		refrescarTablaProductos();
		
	}

	private void refrescarTablaProductos() {
		listaProductosRegistradosData.clear();
		listaProductosRegistradosData.addAll(sede.getListaProductos());
	}

	private void setearDatosUsuario(Cliente client) {
		lblNombreUsuarioCuenta.setText(client.getNombre());
		lblDireccionClienteCuenta.setText(client.getDireccion());
		lblIdentificacionCuenta.setText(client.getIdentificacion());
		lblCorreoCuenta.setText(client.getCorreoElectronico());
	}

	/**
	 * metodo que realiza una compra
	 */
    private void comprar() {
    	int idFactura=main.obtenerCodigoFactura(sede);
    	main.mostrarVentanaEnvio(cliente,idFactura,sede);
    	
		
	}
}
