package Vista;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.Puntuacion;

class VentanaRankingTest {
	ArrayList<Puntuacion> puntuaciones;
	VentanaRanking vr;

	@BeforeEach
	void setUp() throws Exception {
		puntuaciones = new ArrayList<>();
		puntuaciones.add(new Puntuacion("iker", 50, 1));
		puntuaciones.add(new Puntuacion("iker", 50, 2));
		puntuaciones.add(new Puntuacion("iker", 50, 3));
		vr = new VentanaRanking(puntuaciones, 0);
	}

	@Test
	void testVentanaRanking() {
		assertNotNull(new VentanaRanking(puntuaciones, 0));
		assertNotNull(new VentanaRanking(puntuaciones, 1));
		assertNotNull(new VentanaRanking(puntuaciones, 2));
		assertNotNull(new VentanaRanking(puntuaciones, 3));
	}
	
	@Test
	void testDispose() {
		
	}

}
