package ecos.kidsgame.game.viewmodel;

import java.util.List;

import com.google.inject.Inject;

import ecos.framework.ActivityHandler;
import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;
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

	private List<List<SilabaDto>> mAgrupacionDeSilabas;

	private OnChangeListener mChangeListener;

	private String mExplicacion;

	public void init() {
		speechEngine.tryInit();
		mAgrupacionDeSilabas = mAppGame.getSilabesGroup();
		mChangeListener = bindingManager.getOnChangeListener();
		mExplicacion = mAppGame.getExplannationJuego();
		actualizarAgrupacionSilabas();
	}

	private void actualizarAgrupacionSilabas() {
		mChangeListener.onChange("agrupacionSilabasTag", mAgrupacionDeSilabas);
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
	public void seleccionarGrupo(List<SilabaDto> agrupacionSilabasDto) {
		mAppGame.establecerGrupoSilabasSeleccionado(agrupacionSilabasDto);
	}

	// Command
	public void explicar() {
		speechEngine.speak(mExplicacion);
	}

}
