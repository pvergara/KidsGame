package ecos.kidsgame.domainlogic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ecos.kidsgame.domainlogin.Representacion;

public class RepresentacionTest {
    @Test
    public void comoSeCreaUnaRepresentacion() {
	Representacion.desde("representacion");
    }

    @Test
    public void comoSeIdentificanLasRepresentaciones() {
	Representacion unaRepresentacionCreadaDesdeUnaCadena = Representacion.desde("representacion");
	Representacion otraRepresentacionCreadaDesdeUnaCadenaSimilar = Representacion.desde("representacion");

	assertEquals(unaRepresentacionCreadaDesdeUnaCadena, otraRepresentacionCreadaDesdeUnaCadenaSimilar);

	Representacion otraRepresentacionCreadaDesdeUnaCadenaDistinta = Representacion.desde("");
	assertFalse(unaRepresentacionCreadaDesdeUnaCadena.equals(otraRepresentacionCreadaDesdeUnaCadenaDistinta));
    }

    @Test
    public void convertirUnaRepresentacionEnCadena() {
	assertEquals("representacion", Representacion.desde("representacion").toString());
    }
}
