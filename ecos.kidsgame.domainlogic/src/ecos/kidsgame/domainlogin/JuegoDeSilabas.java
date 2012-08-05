package ecos.kidsgame.domainlogin;

import java.util.List;

import ecos.kidsgame.domainlogin.factories.AgrupacionDeSilabasFactory;

public class JuegoDeSilabas {

	private static JuegoDeSilabas mJuego;

	// @Inject
	private AgrupacionDeSilabasFactory agrupacionDeSilabasFactory = new AgrupacionDeSilabasFactory();
	private List<List<Silaba>> agrupacionDeSilabas;

	JuegoDeSilabas() {
		agrupacionDeSilabas = agrupacionDeSilabasFactory.create();
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

}
