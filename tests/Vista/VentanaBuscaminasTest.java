package Vista;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VentanaBuscaminasTest {
	VentanaBuscaminas vb;
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testVentanaBuscaminas() {
		vb = new VentanaBuscaminas();
	}
	
	@Test
	public void testInitialize() {
		vb = new VentanaBuscaminas();
	}
	
	@Test
	public void testCrearTablero() {
		vb.crearTablero();
		assertNotNull(vb);
	}
	

}
