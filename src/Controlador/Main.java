package Controlador;

import Modelo.Partida;
import Vista.VentanaBuscaminas;
import Vista.VentanaInicio;

public class Main {
	public static VentanaBuscaminas vb;
	public static Partida partida;
	public static VentanaInicio vi;
	
	public static void main(String[] args) {
		crearVentanaInicio();
		

	}
	
	// Crear ventana buscaminas
	public static void crearVentanaBuscaminas() {

		// Creamos la ventana del tablero del buscaminas
		vb = new VentanaBuscaminas();

		partida.addObserver(vb);

		vb.setBounds(100, 100, partida.getAncho() * 30, partida.getAlto() * 30 + 50);

		// Lo mostramos
		vb.setVisible(true);
	}
	
	private static void crearVentanaInicio() {
		vi = new VentanaInicio();
		vi.setVisible(true);
	}

	public static void cargar() {
		vb.dispose();
	
		int dif=partida.getDificultad();
		String nom=partida.getNombre();
		partida = Partida.getPartida(nom,dif);
		crearVentanaBuscaminas();
		
	}
}
