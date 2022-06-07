package application.controller;



import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.exception.DatosInvalidosEnvioException;
import application.model.Cliente;
import application.model.Envio;
import application.model.Factura;
import application.model.MedioPago;
import application.model.Producto;
import application.model.Sede;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class EnvioController implements Initializable {
	
	Main main;
	Cliente cliente;
	Integer idFactura;
	Sede    sede;
	double iva=0.16;
	MedioPago medioPago;
	double total;
	double subTotal;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton rbtDebito;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button bntCancelar;

    @FXML
    private TextField txtDireccion;

    @FXML
    private DatePicker dtpFechaFactura;

    @FXML
    private RadioButton rbtCredito;

    @FXML
    private TextField txtCodigoFactura;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtSubTotal;

    @FXML
    private TextField txtNombreQuienRecibe;

    @FXML
    private TextField txtDepartamento;

    @FXML
    private TextField txtTotal;

    @FXML
    void enviarEnvioAction(ActionEvent event) {
    	enviarEnvio();

    }

    @FXML
    void cancelarAction(ActionEvent event) {
    	main.mostrarVentanaCliente(cliente);

    }

	@FXML
    void seleccionarDebitoAction(ActionEvent event) {
    	rbtDebito.setSelected(true);
    	rbtCredito.setSelected(false);
    	medioPago=MedioPago.TARJETA_DEBITO;
    }

    @FXML
    void seleccionarCreditoAction(ActionEvent event) {
    	rbtDebito.setSelected(false);
    	rbtCredito.setSelected(true);
    	medioPago=MedioPago.TARJETA_CREDITO;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setMain(Main main, Cliente cliente2, int idFactura, Sede sede) {
		this.main=main;
		this.cliente=cliente2;
		this.idFactura=idFactura;
		this.sede=sede;
		configFactura();
	}

	private void configFactura() {
		subTotal=cliente.getCarroCompras().getSubtotal();
		txtCodigoFactura.setText(""+(idFactura+1));
		dtpFechaFactura.setValue(LocalDate.now());
		txtSubTotal.setText(""+subTotal);
		total=(subTotal*iva)+subTotal;
		txtTotal.setText(""+total);
	}
    private void enviarEnvio() {
    	String nombreReceptor= txtNombreQuienRecibe.getText();
    	String departamento= txtDepartamento.getText();
    	String ciudad= txtCiudad.getText();
    	String direccion=txtDireccion.getText();
    	
    	try {
			validarDatos(nombreReceptor,departamento,ciudad,direccion,medioPago);
			ArrayList<Producto> listaProductos=(ArrayList<Producto>) cliente.getCarroCompras().getListaProductosCarrosCompras().clone();
			Factura facturaAux= new Factura(""+idFactura,dtpFechaFactura.getValue(), total, subTotal, iva, direccion, listaProductos, cliente);
			Envio envio= new Envio(departamento, ciudad, direccion, nombreReceptor, listaProductos,medioPago);
			cliente.agregarFactura(facturaAux);
			sede.agregarFactura(facturaAux);
			sede.agregarEnvio(envio);
			cliente.getCarroCompras().getListaProductosCarrosCompras().clear();
			main.mostrarVentanaCliente(cliente);
		} catch (DatosInvalidosEnvioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
	}


	private void validarDatos(String nombreReceptor, String departamento, String ciudad, String direccion,
			MedioPago medioPago2) throws DatosInvalidosEnvioException {
		
		String mensajeError="";

		if(nombreReceptor == null || nombreReceptor.equals(""))
			mensajeError += "El nombre de quien recibe  es invalido \n";	

		if(departamento == null || departamento.equals(""))
			mensajeError += "El nombre de quien recibe  es invalido \n";	
		

		if(ciudad == null || ciudad.equals(""))
			mensajeError += "El nombre de quien recibe  es invalido \n";	
		

		if(direccion == null || direccion.equals(""))
			mensajeError+= "El nombre de quien recibe  es invalido \n";	
		

		if(medioPago2 == null )
			mensajeError += "El nombre de quien recibe  es invalido \n";	

		if(!mensajeError.isEmpty())
			throw new DatosInvalidosEnvioException(mensajeError);

	}

  
}
