package ecos.kidsgame.domainlogic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ecos.kidsgame.domainlogin.Fonema;

public class FonemaTest {

	@Test
	public void comoSeCreaUnFonema() {
		Fonema.desde("ca");
	}

	@Test
	public void comoSeComparaUnFonema() {
		assertEquals(Fonema.desde("ca"), Fonema.desde("CA"));
		assertEquals(Fonema.desde("ca"), Fonema.desde("Ca"));

		assertFalse(Fonema.desde("ca").equals(Fonema.desde("za")));
	}
}
