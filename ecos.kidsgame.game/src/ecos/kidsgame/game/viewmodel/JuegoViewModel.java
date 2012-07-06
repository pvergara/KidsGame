package ecos.kidsgame.game.viewmodel;


public class JuegoViewModel {

	private static OnChangeListener mChange;
	public boolean silabaCAEnabled;

	// Command
	public void silabaPulsada(String silaba) {
		desactivarSilaba(silaba);
	}

	private void desactivarSilaba(String silaba) {
		if (silaba.compareTo("CA")==0)
			setSilabaCAActiva(false);
		
	}

	private void setSilabaCAActiva(boolean esActiva) {
		silabaCAEnabled = esActiva;
		mChange.onChange("silabaCAEnabled", silabaCAEnabled);		
	}
	
	public void setOnChangeListener(OnChangeListener changeListener) {
		mChange = changeListener;	
	}

}
