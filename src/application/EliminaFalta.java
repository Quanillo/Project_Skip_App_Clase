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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class EliminaFalta extends Pellas implements Initializable{


	@FXML private TextField nAsignatura;
	@FXML private TextField fecha;
	@FXML private Button eliminaFalta;
	@FXML private CheckBox ok;
	@FXML private Text resolucion;
	@FXML private ComboBox <String> comB;
	@FXML private ComboBox <String> comBfecha;
	
	public void eliminarAsignatura(MouseEvent event) {
		String nombre=comB.getValue();
		String sfecha=comBfecha.getValue();
		Asignatura asAux = super.getUsuario().getListaAsignaturas().get(0);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate faux=LocalDate.parse(sfecha, formatter);
		if(ok.isSelected()==true) {
			try {
				asAux.eliminarFalta(nombre, faux);
				resolucion.setText("Falta eliminada con éxito.");
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				resolucion.setText("Falta no encontrada.");
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
		ObservableList<String> list2=FXCollections.observableArrayList();
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
			list.add(uAux.getListaAsignaturas().get(i).getNombre());
		}
		comB.setItems(list);
		
		comB.setOnAction(event -> {
            comBfecha.getItems().clear();
            for (int i=0; i<uAux.getListaAsignaturas().size();i++) {
            	if(uAux.getListaAsignaturas().get(i).getNombre().equalsIgnoreCase(comB.getValue())) {
            		for(int j=0;j<uAux.getListaAsignaturas().get(i).getListaFaltas().size();j++) {
            			LocalDate fAux=uAux.getListaAsignaturas().get(i).getListaFaltas().get(j).getFecha();
            			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            			String sFecha = fAux.format(formatter);
            			list2.add(sFecha);
            		}
            		comBfecha.setItems(list2);
            		break;
            	}
            }
        });
	}
	
	
}
