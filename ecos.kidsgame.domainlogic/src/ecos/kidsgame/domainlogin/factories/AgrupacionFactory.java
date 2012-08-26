package ecos.kidsgame.domainlogin.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ecos.kidsgame.domainlogin.Agrupacion;
import ecos.kidsgame.domainlogin.Fonema;
import ecos.kidsgame.domainlogin.Palabra;
import ecos.kidsgame.domainlogin.Representacion;
import ecos.kidsgame.domainlogin.Silaba;

public class AgrupacionFactory {

	private List<List<Silaba>> mAgrupacionDeSilabas = new ArrayList<List<Silaba>>();
	private List<String> mVocales = new ArrayList<String>(Arrays.asList(new String[] { "A", "E", "I", "O", "U" }));
	private List<String> mConsonantes = new ArrayList<String>(Arrays.asList(new String[] { 
			"M", "N", "Ñ", "L", "P", 
			"S", "D", "F", "T", "X", 
			"B", "V", "Y", "LL", "CH", 
			"Z", "C", "K", "QU", "J", 
			"G", "GU", 
	}));

	public List<List<Silaba>> create() {
		mAgrupacionDeSilabas.add(generarGrupo("M<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("N<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("Ñ<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("L<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("P<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("S<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("D<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("F<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("T<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("X<vocal>"));

		mAgrupacionDeSilabas.add(generarGrupo("B<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("V<vocal>"));

		mAgrupacionDeSilabas.add(generarGrupo("Y<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("LL<vocal>"));

		mAgrupacionDeSilabas.add(generarGrupo("CH<vocal>"));

		mAgrupacionDeSilabas.add(generarGrupo("Z<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("C<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("K<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("QU<vocal>"));

		mAgrupacionDeSilabas.add(generarGrupo("J<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("G<vocal>"));
		mAgrupacionDeSilabas.add(generarGrupo("GU<vocal>"));

		return mAgrupacionDeSilabas;
	}

	private ArrayList<Silaba> generarGrupo(String patron) {
		ArrayList<Silaba> gruposSilabas = new ArrayList<Silaba>();
		for (String vocal : mVocales) {
			String silabaString = patron.replaceAll("<vocal>", vocal);
			gruposSilabas.add(new Silaba(Fonema.desde(silabaString), Representacion.desde(silabaString)));
		}
		return gruposSilabas;
	}

	public List<Agrupacion> createCompleto() {
		List<Agrupacion> resultado = new ArrayList<Agrupacion>();
		for (String consonante : mConsonantes) {
			Agrupacion agrupacion = new Agrupacion();
			agrupacion.setSilabas(generarGrupo(String.format("%s<vocal>", consonante)));
			agrupacion.setPalabras(getPalabrasConM(String.format("%s", consonante)));
			resultado.add(agrupacion);
		}
		return resultado;
	}

	private List<Palabra> getPalabrasConM(String consonantes) {
		if (consonantes.compareTo("M") == 0) {
			return generarPalabrasConM();
		}
		if (consonantes.compareTo("N") == 0) {
			return generarPalabrasConN();
		}
		if (consonantes.compareTo("Ñ") == 0) {
			return generarPalabrasConEnhe();
		}
		if (consonantes.compareTo("L") == 0) {
			return generarPalabrasConL();
		}
		if (consonantes.compareTo("P") == 0) {
			return generarPalabrasConP();
		}
		return new ArrayList<Palabra>();
	}

	private List<Palabra> generarPalabrasConP() {
		List<Palabra> resultado = new ArrayList<Palabra>();
		resultado.add(generarPalabraCon(new String[] { "PA", "TO" }));
		resultado.add(generarPalabraCon(new String[] { "PE", "RA" }));
		resultado.add(generarPalabraCon(new String[] { "PI", "RA", "TA" }));
		resultado.add(generarPalabraCon(new String[] { "PO","NI" }));
		resultado.add(generarPalabraCon(new String[] { "PU", "ÑO" }));
		return resultado;
	}

	private List<Palabra> generarPalabrasConL() {
		List<Palabra> resultado = new ArrayList<Palabra>();
		resultado.add(generarPalabraCon(new String[] { "LA", "PIZ" }));
		resultado.add(generarPalabraCon(new String[] { "LE","ÑA" }));
		resultado.add(generarPalabraCon(new String[] { "LI", "MON" }));
		resultado.add(generarPalabraCon(new String[] { "LO","BO" }));
		resultado.add(generarPalabraCon(new String[] { "LU", "PA" }));
		return resultado;
	}

	private List<Palabra> generarPalabrasConEnhe() {
		List<Palabra> resultado = new ArrayList<Palabra>();
		resultado.add(generarPalabraCon(new String[] { "MA", "ÑA", "NA" }));
		resultado.add(generarPalabraCon(new String[] { "BA", "ÑE", "RA" }));
		resultado.add(generarPalabraCon(new String[] { "CE", "ÑI", "DO" }));
		resultado.add(generarPalabraCon(new String[] { "MI", "ÑO" }));
		resultado.add(generarPalabraCon(new String[] { "ÑU" }));
		return resultado;
	}

	private List<Palabra> generarPalabrasConM() {
		List<Palabra> resultado = new ArrayList<Palabra>();
		resultado.add(generarPalabraCon(new String[] { "MA", "PA" }));
		resultado.add(generarPalabraCon(new String[] { "ME", "SA" }));
		resultado.add(generarPalabraCon(new String[] { "MI", "RAR" }));
		resultado.add(generarPalabraCon(new String[] { "MO", "LI", "NO" }));
		resultado.add(generarPalabraCon(new String[] { "MU", "LA" }));
		return resultado;
	}

	private List<Palabra> generarPalabrasConN() {
		List<Palabra> resultado = new ArrayList<Palabra>();
		resultado.add(generarPalabraCon(new String[] { "NA", "RIZ" }));
		resultado.add(generarPalabraCon(new String[] { "NE", "MO" }));
		resultado.add(generarPalabraCon(new String[] { "NI", "ÑO" }));
		resultado.add(generarPalabraCon(new String[] { "NO", "VI", "LLO" }));
		resultado.add(generarPalabraCon(new String[] { "NU", "ME", "RO" }));
		return resultado;
	}

	private Palabra generarPalabraCon(String[] silabasString) {
		Collection<Fonema> fonemas = new ArrayList<Fonema>();
		for (String silabaString : silabasString) {
			fonemas.add(Fonema.desde(silabaString));
		}
		return new Palabra(fonemas);
	}

}
