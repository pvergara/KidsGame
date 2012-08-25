package ecos.kidsgame.domainlogin;

import java.util.List;

import ecos.kidsgame.domainlogin.factories.AgrupacionDeSilabasFactory;

public class JuegoDeSilabas {

	private static JuegoDeSilabas mJuego;

	// @Inject
	private AgrupacionDeSilabasFactory agrupacionDeSilabasFactory = new AgrupacionDeSilabasFactory();
	private AgrupacionDeSilabasFactory agrupacionFactory = new AgrupacionDeSilabasFactory();
	private List<List<Silaba>> agrupacionDeSilabas;
	private List<Agrupacion> mAgrupaciones;

	JuegoDeSilabas() {
		agrupacionDeSilabas = agrupacionDeSilabasFactory.create();
		mAgrupaciones = agrupacionFactory.createCompleto();
	}

	public static JuegoDeSilabas getInstancia() {
		if (mJuego == null) {
			mJuego = new JuegoDeSilabas();
		}
		return mJuego;
	}

	public List<List<Silaba>> getAgrupacionDeSilabas() {
		return agrupacionDeSilabas;
	}

	public List<Agrupacion> getAgrupaciones() {
		return mAgrupaciones;
	}

}
