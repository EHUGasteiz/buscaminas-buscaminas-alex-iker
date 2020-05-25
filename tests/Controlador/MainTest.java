package Controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.Partida;
import Controlador.Main;


public class MainTest {
	Partida partida;
	@BeforeEach
	public void setUp() throws Exception {
		partida=Partida.getPartida("Alex", 1);
	}
	
	@Test
	public void testMain() {
		Main.crearVentanaInicio();
		
	}

	@Test
	public void testIniciar() {
		Main.iniciar("Alex", 1);
	}
	
	
	@Test
	public void testCrearVentanaInicio() {
		Main.crearVentanaInicio();
	}
	
	
}
