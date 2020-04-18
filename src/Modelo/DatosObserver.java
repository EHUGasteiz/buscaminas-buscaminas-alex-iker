package Modelo;

public class DatosObserver {
	private int accion;
	private int posicion;
	private String texto;

	public DatosObserver(int accion, int posicion, String texto) {
		super();
		this.accion = accion;
		this.posicion = posicion;
		this.texto = texto;
	}

	public int getAccion() {
		return accion;
	}

	public int getPosicion() {
		return posicion;
	}

	public String getTexto() {
		return texto;
	}
}
