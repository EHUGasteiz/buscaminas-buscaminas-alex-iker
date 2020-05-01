package Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import Modelo.Partida;
import Modelo.Puntuacion;
import Vista.VentanaBuscaminas;
import Vista.VentanaInicio;
import Vista.VentanaRanking;

public class Main {
	public static VentanaBuscaminas vb;
	public static Partida partida;
	public static VentanaInicio vi;

	public static void main(String[] args) {
		crearVentanaInicio();

	}
	
	// Iniciar la partida con el nombre y dificultad seleccionado
	public static void iniciar(String nombre,int dificultad) {
		vi.dispose();
		partida = Partida.getPartida(nombre,dificultad);
		crearVentanaBuscaminas();
		//System.out.println(partida.getNombre());
	}
	
	// Crear ventana buscaminas
	private static void crearVentanaBuscaminas() {

		// Creamos la ventana del tablero del buscaminas
		vb = new VentanaBuscaminas();

		partida.addObserver(vb);

		vb.setBounds(100, 100, partida.getAncho() * 30, partida.getAlto() * 30 + 50);

		// Lo mostramos
		vb.setVisible(true);
	}

	// Creamos ventana de inicio
	private static void crearVentanaInicio() {
		vi = new VentanaInicio();
		vi.setVisible(true);
	}

	// Método para reiniciar
	public static void cargar() {
		vb.dispose();
		int dif = partida.getDificultad();
		String nom = partida.getNombre();
		partida = Partida.getPartida(nom, dif);
		crearVentanaBuscaminas();

	}
	
	//Metodo escribir datos en el ranking
		public static void escribiRanking() {
			ArrayList<Puntuacion> puntuaciones = leerRanking();
			ArrayList<Puntuacion> dif1 = new ArrayList<>();
			ArrayList<Puntuacion> dif2 = new ArrayList<>();
			ArrayList<Puntuacion> dif3 = new ArrayList<>();
			
			for (Puntuacion puntuacion : puntuaciones) {
				switch (puntuacion.getDificultad()) {
				case 1:
					dif1.add(puntuacion);
					break;
				case 2:
					dif2.add(puntuacion);
					break;
				case 3:
					dif3.add(puntuacion);
					break;
				}
			}
			
			switch (partida.getDificultad()) {
			case 1:
				dif1.add(new Puntuacion(partida.getNombre(), partida.getPuntuacion(), 1));
				Collections.sort(dif1);
				while(dif1.size() > 10) {
					dif1.remove(dif1.size() - 1);
				}
				break;
			case 2:
				dif2.add(new Puntuacion(partida.getNombre(), partida.getPuntuacion(), 2));
				Collections.sort(dif1);
				while(dif2.size() > 10) {
					dif2.remove(dif2.size() - 1);
				}
				break;
			case 3:
				dif3.add(new Puntuacion(partida.getNombre(), partida.getPuntuacion(), 3));
				Collections.sort(dif1);
				while(dif3.size() > 10) {
					dif3.remove(dif3.size() - 1);
				}
				break;
			}
			
			try {
		    	FileOutputStream f = new FileOutputStream(new File("ranking.txt"));
				ObjectOutputStream o = new ObjectOutputStream(f);

				// Write objects to file
				for (Puntuacion puntuacion : dif1) {
					o.writeObject(puntuacion);
				}
				for (Puntuacion puntuacion : dif2) {
					o.writeObject(puntuacion);
				}
				for (Puntuacion puntuacion : dif3) {
					o.writeObject(puntuacion);
				}

				o.close();
				f.close();
	            
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	//Metodo leer datos del ranking
	public static ArrayList<Puntuacion> leerRanking(){
		ArrayList<Puntuacion> puntuaciones = new ArrayList<>();
		
	    try {
	    	ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("ranking.txt"));

		    Puntuacion puntuacion = null;

		    while ((puntuacion = (Puntuacion) inputStream.readObject()) != null) {
		    	puntuaciones.add(puntuacion);
		    }
		    inputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	    System.out.println(puntuaciones.toString());
	    
	    return puntuaciones;
	}
	
	//Metodo mostrar puntuaciones
	public static void mostrarPuntuaciones(boolean completas) {
		VentanaRanking vr;
		
		if(completas) {
			vr = new VentanaRanking(leerRanking(),0);
		}else {
			vr = new VentanaRanking(leerRanking(),partida.getDificultad());
		}
		
		vr.setVisible(true);
	}
}
