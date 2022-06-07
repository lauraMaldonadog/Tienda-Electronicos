package application.controller;


import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.exception.AdminNoEncontradoException;
import application.exception.ClienteNoEncontradoException;
import application.exception.DatosInvalidosException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
public class LoginController implements Initializable{
	Main main;
	
	   @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private Button btnIniciarSesion;

	    @FXML
	    private PasswordField txtContrasenia;

	    @FXML
	    private RadioButton rbtCliente;

	    @FXML
	    private RadioButton rbtAdministrador;

	    @FXML
	    private Label lblCrearCuenta;

	    @FXML
	    private TextField txtUsuario;



	    @FXML
	    void iniciarSesionAction(ActionEvent event) {
	    	iniciarSesion();

	    }




		




		@FXML
		void elegirAdminAction(ActionEvent event) {
			limpiarLogging();
			rbtAdministrador.setSelected(true);
			rbtCliente.setSelected(false);
		}

		

		@FXML
		void elegirClienteAction(ActionEvent event) {
			limpiarLogging();
			rbtAdministrador.setSelected(false);
			rbtCliente.setSelected(true);
		}

		private void limpiarLogging() {
			txtUsuario.setText("");
			txtContrasenia.setText("");	
		}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		lblCrearCuenta.setOnMouseClicked(e->{
			main.mostrarVentanaFormularioRegistro();
		});
		
		
	}

	public void setMain(Main main) {
		this.main=main;
		
	}
private void iniciarSesion() {
		
		try {
			if(validarDatos()){
				String user=txtUsuario.getText();
				String passwd=txtContrasenia.getText();
				if(rbtAdministrador.isSelected())
					main.cargarAdmin(user,passwd);
				if(rbtCliente.isSelected())
					main.cargarCliente(user,passwd);
			}
		} catch (DatosInvalidosException | AdminNoEncontradoException | ClienteNoEncontradoException e) {
			e.printStackTrace();
		}
			
		}








/*
 * metodo que valida las casillas de la interfaz para saber si alguna está vacia
 */
private boolean validarDatos() throws DatosInvalidosException {
	String mensaje="";
	if(txtUsuario==null || txtUsuario.getText().isEmpty())
		mensaje+="debe ingresar un usuario\n";
	if(txtUsuario==null || txtUsuario.getText().isEmpty())
		mensaje+="debe ingresar una contraseña\n";
	if(!mensaje.isEmpty())
		throw new DatosInvalidosException(mensaje);
	return true;
}

}

