package Modelo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
	
	public List<Integer> calcularCasillas(int posicion) {
		List<Integer> casillasMostrar = new LinkedList<Integer>();
		int bombasCerca = 0;
		
		int posCont[] = { posicion - ancho - 1, posicion - ancho,
				posicion - ancho + 1, posicion - 1, posicion + 1,
				posicion + ancho - 1, posicion + ancho,
				posicion + ancho + 1 };

		if (!(posCont[0] < 0 || posCont[0] / ancho < posCont[1] / ancho)) {
			casillasMostrar.add(posCont[0]);
			if(esBomba(posCont[0])) {
				bombasCerca++;
			}
		}

		if (!(posCont[1] < 0)) {
			casillasMostrar.add(posCont[1]);
			if(esBomba(posCont[1])) {
				bombasCerca++;
			}
		}

		if (!(posCont[2] < 0 || posCont[2] / ancho > posCont[1] / ancho)) {
			casillasMostrar.add(posCont[2]);
			if(esBomba(posCont[2])) {
				bombasCerca++;
			}
		}

		if (!(posCont[3] < 0 || posCont[3] / ancho < posicion / ancho)) {
			casillasMostrar.add(posCont[3]);
			if(esBomba(posCont[3])) {
				bombasCerca++;
			}
		}

		if (!(posCont[4] > getCantCasillas() - 1
				|| posCont[4] / ancho > posicion / ancho)) {
			casillasMostrar.add(posCont[4]);
			if(esBomba(posCont[4])) {
				bombasCerca++;
			}
		}

		if (!(posCont[5] > getCantCasillas() - 1
				|| posCont[5] / ancho < posCont[6] / ancho)) {
			casillasMostrar.add(posCont[5]);
			if(esBomba(posCont[5])) {
				bombasCerca++;
			}
		}

		if (!(posCont[6] > getCantCasillas() - 1)) {
			casillasMostrar.add(posCont[6]);
			if(esBomba(posCont[6])) {
				bombasCerca++;
			}
		}

		if (!(posCont[7] > getCantCasillas() - 1
				|| posCont[7] / ancho > posCont[6] / ancho)) {
			casillasMostrar.add(posCont[7]);
			if(esBomba(posCont[7])) {
				bombasCerca++;
			}
		}
		
		if(bombasCerca != 0) {
			casillasMostrar = new LinkedList<Integer>();
			casillasMostrar.add(bombasCerca);
		}
		
		return casillasMostrar;
	}
}
