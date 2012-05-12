package ecos.kidsgame.domainlogic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ecos.kidsgame.domainlogin.Fonema;
import ecos.kidsgame.domainlogin.Representacion;
import ecos.kidsgame.domainlogin.Silaba;

public class SilabaTest {

	private Silaba GenerarSilaba(String silaba) {
		return new Silaba(Fonema.desde(silaba), Representacion.desde(silaba));
	}

	@Test
	public void comoSeCreaUnaSilaba() {
		Fonema fonema = Fonema.desde("ca");
		Representacion representacion = Representacion.desde("ca");
		new Silaba(fonema, representacion);
	}

	@Test
	public void comoSeComparanLasSilabas() {
		Silaba unaSilabaCreadaConUnFonema = new Silaba(Fonema.desde("ca"),
				Representacion.desde("ca"));
		Silaba otraSilabaCreadaConElMismoFonema = new Silaba(
				Fonema.desde("ca"), Representacion.desde("ca"));

		assertEquals(unaSilabaCreadaConUnFonema,
				otraSilabaCreadaConElMismoFonema);

		Silaba otraSilabaCreadaConOtroFonema = new Silaba(Fonema.desde("za"),
				Representacion.desde("ca"));
		assertFalse(unaSilabaCreadaConUnFonema
				.equals(otraSilabaCreadaConOtroFonema));
	}

	@Test
	public void deQueSeComponeUnaSilaba() {
		Silaba silaba = GenerarSilaba("ca");
		assertEquals(Fonema.desde("ca"), silaba.getFonema());
		assertEquals(Representacion.desde("ca"), silaba.getRepresentacion());
	}

	@Test
	public void convertirUnaSilabaEnCadena() {
		Silaba silaba = GenerarSilaba("ca");
		assertEquals("ca", silaba.toString());
	}

}
