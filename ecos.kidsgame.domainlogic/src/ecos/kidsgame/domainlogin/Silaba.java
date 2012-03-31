package ecos.kidsgame.domainlogin;

public class Silaba {

    private Fonema mFonema;
    private Representacion mRepresentacion;

    public Silaba(Fonema fonema, Representacion representacion) {
	mFonema = fonema;
	mRepresentacion = representacion;
    }

    public Fonema getFonema() {
	return mFonema;
    }

    public Representacion getRepresentacion() {
	return mRepresentacion;
    }

    @Override
    public boolean equals(Object o) {
	if (!(o instanceof Silaba))
	    return false;
	return mFonema.equals(((Silaba) o).mFonema);
    }

    @Override
    public int hashCode() {
	return mFonema.hashCode();
    }

    @Override
    public String toString() {
	return mRepresentacion.toString();
    }

}
