package Vista;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VentanaInicioTest {
	VentanaInicio vi;

	
	@BeforeEach
	void setUp() throws Exception {
		vi = new VentanaInicio();
	}

	@Test
	void testVentanaInicio() {
		assertNotNull(new VentanaInicio());
	}
	
	@Test
	void testInitialize() {
		assertNotNull(new VentanaInicio());
	}

}
