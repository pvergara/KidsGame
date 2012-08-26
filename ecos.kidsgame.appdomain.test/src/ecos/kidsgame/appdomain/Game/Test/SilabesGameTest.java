package ecos.kidsgame.appdomain.Game.Test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ecos.kidsgame.appdomain.Game.CurrentSilabesGame;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.appdomain.Game.Dto.AgrupacionDto;
import ecos.kidsgame.appdomain.Game.Dto.PalabraDto;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;

public class SilabesGameTest {
	
	@Test
	public void getGameDataGroups()
	{
		SilabesGame silabesGame = new CurrentSilabesGame();
		List<AgrupacionDto> resultado = silabesGame.getGameDataGroups();
		
		assertNotNull(resultado);
		assertTrue(resultado.size()>0);		
	}

	
	@Test
	public void getGameDataGroups_getSilabes()
	{
		SilabesGame silabesGame = new CurrentSilabesGame();
		List<AgrupacionDto> resultado = silabesGame.getGameDataGroups();

		List<SilabaDto> silabasPrimerGrupo = resultado.get(0).silabasDto;
		assertNotNull(silabasPrimerGrupo);
		assertTrue(silabasPrimerGrupo.size()>0);
		assertTrue(silabasPrimerGrupo.get(0).getTexto().compareTo("MA")==0);
	}

	
	@Test
	public void getGame_getWords()
	{
		SilabesGame silabesGame = new CurrentSilabesGame();
		List<AgrupacionDto> resultado = silabesGame.getGameDataGroups();
		
		List<PalabraDto> palabrasPrimerGrupo = resultado.get(0).palabrasDto;
		assertNotNull(palabrasPrimerGrupo);
		assertTrue(palabrasPrimerGrupo.size()>0);
		PalabraDto primeraPalabraDelPrimerGrupo = palabrasPrimerGrupo.get(0);
		assertTrue(primeraPalabraDelPrimerGrupo.getTexto().compareTo("MAPA")==0);
		assertTrue(primeraPalabraDelPrimerGrupo.getSilabas().get(0).getFonema().compareTo("ma")==0);
		assertTrue(primeraPalabraDelPrimerGrupo.getSilabas().get(1).getFonema().compareTo("pa")==0);
	}
}
