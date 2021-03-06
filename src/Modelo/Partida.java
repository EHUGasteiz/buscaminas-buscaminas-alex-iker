package Modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Controlador.Main;

public class Partida extends Observable {
	private int ancho;
	private int alto;
	private static int dificultad;
	private static String nombre;
	private int puntuacion;
	private Timer timer;
	private Map<Integer, Integer> minas;
	private Map<Integer, Integer> posAbiertas;
	private Map<Integer, Integer> banderas;
	private static Partida mPartida;

	// Constructor privado de la clase
	private Partida(int ancho, int alto, int cantidad) {
		super();
		this.ancho = ancho;
		this.alto = alto;
		this.puntuacion = 0;
		
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				actualizarPuntuacion();
			}		
		};
		this.timer = new Timer();
		this.timer.scheduleAtFixedRate(timerTask, 0, 1000);
		
		posAbiertas = new HashMap<Integer, Integer>();
		banderas = new HashMap<Integer, Integer>();
		generarMinas(cantidad);
	}

	/**
	 * getPartida. Metodo estatico que devuelve la instancia unica de la clase
	 * 
	 * @return la instancia unica
	 */
	public static Partida getPartida(String pNombre,int pDificultad) {
		dificultad = pDificultad;
		nombre = pNombre;
		switch (pDificultad) {
		case 1:
			mPartida = new Partida(10, 10, 10);
			break;
		case 2:
			mPartida =  new Partida(20, 10, 25);
			break;
		case 3:
			mPartida =  new Partida(20, 20, 50);
			break;
		}
		

		return mPartida;
	}

	private void generarMinas(int cantidad) {
		minas = new HashMap<Integer, Integer>();

		// Llenamos el mapa de posiciones de mina con numeros aleatorios no repetidos
		int i = 0;
		while (i < cantidad) {
			int random;
			random = randInt(0, getCantCasillas() - 1);
			do {
				random = randInt(0, getCantCasillas() - 1);
			} while (minas.containsKey(random));
			minas.put(random, 0);
			i++;
		}
	}

	private boolean esBomba(int posicion) {
		return minas.containsKey(posicion);
	}

	private void calcularCasillas(int posicion) {
		// Comprobamos si la casilla seleccionada no es una bandera
		if (!banderas.containsKey(posicion)) {
			// Comprobamos si la casilla seleccionada es una bomba
			if (esBomba(posicion)) {
				// Mostramos todas las bombas
				minas.forEach((k, v) -> {
					setChanged();
					notifyObservers(new DatosObserver(0, k, null));
				});
				// Avisamos de que ha explotado la bomba
				detenerTimer();
				setChanged();
				notifyObservers(new DatosObserver(2, -1, "BOOOOOM!!!"));
			} else {
				List<Integer> casillasMostrar = new LinkedList<>();
				int bombasCerca = 0;

				// Calculamos las bombas al rededor de la casilla seleccionada
				for (int i = (posicion / ancho) - 1; i <= (posicion / ancho) + 1; i++) {
					for (int j = (posicion % ancho) - 1; j <= (posicion % ancho) + 1; j++) {
						// Comprobamos que la casilla sea valida y que no ha sido ya seleccionada
						if (i >= 0 && i <= alto - 1 && j >= 0 && j <= ancho - 1 && (i * alto) + j != posicion) {
							// Guardamos la casilla para mostrarla si no se encuentran bombas
							casillasMostrar.add((i * ancho) + j);
							// Actualizamos el ontador de bombas si es una bomba
							if (esBomba((i * ancho) + j)) {
								bombasCerca++;
							}
						}
					}
				}

				posAbiertas.put(posicion, null);

				if (bombasCerca == 0) {
					// Si no hay bombas cerca mostramos la casilla y abrimos las adyacentes
					setChanged();
					notifyObservers(new DatosObserver(1, posicion, null));
					for (int i : casillasMostrar) {
						if (!posAbiertas.containsKey(i)) {
							calcularCasillas(i);
						}
					}
				} else {
					// Mostramos la casilla con el numero de bombas adyacentes
					setChanged();
					notifyObservers(new DatosObserver(1, posicion, "" + bombasCerca));
				}
			}
		}
	}
	
	public void clickCasilla(int posicion) {
		calcularCasillas(posicion);
		if (posAbiertas.size() == getCantCasillas() - minas.size()) {
			detenerTimer();
			Main.escribiRanking();
			setChanged();
			notifyObservers(new DatosObserver(3, -1, null));
		}
	}

	public void clickBandera(int posicion) {
		if (banderas.containsKey(posicion)) {
			banderas.remove(posicion);
			setChanged();
			notifyObservers(new DatosObserver(5, posicion, "" + banderas.size()));
		} else {
			banderas.put(posicion, null);
			setChanged();
			notifyObservers(new DatosObserver(4, posicion, "" + banderas.size()));
		}
	}
	
	private void actualizarPuntuacion() {
		puntuacion++;
		setChanged();
		notifyObservers(new DatosObserver(6, -1, null));
	}

	private int randInt(int min, int max) {
		// Obtenemos un numero para poner las bombas
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public int getCantCasillas() {
		return ancho * alto;
	}

	public int getCantMinas() {
		return minas.size();
	}
	
	public Map<Integer, Integer> getMinas(){
		return minas;
	}
	
	public Map<Integer, Integer> getBanderas(){
		return banderas;
	}
	
	public int getDificultad() {
		return dificultad;
	}
	
	public String getNombre() {
		return nombre;
	}

	public int getPuntuacion() {
		return puntuacion;
	}
	
	private void detenerTimer() {
		timer.cancel();
	}
}
