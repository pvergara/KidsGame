package ecos.kidsgame.domainlogin;

import java.util.Collection;

public class JuegoDeSilabas {

    private Usuario mUsuario;
    private EstadoDelJuego mEstadoDelJuego;
    private Collection<Silaba> mSilabasDeTodasLasPruebas;

    public JuegoDeSilabas(Usuario usuario, Collection<Silaba> silabasDeTodasLasPruebas) {
	mUsuario = usuario;
	mEstadoDelJuego = EstadoDelJuego.Inicial;
	mSilabasDeTodasLasPruebas = silabasDeTodasLasPruebas;
    }

    public EstadoDelJuego getEstado() {
	return mEstadoDelJuego;
    }

    public String getExplicacion() {
	mEstadoDelJuego = EstadoDelJuego.EnPrueba;
	return String.format("Hola {0} este es el juego de las sílabas. Tienes que pasar por varias pruebas para terminarlo", mUsuario.getNombre());
    }

    public PruebaEscucharLasSilabas getPruebaEscucharLasSilabas() {
	return new PruebaEscucharLasSilabas(mSilabasDeTodasLasPruebas);
    }

    public SeleccionarLasSilabasIndicadas getSeleccionarLasSilabasIndicadas() {
	return new SeleccionarLasSilabasIndicadas(mSilabasDeTodasLasPruebas);
    }

    public CompletarPalabrasConSilabas getCompletarPalabrasConSilabas(Collection<Palabra> palabras) {
	return new CompletarPalabrasConSilabas(mSilabasDeTodasLasPruebas, palabras);
    }

}
