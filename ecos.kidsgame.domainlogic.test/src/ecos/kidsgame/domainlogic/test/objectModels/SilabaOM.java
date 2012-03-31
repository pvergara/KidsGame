package ecos.kidsgame.domainlogic.test.objectModels;

import java.util.ArrayList;
import java.util.Collection;

import ecos.kidsgame.domainlogin.Fonema;
import ecos.kidsgame.domainlogin.Representacion;
import ecos.kidsgame.domainlogin.Silaba;

public class SilabaOM {

    public static Silaba generarSilaba(String fonemaYRepresentacion) {
	return new Silaba(Fonema.desde(fonemaYRepresentacion), Representacion.desde(fonemaYRepresentacion));
    }

    public static Collection<Silaba> generarSilabas(String[] strings) {	
	Collection<Silaba> silabas = new ArrayList<Silaba>();
	
	for (String string : strings) {	    
	    silabas.add(generarSilaba(string));
	}
	return silabas;
    }

}
