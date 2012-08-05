package ecos.kidsgame.domainlogic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import ecos.kidsgame.domainlogic.test.objectModels.FonemaOM;
import ecos.kidsgame.domainlogic.test.objectModels.SilabaOM;
import ecos.kidsgame.domainlogin.EstadoDeLaPrueba;
import ecos.kidsgame.domainlogin.EstadoDelJuego;
import ecos.kidsgame.domainlogin.InformacionPendiente;
import ecos.kidsgame.domainlogin.Palabra;
import ecos.kidsgame.domainlogin.Silaba;
import ecos.kidsgame.domainlogin.Usuario;
import ecos.kidsgame.domainlogin.challenge.PruebaCompletarPalabrasConSilabas;
import ecos.kidsgame.domainlogin.challenge.PruebaEscucharLasSilabas;
import ecos.kidsgame.domainlogin.challenge.PruebaSeleccionarLasSilabasIndicadas;
import ecos.kidsgame.domainlogin.challenge.PruebasDelJuego;

public class GamePlayingTest {

	private Usuario usuario;
	private String explicacionDelJuego;
	private Silaba caSilaba;
	private Silaba ceSilaba;
	private Silaba zaSilaba;
	private Collection<Silaba> silabas;
	private String explicacionDeLaPruebaDeEscucharLasSilabas;
	private String explicacionDeLaPruebaDeSeleccionarLasSilabasIndicadas;
	private String explicacionDeLaPruebaDeHacerPalabrasConSilabas;
	private Collection<Palabra> palabras;

	public GamePlayingTest() {
		caSilaba = SilabaOM.generarSilaba("ca");
		ceSilaba = SilabaOM.generarSilaba("ce");
		zaSilaba = SilabaOM.generarSilaba("za");
		silabas = new ArrayList<Silaba>(Arrays.asList(new Silaba[] { caSilaba,
				ceSilaba }));
		palabras = GenerarPalabras();
	}

	private Collection<Palabra> GenerarPalabras() {
		Palabra casa = new Palabra(FonemaOM.generarFonemasDesde(new String[] {
				"ca", "sa" }));
		Palabra cabra = new Palabra(FonemaOM.generarFonemasDesde(new String[] {
				"ca", "bra" }));
		Palabra cebra = new Palabra(FonemaOM.generarFonemasDesde(new String[] {
				"ce", "bra" }));
		Palabra salamanca = new Palabra(
				FonemaOM.generarFonemasDesde(new String[] { "sa", "la", "man",
						"ca" }));
		Palabra cereal = new Palabra(FonemaOM.generarFonemasDesde(new String[] {
				"ce", "re", "al" }));
		Collection<Palabra> coleccion = new ArrayList<Palabra>(
				Arrays.asList(new Palabra[] { casa, cabra, cebra, salamanca,
						cereal }));
		return coleccion;
	}

	@Before
	public void setUp() throws Exception {
		usuario = generarUsuario("Sofía");
		explicacionDelJuego = generarExplicacionDelJuego();
		explicacionDeLaPruebaDeEscucharLasSilabas = generarExplicacionDeLaPrimeraPrueba();
		explicacionDeLaPruebaDeSeleccionarLasSilabasIndicadas = generarExplicacionDeLaPruebaDeSeleccionarLasSilabasIndicadas();
		explicacionDeLaPruebaDeHacerPalabrasConSilabas = generarExplicacionDeLaPruebaDeHacerPalabrasConSilabas();
	}

	private String generarExplicacionDeLaPruebaDeHacerPalabrasConSilabas() {
		return "En esta prueba deberás elegir las sílabas que forman la palabra que te indique";
	}

	private String generarExplicacionDeLaPruebaDeSeleccionarLasSilabasIndicadas() {
		return "En esta prueba irás escuchando sílabas y tendrás que indicar qué sílaba suena cada vez";
	}

	private boolean comprobarSiLaSilabaCoincideConAlgunaDeLasSilabasDeLaColeccionPasada(
			Silaba silabaDevueltaEnLaPrueba,
			Collection<Silaba> silabasUsadasParaIniciarLaPrueba) {
		boolean coincide = false;

		for (Silaba silaba : silabasUsadasParaIniciarLaPrueba) {
			coincide = silabaDevueltaEnLaPrueba.equals(silaba);
			if (coincide)
				break;
		}

		return coincide;
	}

	private String generarExplicacionDeLaPrimeraPrueba() {
		return "En esta prueba tienes que pulsar sobre todas las sílabas, al menos una vez para que sepas como se pronuncian";
	}

	private String generarExplicacionDelJuego() {
		return String
				.format("Hola {0} este es el juego de las sílabas. Tienes que pasar por varias pruebas para terminarlo",
						usuario.getNombre());
	}

