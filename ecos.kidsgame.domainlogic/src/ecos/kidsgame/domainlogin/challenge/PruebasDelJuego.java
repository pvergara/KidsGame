package ecos.kidsgame.domainlogin.challenge;

import java.util.Collection;

import ecos.kidsgame.domainlogin.EstadoDelJuego;
import ecos.kidsgame.domainlogin.Palabra;
import ecos.kidsgame.domainlogin.Silaba;
import ecos.kidsgame.domainlogin.Usuario;

public class PruebasDelJuego {

	private Usuario mUsuario;
	private EstadoDelJuego mEstadoDelJuego;
	private Collection<Silaba> mSilabasDeTodasLasPruebas;

	public PruebasDelJuego(Usuario usuario, Collection<Silaba> silabasDeTodasLasPruebas) {
		mUsuario = usuario;
		mEstadoDelJuego = EstadoDelJuego.Inicial;
		mSilabasDeTodasLasPruebas = silabasDeTodasLasPruebas;
	}

	public EstadoDelJuego getEstado() {
		return mEstadoDelJuego;
	}

	public String getExplicacion() {
		mEstadoDelJuego = EstadoDelJuego.EnPrueba;
		return String.format(
				"Hola %s este es el juego de las sílabas. Tienes que pasar por varias pruebas para terminarlo",
				mUsuario.getNombre());
	}

	public PruebaEscucharLasSilabas getPruebaEscucharLasSilabas() {
		return new PruebaEscucharLasSilabas(mSilabasDeTodasLasPruebas);
	}

	public PruebaSeleccionarLasSilabasIndicadas getSeleccionarLasSilabasIndicadas() {
		return new PruebaSeleccionarLasSilabasIndicadas(mSilabasDeTodasLasPruebas);
	}

	public PruebaCompletarPalabrasConSilabas getCompletarPalabrasConSilabas(Collection<Palabra> palabras) {
		return new PruebaCompletarPalabrasConSilabas(mSilabasDeTodasLasPruebas, palabras);
	}

}
