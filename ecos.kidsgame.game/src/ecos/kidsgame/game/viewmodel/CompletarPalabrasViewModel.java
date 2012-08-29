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

	private String mPalabraACompletar = "";

	private String mPalabraCompleta;

	private SpeakFinished mOnSpeachSiguientePalabra = new SpeakFinished() {

		public void fireFinished() {
			pasarSiguientePalabra();
		}
	};

	private String mPalabraIncorrecta;

	private SpeakFinished mAccionPalabraIncorrecta = new SpeakFinished() {
		public void fireFinished() {
			mostrarPalabraIncompleta();
		}
	};

	private SpeakFinished currentOnSpeakFinished;

	// TODO: Esto o protected o private... PERO NO SE ME OCURRE COMO HACER PARA
	// LLEGAR A ESTE MÉTODO SIN HACERLO PÚBLICO
	public SpeakFinished getOnSpeakFinishedPalabraIncorrecta() {
		return mAccionPalabraIncorrecta;
	}

	// TODO: Esto o protected o private... PERO NO SE ME OCURRE COMO HACER PARA
	// LLEGAR A ESTE MÉTODO SIN HACERLO PÚBLICO
	public SpeakFinished getCurrentOnSpeakFinished() {
		return currentOnSpeakFinished;
	}

	protected void mostrarPalabraIncompleta() {
		notificarPalabra();
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
			mostrarPalabraIncorrecta(silabaDto);
			actualizarCurrentOnSpeackFinished(mAccionPalabraIncorrecta);
			speechEngine.speak("Incorrecto. Vuelve a intentarlo.", currentOnSpeakFinished);
		} else {
			mostrarPalabraCorrecta(mPalabraCompleta);
			actualizarCurrentOnSpeackFinished(mOnSpeachSiguientePalabra);
			speechEngine.speak(String.format("Perfecto. La palabra completa es %s.", mPalabraCompleta),
					currentOnSpeakFinished);
		}
	}

	private void mostrarPalabraCorrecta(String palabraCompleta) {
		mOnChangeListener.onChange("PalabraCorrecta", palabraCompleta);		
	}

	private void actualizarCurrentOnSpeackFinished(SpeakFinished mOnSpeachSiguientePalabra2) {
		currentOnSpeakFinished = mOnSpeachSiguientePalabra2;
	}

	private void mostrarPalabraIncorrecta(SilabaDto silabaDto) {
		String texto = silabaDto.getTexto();
		mPalabraIncorrecta = mPalabraACompletar;
		//TODO: ahora resulta que sabemos que la palabra incompleta muestra la sílaba que falta con guiones bajos
		mPalabraIncorrecta = mPalabraIncorrecta.replaceAll("_.", texto);
		mOnChangeListener.onChange("PalabraIncorrecta", mPalabraIncorrecta);
	}

	public BindingManager getBindingManager() {
		return bindingManager;
	}

}
