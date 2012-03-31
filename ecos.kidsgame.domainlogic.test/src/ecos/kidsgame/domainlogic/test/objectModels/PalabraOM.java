package ecos.kidsgame.domainlogic.test.objectModels;

import java.util.ArrayList;
import java.util.Collection;

import ecos.kidsgame.domainlogin.Fonema;
import ecos.kidsgame.domainlogin.Palabra;

public class PalabraOM {

    public static Palabra generarPalabra(String[] strings) {
	
	Collection<Fonema> fonemas = new ArrayList<Fonema>();
	for (String string : strings) {
	    fonemas.add(Fonema.desde(string));
	}
	
	Palabra palabra = new Palabra(fonemas);
	
	return palabra;
    }

}
