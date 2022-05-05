package application;


import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Pellas{


	//----- Usuario -----------------------------------------------------------------
	
	User usuario=new User();
	
	//----- Elementos -----------------------------------------------------------------

	@FXML private BorderPane bp;
	@FXML private AnchorPane ap;
	@FXML private Button exit;
	@FXML private Button home;
	@FXML private Button anadirFalta;
	@FXML private Button anadirAsignatura;
	@FXML private Button eliminarFalta;
	@FXML private Button eliminarAsignatura;
	@FXML private Button listadoDeFaltas;
	@FXML private Button horasFaltadas;
	@FXML private Button diaMasFaltado;

	

	//Necesitamos el nombre de las asignaturas almacenadas en las sesiones anteriores para generar esta Arraylist.
	
	
	//----- Metodos -------------------------------------------------------------------
	
	
	 User getUsuario() {
		return usuario;
	}

	void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public void cerrarPrograma(ActionEvent e) {
		Platform.exit();
	}

	//----- Layout-navegacion ---------------------------------------------------------
	
	@FXML
	private void principal() {
		loadPage("homePage");
	}
	
	@FXML 
	private void home(MouseEvent event) {
		loadPage("homePage");
	}
	@FXML 
	private void aFalta(MouseEvent event) {
		loadPage("anadirFaltaPage");
	}
	@FXML 
	private void eFalta(MouseEvent event) {
		loadPage("eliminarFaltaPage");
	}
	@FXML 
	private void aAsignatura(MouseEvent event) {
		loadPage("anadirAsignaturaPage");
	}
	@FXML 
	private void eAsignatura(MouseEvent event) {
		loadPage("eliminaAsignaturaPage");
	}
	@FXML 
	private void ListFaltas(MouseEvent event) {
		loadPage("listadoFaltasPage");
	}
	@FXML 
	private void hFaltadas(MouseEvent event) {
		loadPage("horasFaltadasPage");
	}
	@FXML 
	private void dMasFaltado(MouseEvent event) {
		loadPage("diaMasFaltadoPage");
	}

	private void loadPage(String page) {
		Parent root=null;
	
		try {
			root=FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bp.setCenter(root);;
	}



	//CODIGO MAKINA
	
}


