package ecos.kidsgame.game.viewmodel;

import ecos.framework.Binding.OnChangeListener;


public class JuegoViewModel {

	private static OnChangeListener mChange;
	
	public void setOnChangeListener(OnChangeListener changeListener) {
		mChange = changeListener;	
	}

	public boolean silabaCAEnabled;
	public boolean silabaCEEnabled;
	private boolean silabaCIEnabled;
	private boolean silabaCOEnabled;
	private boolean silabaCUEnabled;

	// Command
	public void silabaPulsada(String silaba) {
		desactivarSilaba(silaba);
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

}
