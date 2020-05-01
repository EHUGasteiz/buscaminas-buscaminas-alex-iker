package Modelo;

import java.io.Serializable;

public class Puntuacion implements Serializable, Comparable<Puntuacion>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int puntuacion;
	private int dificultad;
	
	public Puntuacion(String nombre, int puntuacion, int dificultad) {
		super();
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.dificultad = dificultad;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntuacion() {
		return puntuacion;
	}
	
	public int getDificultad() {
		return dificultad;
	}

	@Override
	public String toString() {
		return "Nombre:" + nombre + "|Puntuacion:" + puntuacion + "|Dificultad:" + dificultad;
	}

	@Override
	public int compareTo(Puntuacion o) {
		return this.puntuacion - o.getPuntuacion();
	}
	
	
}
