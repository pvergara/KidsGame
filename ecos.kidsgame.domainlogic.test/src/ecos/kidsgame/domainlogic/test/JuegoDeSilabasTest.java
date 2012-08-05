package ecos.kidsgame.domainlogic.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ecos.kidsgame.domainlogin.JuegoDeSilabas;
import ecos.kidsgame.domainlogin.Silaba;

public class JuegoDeSilabasTest {

	private JuegoDeSilabas juegoDeSilabas;

	@Before
	public void setUp() {
		juegoDeSilabas = JuegoDeSilabas.getInstancia();
	}

	@Test
	public void comoSeInstanciaElJuegoDeSilabas() {
		assertTrue(juegoDeSilabas instanceof JuegoDeSilabas);
		assertNotNull(juegoDeSilabas);
	}

	@Test
	public void comoSeObtienenLosGruposDeSilabasDelJuego() {
		List<List<Silaba>> agrupacionDeSilabas = juegoDeSilabas.getAgrupacionDeSilabas(); 
		assertNotNull(agrupacionDeSilabas);
		assertTrue(agrupacionDeSilabas.size()>0);
		
	}

}
