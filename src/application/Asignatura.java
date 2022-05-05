package application;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;


public class Asignatura implements Serializable{
	
	
	//----- Atributos --------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int horasMax;
	ArrayList<Faltas> listaFaltas =new ArrayList<Faltas>();
	
	
	//----- Constructores -----------------------------------------------------------------
	public Asignatura(String nombre, int horasMax) {
		super();
		this.nombre = nombre;
		this.horasMax = horasMax;
		
	}
	
	public Asignatura(String nombre, int horasTotal, int porcentaje) {
		this.nombre=nombre;
		horasMax=(porcentaje*horasTotal)/100;
		
	}

	//----- Metodos -----------------------------------------------------------------------
	
	
	
	//crea un objeto Horas y lo añade al ArrayList de la asignatura
	public void aniadeHoras (String asignatura, int numHoras, LocalDate fecha) {
		Faltas aux=new Faltas(asignatura, numHoras, fecha);
		listaFaltas.add(aux);
		try {
			escribirFaltas(aux);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void setHoras(ArrayList<Faltas> horas) {
		this.listaFaltas = horas;
	}

	//cuenta las horas faltadas que tiene una asignatura recorriendo el ArrayList de horas
	public int cuentaHorasFaltadas () {
		int totalHoras=0;
		for(int i=0; i<listaFaltas.size(); i++) {
			totalHoras+=listaFaltas.get(i).getNumHoras();
		}
		return totalHoras;
	}
	//cuenta las horas faltadas en un mes que tiene una asignatura recorriendo el ArrayList de horas y comprobando si el mes coincide 
	//con el mes pasado como parametro
	public int cuentaHorasFaltadasMes (int mes) {
		int totalHoras=0;
		for(int i=0; i<listaFaltas.size();i++) {
			if(mes==listaFaltas.get(i).getMes()) {
				totalHoras+=listaFaltas.get(i).getNumHoras();
			}
		}
		return totalHoras;
	}
	//
	public int horasPorFaltar () {
		int horasQueQuedan=0;
		int totalHoras=this.cuentaHorasFaltadas();
		horasQueQuedan=horasMax-totalHoras;
		return horasQueQuedan;
	}
	
	//dia más faltado en una asignatura. Recorremos la lista de días faltados sumando en un int el contador de cada uno
	//posteriormente los agregamos a un array y buscamos el valor maximo. Cuando encontremos uno mayor que el anterior
	//el String que devuelve cambiará a dicho día de la semana, obteniendo el que mas ha acumulado en el contador.
	public  String diaMasFaltadoAsignatura () {
		String diaMasFaltado="";
		int cL=0; int cM=0;int cX=0; int cJ=0;int cV=0;
		for (int i=0; i<listaFaltas.size(); i++) {
			//Para medir los dias mas faltados segun las horas
			/*if(listaFaltas.get(i).getAsignatura().equalsIgnoreCase(nombre)) {
				String dia=listaFaltas.get(i).getDia();
				if(dia.equalsIgnoreCase("MONDAY")) {
					cL+=listaFaltas.get(i).getNumHoras();
				}
				else if(dia.equalsIgnoreCase("TUESDAY")) {
					cM+=listaFaltas.get(i).getNumHoras();
				}
				else if(dia.equalsIgnoreCase("WEDNESDAY")) {
					cX+=listaFaltas.get(i).getNumHoras();
				}
				else if(dia.equalsIgnoreCase("THURSDAY")) {
					cJ+=listaFaltas.get(i).getNumHoras();
				}
				else if(dia.equalsIgnoreCase("FRIDAY")) {
					cV+=listaFaltas.get(i).getNumHoras();
				}	
			}*/
			
			//Para medir los dias mas faltados según las faltas(dias)
			 if(listaFaltas.get(i).getAsignatura().equalsIgnoreCase(nombre)) {
				String dia=listaFaltas.get(i).getDia();
				if(dia.equalsIgnoreCase("MONDAY")) {
					cL++;
				}
				else if(dia.equalsIgnoreCase("TUESDAY")) {
					cM++;
				}
				else if(dia.equalsIgnoreCase("WEDNESDAY")) {
					cX++;
				}
				else if(dia.equalsIgnoreCase("THURSDAY")) {
					cJ++;
				}
				else if(dia.equalsIgnoreCase("FRIDAY")) {
					cV++;
				}	
			}
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
	
	public int faltasPorDias (int numDia) {
		int numFaltas=0;
		int cL=0; int cM=0;int cX=0; int cJ=0;int cV=0;
		for (int i=0; i<listaFaltas.size(); i++) {
			//Para medir los dias mas faltados segun las horas
			/*if(listaFaltas.get(i).getAsignatura().equalsIgnoreCase(nombre)) {
				String dia=listaFaltas.get(i).getDia();
				if(dia.equalsIgnoreCase("MONDAY")) {
					cL+=listaFaltas.get(i).getNumHoras();
				}
				else if(dia.equalsIgnoreCase("TUESDAY")) {
					cM+=listaFaltas.get(i).getNumHoras();
				}
				else if(dia.equalsIgnoreCase("WEDNESDAY")) {
					cX+=listaFaltas.get(i).getNumHoras();
				}
				else if(dia.equalsIgnoreCase("THURSDAY")) {
					cJ+=listaFaltas.get(i).getNumHoras();
				}
				else if(dia.equalsIgnoreCase("FRIDAY")) {
					cV+=listaFaltas.get(i).getNumHoras();
				}	
			}*/
			//Para medir los dias mas faltados según las faltas(dias)
			if(listaFaltas.get(i).getAsignatura().equalsIgnoreCase(nombre)) {
				String dia=listaFaltas.get(i).getDia();
				if(dia.equalsIgnoreCase("MONDAY")) {
					cL++;
				}
				else if(dia.equalsIgnoreCase("TUESDAY")) {
					cM++;
				}
				else if(dia.equalsIgnoreCase("WEDNESDAY")) {
					cX++;
				}
				else if(dia.equalsIgnoreCase("THURSDAY")) {
					cJ++;
				}
				else if(dia.equalsIgnoreCase("FRIDAY")) {
					cV++;
				}	
			}
		}
		
		if(numDia==1) {
			numFaltas=cL;
		}
		else if(numDia==2){
			numFaltas=cM;
		}
		else if(numDia==3) {
			numFaltas=cX;
		}
		else if(numDia==4) {
			numFaltas=cJ;
		}
		else if(numDia==5) {
			numFaltas=cV;
		}
		else
			numFaltas=0;
		
		return numFaltas;
	}
	
	public String listadoFaltas() {	
		String listado="";
		for (int i=0; i<listaFaltas.size(); i++) {
			listado+= listaFaltas.get(i).toString() + "\n";
		}
		return listado;
	}

	public String getListadoDeFaltas() {
		String h="";
		for (int i=0; i<listaFaltas.size();i++) {
			h+="Horas: "+listaFaltas.get(i).getNumHoras() + "Fecha: " + listaFaltas.get(i).getFecha();
		}
		return h;
	}
	
	//----- Lectura y escritura de Faltas -------------------------------------------------
	
	
	public  void escribirFaltas(Faltas f) throws FileNotFoundException , IOException {
		File archivoFaltas=new File("data\\faltas.dat");
		Faltas falta=f;
		FileOutputStream fo=null;
		ObjectOutputStream datos=null;
		User user=new User();
		if(!archivoFaltas.exists()) {
			fo=new FileOutputStream(archivoFaltas);
			datos=new ObjectOutputStream(fo);
		}
		else {
			fo=new FileOutputStream(archivoFaltas, true);
			datos=new MiObjectOutputStream(fo);
		}
		for(int i=0; i<user.getListaAsignaturas().size();i++) {
			if(user.getListaAsignaturas().get(i).getNombre().equals(f.getAsignatura())) {
				falta=new Faltas(falta.getAsignatura(), falta.getNumHoras(), falta.getFecha());
				datos.writeObject(falta);
				break;
			}
			
		}
		datos.close();
	}
	
	public  void escribirFaltas(File f) throws FileNotFoundException , IOException {
		Faltas faltas;
		ArrayList<Faltas> aux =listaFaltas;
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
		for(int i=0;i<aux.size(); i++) {
			faltas=new Faltas(aux.get(i).getAsignatura(), aux.get(i).getNumHoras(), aux.get(i).getFecha());
			datos.writeObject(faltas);
		}
		datos.close();
	}

	public  String leerFaltas () throws FileNotFoundException , IOException, ClassNotFoundException {
		File faltasFile=new File("data\\faltas.dat");
		Faltas falta;
		String listadoFaltas = null;
		FileInputStream fi = null;
		ObjectInputStream datos = null;
		fi = new FileInputStream(faltasFile);
		datos=new ObjectInputStream(fi);
		try {
			while(true) {
				falta=(Faltas) datos.readObject();
				listadoFaltas=falta.toString();
				listaFaltas.add(falta);			
			}
		}
		catch(EOFException e) {}
		datos.close();
		return listadoFaltas;
	}
	
	//----- Setteo de Pellas ------------------------------------------
	
	public  void setFaltasInicio (Asignatura asignatura) throws FileNotFoundException , IOException, ClassNotFoundException {
		listaFaltas.clear();
		File faltasFile=new File("data\\faltas.dat");
		Faltas faltas=null;
		FileInputStream fi = null;
		ObjectInputStream datos = null;
		if(!faltasFile.exists()) {
			FileOutputStream fo=null;
			ObjectOutputStream datos1=null;
			fo=new FileOutputStream(faltasFile);
			datos1=new ObjectOutputStream(fo);
			datos1.close();
		}
		fi = new FileInputStream(faltasFile);
		datos=new ObjectInputStream(fi);
		try {
			while(true) {
				faltas=(Faltas) datos.readObject();
				if(faltas.getAsignatura().equalsIgnoreCase(nombre)) {
					listaFaltas.add(faltas);
				}
			}
		}catch(EOFException e) {}
		datos.close();
		
	}

	//----- Eliminar Faltas ------------------------------------------
	

	public void eliminarFaltasAsignatura (Asignatura asignatura) throws FileNotFoundException , ClassNotFoundException, IOException {
		User uAux=new User(); //creamos un usuario aux para acceder a la lista de asignaturas
		Asignatura asAux=null;
		Faltas faltas;
		ArrayList<Faltas> listaAux=new ArrayList<Faltas>();
		
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) { //este bucle recorre la lista de asignaturas del usuario
			asAux=uAux.getListaAsignaturas().get(i);
			for(int k=0; k<asAux.listaFaltas.size();k++) { //este bucle recorre la lista de faltas de la asignatura del bucle anterior
				faltas=asAux.listaFaltas.get(k);
				if(!faltas.getAsignatura().equalsIgnoreCase(asignatura.getNombre())) { //si tiene el mismo nombre que la asignatura pasada por parametro no se añaden las faltas a la lista de faltas auxiliar
					listaAux.add(faltas);  //como la falta no es de la asignatura pasada por parametro la añado a la lista auxiliar de faltas
				}
			}
		}
		File faltasFile=new File("data\\faltas.dat"); 
		faltasFile.delete();  //eliminamos los datos del archivo faltas.dat
		for(int j=0; j<listaAux.size();j++) {  //recorremos la lista de faltas auxiliar (sin las faltas de la asignatura eliminada)
			faltas=listaAux.get(j);
			escribirFaltas(faltas); //escribimos las faltas en el archivo faltas.dat
		}
	}
	
	public void eliminarFalta (String asignatura, LocalDate fecha) throws FileNotFoundException, ClassNotFoundException, IOException {
		User uAux=new User();
		Asignatura asAux=null;
		Faltas falta;
		ArrayList<Faltas> listaAux=new ArrayList<Faltas>();
		for(int i=0; i<uAux.getListaAsignaturas().size();i++) { //este bucle recorre la lista de asignaturas del usuario
			asAux=uAux.getListaAsignaturas().get(i);
			for(int k=0; k<asAux.listaFaltas.size();k++) { //este bucle recorre la lista de faltas de la asignatura del bucle anterior
				falta=asAux.listaFaltas.get(k);
				if(!falta.getAsignatura().equalsIgnoreCase(asignatura) || !falta.getFecha().equals(fecha)) {
					listaAux.add(falta);
				}
			}
		}
		File faltasFile=new File("data\\faltas.dat"); 
		faltasFile.delete();  //eliminamos los datos del archivo faltas.dat
		for(int j=0; j<listaAux.size();j++) {  //recorremos la lista de faltas auxiliar (sin las faltas de la asignatura eliminada)
			falta=listaAux.get(j);
			escribirFaltas(falta); //escribimos las faltas en el archivo faltas.dat
		}
		for(int k=0; k<uAux.getListaAsignaturas().size();k++) {
			setFaltasInicio(uAux.getListaAsignaturas().get(k));
		}
	}

	

	//----- ToString -----------------------------------------------------------------
		
	@Override
	public String toString() {
		return  nombre + "\n" + toStringListaFaltas();
	}

	public String toStringListaFaltas() {
		String listado="";
		for(int i=0; i<listaFaltas.size(); i++) {
			listado+=listaFaltas.get(i).toString() + "\n";
		}
		return listado;
	}
	
	//----- Setters and Getters -------------------------------------------------------

	int getHorasMax() {
		return horasMax;
	}

	void setHorasMax(int horasMax) {
		this.horasMax = horasMax;
	}

	String getNombre() {
		return nombre;
	}

	void setNombre(String nombre) {
		this.nombre = nombre;
	}

	ArrayList<Faltas> getListaFaltas() {
		return listaFaltas;
	}

	void setListaFaltas(ArrayList<Faltas> listaFaltas) {
		this.listaFaltas = listaFaltas;
	}

}