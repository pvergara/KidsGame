package ecos.kidsgame.appdomain.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;
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
				new Silaba(Fonema.desde("ca"), Representacion.desde("Ca")),
				new Silaba(Fonema.desde("ce"), Representacion.desde("Ce")),
				new Silaba(Fonema.desde("ci"), Representacion.desde("Ci")),
				new Silaba(Fonema.desde("co"), Representacion.desde("Co")),
				new Silaba(Fonema.desde("cu"), Representacion.desde("Cu")) }));

		JuegoDeSilabas juego = new JuegoDeSilabas(usuario, silabas);
		juego.getExplicacion();
		escucharLasSilabas = juego
				.getPruebaEscucharLasSilabas();
	}

	public List<SilabaDto> getSilabes()
	{
		List<SilabaDto> resultado = new ArrayList<SilabaDto>();
		for (Silaba silaba : silabas)
		{
			SilabaDto silabaDto = new SilabaDto();
			silabaDto.setFonema(silaba.getFonema().toString());
			silabaDto.setTexto(silaba.getRepresentacion().toString());
			resultado.add(silabaDto);
		}
		return resultado;
	}

	public String getExplannation()
	{
		return escucharLasSilabas.getExplicacion();
	}

	public void play(SilabaDto silabe)
	{
		Silaba silaba = new Silaba(Fonema.desde(silabe.getFonema()), Representacion.desde(silabe.getTexto()));
		escucharLasSilabas.jugar(silaba);		
	}

	public Boolean accomplished()
	{		
		return escucharLasSilabas.getEstado().equals(EstadoDeLaPrueba.Finalizada);
	}

}
