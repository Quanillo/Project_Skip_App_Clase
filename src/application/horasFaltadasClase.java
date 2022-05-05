package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class horasFaltadasClase implements Initializable{
	
	//----- Elementos -----------------------------------------------------------------
	
	@FXML private AnchorPane ap;
	@FXML private Button buscar;
	@FXML private ProgressBar pb;
	@FXML private Text faltasTotales;
	@FXML private Text faltasActuales;
	@FXML private PieChart pc;
	@FXML private BarChart<?, ?> bc;
	@FXML private ComboBox <String> comB;
	int fTotales;
	int fActuales;
	double progres;
	double progress;
	
	//----- Metodos -------------------------------------------------------------------
	
	public void statsFaltas () {
		String nombreAsignatrua=comB.getValue();
		User uAux=new User();
		Asignatura aAux=null;
		
		if(nombreAsignatrua.equalsIgnoreCase("todas")) {
			int fTotalesTodas = 0; int fActualesTodas=0;
			for(int j=0; j<uAux.getListaAsignaturas().size(); j++) {
				aAux=uAux.getListaAsignaturas().get(j);
				fActuales+=aAux.cuentaHorasFaltadas();
				fTotales+=aAux.getHorasMax();
				fActualesTodas+=aAux.cuentaHorasFaltadas();
				fTotalesTodas+=aAux.getHorasMax();
				progres=(fActualesTodas*100)/fTotalesTodas;
				progress=progres/100;
				faltasTotales.setText(""+fTotalesTodas);
				faltasActuales.setText(""+fActualesTodas);
				
			}
			setBarChart(fTotales, fActuales);
			setPieChart(fTotalesTodas, fActualesTodas);
			pb.setProgress(progress);
		}else {
			for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
				aAux=uAux.getListaAsignaturas().get(i);
				if(nombreAsignatrua.equalsIgnoreCase(aAux.getNombre())) {
					fActuales=aAux.cuentaHorasFaltadas();
					fTotales=aAux.getHorasMax();
					progres=(fActuales*100)/fTotales;
					progress=progres/100;
					faltasTotales.setText(""+fTotales);
					faltasActuales.setText(""+fActuales);
					setPieChart(fTotales, fActuales);
					setBarChart(fTotales, fActuales);
					pb.setProgress(progress);
					break;
				}
			}
		}
		
	}
	public void setPieChart (int faltasTotales, int faltasActuales) {
		ObservableList<PieChart.Data>piechart=
				FXCollections.observableArrayList(
						new PieChart.Data("Horas totales", faltasTotales),
						new PieChart.Data("Horas faltadas", faltasActuales)); 
		pc.setData(piechart);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setBarChart (int faltasTotales, int faltasActuales) {
		bc.getData().clear();
		XYChart.Series set1=new XYChart.Series<>();
		set1.getData().add(new XYChart.Data<>("Horas totales", faltasTotales));
		set1.getData().add(new XYChart.Data<>("Horas faltadas", faltasActuales));
		bc.getData().addAll(set1);
	
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Iniciamos el PieChar
		ObservableList<PieChart.Data>piechart=
				FXCollections.observableArrayList(
						new PieChart.Data("Horas faltadas", fTotales),
						new PieChart.Data("Horas totales", fActuales)); 
		pc.setData(piechart);
		//Iniciamos el BarChart
		XYChart.Series set1=new XYChart.Series<>();
		set1.getData().add(new XYChart.Data<>("Horas totales", fTotales));
		set1.getData().add(new XYChart.Data<>("Horas faltadas", fActuales));
		bc.getData().addAll(set1);
		//Inicimaos ProgressBar
		pb.setProgress(0.0);
		
		User uAux=new User();
		ObservableList<String> list=FXCollections.observableArrayList();
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
			list.add(uAux.getListaAsignaturas().get(i).getNombre());
		}
		list.add("Todas");
		comB.setItems(list);
	}	
	
}
