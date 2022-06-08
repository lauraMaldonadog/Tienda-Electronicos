package application.controller;



import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Main;
import application.exception.DatosInvalidosClienteException;
import application.exception.DatosInvalidosProductoException;
import application.exception.DatosInvalidosReporteException;
import application.model.Administrador;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class AdminController implements Initializable{

	Main main;
	Administrador admin;
	Sede          sedeAdmin;
	//objetos seleccionados
	Cliente clienteGestionSeleccionado=null;
	Cliente clienteGestionFacturaSeleccionado=null;
	Producto productoGestionSeleccionado=null;
	Producto productoReporteAdicionalSeleccionado=null;
	Factura  facturaGestionFacturaSeleccionada=null;
	Factura facturaVentaSeleccionada=null;
	Sede     sedeVentaSeleccionada=null;
	Sede     nombreSucursalReporteAdicionalSeleccionado=null;
	
	//filtrado de objeros

	FilteredList<Cliente> filteredClienteGestioData;
	FilteredList<Producto> filteredProductosGestioData;
	FilteredList<Producto> filteredProductosReporteAdicionalData;
	FilteredList<Cliente> filteredClientesFacturasClientesData;
	FilteredList<Sede> filteredSedeReporteVentasData;
	FilteredList<Sede> filteredSedeReporteAdicionalData;
	//datas de las tablas
	ObservableList<Cliente> listaClientesRegistradosData = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosRegistradosData = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosRegistradosReporteAdicionalData = FXCollections.observableArrayList();
	ObservableList<Factura> listaFacturasEmpresaData = FXCollections.observableArrayList();
	ObservableList<Factura> listaFacturasClienteData = FXCollections.observableArrayList();
	ObservableList<Sede> listaSedesRegistradasData = FXCollections.observableArrayList();

	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtBuscarProducto;

    @FXML
    private TextField txtCorreoElectronicoCliente;

    @FXML
    private TableColumn<Factura, Double> columnSubtotalVentas;

    @FXML
    private TableColumn<Producto, Double> columnPrecioProductoGestion;

    @FXML
    private TableView<Producto> tablaGestionProductos;

    @FXML
    private TableColumn<Factura, Double> columnTotalVentas;

    @FXML
    private TableColumn<Cliente, String> columnDireccionGestionCliente;

    @FXML
    private TextField txtNombreClienteFactura;

    @FXML
    private TextField txtNombreProductoGestion;
    @FXML
    private Label lblNombreAdmin;
    @FXML
    private TextField txtCantidadDisponibleProductoGestion;

    @FXML
    private TableColumn<Producto, String> columnCodigoProductoGestion;

    @FXML
    private TableColumn<Factura, Double> columnIvaFacturaCliente;

    @FXML
    private TextField txtNombreSucursalReporteVenta;

    @FXML
    private TableColumn<Producto, String> columnNombreProductoReporteAdicional;

    @FXML
    private TextField txtCodigoProductoGestion;

    @FXML
    private TableColumn<Factura, Double> columnTotalFacturaCliente;

    @FXML
    private TableView<Factura> tablaHistorialVentas;

    @FXML
    private Button btnProductoMasVendido;
    @FXML
    private Button btnGestionarSedes;

    @FXML
    private TableColumn<Producto, Integer> columnCantidadDisponibleProductoGestion;

    @FXML
    private TableColumn<Factura, Double> columnIvaVentas;

    @FXML
    private Button btnAgregarCliente;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private Button btnNuevoCliente;


    @FXML
    private Button mostrarReporteVentaAction;

    @FXML
    private TableView<Sede> tablaSucursalVenta;

    @FXML
    private TableView<Cliente> tablaClienteFactura;

    @FXML
    private TextField txtIdentificacionCliente;

    @FXML
    private TextField txtContraseniaCliente;

    @FXML
    private TextField txtBuscarCliente;

    @FXML
    private TableColumn<Factura, String> columnDireccionFacturaCliente;

    @FXML
    private Button btnBuscarImagenProducto;

    @FXML
    private DatePicker dtpFechaInicioVenta;

    @FXML
    private TableColumn<Factura, DatePicker> columnFechaFacturaCliente;

    @FXML
    private Button btnClienteEstrella;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnNuevoProducto;

    @FXML
    private TextField txtDireccionCliente;

    @FXML
    private Button btnEliminarGestionCliente;

    @FXML
    private TableColumn<Factura, String> columnDireccionVentas;

    @FXML
    private DatePicker dtpFechaFinalVenta;

    @FXML
    private TableColumn<Factura, Double> columnSubtotalFacturaCliente;

    @FXML
    private TableView<Producto> tablaProductoReporteAdicional;

    @FXML
    private TableColumn<Cliente, DatePicker> columnFechDeNacimientoGestionCliente;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private TableColumn<Sede, String> columnNombreSucursalReporteAdicional;

    @FXML
    private TableColumn<Producto, String> columnNombreProductoGestion;

    @FXML
    private TableColumn<Cliente, String> columnCorreoElectronicoGestionCliente;

    @FXML
    private TextField txtPrecioProductoGestion;

    @FXML
    private TableView<Factura> tablaFacturaCliente;

    @FXML
    private ImageView imgProducto;

    @FXML
    private Label lblUsuario;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    private TextField txtNombreSucursalReporteAdicional;

    @FXML
    private TableColumn<Cliente, String> columnIdentificacionClienteFactura;

    @FXML
    private DatePicker dpFechaNacimientoCliente;

    @FXML
    private TableColumn<Factura, String> columnCodigoVentas;

    @FXML
    private TableColumn<Cliente, String> columnIdentificacionGestionCliente;

    @FXML
    private TableColumn<Sede, String> columnNombreSucursalVenta;

    @FXML
    private Button btnFechaProductoMasVendido;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private TableColumn<Cliente, String> columnNombreClienteFactura;

    @FXML
    private TextField txtNombreProductoReporteAdicional;

    @FXML
    private TableView<Cliente> tablaGestionClientes;

    @FXML
    private ComboBox<TipoProducto> comboCategoriaProductoGestion;

    @FXML
    private TableColumn<Cliente, String> columnNombreGestionCliente;

    @FXML
    private TableView<Sede> tablaNombreSucursalReporteAdicional;

    @FXML
    private TableColumn<Factura, String> columnCodigoFacturaCliente;

    @FXML
    private TableColumn<Factura, DatePicker> columnFechaVentas;

    @FXML
    private TableColumn<Producto, TipoProducto> columnCategoriaProductoGestion;

    
    @FXML
    void mostrarFacturaCliente(MouseEvent event) {
    	clienteGestionFacturaSeleccionado=tablaClienteFactura.getSelectionModel().getSelectedItem();
    	if(clienteGestionFacturaSeleccionado!=null){
    		setearTablaClienteFactura(clienteGestionFacturaSeleccionado);

    	}
        }
    @FXML
    void agregarClienteAction(ActionEvent event) {
    	agregarCliente();

    }



	@FXML
    void nuevoClienteAction(ActionEvent event) {
    	limpiarCliente();

    }


	@FXML
    void elimicarClienteAction(ActionEvent event) {
		eliminarCliente();

    }
	
	@FXML
    void mostrarReporteVentaAction(ActionEvent event) {
		System.out.println("mostrando reporte");
		mostrarReporte();

    }


	@FXML
    void actualizarClienteAction(ActionEvent event) {
    	actualizarCliente();

    }

 



	@FXML
    void agragarProductoAction(ActionEvent event) {
    	agregarProducto();

    }




	@FXML
    void nuevoProductoAction(ActionEvent event) {
    	limpiarProducto();

    }



	@FXML
    void eliminarProductoAction(ActionEvent event) {
		eliminarProducto();

    }


	@FXML
    void actualizarProducto(ActionEvent event) {
    	actualizarProducto();

    }



	@FXML
    void buscarImagenAction(ActionEvent event) {
    	buscarImagen();

    }



	@FXML
    void obtenerClienteEstrellaAction(ActionEvent event) {
		try {
			obtenerClienteEstrella();
			
		} catch (Exception e) {
			Utilidades.mostrarMensaje("Error","error al mostrar el cliente estrella", "msm :"+e.getMessage()+" cause :"+e.getCause(), AlertType.ERROR);
		}

    }

	@FXML
    void obtenerProductoPorFechaMasVendidoAction(ActionEvent event) {
		try {
		obtenerProductoPorFechaMasVendido();
			
		} catch (Exception e) {
			Utilidades.mostrarMensaje("Error","error al mostrar la fecha en que más se vendio el producto", "msm :"+e.getMessage()+" cause :"+e.getCause(), AlertType.ERROR);
		}

    }


	@FXML
    void obtenerProductosMasVendidosAction(ActionEvent event) {
		try {
			obtenerProductosMasVendidos();
			
		} catch (Exception e) {
			Utilidades.mostrarMensaje("Error","error al mostrar los tres productos más vendidos", "msm :"+e.getMessage()+" cause :"+e.getCause(), AlertType.ERROR);
		}

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
	@FXML
    void gestionaSedesAction(ActionEvent event) {
    	try {
			main.mostrarVentanaSeleccionDeSucursal(admin);
		} catch (IOException e) {
			Utilidades.mostrarMensaje("Error","error al gestionar sedes", "msm :"+e.getMessage()+" cause :"+e.getCause(), AlertType.ERROR);
		}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		//configuramos las columnas de la tabla de gestion de clientes
		columnNombreGestionCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnIdentificacionGestionCliente.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
		columnDireccionGestionCliente.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		columnCorreoElectronicoGestionCliente.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
		columnFechDeNacimientoGestionCliente.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
		//configuramos las columnas de la tabla de gestion productos
		columnNombreProductoGestion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnCodigoProductoGestion.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnPrecioProductoGestion.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnCantidadDisponibleProductoGestion.setCellValueFactory(new PropertyValueFactory<>("existencias"));
		columnCategoriaProductoGestion.setCellValueFactory(new PropertyValueFactory<>("existencias"));
		columnCategoriaProductoGestion.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		//configuramos las columnas de las tablas de factura clientes
		columnNombreClienteFactura.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnIdentificacionClienteFactura.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
		
		columnCodigoFacturaCliente.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnFechaFacturaCliente.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		columnTotalFacturaCliente.setCellValueFactory(new PropertyValueFactory<>("total"));
		columnSubtotalFacturaCliente.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
		columnIvaFacturaCliente.setCellValueFactory(new PropertyValueFactory<>("iva"));
		columnDireccionFacturaCliente.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		// configuramos las columnas de las tablas de reporte de ventas
		columnNombreSucursalVenta.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		
		columnCodigoVentas.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnFechaVentas.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		columnTotalVentas.setCellValueFactory(new PropertyValueFactory<>("total"));
		columnSubtotalVentas.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
		columnIvaVentas.setCellValueFactory(new PropertyValueFactory<>("iva"));
		columnDireccionVentas.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		//configuramos las columnas de las tablas de reportes adicionales
		columnNombreSucursalReporteAdicional.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnNombreProductoReporteAdicional.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		
		//seteamos los objetos seleccionados en la seccion gestion de clientes
		tablaGestionClientes.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			clienteGestionSeleccionado = newSelection;
			if(clienteGestionSeleccionado!=null)
				mostrarCliente(clienteGestionSeleccionado);
			
		});
		
		//seteamos los objetos seleccionados de la gestion seccion productos 
		tablaGestionProductos.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			productoGestionSeleccionado = newSelection;
			if(productoGestionSeleccionado!=null)
				mostrarProducto(productoGestionSeleccionado);

		});

		

		//seteamos los objetos seleccionados de la gestion seccion reporte de ventas
		tablaSucursalVenta.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			sedeVentaSeleccionada = newSelection;

		});
		tablaHistorialVentas.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			facturaVentaSeleccionada = newSelection;

		});

		//seteamos los objetos seleccionados de la gestion seccion reporte de ventas adicionales
		tablaNombreSucursalReporteAdicional.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			nombreSucursalReporteAdicionalSeleccionado = newSelection;
			refrescarTablaProductosRegistradosReporteAdicional(nombreSucursalReporteAdicionalSeleccionado.getListaProductos());

		});
		
		tablaProductoReporteAdicional.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {

			productoReporteAdicionalSeleccionado = newSelection;

		});
			

		
		//seccion donde se configuran los bindings para la busqueda automatica de gestion cliente
		filteredClienteGestioData = new FilteredList<>(listaClientesRegistradosData, p -> true);

		txtBuscarCliente.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredClienteGestioData.setPredicate(cliente-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (cliente.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (cliente.getIdentificacion().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		//seccion donde se configuran los bindings para la busqueda automatica gestion producto
		filteredProductosGestioData = new FilteredList<>(listaProductosRegistradosData, p -> true);

		txtBuscarProducto.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredProductosGestioData.setPredicate(producto-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (producto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				} else if (producto.getCodigo().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				}
				return false; 
			});
		});
		//seccion donde se configuran los bindings para la busqueda automatica facturas clientes 
		filteredClientesFacturasClientesData = new FilteredList<>(listaClientesRegistradosData, p -> true);

		txtNombreClienteFactura.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredClientesFacturasClientesData.setPredicate(cliente-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (cliente.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				} else if (cliente.getIdentificacion().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				}
				return false; 
			});
		});
		//seccion donde se configuran los bindings para la busqueda automatica reporte de ventas 
		filteredSedeReporteVentasData = new FilteredList<>(listaSedesRegistradasData, p -> true);

		txtNombreSucursalReporteVenta.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredSedeReporteVentasData.setPredicate(sede-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (sede.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				} else if (sede.getCiudad().getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				}
				return false; 
			});
		});
		//seccion donde se configuran los bindings para la busqueda automatica reportes adicionales 
		filteredSedeReporteAdicionalData = new FilteredList<>(listaSedesRegistradasData, p -> true);

		txtNombreSucursalReporteAdicional.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredSedeReporteAdicionalData.setPredicate(sede-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (sede.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				} else if (sede.getCiudad().getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				}
				return false; 
			});
		});
		//seccion donde se configuran los bindings para la busqueda automatica reportes adicionales 
		filteredProductosReporteAdicionalData = new FilteredList<>(listaProductosRegistradosReporteAdicionalData, p -> true);

		txtNombreProductoReporteAdicional.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredProductosReporteAdicionalData.setPredicate(producto-> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (producto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				} else if (producto.getCodigo().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				}
				return false; 
			});
		});
		//configuramos combobox
		comboCategoriaProductoGestion.getItems().addAll(TipoProducto.TECNOLOGIA_MOVIL,TipoProducto.TECNOLOGIA_COMPUTACIONAL,TipoProducto.MUSICA,
													    TipoProducto.HOGAR,TipoProducto.EMPRESARIALES);

	}








	public void setMain(Main main, Administrador admin) {
		this.main=main;
		this.admin=admin;
		lblNombreAdmin.setText(admin.getNombre());
		this.sedeAdmin=obtenerSedeActual();
		//configuramos las tablas para que autoseteen los valores filtrados

		//tabla clientes de la seccion administracion de clientes
    	SortedList<Cliente> sortedDataClienteGestion = new SortedList<>(filteredClienteGestioData);

    	sortedDataClienteGestion.comparatorProperty().bind(tablaGestionClientes.comparatorProperty());
    	tablaGestionClientes.setItems(sortedDataClienteGestion);

  	    //tabla productos de la seccion administracion de clientes
    	SortedList<Producto> sortedDataProductosGestion = new SortedList<>(filteredProductosGestioData);

    	sortedDataProductosGestion.comparatorProperty().bind(tablaGestionProductos.comparatorProperty());
    	tablaGestionProductos.setItems(sortedDataProductosGestion);

    	//tabla cliente de la seccion facturas  clientes
    	SortedList<Cliente> sortedDataClientesFactuas = new SortedList<>(filteredClientesFacturasClientesData);

    	sortedDataClientesFactuas.comparatorProperty().bind(tablaClienteFactura.comparatorProperty());
    	tablaClienteFactura.setItems(sortedDataClientesFactuas);
    	
    	//tabla sedes de la seccion reporte de ventas 
    	SortedList<Sede> sortedDataSedes = new SortedList<>(filteredSedeReporteVentasData);

    	sortedDataSedes.comparatorProperty().bind(tablaSucursalVenta.comparatorProperty());
    	tablaSucursalVenta.setItems(sortedDataSedes);

    	//tabla sedes de la seccion reportes adicionales
    	SortedList<Sede> sortedDataSedes2 = new SortedList<>(filteredSedeReporteAdicionalData);

    	sortedDataSedes2.comparatorProperty().bind(tablaNombreSucursalReporteAdicional.comparatorProperty());
    	tablaNombreSucursalReporteAdicional.setItems(sortedDataSedes2);
    	//tabla sedes de la seccion reportes adicionales
    	SortedList<Producto> sortedDataProductosReporteAdicional = new SortedList<>(filteredProductosReporteAdicionalData);

    	sortedDataProductosReporteAdicional.comparatorProperty().bind(tablaProductoReporteAdicional.comparatorProperty());
    	tablaProductoReporteAdicional.setItems(sortedDataProductosReporteAdicional);
    	
    	refrescarTablas();




	}

	/*
	 * metodo que refresca las tablas
	 */
	private void refrescarTablas() {

		listaSedesRegistradasData.clear();
		listaSedesRegistradasData.addAll(main.obtenerSedesRegistradas());
		
		listaClientesRegistradosData.clear();
		listaClientesRegistradosData.addAll(main.obtenerClientesRegistrados());

		listaProductosRegistradosData.clear();
		listaProductosRegistradosData.addAll(main.obtenerProductosRegistrados(sedeAdmin));
		
	
	}

	private void setearTablaClienteFactura(Cliente clienteGestionFacturaSeleccionado2) {
		listaFacturasClienteData.clear();
		listaFacturasClienteData.addAll(clienteGestionFacturaSeleccionado2.getListaFacturas());

		System.out.println(listaFacturasClienteData.toString());
		
		tablaFacturaCliente.setItems(listaFacturasClienteData);
		tablaFacturaCliente.refresh();
		clienteGestionFacturaSeleccionado=null;
	}


	/*
	 * metodo que setea una imagen en la tab de gestion de productos
	 */
	private void buscarImagen() {

		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File imgFile = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            imgProducto.setImage(image);
        }
	}

	
	
	
	/*
	 * ---------------------------------------METODOS QUE LIMPIAN LAS CELDAS DE GESTION---------------------------------
	 */
	
	/*
	 * metodo que limpia las celdas de gestion del cliente
	 */
    private void limpiarCliente() {
    	txtNombreCliente.setText("");
    	txtIdentificacionCliente.setText("");
    	txtDireccionCliente.setText("");
    	txtCorreoElectronicoCliente.setText("");
    	dpFechaNacimientoCliente.setValue(null);
    	txtContraseniaCliente.setText("");
		
	}
    /*
     * metodo que limpia las celdas de gestion de producto
     */
    private void limpiarProducto() {
    	txtNombreProductoGestion.setText("");
    	txtCodigoProductoGestion.setText("");
    	txtPrecioProductoGestion.setText("");
    	txtCantidadDisponibleProductoGestion.setText("");
    	comboCategoriaProductoGestion.setValue(null);
    	imgProducto.setImage(null);
	}
    
    
	private Sede obtenerSedeActual() {
		ArrayList<Sede> listaSedesAdmin=admin.getListaSedes();
		if(listaSedesAdmin.size()>1){
			while(true){
			try {
				int contador=1;
				String listaString="";
				for (Sede sede : listaSedesAdmin) {
					listaString+="nombre sede: "+sede.getNombre()+" ciudad:"+sede.getCiudad()+" pos: "+contador+"\n";
				}
				
				int seleccion=Integer.parseInt(JOptionPane.showInputDialog(listaString));
			
				if(seleccion>contador || seleccion<contador)
					throw new IndexOutOfBoundsException("seleccion no valida");
				return listaSedesAdmin.get(seleccion);

			} catch (Exception e) {
			}
			
		}}else{
			return listaSedesAdmin.get(0);
		}
	}
    
    /*
     * -------------------------------------METODOS AGREGAR--------------------------------
     */
	
	
	/*
	 * metodo que sirve para agregar clientes
	 */
    private void agregarCliente() {
    	try {
    		String nombre=txtNombreCliente.getText();
    		String id=txtIdentificacionCliente.getText();
    		String direccion=txtDireccionCliente.getText();
    		String correo=txtCorreoElectronicoCliente.getText();
    		LocalDate fechaNacimiento=dpFechaNacimientoCliente.getValue();
    		String contrasenia=txtContraseniaCliente.getText();
    		validatDatosCliente(nombre,id,correo,fechaNacimiento,contrasenia);

    		Cliente cliente= new Cliente(nombre, id, direccion, correo, fechaNacimiento, contrasenia);
    		main.agregarCliente(cliente);
    		refrescarTablas();
    		
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
	}
    private void agregarProducto() {
    	try {
			
    		String nombre=txtNombreProductoGestion.getText();
    		String codigo=txtCodigoProductoGestion.getText();
    		double precio=Double.parseDouble(txtPrecioProductoGestion.getText());
    		int    cantDisponible=Integer.parseInt(txtCantidadDisponibleProductoGestion.getText());
    		TipoProducto tipo=comboCategoriaProductoGestion.getValue();
    		Image imagen=imgProducto.getImage();
    		validarDatosProducto(nombre,codigo,precio,cantDisponible,tipo,imagen);
    		
    		Producto producto= new Producto(nombre, codigo, precio,"", imagen, cantDisponible, tipo);
    		main.agregarProducto(producto,sedeAdmin);
    		refrescarTablas();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


    /*
     * 	--------------------------------------------------------------metodos para validar los datos -----------------------------
     */

    private void validarDatosProducto(String nombre, String codigo, double precio, int cantDisponible,
			TipoProducto tipo, Image imagen) throws DatosInvalidosProductoException {
    	
    	String mensajeError="";


    	if(nombre == null || nombre.equals(""))
    		mensajeError += "El nombre es invalido \n";	

    	if(codigo == null || codigo.equals(""))
    		mensajeError += "El codigo es invalido \n";	

    	if(precio<0.0)
    		mensajeError += "El precio debe ser un valor positivo \n";	
    	if(cantDisponible<0)
    		mensajeError += "La cantidad debe ser positiva \n";	
    	
    	if(tipo==null)
    		mensajeError += "El tipo es invalido \n";	

    	if(imagen==null)
    		mensajeError += "La imagen es invalida \n";	
    	
    	if(!mensajeError.isEmpty())
    		throw new DatosInvalidosProductoException(mensajeError);



    }



	/*
     * metodo que valida los datos del cliente ingresados en la interfaz
     */
	private void validatDatosCliente(String nombre, String id, String correo, LocalDate fechaNacimiento,
			String contrasenia) throws DatosInvalidosClienteException {
		String mensajeError="";

		if(nombre == null || nombre.equals(""))
			mensajeError += "El nombre es invalido \n";	
		
		if(id == null || id.equals(""))
			mensajeError += "El id es invalido \n";	
		
		if(correo == null || correo.equals(""))
			mensajeError += "El nombre es invalido \n";	

		if(fechaNacimiento == null)
			mensajeError += "La fecha de nacimiento es invalida \n";	
		
		if(contrasenia == null || contrasenia.equals(""))
			mensajeError += "La contraseña es invalido \n";	
	
	if(!mensajeError.isEmpty())
		throw new DatosInvalidosClienteException(mensajeError);

}
	
	/*
	 * ------------------------------------------------------METODOS PARA ACTUALIZAR-------------------------------------------------- */ 
	   private void actualizarCliente() {
		try {
    		String nombre=txtNombreCliente.getText();
    		String id=txtIdentificacionCliente.getText();
    		String direccion=txtDireccionCliente.getText();
    		String correo=txtCorreoElectronicoCliente.getText();
    		LocalDate fechaNacimiento=dpFechaNacimientoCliente.getValue();
    		String contrasenia=txtContraseniaCliente.getText();
    		validatDatosCliente(nombre,id,correo,fechaNacimiento,contrasenia);

    		Cliente cliente= new Cliente(nombre, id, direccion, correo, fechaNacimiento, contrasenia);
    		if(clienteGestionSeleccionado!=null){
    			main.actualizarCliente(cliente,clienteGestionSeleccionado);
    			refrescarTablas();
    		}else{
    			System.out.println("debe seleccionar un cliente");
    		}
    		
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}

	}
	
	
	   /*
	    * metodo que actualiza un producto
	    */
    private void actualizarProducto() {
    		try {
			
    		String nombre=txtNombreProductoGestion.getText();
    		String codigo=txtCodigoProductoGestion.getText();
    		double precio=Double.parseDouble(txtPrecioProductoGestion.getText());
    		int    cantDisponible=Integer.parseInt(txtCantidadDisponibleProductoGestion.getText());
    		TipoProducto tipo=comboCategoriaProductoGestion.getValue();
    		Image imagen=imgProducto.getImage();
    		validarDatosProducto(nombre,codigo,precio,cantDisponible,tipo,imagen);
    		
    		Producto producto= new Producto(nombre, codigo, precio,"", imagen, cantDisponible, tipo);
    		if(productoGestionSeleccionado!=null){
    			main.actualizarProducto(sedeAdmin,producto,productoGestionSeleccionado);
    			refrescarTablas();
    		}else{
    			System.out.println("debe seleccionar un producto");
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /*
     * -----------------------------------------METODOS MOSTRAR--------------------
     */
    
    
    /*
     * metodo que muestra los atributos de un clinet
     */
	private void mostrarCliente(Cliente clienteSeleccionado) {
		txtNombreCliente.setText(clienteSeleccionado.getNombre());
		txtIdentificacionCliente.setText(clienteSeleccionado.getIdentificacion());
		txtDireccionCliente.setText(clienteSeleccionado.getDireccion());
		txtCorreoElectronicoCliente.setText(clienteSeleccionado.getCorreoElectronico());
		txtContraseniaCliente.setText(clienteSeleccionado.getContrasenia());
		dpFechaNacimientoCliente.setValue(clienteSeleccionado.getFechaNacimiento());
		
	}
	private void mostrarProducto(Producto productoSeleccionado) {
		txtNombreProductoGestion.setText(productoSeleccionado.getNombre());
		txtCodigoProductoGestion.setText(productoSeleccionado.getCodigo());
		txtPrecioProductoGestion.setText(""+productoSeleccionado.getPrecio());
		txtCantidadDisponibleProductoGestion.setText(""+productoSeleccionado.getExistencias());
		comboCategoriaProductoGestion.setValue(productoSeleccionado.getCategoria());
		imgProducto.setImage(productoSeleccionado.getFotoProducto());
	}

	/*
	 * --------------------------------------METODOS ELIMINAR------------------
	 *  */

    private void eliminarCliente() {
    	
    	try {
    		if(clienteGestionSeleccionado!=null){
    			if(!clienteGestionSeleccionado.hasFacturas()){
    				main.eliminarCliente(clienteGestionSeleccionado);
    				refrescarTablas();
    			}
    			
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
    private void eliminarProducto() {
		try {
    		if(productoGestionSeleccionado!=null){
    			if(!main.hasVentasAsociadas(sedeAdmin,productoGestionSeleccionado)){
    				main.eliminarProducto(sedeAdmin,productoGestionSeleccionado);
    				productoGestionSeleccionado=null;
    				refrescarTablas();
    			}
    			
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void mostrarReporte() {
		LocalDate fechaInicio=dtpFechaInicioVenta.getValue();
		LocalDate fechaFin=dtpFechaFinalVenta.getValue();
		Sede sede=sedeVentaSeleccionada;
		try {
			validarDatosReporte(fechaInicio,fechaFin,sede);
			ArrayList<Factura> listaFacturas=sede.getFacturasPorFecha(fechaInicio,fechaFin);
			setearTablaReporteVentas(listaFacturas);
			
		} catch (DatosInvalidosReporteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setearTablaReporteVentas(ArrayList<Factura> listaFacturasFiltradas) {
		listaFacturasEmpresaData.clear();
		listaFacturasEmpresaData.addAll(listaFacturasFiltradas);

		System.out.println(listaFacturasFiltradas.toString());
		
		tablaHistorialVentas.setItems(listaFacturasEmpresaData);
		tablaHistorialVentas.refresh();
	}

	private void validarDatosReporte(LocalDate fechaInicio, LocalDate fechaFin, Sede sede) throws DatosInvalidosReporteException {

		String mensajeError="";

		if(fechaInicio == null)
			mensajeError += "La fecha de inicio es invalida \n";	
		if(fechaFin == null)
			mensajeError += "La fecha fin es invalida \n";	
		if(sede == null)
			mensajeError += "debe seleccionar una sede \n";	

		if(!mensajeError.isEmpty())
			throw new DatosInvalidosReporteException(mensajeError);		

	} 

    private void obtenerClienteEstrella() {
    	if(nombreSucursalReporteAdicionalSeleccionado!=null){
    		Cliente clienteEstrella=nombreSucursalReporteAdicionalSeleccionado.getclienteEstrella();
    		if(clienteEstrella!=null)
    			Utilidades.mostrarMensaje("Cliente estrella", "El cliente con más compras es ",
    					"nombre: "+clienteEstrella.getNombre()+" id:"+clienteEstrella.getIdentificacion(), AlertType.INFORMATION);
    	}
		
	}
    private void obtenerProductoPorFechaMasVendido() {
    	if(nombreSucursalReporteAdicionalSeleccionado!=null && productoReporteAdicionalSeleccionado!=null  ){
    		LocalDate fechaProducto=nombreSucursalReporteAdicionalSeleccionado.obtenerfechaProductoMasVendido(productoReporteAdicionalSeleccionado.getCodigo());
    		if(fechaProducto!=null)
    			Utilidades.mostrarMensaje("Fecha en que más se vendió un producto","El producto ["+productoReporteAdicionalSeleccionado.getNombre()+"] se vendio mas veces en la fecha",
    									  fechaProducto.toString(), AlertType.INFORMATION);


    	}
		
	}
    private void obtenerProductosMasVendidos() {
    	if(nombreSucursalReporteAdicionalSeleccionado!=null){
    		ArrayList<Producto> listaProductosMasVendidos=nombreSucursalReporteAdicionalSeleccionado.getProductosMasVendidos();
    		if(listaProductosMasVendidos.size()>0){
    			String info="";
    			for (Producto producto : listaProductosMasVendidos) {
    				info+=producto.toString();
				}

    			Utilidades.mostrarMensaje("Tres productos mas vendidos","Productos mas vendidos por la sede"+nombreSucursalReporteAdicionalSeleccionado.getNombre(),
    									  info, AlertType.INFORMATION);
    		}
    			
    	}
		
	}


	private void refrescarTablaProductosRegistradosReporteAdicional(ArrayList<Producto> listaProductos) {
		listaProductosRegistradosReporteAdicionalData.clear();
		listaProductosRegistradosReporteAdicionalData.addAll(listaProductos);
		tablaProductoReporteAdicional.refresh();
	}
} 
