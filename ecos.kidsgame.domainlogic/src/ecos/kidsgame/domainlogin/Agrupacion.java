package ecos.kidsgame.domainlogin;

import java.util.List;

public class Agrupacion {
	private List<Palabra> mPalabras;
	private List<Silaba> mSilabas;

	public List<Palabra> getPalabras()
	{
		return mPalabras;
	}

	public void setSilabas(List<Silaba> silabas) {
		mSilabas = silabas;
	}

	public void setPalabras(List<Palabra> grupoDePalabras) {
		mPalabras = grupoDePalabras;
	}

	public List<Silaba> getSilabas() {
		return mSilabas;
	}
}
