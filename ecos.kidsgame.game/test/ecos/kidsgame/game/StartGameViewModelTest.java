package ecos.kidsgame.game;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import ecos.framework.ActivityHandler;
import ecos.kidsgame.game.viewmodel.StartGameViewModel;

public class StartGameViewModelTest {
	@Test
	public void alInvocarIniciarSiguienteActividadSePideAbrirJuegoActivity() throws Exception {
		ActivityHandler mockedActivityHandler = mock(ActivityHandler.class);
		StartGameViewModel startGameViewModel = new StartGameViewModel();
		startGameViewModel.mActivityHandler = (mockedActivityHandler);
		StartGameActivity currectContext = mock(StartGameActivity.class);
		startGameViewModel.iniciarSiguienteActividad(currectContext);

		verify(mockedActivityHandler).showActivity(currectContext, JuegoActivity.class);
	}
}
