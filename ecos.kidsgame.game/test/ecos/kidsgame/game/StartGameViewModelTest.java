package ecos.kidsgame.game;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ecos.framework.ActivityHandler;
import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.appdomain.Game.Dto.AgrupacionDto;
import ecos.kidsgame.game.viewmodel.StartGameViewModel;

public class StartGameViewModelTest {

	private StartGameViewModel startGameViewModel;
	private SpeechEngine speechEngineMocked;
	private ActivityHandler activityHandlerMocked;
	private StartGameActivity currectContextMocked;
	private BindingManager bindingManagerMocked;
	private SilabesGame appGameMocked;
	private OnChangeListener changeListenerMocked;
	private String explicacion ="";

	@Before
	public void setUp() {
		startGameViewModel = new StartGameViewModel();

		speechEngineMocked = mock(SpeechEngine.class);
		startGameViewModel.speechEngine = speechEngineMocked;

		activityHandlerMocked = mock(ActivityHandler.class);
		startGameViewModel.mActivityHandler = (activityHandlerMocked);

		bindingManagerMocked = mock(BindingManager.class);
		changeListenerMocked = mock(OnChangeListener.class);
		when(bindingManagerMocked.getOnChangeListener()).thenReturn(changeListenerMocked);
		startGameViewModel.bindingManager = (bindingManagerMocked);

		appGameMocked = mock(SilabesGame.class);
		when(appGameMocked.getExplannationJuego()).thenReturn(explicacion);
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
	public void alIniciarSeConsultanLosGruposDeDatosAsociadosAlJuego() {
		startGameViewModel.init();

		verify(startGameViewModel.mAppGame).getGameDataGroups();
	}

	@Test
	public void alIniciarSeNotificaLaModificacionEnLosGruposDeSilabas() {
		startGameViewModel.init();

		verify(changeListenerMocked).onChange(Mockito.anyString(), Mockito.any(List.class));
	}

	@Test
	public void alIniciarSeConsultaLaExplicacionDelJuego() {
		startGameViewModel.init();

		verify(appGameMocked).getExplannationJuego();
	}

	@Test
	public void alInvocarIniciarLaPrimeraPruebaActividadSePideAbrirJuegoActivity() throws Exception {
		startGameViewModel.iniciarPrimeraPrueba(currectContextMocked);

		verify(activityHandlerMocked).showActivity(currectContextMocked, JuegoActivity.class);
	}

	@Test
	public void alInvocarIniciarLaSegundaPruebaActividadSePideAbrirJuegoActivity() throws Exception {
		startGameViewModel.init();
		startGameViewModel.iniciarSegundaPrueba(currectContextMocked);

		verify(activityHandlerMocked).showActivity(currectContextMocked, EncontrarSilabaActivity.class);
	}

	@Test
	public void alSeleccionarUnGrupoDeSilabasSeEstableceEsteNuevoGrupoParaLaRealizacionDeLasPruebas() {
		AgrupacionDto agrupacionSilabasDto = mock(AgrupacionDto.class);
		startGameViewModel.init();
		startGameViewModel.seleccionarGrupo(agrupacionSilabasDto);

		verify(appGameMocked).establecerGrupoSilabasSeleccionado(agrupacionSilabasDto);
	}

	@Test
	public void alPedirLaExplicacionEstaSeraContadaDesdeElSpeechEngine() {
		startGameViewModel.init();
		startGameViewModel.explicar();
		verify(speechEngineMocked).speak(explicacion);
	}
	
	@Test
	public void alInvocarIniciarTerceraPruebaSePideAbrirCompletarPalabraActivity(){
		startGameViewModel.init();
		startGameViewModel.iniciarTerceraPrueba(currectContextMocked);
		
		verify(activityHandlerMocked).showActivity(currectContextMocked, CompletarPalabraActivity.class);
			
	}
}
