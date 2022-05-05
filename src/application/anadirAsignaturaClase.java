package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class anadirAsignaturaClase extends Pellas{

	//----- Elementos -----------------------------------------------------------------

	@FXML private TextField nAsignatura;
	@FXML private TextField hTotales;
	@FXML private TextField pAsistencia;
	@FXML private Button anadirAsignatura;
	@FXML private Button clear;
	@FXML private Text resolucion;
	
	//----- Metodos -------------------------------------------------------------------
	
	public void crearAsignatura(MouseEvent event) {
		String nombre=nAsignatura.getText();
		int horasTotales=Integer.parseInt(hTotales.getText());
		int porcentaje=Integer.parseInt(pAsistencia.getText());
		Asignatura aAux=new Asignatura(nombre, horasTotales, porcentaje);
		try {
			super.getUsuario().escribirAsignatura(aAux);
			resolucion.setText("Asignatura añadida con éxito.");
		} catch (IOException e) {
			e.printStackTrace();
			resolucion.setText("Fallo al añadir Asignatura.");
			
		}	
		clear();
	}
	
	public void clear (MouseEvent event) {
		nAsignatura.clear();
		hTotales.clear();
		pAsistencia.clear();
	}
	public void clear () {
		nAsignatura.clear();
		hTotales.clear();
		pAsistencia.clear();
	}
	
}
