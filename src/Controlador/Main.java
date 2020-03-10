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
			partida = new Partida(10, 20, 25);
			break;
		case 2:
			partida = new Partida(20, 20, 50);
			break;
		}
		
		//Creamos el tablero
		crearVenataBuscaminas();
	}
	
	//Crear ventana buscaminas
	private static void crearVenataBuscaminas(){
		//Creamos la ventana del tablero del buscaminas
		vb = new VentanaBuscaminas();
		
		vb.setBounds(100, 100, partida.getAncho() * 30, partida.getAlto() * 30);
		
		//Lo mostramos
		vb.setVisible(true);
	}
}
