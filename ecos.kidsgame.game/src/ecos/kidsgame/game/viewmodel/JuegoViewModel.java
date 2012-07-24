package ecos.kidsgame.game.viewmodel;

import java.util.List;

import com.google.inject.Inject;

import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.framework.Speech.SpeakFinished;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;


public class JuegoViewModel {

	private static OnChangeListener mChange;
	
    @Inject                            
    BindingManager mBindingManager;
    
	@Inject                            
	SpeechEngine mSpeechEngine;
    
	@Inject                            
	SilabesGame mAppGame;

	private List<SilabaDto> mSilabas;

	private boolean mAvisado;

	private SpeakFinished mOnSilabeSpeakFinished = new SpeakFinished() {
		
		public void fireFinished() {
			if(mAppGame.accomplished() && !mAvisado)
			{
				permitirPasarSiguenteJuego();
				mAvisado=true;
			}
		}
	};

	private SpeakFinished mOnExplanationFinished = new SpeakFinished() {
		
		public void fireFinished() {
			mChange.onChange("iniciarEnabled", false);		
			mostrarSilabas(mSilabas);
		}
	};

	private void mostrarSilabas(List<SilabaDto> silabas) {
		for (int i = 0; i < silabas.size(); i++) {
			String elementName = "silaba"+(i+1)+"Enabled";
			mChange.onChange(elementName, true);
		}
	}
	
	// Command
	public void silabaPulsada(SilabaDto silaba) {
		mAppGame.play(silaba);
		pronunciarSilaba(silaba.getFonema());
	}

	public void init()
	{
		pronunciarSilaba("");
		mChange = mBindingManager.getOnChangeListener();
		
		mSilabas = mAppGame.getSilabes();
		actualizarSilabas(mSilabas);
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
//		mostrarSilabas(mSilabas);		
	}

}
