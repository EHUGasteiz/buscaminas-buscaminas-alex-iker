package Controlador;

import java.util.Random;
import Vista.VentanaBuscaminas;

public class Main {
	private static VentanaBuscaminas vb;
	public static int[] minas;
	public static int ancho;
	public static int alto;

	public static void main(String[] args) {
		solicitarDificultad();
	}
	
	//Solicitamos la dificultad a la que desea jugar el jugador
	private static void solicitarDificultad() {
		//Variable temporal, más adelante se le pedira al usuario
		int opc = 0;
		
		//Variables con los datos para crear el tablero y la cantidad de minas
		int cantidadMinas = 0;
		
		//Definimos las variables dependiendo de la dificultad seleccionada
		switch (opc) {
		case 0:
			alto = 10;
			ancho = 10;
			cantidadMinas = 10;
			break;

		case 1:
			alto = 20;
			ancho = 10;
			cantidadMinas = 15;
			break;
			
		case 2:
			alto = 20;
			ancho = 20;
			cantidadMinas = 20;
			break;
		}
		
		//Generamos las posiciones de las minas aleatoriamente
		generarMinas(cantidadMinas, alto * ancho);
		//imprimirMinas();
		
		//Creamos el tablero
		crearVenataBuscaminas();
	}
	
	//Crear ventana buscaminas
	private static void crearVenataBuscaminas(){
		//Creamos la ventana del tablero del buscaminas
		vb = new VentanaBuscaminas();
		
		//Le damos el tamaño deseado
		vb.setLayout(alto, ancho);
		
		//Lo mostramos
		vb.setVisible(true);
	}

	//Generar aleatoriamete las posiciones en las que van a estar las minas
	private static void generarMinas(int cantidad, int casillas) {
		minas = new int[cantidad];
		
		//Llenamos el array de posiciones de mina con numeros aleatorios no repetidos
		int i = 0;
		while (i < cantidad) {
			int random = randInt(0, casillas - 1);
			
			boolean repetido = false;
			for(int j = 0; j <= i && repetido == false; j++) {
				if (random == minas[j]) {
					repetido = true;
				}
			}
			
			if (!repetido) {
				minas[i] = random;
				i++;
			}
		}
	}
	
	//Generamos un numero aleatorio dentro de un rango dependiente de la cantidad de casillas
	public static int randInt(int min, int max) {
		Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
	
	private static void imprimirMinas() {
		String m = "";
		for (int i = 0; i < minas.length; i++) {
			m += ", " + minas[i];
		}
		
		javax.swing.JOptionPane.showMessageDialog(null, m);
	}
}
