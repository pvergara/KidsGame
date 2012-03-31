package ecos.kidsgame.domainlogin;

public class InformacionPendiente {

    private Silaba mSilaba;
    private Palabra mPalabraCompleta;

    public InformacionPendiente(Palabra palabraIncompleta, Palabra palabraCompleta, Silaba silabaQueCompletaLaPalabra) {
	mSilaba = silabaQueCompletaLaPalabra;
	mPalabraCompleta = palabraCompleta;
    }

    public Silaba getSilaba() {
	return mSilaba;
    }

    public Palabra getPalabraCompleta() {
	return mPalabraCompleta;
    }

}
