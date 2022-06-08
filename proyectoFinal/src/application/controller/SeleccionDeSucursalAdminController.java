package application.controller;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Administrador;
import application.model.Ciudad;
import application.model.Sede;
import application.model.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SeleccionDeSucursalAdminController implements Initializable{
	Main main;
	Administrador admin;
	
	Sede sedeRegistradaSeleccionada;
	Sede sedeDisponibleSeleccionada;
	
	
	FilteredList<Sede> filteredSedeRegistradaData;
	FilteredList<Sede> filteredSedeDisponibleData;
	

	ObservableList<Sede> listaSedesRegistradasData = FXCollections.observableArrayList();
	ObservableList<Sede> listaSedesDisponiblesData = FXCollections.observableArrayList();

   @FXML
   private TableView<Sede> tableSedesRegistradas;

   @FXML
   private TableColumn<Sede, Ciudad> columnCiudadSedesRegistradas;

   @FXML
   private Button btnEliminar;

   @FXML
   private TableColumn<Sede, String> columnNombreSedesRegistrados;

   @FXML
   private TableView<Sede> tableSedesDisponibles;

   @FXML
   private TableColumn<Sede, Ciudad> columnCiudadSedesDisponibles;

   @FXML
   private Label lblNombreAdmin;

   @FXML
   private Button btnAgregar;

   @FXML
   private TableColumn<Sede, String> columnNombreSedesDisponibles;

   @FXML
   private TextField txtFiltrarSedesQueAdministra;

   @FXML
   private Button btnCerrar;

   @FXML
   private TextField txtFiltrarSedesdisponibles;

   @FXML
   void agregarAction(ActionEvent event) {
	   try {
	   agregar();
	} catch (Exception e) {
		Utilidades.mostrarMensaje("Error","Error al agregar", "msm :"+e.getMessage()+" cause:"+e.getCause(), AlertType.ERROR);
	}

   }



@FXML
   void eliminarAction(ActionEvent event) {
	try {
		eliminar();
	} catch (Exception e) {
		Utilidades.mostrarMensaje("Error","Error al eliminar", "msm :"+e.getMessage()+" cause:"+e.getCause(), AlertType.ERROR);
	}

   }





@FXML
   void CerrarAction(ActionEvent event) {

   }
   @FXML
   void seleccionarSedeDisponibleEvent(MouseEvent event) {
	   sedeDisponibleSeleccionada=tableSedesDisponibles.getSelectionModel().getSelectedItem();
   }

   @FXML
   void seleccionarSedeRegistradaEvent(MouseEvent event) {
	   sedeRegistradaSeleccionada=tableSedesRegistradas.getSelectionModel().getSelectedItem();
   }
   @Override
   public void initialize(URL location, ResourceBundle resources) {
	   columnNombreSedesDisponibles.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	   columnNombreSedesRegistrados.setCellValueFactory(new PropertyValueFactory<>("nombre"));

	   columnCiudadSedesDisponibles.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
	   columnCiudadSedesRegistradas.setCellValueFactory(new PropertyValueFactory<>("ciudad"));

	   //seccion donde se configuran los bindings para la busqueda automatica de gestion cliente
	   filteredSedeDisponibleData = new FilteredList<>(listaSedesDisponiblesData, p -> true);

	   txtFiltrarSedesdisponibles.textProperty().addListener((observable, oldValue, newValue) -> {
		   filteredSedeDisponibleData.setPredicate(sede-> {
			   if (newValue == null || newValue.isEmpty()) {
				   return true;
			   }
			   String lowerCaseFilter = newValue.toLowerCase();

			   if (sede.getNombre().toLowerCase().contains(lowerCaseFilter)) {
				   return true; // Filter matches first name.
			   } 
			   return false; // Does not match.
		   });
	   });
	   //	seccion donde se configuran los bindings para la busqueda automatica de gestion cliente
	   filteredSedeRegistradaData = new FilteredList<>(listaSedesRegistradasData, p -> true);

	   txtFiltrarSedesQueAdministra.textProperty().addListener((observable, oldValue, newValue) -> {
		   filteredSedeRegistradaData.setPredicate(sede-> {
			   if (newValue == null || newValue.isEmpty()) {
				   return true;
			   }
			   String lowerCaseFilter = newValue.toLowerCase();

			   if (sede.getNombre().toLowerCase().contains(lowerCaseFilter)) {
				   return true; // Filter matches first name.
			   } 
			   return false; // Does not match.
		   });
	   });
}
public void setMain(Main main,Administrador  admin){
	this.main=main;
	this.admin=admin;
	//tabla sedes de la seccion reportes adicionales
	SortedList<Sede> sortedDataSedesDisponibles = new SortedList<>(filteredSedeDisponibleData);

	sortedDataSedesDisponibles.comparatorProperty().bind(tableSedesDisponibles.comparatorProperty());
	tableSedesDisponibles.setItems(sortedDataSedesDisponibles);
		//tabla sedes de la seccion reportes adicionales
	SortedList<Sede> sortedDataSedesRegistradas = new SortedList<>(filteredSedeRegistradaData);

	sortedDataSedesRegistradas.comparatorProperty().bind(tableSedesRegistradas.comparatorProperty());
	tableSedesRegistradas.setItems(sortedDataSedesRegistradas);
	refrescarTablas();
}

private void refrescarTablas() {
	listaSedesDisponiblesData.clear();
	listaSedesDisponiblesData.addAll(main.obtenerSedesDisponibles());
	
	listaSedesRegistradasData.clear();
	listaSedesRegistradasData.addAll(admin.getListaSedes());
	
}
   private void agregar() {
	   if(sedeDisponibleSeleccionada!=null){
		   admin.setSede(sedeDisponibleSeleccionada);
		   sedeDisponibleSeleccionada.setAdministrador(admin);
		   refrescarTablas();
	   }
   }
   private void eliminar() {
	   if(sedeRegistradaSeleccionada!=null){
		   admin.rmSede(sedeRegistradaSeleccionada);
		   sedeRegistradaSeleccionada.setAdministrador(null);
		   refrescarTablas();
	   }
   }
}
