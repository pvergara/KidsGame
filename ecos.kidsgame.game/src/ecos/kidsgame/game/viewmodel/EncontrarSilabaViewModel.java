package ecos.kidsgame.game.viewmodel;

import java.util.List;

import com.google.inject.Inject;

import ecos.framework.ActivityHandler;
import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.framework.Speech.SpeakFinished;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;

public class EncontrarSilabaViewModel {

	private OnChangeListener mChange;

	@Inject
	BindingManager mBindingManager;

	@Inject
	SpeechEngine mSpeechEngine;

	@Inject
	SilabesGame mAppGame;

	@Inject
	ActivityHandler mActivityHandler;

	private List<SilabaDto> mSilabas;

	private SpeakFinished mOnSilabaCorrectaFinished = new SpeakFinished() {

		public void fireFinished() {
			if (mAppGame.accomplishedEncontrar()) {
				mSpeechEngine.speak("Perfecto. Puedes pasar a la siguiente prueba.");
				ocultarIniciar();
				mostrarSiguiente();
				ocultarRepetir();
			} else {
				mSilabaPendiente = mAppGame.getSilabaPendiente();
				pronunciarSilabaSeleccionada();
			}
		}
	};


	private SilabaDto mSilabaPendiente;

	public void init() {
		mChange = mBindingManager.getOnChangeListener();

		mSilabas = mAppGame.getSilabes();
		mSilabaPendiente = mAppGame.getSilabaPendiente();
		actualizarSilabas(mSilabas);
	}

	protected void ocultarRepetir() {
		mChange.onChange("repetirVisibility", false);

	}

	protected void mostrarSiguiente() {
		mChange.onChange("siguienteVisibility", true);
	}

	private void mostrarRepetir() {
		mChange.onChange("repetirVisibility", true);

	}

	protected void pronunciarSilabaSeleccionada() {
		mSpeechEngine.speak("Busca la sílaba " + mSilabaPendiente.getFonema());

	}

	protected void activarSilabas(List<SilabaDto> silabas) {
		for (int i = 0; i < silabas.size(); i++) {
			String elementName = "silaba" + (i + 1) + "Enabled";
			mChange.onChange(elementName, true);
		}
	}

	private void actualizarSilabas(List<SilabaDto> silabas) {
		int i = 1;
		for (SilabaDto silaba : silabas) {
			String elementName = "silaba" + i + "Tag";
			mChange.onChange(elementName, silaba);
			i++;
		}
	}

	public BindingManager getBindingManager() {
		return mBindingManager;
	}

	// Command!!!!
	public void iniciar() {
		activarSilabas(mSilabas);
		pronunciarSilabaSeleccionada();
		mostrarRepetir();
		desactivarIniciar();

	}

	private void desactivarIniciar() {
		mChange.onChange("iniciarEnabled", false);

	}

	private void ocultarIniciar() {
		mChange.onChange("iniciarVisibility", false);

	}

	public void jugar(SilabaDto silabaDto) {
		if (!mAppGame.playExplicacion(silabaDto))
			mSpeechEngine.speak("Esa no es la sílaba correcta. Vuelve a intentarlo.");
		else {
			mSpeechEngine.speak("Correcto.", mOnSilabaCorrectaFinished);
		}

	}

	// Command!!!!
	public void repetirSilabaSeleccionada() {
		pronunciarSilabaSeleccionada();
	}

	// Command!!!!
	public void explicar() {
		mSpeechEngine.speak(mAppGame.getExplannationEncontrar());
	}
}
