package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class anadirFaltaClase implements Initializable{
	
	//----- Elementos -----------------------------------------------------------------

	@FXML private TextField nHoras;
	@FXML private TextField tFecha;
	@FXML private Button anadirFalta;
	@FXML private Button clear;
	@FXML private Text resolucion;
	@FXML private ComboBox <String> comB;
	
	//----- Metodos -------------------------------------------------------------------
	
	public void setFalta(MouseEvent event) throws IOException {
		int horas=Integer.parseInt(nHoras.getText());
		String fecha=tFecha.getText(); 
		String nombre=comB.getValue();
		User userAux=new User();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate aux=LocalDate.parse(fecha, formatter);
		for(int i=0;i<userAux.getListaAsignaturas().size();i++) {
			if (nombre.equalsIgnoreCase(userAux.getListaAsignaturas().get(i).getNombre())) { //si el nombre pasado por parametro es igual aun nombre del ArrayList de asignaturas
				System.out.println(aux);
				userAux.getListaAsignaturas().get(i).aniadeHoras(nombre, horas, aux);
				resolucion.setText("Falta añadida con éxito.");
			}
		}	
		nHoras.clear();
		tFecha.clear();
	}
	
	
	public void clear (MouseEvent event) {
		nHoras.clear();
		tFecha.clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		User uAux=new User();
		ObservableList<String> list=FXCollections.observableArrayList();
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
			list.add(uAux.getListaAsignaturas().get(i).getNombre());
		}
		comB.setItems(list);
	}
	
}
