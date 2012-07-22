package ecos.kidsgame.game.viewmodel;

import java.util.Collection;

import com.google.inject.Inject;

import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.framework.Speech.SpeakFinished;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.SilabesGame;


public class JuegoViewModel {

	private static OnChangeListener mChange;
	
	public boolean silabaCAEnabled;
	public boolean silabaCEEnabled;
	private boolean silabaCIEnabled;
	private boolean silabaCOEnabled;
	private boolean silabaCUEnabled;
    
    @Inject                            
    BindingManager mBindingManager;
    
	@Inject                            
	SpeechEngine mSpeechEngine;
    
	@Inject                            
	SilabesGame mAppGame;

	private Collection<String> mSilabas;

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
	
	// Command
	public void silabaPulsada(String silaba) {
		mAppGame.play(silaba);
		pronunciarSilaba(silaba);
	}

	public void init()
	{
		pronunciarSilaba("");
		mChange = mBindingManager.getOnChangeListener();
		
		mSilabas = mAppGame.getSilabes();
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

	private void desactivarSilaba(String silaba) {
		if (silaba.compareTo("CA")==0)
			setSilabaCAActiva(false);
		if (silaba.compareTo("CE")==0)
			setSilabaCEActiva(false);
		if (silaba.compareTo("CI")==0)
			setSilabaCIActiva(false);
		if (silaba.compareTo("CO")==0)
			setSilabaCOActiva(false);
		if (silaba.compareTo("CU")==0)
			setSilabaCUActiva(false);
		
	}

	private void setSilabaCEActiva(boolean esActiva) {
		silabaCEEnabled = esActiva;
		mChange.onChange("silabaCEEnabled", silabaCEEnabled);		
	}

	private void setSilabaCAActiva(boolean esActiva) {
		silabaCAEnabled = esActiva;
		mChange.onChange("silabaCAEnabled", silabaCAEnabled);		
	}

	private void setSilabaCIActiva(boolean esActiva) {
		silabaCIEnabled = esActiva;
		mChange.onChange("silabaCIEnabled", silabaCIEnabled);		
	}

	private void setSilabaCOActiva(boolean esActiva) {
		silabaCOEnabled = esActiva;
		mChange.onChange("silabaCOEnabled", silabaCOEnabled);		
	}

	private void setSilabaCUActiva(boolean esActiva) {
		silabaCUEnabled = esActiva;
		mChange.onChange("silabaCUEnabled", silabaCUEnabled);		
	}

	public BindingManager getBindingManager()
	{
		return mBindingManager;
	}

	public void iniciarJuego()
	{
		String explicacion = mAppGame.getExplannation();
		mSpeechEngine.speak(explicacion);
		
		mChange.onChange("iniciarEnabled", false);		
		setSilabaCAActiva(true);		
		setSilabaCEActiva(true);		
		setSilabaCIActiva(true);		
		setSilabaCOActiva(true);		
		setSilabaCUActiva(true);		
	}

}
