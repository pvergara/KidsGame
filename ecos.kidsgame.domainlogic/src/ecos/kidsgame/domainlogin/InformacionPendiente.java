package ecos.kidsgame.domainlogin;

public class InformacionPendiente {

	private Silaba mSilaba;
	private Palabra mPalabraCompleta;
	private Palabra mPalabraIncompleta;

	public InformacionPendiente(Palabra palabraIncompleta, Palabra palabraCompleta, Silaba silabaQueCompletaLaPalabra) {
		mPalabraIncompleta = palabraIncompleta;
		mSilaba = silabaQueCompletaLaPalabra;
		mPalabraCompleta = palabraCompleta;
	}

	public Silaba getSilaba() {
		return mSilaba;
	}

	public Palabra getPalabraCompleta() {
		return mPalabraCompleta;
	}

	public Palabra getPalabraIncompleta() {
		return mPalabraIncompleta;
	}
}
