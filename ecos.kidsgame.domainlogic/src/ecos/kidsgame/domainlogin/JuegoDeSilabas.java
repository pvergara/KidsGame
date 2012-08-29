package ecos.kidsgame.domainlogin;

import java.util.List;

import ecos.kidsgame.domainlogin.factories.AgrupacionFactory;

public class JuegoDeSilabas {

	private static JuegoDeSilabas mJuego;

	// @Inject
	private AgrupacionFactory agrupacionFactory = new AgrupacionFactory();
	private List<Agrupacion> mAgrupaciones;

	JuegoDeSilabas() {
		mAgrupaciones = agrupacionFactory.createCompleto();
	}

	public static JuegoDeSilabas getInstancia() {
		if (mJuego == null) {
			mJuego = new JuegoDeSilabas();
		}
		return mJuego;
	}

	public List<Agrupacion> getAgrupaciones() {
		return mAgrupaciones;
	}

}
