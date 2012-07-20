package ecos.kidsgame.domainlogin;

import java.util.Collection;

public class CompletarPalabrasConSilabas {

    private EstadoDeLaPrueba mEstado;
    private Collection<Silaba> mSilabas;
    private Collection<Palabra> mPalabrasPendientesDeJugar;
    private InformacionPendiente mInformacionPendienteDeLaPrueba;

    private Silaba extraerAlgunaDeEstasSilabasDeLaPalabra(Collection<Silaba> silabas, Palabra palabra) {
	Silaba silabaSeleccionada = null;
	for (Silaba silaba : silabas) {
	    if (palabra.hasSilaba(silaba)) {
		silabaSeleccionada = silaba;
		break;
	    }
	}
	palabra.sustituirSilabaPorSilaba(silabaSeleccionada, new Silaba(Fonema.desde(""), Representacion.desde("")));
	return silabaSeleccionada;
    }

    private Collection<Palabra> eliminarPalabraDeLaColeccion(Collection<Palabra> palabrasPendientesDeJugar, Palabra palabra) {
	Collection<Palabra> palabrasPendientesDeJugar2 = palabrasPendientesDeJugar;
	palabrasPendientesDeJugar2.remove(palabra);
	return palabrasPendientesDeJugar2;
    }

    private Palabra seleccionarUnaPalabraQueContengaAlgunaDeLasSilabas(Collection<Palabra> palabrasPendientesDeJugar, Collection<Silaba> silabas) {
	for (Palabra palabra : palabrasPendientesDeJugar) {
	    if (palabra.hasAny(silabas))
		return palabra;
	}
	return null;
    }

    public CompletarPalabrasConSilabas(Collection<Silaba> silabas, Collection<Palabra> palabras) {
	CambiarAEstado(EstadoDeLaPrueba.Inicial);
	mSilabas = silabas;
	mPalabrasPendientesDeJugar = palabras;
    }

    public EstadoDeLaPrueba getEstado() {
	return mEstado;
    }

    public String getExplicacion() {
	CambiarAEstado(EstadoDeLaPrueba.PendienteMasInformacion);
	return "En esta prueba deberás elegir las sílabas que forman la palabra que te indique";
    }

    public InformacionPendiente getInformacionPendiente() {

	if (mPalabrasPendientesDeJugar.size() == 0)
	    CambiarAEstado(EstadoDeLaPrueba.Finalizada);
	else
	    CambiarAEstado(EstadoDeLaPrueba.EsperandoRespuesta);

	if (mEstado == EstadoDeLaPrueba.Finalizada)
	    return null;

	InformacionPendiente informacionPendienteDeLaPrueba = extraerUnaPalabraDeLaColeccionQueContengaSilaba(mPalabrasPendientesDeJugar, mSilabas);

	return informacionPendienteDeLaPrueba;
    }

    private void CambiarAEstado(EstadoDeLaPrueba nuevoEstado) {
	mEstado = nuevoEstado;
    }

    private InformacionPendiente extraerUnaPalabraDeLaColeccionQueContengaSilaba(Collection<Palabra> palabrasPendientesDeJugar, Collection<Silaba> silabas) {
	Palabra palabraCompleta = seleccionarUnaPalabraQueContengaAlgunaDeLasSilabas(palabrasPendientesDeJugar, silabas);
	Palabra palabraIncompleta = (Palabra) palabraCompleta.clone();
	Silaba silabaQueCompletaLaPalabra = extraerAlgunaDeEstasSilabasDeLaPalabra(silabas, palabraIncompleta);

	mPalabrasPendientesDeJugar = eliminarPalabraDeLaColeccion(palabrasPendientesDeJugar, palabraCompleta);

	mInformacionPendienteDeLaPrueba = new InformacionPendiente(palabraIncompleta, palabraCompleta, silabaQueCompletaLaPalabra);

	return mInformacionPendienteDeLaPrueba;
    }

    public boolean jugar(Silaba silaba) {
	boolean resultado;

	resultado = silaba.equals(mInformacionPendienteDeLaPrueba.getSilaba());
	if (resultado) {
	    if (resultado) {
		if (mPalabrasPendientesDeJugar.size() == 0)
		    CambiarAEstado(EstadoDeLaPrueba.Finalizada);
		else
		    CambiarAEstado(EstadoDeLaPrueba.PendienteMasInformacion);
	    }
	}
	return resultado;
    }

}
