package Modelo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PartidaTest {
	Partida p1;
	Partida p2;
	Partida p3;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		p1 = Partida.getPartida("Iker", 1);
		p2 = Partida.getPartida("Iker", 1);
		p3 = Partida.getPartida("Iker", 1);
	}
	
	@Test
	public void testGetPartida() {
		assertNotNull(Partida.getPartida("Iker", 1));
		assertNotNull(Partida.getPartida("Iker", 2));
		assertNotNull(Partida.getPartida("Iker", 3));
	}
	
	@Test
	public void testClickCasilla() {
		for (int i = 0; i < 100; i++) {
			p1.clickCasilla(i);
		}
	}
	
	@Test
	public void testClickBandera() {
		p1.clickBandera(0);
		assertEquals(p1.getBanderas().size(), 1);
		p1.clickBandera(0);
		assertEquals(p1.getBanderas().size(), 0);
	}
	
	@Test
	public void testGetAncho() {
		assertEquals(p1.getAncho(), 10);
	}
	
	@Test
	public void testGetAlto() {
		assertEquals(p1.getAlto(), 10);
	}
	
	@Test
	public void testGetCantCasillas() {
		assertEquals(p1.getCantCasillas(), 100);
	}
	
	@Test
	public void testGetCantMinas() {
		assertEquals(p1.getCantMinas(), 10);
	}
	
	@Test
	public void testGetMinas() {
		assertEquals(p1.getMinas().size(), 10);
	}
	
	@Test
	public void testGetBanderas() {
		assertEquals(p1.getBanderas().size(), 0);
	}
	
	@Test
	public void testGetDificultad() {
		assertEquals(p1.getDificultad(), 1);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals(p1.getNombre(), "Iker");
	}
	
	@Test
	public void testGetPuntuacion() {
		assertNotEquals(p1.getPuntuacion(), 0);
	}	
}
