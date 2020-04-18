package Controlador;

import Modelo.Partida;
import Vista.VentanaBuscaminas;

public class Main {
	public static VentanaBuscaminas vb;
	public static Partida partida;

	public static void main(String[] args) {

		solicitarDificultad(0);

	}

	// Solicitamos la dificultad a la que desea jugar el jugador
	private static void solicitarDificultad(int opc) {
		// Definimos las variables dependiendo de la dificultad seleccionada
		switch (opc) {
		case 0:
			partida = Partida.getPartida(10, 10, 10);
			break;
		case 1:
			partida = Partida.getPartida(10, 20, 25);
			break;
		case 2:
			partida = Partida.getPartida(20, 10, 25);
			break;
		case 3:
			partida = Partida.getPartida(20, 20, 50);
			break;
		}

		// Creamos el tablero
		crearVentanaBuscaminas();
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

	public static void cargar() {
		vb.dispose();
		solicitarDificultad(0);
	}
}
