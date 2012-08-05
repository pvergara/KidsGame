package ecos.kidsgame.game.viewmodel;

import java.util.List;

import com.google.inject.Inject;

import ecos.framework.ActivityHandler;
import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;
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


	public void init() {
		speechEngine.tryInit();
		mAgrupacionDeSilabas = mAppGame.getSilabesGroup();
		mChangeListener = bindingManager.getOnChangeListener();
		actualizarAgrupacionSilabas();
	}
    
    
	private void actualizarAgrupacionSilabas() {
		byte i=1;
		for (List<SilabaDto> grupoSilabas : mAgrupacionDeSilabas) {
			mChangeListener.onChange("grupoSilaba"+i+"Tag", grupoSilabas);
			i++;
		}
		
	}

	// Command
	public void iniciarSiguienteActividad(StartGameActivity currectContext) {
		mActivityHandler.showActivity(currectContext, JuegoActivity.class);
	}


	public BindingManager getBindingManager() {
		return bindingManager;
	}


	// Command
	public void seleccionarGrupo(List<SilabaDto> agrupacionSilabasDto) {
		mAppGame.establecerGrupoSilabasSeleccionado(agrupacionSilabasDto);
	}

}
