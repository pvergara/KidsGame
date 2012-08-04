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
import ecos.kidsgame.game.EncontrarSilabaActivity;
import ecos.kidsgame.game.JuegoActivity;


public class JuegoViewModel {

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

	public void init()
	{
		mChange = mBindingManager.getOnChangeListener();
		
		mSilabas = mAppGame.getSilabes();
		actualizarSilabas(mSilabas);
	}
	
	
	private boolean mAvisado;

	private SpeakFinished mOnSilabeSpeakFinished = new SpeakFinished() {
		
		public void fireFinished() {
			if(mAppGame.accomplished() && !mAvisado)
			{
				permitirPasarSiguenteJuego();
				mAvisado=true;
				mostrarBotonSiguiente();
				ocultarBotonIniciar();
			}
		}
	};

	private SpeakFinished mOnExplanationFinished = new SpeakFinished() {
		
		public void fireFinished() {
			mostrarSilabas(mSilabas);
		}
	};

	private void mostrarSilabas(List<SilabaDto> silabas) {
		for (int i = 0; i < silabas.size(); i++) {
			String elementName = "silaba"+(i+1)+"Enabled";
			mChange.onChange(elementName, true);
		}
	}
	
	protected void ocultarBotonIniciar() {
		mChange.onChange("iniciarVisibility", false);
	}

	protected void mostrarBotonSiguiente() {
		mChange.onChange("siguienteVisibility", true);
	}

	// Command
	public void silabaPulsada(SilabaDto silaba) {
		mAppGame.play(silaba);
		pronunciarSilaba(silaba.getFonema());
	}
	
	
	private void actualizarSilabas(List<SilabaDto> silabas) {
		int i=1;
		for (SilabaDto silaba : silabas) {
			String elementName = "silaba"+i+"Tag";
			mChange.onChange(elementName, silaba);
			i++;
		}		
	}

	private void permitirPasarSiguenteJuego()
	{
		mSpeechEngine.speak("Perfecto, puedes pasar a la siguiente fase.");		
	}

	private void pronunciarSilaba(String silaba)
	{
		try
		{
			mSpeechEngine.speak(silaba,mOnSilabeSpeakFinished);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	public BindingManager getBindingManager()
	{
		return mBindingManager;
	}

	public void iniciarJuego()
	{
		String explicacion = mAppGame.getExplannation();
		mSpeechEngine.speak(explicacion,mOnExplanationFinished);
		mChange.onChange("iniciarEnabled", false);		
	}

	//Command!!!
	public void siguienteJuego(JuegoActivity currectContext) {
		mActivityHandler.showActivity(currectContext, EncontrarSilabaActivity.class);
	}

}
