package ecos.kidsgame.domainlogic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import ecos.kidsgame.domainlogic.test.objectModels.PalabraOM;
import ecos.kidsgame.domainlogic.test.objectModels.SilabaOM;
import ecos.kidsgame.domainlogin.Fonema;
import ecos.kidsgame.domainlogin.Palabra;
import ecos.kidsgame.domainlogin.Representacion;
import ecos.kidsgame.domainlogin.Silaba;

public class PalabraTest {

    @Test
    public void comoSeCreaUnaPalabra() {
	Collection<Fonema> fonemas = new ArrayList<Fonema>(Arrays.asList(new Fonema[] { Fonema.desde("ca"), Fonema.desde("sa") }));
	new Palabra(fonemas);
    }

    @Test
    public void comoAveriguarSiUnaPalabraContieneUnaDeterminadaSilaba() {
	Palabra palabra = PalabraOM.generarPalabra(new String[] { "ca", "sa" });

	assertTrue(palabra.hasSilaba(SilabaOM.generarSilaba("ca")));
	assertFalse(palabra.hasSilaba(SilabaOM.generarSilaba("za")));
    }

    @Test
    public void comoAveriguarSiUnaPalabraContieneAlgunaDeLasSilabas() {
	Palabra palabra = PalabraOM.generarPalabra(new String[] { "ca", "sa" });
	Collection<Silaba> silabasCoincidentes = SilabaOM.generarSilabas(new String[] { "man", "ca" });
	Collection<Silaba> silabasNoCoincidentes = SilabaOM.generarSilabas(new String[] { "ma", "ni", "cu", "ra" });

	assertTrue(palabra.hasAny(silabasCoincidentes));
	assertFalse(palabra.hasAny(silabasNoCoincidentes));
    }

    @Test
    public void comoSeConvierteUnaPalabraEnCadenaDeCaracteres_creadaDesdeColeccionDeFonemas() {
	Palabra palabra = PalabraOM.generarPalabra(new String[] { "pa", "la", "bra" });

	assertEquals("PALABRA", palabra.toString());
    }

    @Test
    public void comoSeConvierteUnaPalabraRepresentacion_creadaDesdeColeccionDeFonemas() {
	Palabra palabra = PalabraOM.generarPalabra(new String[] { "pa", "la", "bra" });

	assertEquals(Representacion.desde("PALABRA"), palabra.toRepresentacion());
    }

    @Test
    public void comoSeSustituyeUnaSilabaDeUnaPalabraPorOtra() {
	Palabra palabra = PalabraOM.generarPalabra(new String[]{"a","lu","ni","zar"});
	palabra.sustituirSilabaPorSilaba(SilabaOM.generarSilaba("zar"),SilabaOM.generarSilaba("___"));
	
	assertEquals("ALUNI___", palabra.toString());
	
    }
}
