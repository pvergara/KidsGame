package ecos.kidsgame.appdomain.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


import ecos.kidsgame.domainlogin.EstadoDeLaPrueba;
import ecos.kidsgame.domainlogin.Fonema;
import ecos.kidsgame.domainlogin.JuegoDeSilabas;
import ecos.kidsgame.domainlogin.PruebaEscucharLasSilabas;
import ecos.kidsgame.domainlogin.Representacion;
import ecos.kidsgame.domainlogin.Silaba;
import ecos.kidsgame.domainlogin.Usuario;

public class CurrentSilabesGame implements SilabesGame 
{

	private Collection<Silaba> silabas;
	private PruebaEscucharLasSilabas escucharLasSilabas;

	public CurrentSilabesGame()
	{
		Usuario usuario = new Usuario("Sofía");
		silabas = new ArrayList<Silaba>(Arrays.asList(new Silaba[] {
				new Silaba(Fonema.desde("ca"), Representacion.desde("CA")),
				new Silaba(Fonema.desde("ce"), Representacion.desde("CE")),
				new Silaba(Fonema.desde("ci"), Representacion.desde("CI")),
				new Silaba(Fonema.desde("co"), Representacion.desde("CO")),
				new Silaba(Fonema.desde("cu"), Representacion.desde("CU")) }));

		JuegoDeSilabas juego = new JuegoDeSilabas(usuario, silabas);
		juego.getExplicacion();
		escucharLasSilabas = juego
				.getPruebaEscucharLasSilabas();
	}

	public Collection<String> getSilabes()
	{
		Collection<String> resultado = new ArrayList<String>();
		for (Silaba silaba : silabas)
		{
			resultado.add(silaba.toString());
		}
		return resultado;
	}

	public String getExplannation()
	{
		return escucharLasSilabas.getExplicacion();
	}

	public void play(String silabe)
	{
		Silaba silaba = new Silaba(Fonema.desde(silabe.toLowerCase()), Representacion.desde(silabe));
		escucharLasSilabas.jugar(silaba);		
	}

	public Boolean accomplished()
	{		
		return escucharLasSilabas.getEstado().equals(EstadoDeLaPrueba.Finalizada);
	}

}
