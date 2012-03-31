package ecos.kidsgame.domainlogin;

public class Representacion {

    private String mRepresentacion;

    public static Representacion desde(String representacionj) {
	Representacion instancia = new Representacion();
	instancia.mRepresentacion = representacionj;
	return instancia;
    }

    @Override
    public boolean equals(Object o) {
	if (!(o instanceof Representacion))
	    return false;

	Representacion otraRepresentacion = (Representacion) o;
	return this.mRepresentacion.equals(otraRepresentacion.mRepresentacion);
    }

    @Override
    public int hashCode() {
	return mRepresentacion.hashCode();
    }

    @Override
    public String toString() {
	return mRepresentacion;
    }

    //TODO: to be Tested
    public void append(Representacion representacion) {
	StringBuilder sb = new StringBuilder(mRepresentacion);
	sb.append(representacion.toString());
	
	mRepresentacion = sb.toString();
    }

}
