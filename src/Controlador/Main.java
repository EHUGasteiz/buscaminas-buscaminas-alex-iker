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
}
