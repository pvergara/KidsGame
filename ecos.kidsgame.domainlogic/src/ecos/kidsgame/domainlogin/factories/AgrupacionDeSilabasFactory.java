package ecos.kidsgame.domainlogin.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ecos.kidsgame.domainlogin.Fonema;
import ecos.kidsgame.domainlogin.Representacion;
import ecos.kidsgame.domainlogin.Silaba;

public class AgrupacionDeSilabasFactory {

	private List<List<Silaba>> agrupacionDeSilabas = new ArrayList<List<Silaba>>();
	private List<String> mVocales = new ArrayList<String>(Arrays.asList(new String[]{"A","E","I","O","U"}));

	public List<List<Silaba>> create() {
		agrupacionDeSilabas.add(generarGrupo("M<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("N<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("Ñ<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("L<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("P<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("S<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("D<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("F<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("T<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("X<vocal>"));
		
		agrupacionDeSilabas.add(generarGrupo("B<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("V<vocal>"));
		
		agrupacionDeSilabas.add(generarGrupo("Y<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("LL<vocal>"));

		agrupacionDeSilabas.add(generarGrupo("CH<vocal>"));

		agrupacionDeSilabas.add(generarGrupo("Z<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("C<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("K<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("QU<vocal>"));

		agrupacionDeSilabas.add(generarGrupo("J<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("G<vocal>"));
		agrupacionDeSilabas.add(generarGrupo("GU<vocal>"));
		
		return agrupacionDeSilabas;
	}

	private ArrayList<Silaba> generarGrupo(String patron) {
		ArrayList<Silaba> gruposSilabas = new ArrayList<Silaba>();
		for (String vocal : mVocales) {
			String silabaString = patron.replaceAll("<vocal>", vocal);
			gruposSilabas.add(new Silaba(Fonema.desde(silabaString), Representacion.desde(silabaString)));
		}
		return gruposSilabas;
	}

}
