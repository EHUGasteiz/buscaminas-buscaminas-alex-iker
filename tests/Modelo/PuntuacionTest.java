package Modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PuntuacionTest {
	Puntuacion p1;

	@BeforeEach
	void setUp() throws Exception {
		p1 = new Puntuacion("iker", 50, 1);
	}

	@Test
	void testPuntuacion() {
		assertNotNull(new Puntuacion("iker", 50, 1));
	}
	
	@Test
	void testGetNombre() {
		assertEquals(p1.getNombre(), "iker");
	}
	
	@Test
	void testGetPuntuacion() {
		assertEquals(p1.getPuntuacion(), 50);
	}
	
	@Test
	void testGetDificultad() {
		assertEquals(p1.getDificultad(), 1);
	}
	
	@Test
	void testToString() {
		assertEquals(p1.toString(), "Nombre:" + p1.getNombre() + "|Puntuacion:" + p1.getPuntuacion() + "|Dificultad:" + p1.getDificultad());
	}
	
	@Test
	void testCompareTo() {
		assertEquals(p1.compareTo(new Puntuacion("iker", 51, 1)), -1);
		assertEquals(p1.compareTo(new Puntuacion("iker", 49, 1)), 1);
		assertEquals(p1.compareTo(new Puntuacion("iker", 50, 1)), 0);
	}

}
