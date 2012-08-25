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

public class AgrupacionDeSilabasFactory {

	private List<List<Silaba>> mAgrupacionDeSilabas = new ArrayList<List<Silaba>>();
	private List<String> mVocales = new ArrayList<String>(Arrays.asList(new String[]{"A","E","I","O","U"}));

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
		create();
		List<Agrupacion> resultado = new ArrayList<Agrupacion>();
		for (List<Silaba> grupoDeSilabas : mAgrupacionDeSilabas) {
			Agrupacion agrupacion = new Agrupacion();
			agrupacion.setSilabas(grupoDeSilabas);
			List<Palabra> grupoDePalabras = generarPalabrasCon(grupoDeSilabas);
			agrupacion.setPalabras(grupoDePalabras);
			
			resultado.add(agrupacion);
		}
		
		return resultado;
	}

	private List<Palabra> generarPalabrasCon(List<Silaba> grupoDeSilabas) {
		List<Palabra> palabras = new ArrayList<Palabra>();
		for (Silaba silaba : grupoDeSilabas) {
			Collection<Fonema> fonemas = new ArrayList<Fonema>(Arrays.asList(new Fonema[]{silaba.getFonema(),silaba.getFonema()}));
			Palabra palabra = new Palabra(fonemas);
			palabras.add(palabra);
		}
		return palabras;
	}

}
