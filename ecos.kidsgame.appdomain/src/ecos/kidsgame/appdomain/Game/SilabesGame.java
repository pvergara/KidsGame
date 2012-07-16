package ecos.kidsgame.appdomain.Game;

import java.util.Collection;


public interface SilabesGame
{

	Collection<String> getSilabes();
	String getExplannation();
	void play(String silabe);
	Boolean accomplished();
}
