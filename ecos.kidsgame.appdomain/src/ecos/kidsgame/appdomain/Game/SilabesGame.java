package ecos.kidsgame.appdomain.Game;

import java.util.List;

import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;


public interface SilabesGame
{

	List<SilabaDto> getSilabes();
	String getExplannation();
	void play(SilabaDto silabe);
	Boolean accomplished();
}
