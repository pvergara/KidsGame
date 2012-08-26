package ecos.kidsgame.game.viewmodel;

import java.util.List;

import com.google.inject.Inject;

import ecos.framework.ActivityHandler;
import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.appdomain.Game.Dto.AgrupacionDto;
import ecos.kidsgame.game.CompletarPalabraActivity;
import ecos.kidsgame.game.EncontrarSilabaActivity;
import ecos.kidsgame.game.JuegoActivity;
import ecos.kidsgame.game.StartGameActivity;

public class StartGameViewModel {

	@Inject
	public ActivityHandler mActivityHandler;

	@Inject
	public SpeechEngine speechEngine;

	@Inject
	public SilabesGame mAppGame;

	@Inject
	public BindingManager bindingManager;

	private OnChangeListener mChangeListener;

	private String mExplicacion;

	private List<AgrupacionDto> mAgrupaciones;

	public void init() {
		speechEngine.tryInit();
		mAppGame.getSilabesGroup();
		mAgrupaciones = mAppGame.getGameDataGroups();
		mChangeListener = bindingManager.getOnChangeListener();
		mExplicacion = mAppGame.getExplannationJuego();
		actualizarAgrupaciones();
	}

	private void actualizarAgrupaciones() {
		mChangeListener.onChange("agrupacionesTag", mAgrupaciones);
	}

	public BindingManager getBindingManager() {
		return bindingManager;
	}

	// Command
	public void iniciarPrimeraPrueba(StartGameActivity currentContext) {
		mActivityHandler.showActivity(currentContext, JuegoActivity.class);
	}

	// Command
	public void iniciarSegundaPrueba(StartGameActivity currentContext) {
		mActivityHandler.showActivity(currentContext, EncontrarSilabaActivity.class);
	}

	// Command
	public void seleccionarGrupo(AgrupacionDto agrupacionSilabasDto) {
		mAppGame.establecerGrupoSilabasSeleccionado(agrupacionSilabasDto);
	}

	// Command
	public void explicar() {
		speechEngine.speak(mExplicacion);
	}

	// Command
	public void iniciarTerceraPrueba(StartGameActivity currentContext) {
		mActivityHandler.showActivity(currentContext, CompletarPalabraActivity.class);
	}

}
