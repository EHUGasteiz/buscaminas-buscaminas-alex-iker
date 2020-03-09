package Modelo;

import java.util.Arrays;
import java.util.Random;

public class Partida {
	private int ancho;
	private int alto;
	private int[] minas;
	
	public Partida(int ancho, int alto, int cantidad) {
		super();
		this.ancho = ancho;
		this.alto = alto;
		generarMinas(cantidad);
	}
	
	private void generarMinas(int cantidad) {
		minas = new int[cantidad];
		int casillas = this.ancho * this.alto;
		
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
		
		Arrays.sort(minas);
	}
	
	private int randInt(int min, int max) {
		Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
	
	public void imprimirMinas() {
		String m = "";
		for (int i = 0; i < minas.length; i++) {
			if(i == 0) {
				m += minas[i];
			}else {
				m += ", " + minas[i];
			}
			
		}
		
		javax.swing.JOptionPane.showMessageDialog(null, m);
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public int[] getMinas() {
		return minas;
	}
	
	public int getCantCasillas() {
		return ancho * alto;
	}
	
	public boolean esBomba(int posicion) {
		boolean esBomba = false;
		
		int posicionArray = Arrays.binarySearch(minas, posicion);
		if(posicionArray >= 0) {
			esBomba = true;
		}
		
		return esBomba;
	}
}
