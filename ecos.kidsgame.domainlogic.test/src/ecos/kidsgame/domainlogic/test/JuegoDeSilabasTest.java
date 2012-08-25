package ecos.kidsgame.domainlogic.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ecos.kidsgame.domainlogin.Agrupacion;
import ecos.kidsgame.domainlogin.JuegoDeSilabas;
import ecos.kidsgame.domainlogin.Palabra;
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
		List<Agrupacion> agrupaciones = juegoDeSilabas.getAgrupaciones(); 
		assertNotNull(agrupaciones);
		assertTrue(agrupaciones.size()>0);

		List<Silaba> agrupacionDeSilabas = agrupaciones.get(0).getSilabas(); 
		assertNotNull(agrupacionDeSilabas);
		assertTrue(agrupacionDeSilabas.size()>0);
		
	}

	@Test
	public void comoSeObtienenLasGruposDePalabrasDelJuego() {
		List<Agrupacion> agrupaciones = juegoDeSilabas.getAgrupaciones(); 
		assertNotNull(agrupaciones);
		assertTrue(agrupaciones.size()>0);

		List<Palabra> palabrasAgrupadas = agrupaciones.get(0).getPalabras();
		assertNotNull(palabrasAgrupadas);
		assertTrue(palabrasAgrupadas.size()>0);
		
	}

}