	private Usuario generarUsuario(String nombreUsuario) {
		return new Usuario(nombreUsuario);
	}

	private void simularPulsarTodasLasSilabasDeLaPrueba(
			PruebaEscucharLasSilabas escucharLasSilabas) {
		for (Silaba silaba : silabas) {
			escucharLasSilabas.jugar(silaba);
		}

	}

	private Silaba obtenerUnaSilabaDeLaColeccionQueNoCoincideConLaSilaba(
			Collection<Silaba> silabas2, Silaba silaba) {
		Silaba silabaCoincidente = null;
		for (Silaba silaba2 : silabas2) {
			if (!silaba.equals(silaba2)) {
				silabaCoincidente = silaba2;
				break;
			}
		}
		return silabaCoincidente;
	}

	private Silaba consultarQueSilabaDeLaColeccionCoincideConLaSilaba(
			Collection<Silaba> silabas2, Silaba silaba) {
		Silaba silabaCoincidente = null;
		for (Silaba silaba2 : silabas2) {
			if (silaba.equals(silaba2)) {
				silabaCoincidente = silaba2;
				break;
			}
		}
		return silabaCoincidente;
	}

	@Test
	public void comoEmpezarElJuegoDeLasSilabas() {

		PruebasDelJuego juego = new PruebasDelJuego(usuario, silabas);

		assertEquals(EstadoDelJuego.Inicial, juego.getEstado());
		assertEquals(explicacionDelJuego, juego.getExplicacion());
		assertEquals(EstadoDelJuego.EnPrueba, juego.getEstado());
	}

	@Test
	public void comoIniciarLaPrimeraPrueba() {
		PruebasDelJuego juego = new PruebasDelJuego(usuario, silabas);

		juego.getExplicacion();
		assertEquals(EstadoDelJuego.EnPrueba, juego.getEstado());

		PruebaEscucharLasSilabas escucharLasSilabas = juego
				.getPruebaEscucharLasSilabas();
		assertEquals(EstadoDeLaPrueba.Inicial, escucharLasSilabas.getEstado());
		assertEquals(explicacionDeLaPruebaDeEscucharLasSilabas,
				escucharLasSilabas.getExplicacion());
		assertEquals(EstadoDeLaPrueba.EsperandoRespuesta,
				escucharLasSilabas.getEstado());
	}

	@Test
	public void comoJugarALaPrimeraPrueba() {
		PruebasDelJuego juego = new PruebasDelJuego(usuario, silabas);

		PruebaEscucharLasSilabas escucharLasSilabas = juego
				.getPruebaEscucharLasSilabas();

		simularPulsarTodasLasSilabasDeLaPrueba(escucharLasSilabas);
		assertEquals(EstadoDeLaPrueba.Finalizada,
				escucharLasSilabas.getEstado());
	}

	@Test
	public void comoIniciarLaSegundaPrueba() {
		PruebasDelJuego juego = new PruebasDelJuego(usuario, silabas);
		PruebaSeleccionarLasSilabasIndicadas seleccionarLasSilabas = juego
				.getSeleccionarLasSilabasIndicadas();

		assertEquals(EstadoDeLaPrueba.Inicial,
				seleccionarLasSilabas.getEstado());
		assertEquals(explicacionDeLaPruebaDeSeleccionarLasSilabasIndicadas,
				seleccionarLasSilabas.getExplicacion());
		assertEquals(EstadoDeLaPrueba.PendienteMasInformacion,
				seleccionarLasSilabas.getEstado());
	}

	@Test
	public void comoJugarALaSegundaPrueba() {
		PruebasDelJuego juego = new PruebasDelJuego(usuario, silabas);
		PruebaSeleccionarLasSilabasIndicadas seleccionarLasSilabas = juego
				.getSeleccionarLasSilabasIndicadas();

		Silaba silaba = seleccionarLasSilabas.getSilabaPendiente();
		assertTrue(comprobarSiLaSilabaCoincideConAlgunaDeLasSilabasDeLaColeccionPasada(
				silaba, silabas));

		// Dummy response
		assertFalse(seleccionarLasSilabas.jugar(zaSilaba));
		assertEquals(EstadoDeLaPrueba.EsperandoRespuesta,
				seleccionarLasSilabas.getEstado());

		assertTrue(seleccionarLasSilabas.jugar(caSilaba));
		assertEquals(EstadoDeLaPrueba.PendienteMasInformacion,
				seleccionarLasSilabas.getEstado());

		silaba = seleccionarLasSilabas.getSilabaPendiente();

		// Dummy response
		assertFalse(seleccionarLasSilabas.jugar(zaSilaba));
		assertEquals(EstadoDeLaPrueba.EsperandoRespuesta,
				seleccionarLasSilabas.getEstado());

		assertTrue(comprobarSiLaSilabaCoincideConAlgunaDeLasSilabasDeLaColeccionPasada(
				silaba, silabas));

		assertTrue(seleccionarLasSilabas.jugar(silaba));

		assertEquals(EstadoDeLaPrueba.Finalizada,
				seleccionarLasSilabas.getEstado());

		// Dummy response
		assertFalse(seleccionarLasSilabas.jugar(zaSilaba));
		assertEquals(EstadoDeLaPrueba.Finalizada,
				seleccionarLasSilabas.getEstado());
	}

