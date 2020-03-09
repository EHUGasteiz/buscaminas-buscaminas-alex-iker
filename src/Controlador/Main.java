package Controlador;

import Modelo.Partida;
import Vista.VentanaBuscaminas;

public class Main {
	private static VentanaBuscaminas vb;
	public static Partida partida;

	public static void main(String[] args) {
		solicitarDificultad(0);
	}
	
	//Solicitamos la dificultad a la que desea jugar el jugador
	private static void solicitarDificultad(int opc) {
		//Definimos las variables dependiendo de la dificultad seleccionada
		switch (opc) {
		case 0:
			partida = new Partida(10, 10, 10);
			break;
		case 1:
			partida = new Partida(20, 10, 15);
			break;
		case 2:
			partida = new Partida(20, 20, 20);
			break;
		}
		
		//Creamos el tablero
		crearVenataBuscaminas();
	}
	
	//Crear ventana buscaminas
	private static void crearVenataBuscaminas(){
		//Creamos la ventana del tablero del buscaminas
		vb = new VentanaBuscaminas();
		
		//Lo mostramos
		vb.setVisible(true);
	}
}
