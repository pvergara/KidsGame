package ecos.kidsgame.game;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ecos.framework.ActivityHandler;
import ecos.framework.Binding.BindingManager;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.game.viewmodel.StartGameViewModel;

public class StartGameViewModelTest {

	private StartGameViewModel startGameViewModel;
	private SpeechEngine speechEngineMocked;
	private ActivityHandler activityHandlerMocked;
	private StartGameActivity currectContextMocked;
	private BindingManager bindingManagerMocked;
	private SilabesGame appGameMocked;

	@Before
	public void setUp() {
		startGameViewModel = new StartGameViewModel();

		speechEngineMocked = mock(SpeechEngine.class);
		startGameViewModel.speechEngine = speechEngineMocked;

		activityHandlerMocked = mock(ActivityHandler.class);
		startGameViewModel.mActivityHandler = (activityHandlerMocked);		

		bindingManagerMocked = mock(BindingManager.class);
		startGameViewModel.bindingManager = (bindingManagerMocked);		

		appGameMocked = mock(SilabesGame.class);
		startGameViewModel.mAppGame = (appGameMocked);		

		currectContextMocked = mock(StartGameActivity.class);
	}

	@Test
	public void alIniciarSeIntentaraIniciarElSpeechEngine() {
		startGameViewModel.init();

		verify(speechEngineMocked).tryInit();
	}

	@Test
	public void alIniciarSeEstablecenElBindingManager() {
		startGameViewModel.init();

		assertTrue(startGameViewModel.bindingManager instanceof BindingManager);
		assertNotNull(startGameViewModel.bindingManager);
	}

	@Test
	public void alIniciarConsultanLosGruposDeSilabasDelJuego() {
		startGameViewModel.init();

		verify(startGameViewModel.mAppGame).getSilabesGroup();
	}

	@Test
	public void alInvocarIniciarSiguienteActividadSePideAbrirJuegoActivity() throws Exception {
		startGameViewModel.iniciarSiguienteActividad(currectContextMocked);

		verify(activityHandlerMocked).showActivity(currectContextMocked, JuegoActivity.class);
	}
}
