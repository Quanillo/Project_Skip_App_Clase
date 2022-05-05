package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class User {
	
		User(){
			try {
				setAsignaturasInicio();
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	
		//----- Atributos -----------------------------------------------------------------

		ArrayList<Asignatura> listaAsignaturas =new ArrayList<Asignatura>();

		//----- Metodos -------------------------------------------------------------------

		ArrayList<Asignatura> getListaAsignaturas() {
			return listaAsignaturas;
		}

		//----- Lectura y escritura de asignaturas ------------------------------------------
		
		public void escribirAsignatura(Asignatura asig) throws FileNotFoundException , IOException {
			File asignaturasFile=new File("data\\asignaturas.dat");
			Asignatura asignatura=asig;
			FileOutputStream fo=null;
			ObjectOutputStream datos=null;
			if(!asignaturasFile.exists()) {
				fo=new FileOutputStream(asignaturasFile);
				datos=new ObjectOutputStream(fo);
			}
			else {
				fo=new FileOutputStream(asignaturasFile, true);
				datos=new MiObjectOutputStream(fo);
			}
			
			asignatura=new Asignatura(asignatura.getNombre(), asignatura.getHorasMax());
			datos.writeObject(asignatura);
			this.listaAsignaturas.add(asignatura);
			
			datos.close();
		}

		public void leer (File f) throws FileNotFoundException , IOException, ClassNotFoundException {
			Asignatura asignatura;
			FileInputStream fi = null;
			ObjectInputStream datos = null;
			fi = new FileInputStream(f);
			datos=new ObjectInputStream(fi);
			try {
				while(true) {
					asignatura=(Asignatura) datos.readObject();
					listaAsignaturas.add(asignatura);
				}
			}
			catch(EOFException e) {}
			datos.close();
		}
	
		//----- Setteo de Asignaturas ------------------------------------------
		
		public  void setAsignaturasInicio () throws FileNotFoundException , IOException, ClassNotFoundException {	
			listaAsignaturas.clear();
			File directorio=new File ("." , "data");
			File asignaturasFile=new File("data//asignaturas.dat");
			Asignatura asignatura;
			FileInputStream fi = null;
			ObjectInputStream datos = null;
			if(!directorio.exists()) {
				directorio.mkdir();
			}
			if(!asignaturasFile.exists()) {
				FileOutputStream fo=null;
				ObjectOutputStream datos1=null;
				fo=new FileOutputStream(asignaturasFile);
				datos1=new ObjectOutputStream(fo);
				datos1.close();
			}
			fi = new FileInputStream(asignaturasFile);
			datos=new ObjectInputStream(fi);
			try {
				while(true) {
					asignatura=(Asignatura) datos.readObject();
					listaAsignaturas.add(asignatura); 
					asignatura.setFaltasInicio(asignatura);
				}
			}
			catch(EOFException e) {}
			datos.close();
			
		}

		//----- Eliminar Asignaturas ------------------------------------------
		
		public void eliminarAsignatura (String nombreAsignatura) throws FileNotFoundException , IOException, ClassNotFoundException {
			
			Asignatura asignatura;
			ArrayList<Asignatura> listaAux=new ArrayList<Asignatura>(); //creamos una lista de asignaturas auxiliar
			for(int i=0; i<listaAsignaturas.size();i++) { //recorremos  la lista de asignaturas
				asignatura=listaAsignaturas.get(i);
				if(asignatura.getNombre().equalsIgnoreCase(nombreAsignatura)) { //si tiene el mismo nombre que el pasado por parametro eliminamos las faltas de dicha asignatura
					asignatura.eliminarFaltasAsignatura(asignatura);
				}
				else {
					listaAux.add(asignatura);
				}
			}
			listaAsignaturas.clear(); //limpiamos la lista de asignaturas
			File asignaturasFile=new File("data\\asignaturas.dat");
			asignaturasFile.delete(); //eliminamos el fichero de asignaturas.dat
			
			for(int j=0; j<listaAux.size();j++) {
				asignatura=listaAux.get(j);
				listaAsignaturas.add(asignatura);
				escribirAsignatura(asignatura);
			}
		}
	
		
}


//CODIGO TRASH

/*public void escribir(File f) throws FileNotFoundException , IOException {
Asignatura asignatura=null;
FileOutputStream fo=null;
ObjectOutputStream datos=null;
ArrayList<Asignatura> aux =listaAsignaturas;

if(!f.exists()) {
	fo=new FileOutputStream(f);
	datos=new ObjectOutputStream(fo);
}
else {
	fo=new FileOutputStream(f, true);
	datos=new MiObjectOutputStream(fo);
}
for(int i=0;i<aux.size(); i++) {
	asignatura=new Asignatura(aux.get(i).getNombre(), aux.get(i).getHorasMax());
	datos.writeObject(asignatura);
	this.listaAsignaturas.add(asignatura);
}
datos.close();
}*/
