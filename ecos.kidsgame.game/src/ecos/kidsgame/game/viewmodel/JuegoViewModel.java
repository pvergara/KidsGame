package ecos.kidsgame.game.viewmodel;

import com.google.inject.Inject;

import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.framework.Speech.SpeechEngine;


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

	// Command
	public void silabaPulsada(String silaba) {
		pronunciarSilaba(silaba);
		desactivarSilaba(silaba);
	}

	public void init()
	{
		mSpeechEngine.speak("");
		mChange = mBindingManager.getOnChangeListener();
	}
	
	
	private void pronunciarSilaba(String silaba)
	{
		try
		{
			mSpeechEngine.speak(silaba);
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

}
