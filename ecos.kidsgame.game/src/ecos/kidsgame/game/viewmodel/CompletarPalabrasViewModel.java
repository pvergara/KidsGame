package ecos.kidsgame.game.viewmodel;

import java.util.List;

import com.google.inject.Inject;

import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.framework.Speech.SpeakFinished;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;

public class CompletarPalabrasViewModel {

	@Inject
	public BindingManager bindingManager;

	@Inject
	public SilabesGame appGame;

	private OnChangeListener mOnChangeListener;

	private List<SilabaDto> mSilabas;

	private String mExplicacion;

	@Inject
	public SpeechEngine speechEngine;

	private String mPalabraACompletar;

	private String mPalabraCompleta;

	private SpeakFinished mOnSpeachSiguientePalabra = new SpeakFinished() {

		public void fireFinished() {
			pasarSiguientePalabra();
		}
	};

	// TODO: Esto o protected o private... PERO NO SE ME OCURRE COMO HACER PARA
	// LLEGAR A ESTE MÉTODO SIN HACERLO PÚBLICO
	public SpeakFinished getOnSpeakFinishedSiguientePalabra() {
		return mOnSpeachSiguientePalabra;
	}

	public void init() {
		mOnChangeListener = bindingManager.getOnChangeListener();
		mExplicacion = appGame.getExplicacionPruebaCompletarPalabras();

		mSilabas = appGame.getSilabes();
		actualizarSilabas();
	}

	protected void pasarSiguientePalabra() {
		if (!appGame.accomplishedCompletarPalabra()) {
			iniciarSiguientePalabra();
		} else {
			speechEngine.speak("Perfecto. Terminaste la prueba.");
		}
	}

	private void actualizarSilabas() {
		mOnChangeListener.onChange("Silabas", mSilabas);
	}

	// Command!!!
	public void explicar() {
		speechEngine.speak(mExplicacion);
	}

	// Command!!!
	public void iniciar() {
		activarSilabas();
		desactivarIniciar();
		iniciarSiguientePalabra();
	}

	private void iniciarSiguientePalabra() {
		mPalabraACompletar = appGame.getPalabraPendienteDeCompletar();
		mPalabraCompleta = appGame.getPalabraPendienteCompleta();
		notificarPalabra();
	}

	private void notificarPalabra() {
		mOnChangeListener.onChange("Palabra", mPalabraACompletar);
	}

	private void desactivarIniciar() {
		mOnChangeListener.onChange("IniciarActivo", false);
	}

	private void activarSilabas() {
		mOnChangeListener.onChange("SilabasActivas", true);
	}

	// Command!!!
	public void jugar(SilabaDto silabaDto) {
		if (!appGame.playCompletar(silabaDto)) {
			speechEngine.speak("Esa no es la sílaba correcta para completar la palabra. Vuelve a intentarlo.");
		} else {
			speechEngine.speak(String.format("Perfecto. La palabra completa es %s.", mPalabraCompleta),
					mOnSpeachSiguientePalabra);
		}
	}

	public BindingManager getBindingManager() {
		return bindingManager;
	}

}
