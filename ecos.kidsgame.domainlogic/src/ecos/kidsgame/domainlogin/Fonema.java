package ecos.kidsgame.domainlogin;

public class Fonema {

    //TODO: test pending
    @Override
    public String toString() {
	return mFonema;
    }

    private String mFonema;

    public static Fonema desde(String fonema) {
	Fonema instancia = new Fonema();
	instancia.mFonema = fonema.toLowerCase();
	return instancia;
    }

    @Override
    public boolean equals(Object o) {
	if (!(o instanceof Fonema))
	    return false;

	return mFonema.equals(((Fonema) o).mFonema);
    }
}
