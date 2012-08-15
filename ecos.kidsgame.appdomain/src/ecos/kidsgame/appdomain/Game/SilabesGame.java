package ecos.kidsgame.appdomain.Game;

import java.util.List;

import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;


public interface SilabesGame
{
	List<SilabaDto> getSilabes();
	String getExplannation();
	void play(SilabaDto silabe);
	Boolean accomplished();
	String getExplannationEncontrar();
	SilabaDto getSilabaPendiente();
	Boolean playExplicacion(SilabaDto silabe);
	Boolean accomplishedEncontrar();
	List<List<SilabaDto>> getSilabesGroup();
	void establecerGrupoSilabasSeleccionado(List<SilabaDto> agrupacionSilabasDto);
	String getExplannationJuego();
	String getExplicacionPruebaCompletarPalabras();
	String getPalabraPendienteDeCompletar();
	Boolean playCompletar(SilabaDto silabaDto);
	String getPalabraPendienteCompleta();
	Boolean accomplishedCompletarPalabra();
}
