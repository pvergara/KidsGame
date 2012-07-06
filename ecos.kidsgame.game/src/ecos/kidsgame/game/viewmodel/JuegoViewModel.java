package ecos.kidsgame.game.viewmodel;

import ecos.framework.Binding.OnChangeListener;


public class JuegoViewModel {

	private static OnChangeListener mChange;
	public boolean silabaCAEnabled;
	public boolean silabaCEEnabled;

	// Command
	public void silabaPulsada(String silaba) {
		desactivarSilaba(silaba);
	}

	private void desactivarSilaba(String silaba) {
		if (silaba.compareTo("CA")==0)
			setSilabaCAActiva(false);
		if (silaba.compareTo("CE")==0)
			setSilabaCEActiva(false);
		
	}

	private void setSilabaCEActiva(boolean esActiva) {
		silabaCEEnabled = esActiva;
		mChange.onChange("silabaCEEnabled", silabaCEEnabled);		
	}

	private void setSilabaCAActiva(boolean esActiva) {
		silabaCAEnabled = esActiva;
		mChange.onChange("silabaCAEnabled", silabaCAEnabled);		
	}
	
	public void setOnChangeListener(OnChangeListener changeListener) {
		mChange = changeListener;	
	}

}
