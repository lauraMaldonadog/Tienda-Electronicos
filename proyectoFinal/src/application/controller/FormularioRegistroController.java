package application.controller;



import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Main;
import application.exception.DatosInvalidosClienteException;
import application.model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class FormularioRegistroController implements Initializable {
	Main main;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private RadioButton rbtCliente;

    @FXML
    private RadioButton rbtAdministrador;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnResgristar;

    @FXML
    private DatePicker dtpFecha;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtContrasenia;

    @FXML
    void registrarAction(ActionEvent event) {
    	registrar();

    }

    @FXML
    void cancelarAction(ActionEvent event) {
    	try {
			main.mostrarVentanaLogin();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	@FXML
    void elegirAdministrador(ActionEvent event) {
		rbtAdministrador.setSelected(true);
		rbtCliente.setSelected(false);

    }

   
	@FXML
    void elegirCliente(ActionEvent event) {
		rbtAdministrador.setSelected(false);
		rbtCliente.setSelected(true);

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}


	public void setMain(Main main) {
		this.main=main;
		
	}

    private void registrar() {
    	try {
    		String nombre=txtNombre.getText();
    		String id=txtIdentificacion.getText();
    		String direccion=txtDireccion.getText();
    		String correo=txtCorreo.getText();
    		LocalDate fechaNacimiento=dtpFecha.getValue();
    		String contrasenia=txtContrasenia.getText();
    		validatDatosCliente(nombre,id,correo,fechaNacimiento,contrasenia);

    		Cliente cliente= new Cliente(nombre, id, direccion, correo, fechaNacimiento, contrasenia);
    		System.out.println(cliente.toString());
    		main.agregarCliente(cliente);
    		main.mostrarVentanaLogin();
    		
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
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
	
}

