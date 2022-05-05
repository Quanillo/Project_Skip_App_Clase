package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class listadoFaltasClase implements Initializable{
	
	//----- Elementos -----------------------------------------------------------------
	
	@FXML private ScrollPane sp;
	@FXML private TextField nAsignatura;
	@FXML private Button buscar;
	@FXML private Text txtListado;
	@FXML private ComboBox <String> comB;
	String asigNombre;

	//----- Metodos -------------------------------------------------------------------
	
	public void printFaltas (ActionEvent e) {
		String listado="";
		User aux=new User();
		Asignatura auxAsig;
		asigNombre=comB.getValue();
		int s=aux.getListaAsignaturas().size();
		String nombre;
		if(asigNombre.equalsIgnoreCase("todas")) {
			for(int j=0; j<s; j++) {
				auxAsig=aux.getListaAsignaturas().get(j);
				listado += aux.getListaAsignaturas().get(j).toString() + "\n";
			}
		}
		else {
			for(int i=0; i<s; i++) {
				nombre=aux.listaAsignaturas.get(i).getNombre();
				if(asigNombre.equalsIgnoreCase(nombre)) {
					auxAsig=aux.getListaAsignaturas().get(i);
					listado=auxAsig.toString();
					break;
				}
				else {
					listado="NO DATA FOUND";
				}
			}
		}
		txtListado.setText(listado);
	}	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		User uAux=new User();
		ObservableList<String> list=FXCollections.observableArrayList();
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
			list.add(uAux.getListaAsignaturas().get(i).getNombre());
		}
		list.add("Todas");
		comB.setItems(list);
	}

}
