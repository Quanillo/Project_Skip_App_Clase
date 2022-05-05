package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class diaMasFaltadoClase implements Initializable{
	
	@FXML private Button buscar;
	@FXML private PieChart pc;
	@FXML private Text diaMasFaltado;
	@FXML private ComboBox <String> comB;
	@SuppressWarnings("rawtypes")
	@FXML private LineChart lc;
	
	
	public void setStats() {
		lc.getData().clear();
		String nombreAsignatrua=comB.getValue();
		User uAux=new User();
		Asignatura aAux=null;
		
		if(nombreAsignatrua.equalsIgnoreCase("todas")) {
				setLineChartTodas();
				setPieChartTodas();
				diaMasFaltado.setText(diaMasFaltadoTodas());
		}
		else {
			for(int j=0; j<uAux.getListaAsignaturas().size(); j++) {
				aAux=uAux.getListaAsignaturas().get(j);
				if(aAux.getNombre().equalsIgnoreCase(nombreAsignatrua)) {
					setLineChart(nombreAsignatrua);
					setPieChart(nombreAsignatrua);
					diaMasFaltado.setText(aAux.diaMasFaltadoAsignatura());
					break;
				}
			}
		}
	}
	
	public void setPieChart (String nombreAsignatura) {
		User uAux=new User();
		Asignatura aAux = null;
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
			if(nombreAsignatura.equals(uAux.getListaAsignaturas().get(i).getNombre())) {
				aAux=uAux.getListaAsignaturas().get(i);
				break;
			}
		}
		ObservableList<PieChart.Data>piechart=
				FXCollections.observableArrayList(
						new PieChart.Data("Lunes", aAux.faltasPorDias(1)),
						new PieChart.Data("Martes", aAux.faltasPorDias(2)),
						new PieChart.Data("Miercoless", aAux.faltasPorDias(3)),
						new PieChart.Data("Jueves", aAux.faltasPorDias(4)),
						new PieChart.Data("Viernes", aAux.faltasPorDias(5))); 
		pc.setData(piechart);
	}
	
	public void setPieChartTodas () {
		User uAux=new User();
		Asignatura aAux = null;
		int faltasTotalL=0;int faltasTotalM=0;int faltasTotalX=0;int faltasTotalJ=0;int faltasTotalV=0;
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
			aAux=uAux.getListaAsignaturas().get(i);
			faltasTotalL+=aAux.faltasPorDias(1);
			faltasTotalM+=aAux.faltasPorDias(2);
			faltasTotalX+=aAux.faltasPorDias(3);
			faltasTotalJ+=aAux.faltasPorDias(4);
			faltasTotalV+=aAux.faltasPorDias(5);
		}
		ObservableList<PieChart.Data>piechart=
				FXCollections.observableArrayList(
						new PieChart.Data("Lunes", faltasTotalL),
						new PieChart.Data("Martes", faltasTotalM),
						new PieChart.Data("Miercoles",faltasTotalX),
						new PieChart.Data("Jueves", faltasTotalJ),
						new PieChart.Data("Viernes",faltasTotalV)); 
		pc.setData(piechart);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  void setLineChart(String nombreAsignatura) {
		
		User uAux=new User();
		Asignatura aAux = null;
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
			if(nombreAsignatura.equals(uAux.getListaAsignaturas().get(i).getNombre())) {
				aAux=uAux.getListaAsignaturas().get(i);
			}
		}
		XYChart.Series series=new XYChart.Series();
		series.setName("Faltas");
		
		series.getData().add(new XYChart.Data("Sep", aAux.cuentaHorasFaltadasMes(9)));
		series.getData().add(new XYChart.Data("Oct", aAux.cuentaHorasFaltadasMes(10)));
		series.getData().add(new XYChart.Data("Nov", aAux.cuentaHorasFaltadasMes(11)));
		series.getData().add(new XYChart.Data("Dic", aAux.cuentaHorasFaltadasMes(12)));
		series.getData().add(new XYChart.Data("Ene", aAux.cuentaHorasFaltadasMes(1)));
		series.getData().add(new XYChart.Data("Feb", aAux.cuentaHorasFaltadasMes(2)));
		series.getData().add(new XYChart.Data("Mar", aAux.cuentaHorasFaltadasMes(3)));
		series.getData().add(new XYChart.Data("Abr", aAux.cuentaHorasFaltadasMes(4)));
		series.getData().add(new XYChart.Data("May", aAux.cuentaHorasFaltadasMes(5)));
		series.getData().add(new XYChart.Data("Jun", aAux.cuentaHorasFaltadasMes(6)));
		
		lc.getData().add(series);
	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  void setLineChartTodas() {
		int hFaltadas9=0;int hFaltadas10=0;int hFaltadas11=0;int hFaltadas12=0;int hFaltadas1=0;int hFaltadas2=0;int hFaltadas3=0;int hFaltadas4=0;int hFaltadas5=0;int hFaltadas6=0;
		User uAux=new User();
		Asignatura aAux = null;
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
			aAux=uAux.getListaAsignaturas().get(i);
			hFaltadas9+=aAux.cuentaHorasFaltadasMes(9);
			hFaltadas10+=aAux.cuentaHorasFaltadasMes(10);
			hFaltadas11+=aAux.cuentaHorasFaltadasMes(11);
			hFaltadas12+=aAux.cuentaHorasFaltadasMes(12);
			hFaltadas1+=aAux.cuentaHorasFaltadasMes(1);
			hFaltadas2+=aAux.cuentaHorasFaltadasMes(2);
			hFaltadas3+=aAux.cuentaHorasFaltadasMes(3);
			hFaltadas4+=aAux.cuentaHorasFaltadasMes(4);
			hFaltadas5+=aAux.cuentaHorasFaltadasMes(5);
			hFaltadas6+=aAux.cuentaHorasFaltadasMes(6);
		}
		XYChart.Series series=new XYChart.Series();
		series.setName("Faltas");
		
		series.getData().add(new XYChart.Data("Sep",hFaltadas9));
		series.getData().add(new XYChart.Data("Oct",hFaltadas10));
		series.getData().add(new XYChart.Data("Nov", hFaltadas11));
		series.getData().add(new XYChart.Data("Dic", hFaltadas12));
		series.getData().add(new XYChart.Data("Ene", hFaltadas1));
		series.getData().add(new XYChart.Data("Feb",hFaltadas2));
		series.getData().add(new XYChart.Data("Mar",hFaltadas3));
		series.getData().add(new XYChart.Data("Abr", hFaltadas4));
		series.getData().add(new XYChart.Data("May", hFaltadas5));
		series.getData().add(new XYChart.Data("Jun", hFaltadas6));

		lc.getData().add(series);	
	}
	
	public String diaMasFaltadoTodas() {
		String diaMasFaltado = null;
		User uAux=new User();
		Asignatura aAux = null;
		int cL=0;int cM=0;int cX=0;int cJ=0;int cV=0;
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
			aAux=uAux.getListaAsignaturas().get(i);
			cL+=aAux.faltasPorDias(1);
			cM+=aAux.faltasPorDias(2);
			cX+=aAux.faltasPorDias(3);
			cJ+=aAux.faltasPorDias(4);
			cV+=aAux.faltasPorDias(5);
		}
		int arrayDias[]= {cL,cM,cX,cJ,cV};
		int max=Integer.MIN_VALUE;
		for(int j=0;j<5;j++) {
			if(arrayDias[j]>max) {
				max=arrayDias[j];
			}
			if(max==cL)
				diaMasFaltado="Lunes";
			else if(max==cM)
				diaMasFaltado="Martes";
			else if(max==cX)
				diaMasFaltado="Miercoles";
			else if(max==cJ)
				diaMasFaltado="Jueves";
			else if(max==cV)
				diaMasFaltado="Viernes";
		}
		return diaMasFaltado;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		XYChart.Series series=new XYChart.Series();
		series.setName("Faltas");
		
		series.getData().add(new XYChart.Data("Sep", 0));
		series.getData().add(new XYChart.Data("Oct", 0));
		series.getData().add(new XYChart.Data("Nov", 0));
		series.getData().add(new XYChart.Data("Dic", 0));
		series.getData().add(new XYChart.Data("Ene", 0));
		series.getData().add(new XYChart.Data("Feb", 0));
		series.getData().add(new XYChart.Data("Mar", 0));
		series.getData().add(new XYChart.Data("Abr", 0));
		series.getData().add(new XYChart.Data("May", 0));
		series.getData().add(new XYChart.Data("Jun", 0));
		
		lc.getData().add(series);
		
		User uAux=new User();
		ObservableList<String> list=FXCollections.observableArrayList();
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) {
			list.add(uAux.getListaAsignaturas().get(i).getNombre());
		}
		list.add("Todas");
		comB.setItems(list);
	}
	
	

}
