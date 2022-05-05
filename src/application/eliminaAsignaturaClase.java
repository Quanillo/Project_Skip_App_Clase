package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class eliminaAsignaturaClase extends Pellas implements Initializable{

	@FXML private TextField nAsignatura;
	@FXML private Button eliminaAsignatura;
	@FXML private CheckBox ok;
	@FXML private Text resolucion;
	@FXML private ComboBox <String> comB;
	
	public void eliminarAsignatura(MouseEvent event) {
		String nombre=comB.getValue();
		if(ok.isSelected()==true) {
			try {
				super.getUsuario().eliminarAsignatura(nombre);
				resolucion.setText("Asignatura eliminada con éxito.");
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				resolucion.setText("Asignatura no encontrada.");
			}
		}
		else {
			resolucion.setText("Quizás en otra ocasión.");
		}
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
