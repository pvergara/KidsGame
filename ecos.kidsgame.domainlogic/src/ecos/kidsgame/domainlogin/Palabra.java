package ecos.kidsgame.domainlogin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Palabra implements Cloneable {

	@Override
	public String toString() {
		Representacion representacionDeLaPalabra = toRepresentacion();
		return representacionDeLaPalabra.toString();
	}

	public Representacion toRepresentacion() {
		return joinByRepresentacion(mSilabas);
	}

	private Representacion joinByRepresentacion(Collection<Silaba> mSilabas2) {
		Representacion representacion = Representacion.desde("");
		for (Silaba silaba : mSilabas2) {
			representacion.append(silaba.getRepresentacion());
		}
		return representacion;
	}

	private List<Silaba> mSilabas;

	public Palabra(Collection<Fonema> fonemas) {
		mSilabas = new ArrayList<Silaba>();
		for (Fonema fonema : fonemas) {
			mSilabas.add(new Silaba(fonema, Representacion.desde(fonema.toString().toUpperCase())));
		}
	}

	public void sustituirSilabaPorSilaba(Silaba silabaSeleccionada, Silaba nuevaSilaba) {
		int indice = mSilabas.indexOf(silabaSeleccionada);
		mSilabas.set(indice, nuevaSilaba);
	}

	public boolean hasSilaba(Silaba silaba) {
		return mSilabas.contains(silaba);
	}

	public boolean hasAny(Collection<Silaba> silabas) {
		for (Silaba silaba : silabas) {
			if (this.hasSilaba(silaba))
				return true;
		}
		return false;
	}

	// TODO: test pending
	public Object clone() {
		Palabra obj = null;
		Collection<Fonema> fonemas = new ArrayList<Fonema>();
		for (Silaba silaba : mSilabas) {
			fonemas.add(silaba.getFonema());
		}
		obj = new Palabra(fonemas);
		return obj;
	}

	public List<Silaba> getSilabas() {
		return mSilabas;
	}
}