package Modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DatosOnserverTest {
	DatosObserver d1;

	@BeforeEach
	void setUp() throws Exception {
		d1 = new DatosObserver(1,1,"a");
	}

	@Test
	void testDatosObserver() {
		assertNotNull(new DatosObserver(1,1,"a"));
	}

	@Test
	void testGetAccion() {
		assertEquals(d1.getAccion(), 1);
	}

	@Test
	void testGetPosicion() {
		assertEquals(d1.getPosicion(), 1);
	}

	@Test
	void testGetTexto() {
		assertEquals(d1.getTexto(), "a");
	}

}
