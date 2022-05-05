package application;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Faltas implements Serializable{
	
	
	//----- Atributos --------------------------------------------------------------------
	
	private static final long serialVersionUID = 1L;
	private int numHoras; 
	private LocalDate fecha;
	String asignatura;
	 
	//----- Constructores -----------------------------------------------------------------
	
	public Faltas(String asignatura, int numHoras, String fecha) {
		
		this.asignatura=asignatura;
		this.numHoras=numHoras;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate aux=LocalDate.parse(fecha, formatter);
		this.setFecha(aux);
	}
	
	public Faltas(String asignatura, int numHoras, LocalDate fecha) {
		
		this.asignatura=asignatura;
		this.numHoras=numHoras;
		this.fecha=fecha;
	}
	
	//----- Metodos -----------------------------------------------------------------------
	
	//aniade una hora al objeto Horas escribiendolo en el archivo
	void setHora(int hora) {
		this.numHoras = hora;
		String horaS=String.valueOf(hora)+"\n";
		File archivoHora=new File("data\\horas"+ this.asignatura +".txt");
		try {
			FileWriter fw=new FileWriter(archivoHora, true);
			fw.write(horaS);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//aniade una fecha al objeto Horas escribiendolo en el archivo
	void setFecha(LocalDate fecha)  {
		this.fecha = fecha;
		String fechaS = fecha.toString()+"\n";
		File archivoFecha=new File("data\\faltas.dat");
		try {
			FileWriter fw=new FileWriter(archivoFecha, true);
			fw.write(fechaS);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	
	
	int getNumHoras() {
		return numHoras;
	}


	void setNumHoras(int numHoras) {
		this.numHoras = numHoras;
	}


	String getAsignatura() {
		return asignatura;
	}


	void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}


	LocalDate getFecha() {
		return fecha;
	}

	int getMes () {
		int mes=this.fecha.getMonthValue();
		return  mes;
	}
	
	String getDia () {
		String dia=String.valueOf(fecha.getDayOfWeek());
		return dia;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		String aux= fecha.format(formatters);
		return  "Dia: " + aux + "		Horas faltadas: " + numHoras;
	}
	
}



	
	
	//CODIGO TRASH
	
	
	/*void setFechaInicio(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	LocalDate getFecha() {
		return fecha;
	}
	
	public Faltas(int hora) {
		super();
		this.numHoras = hora;
	}
	
	public Faltas(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}
	
	int getHora() {
		return numHoras;
	}
	
	
	
	Asignatura getAsignatura() {
		return asignatura;
	}
	
	void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}*/
		
		/*public void escribir(File f) throws FileNotFoundException , IOException {
		Faltas faltas;
		FileOutputStream fo=null;
		ObjectOutputStream datos=null;
		
		if(!f.exists()) {
			fo=new FileOutputStream(f);
			datos=new ObjectOutputStream(fo);
		}
		else {
			fo=new FileOutputStream(f, true);
			datos=new MiObjectOutputStream(fo);
		}
		faltas=new Faltas(this.getAsignatura(), this.getNumHoras(), this.getFecha());
		datos.writeObject(faltas);
		datos.close();
	}*/


