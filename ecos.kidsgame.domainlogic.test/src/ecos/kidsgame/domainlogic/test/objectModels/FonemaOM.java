package ecos.kidsgame.domainlogic.test.objectModels;

import java.util.ArrayList;
import java.util.Collection;

import ecos.kidsgame.domainlogin.Fonema;

public class FonemaOM {
    public static Collection<Fonema> generarFonemasDesde(String[] strings) {
	Collection<Fonema> fonemas = new ArrayList<Fonema>();

	for (String string : strings) {
	    fonemas.add(Fonema.desde(string));
	}

	return fonemas;
    }

}