	@Test
	public void comoIniciarLaTerceraPrueba() {
		PruebasDelJuego juego = new PruebasDelJuego(usuario, silabas);
		PruebaCompletarPalabrasConSilabas completarPalabrasConSilabas = juego
				.getCompletarPalabrasConSilabas(palabras);

		assertEquals(EstadoDeLaPrueba.Inicial,
				completarPalabrasConSilabas.getEstado());
		assertEquals(explicacionDeLaPruebaDeHacerPalabrasConSilabas,
				completarPalabrasConSilabas.getExplicacion());
		assertEquals(EstadoDeLaPrueba.PendienteMasInformacion,
				completarPalabrasConSilabas.getEstado());
	}

	@Test
	public void comoJugarALaTerceraPrueba() {
		PruebasDelJuego juego = new PruebasDelJuego(usuario, silabas);

		Collection<Palabra> palabrasParametro = copy(palabras);

		PruebaCompletarPalabrasConSilabas completarPalabrasConSilabas = juego
				.getCompletarPalabrasConSilabas(palabrasParametro);

		for (int i = 0; i < palabras.size(); i++) {
			InformacionPendiente informacionPendiente = completarPalabrasConSilabas
					.getInformacionPendiente();
			assertEquals(EstadoDeLaPrueba.EsperandoRespuesta,
					completarPalabrasConSilabas.getEstado());

			Palabra palabraCompleta = informacionPendiente.getPalabraCompleta();
			assertNotNull(palabraCompleta.toString());
			assertTrue(
					String.format(
							"%s No aparece dentro de ninguna de estas palabras",
							palabraCompleta),
					comprobarSiLaPalabraCompletaCoincideConAlgunaDeLasPalabrasDeLaColeccion(
							palabraCompleta, palabras));
			assertTrue(comprobarSiLaSilabaCoincideConAlgunaDeLasSilabasDeLaColeccionPasada(
					informacionPendiente.getSilaba(), silabas));

			Silaba silabaDeLaColeccionQueCorresponde = consultarQueSilabaDeLaColeccionCoincideConLaSilaba(
					silabas, informacionPendiente.getSilaba());
			Silaba silabaDeLaColeccionQueNOCorresponde = obtenerUnaSilabaDeLaColeccionQueNoCoincideConLaSilaba(
					silabas, informacionPendiente.getSilaba());

			// Dummy response
			assertFalse(completarPalabrasConSilabas
					.jugar(silabaDeLaColeccionQueNOCorresponde));
			assertEquals(EstadoDeLaPrueba.EsperandoRespuesta,
					completarPalabrasConSilabas.getEstado());

			assertTrue(completarPalabrasConSilabas
					.jugar(silabaDeLaColeccionQueCorresponde));

			// TODO: IF I+1==SIZE ESTADO FINALIZADO!!!!!!!!!!! (una vez que se
			// juega la última y se acierta el estado ya deberia ser ese)
			if (i + 1 == palabras.size())
				assertEquals(EstadoDeLaPrueba.Finalizada,
						completarPalabrasConSilabas.getEstado());
			else
				assertEquals(EstadoDeLaPrueba.PendienteMasInformacion,
						completarPalabrasConSilabas.getEstado());
		}

		assertEquals(EstadoDeLaPrueba.Finalizada,
				completarPalabrasConSilabas.getEstado());
	}

	private boolean comprobarSiLaPalabraCompletaCoincideConAlgunaDeLasPalabrasDeLaColeccion(
			Palabra palabraCompleta, Collection<Palabra> palabras2) {
		boolean resultado = false;
		for (Palabra palabra : palabras2) {
			if (palabra.equals(palabraCompleta))
				return true;
		}
		return resultado;
	}

	// TODO... CollectionUtils
	public static <T> Collection<T> copy(Collection<T> source) {
		Collection<T> dest = new ArrayList<T>();
		for (T item : source) {
			dest.add(item);
		}
		return dest;
	}

}
