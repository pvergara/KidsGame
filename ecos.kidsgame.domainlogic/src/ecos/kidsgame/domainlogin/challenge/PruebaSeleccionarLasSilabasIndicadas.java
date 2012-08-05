package ecos.kidsgame.domainlogin.challenge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import ecos.kidsgame.domainlogin.EstadoDeLaPrueba;
import ecos.kidsgame.domainlogin.Silaba;

public class PruebaSeleccionarLasSilabasIndicadas {

	private EstadoDeLaPrueba mEstado;
	private ArrayList<Silaba> mSilabas;
	private Silaba mSilaba;

	public PruebaSeleccionarLasSilabasIndicadas(Collection<Silaba> silabas) {
		mEstado = EstadoDeLaPrueba.Inicial;
		mSilabas = new ArrayList<Silaba>(silabas);
	}

	public String getExplicacion() {
		mEstado = EstadoDeLaPrueba.PendienteMasInformacion;
		return "En esta prueba irás escuchando sílabas y tendrás que indicar qué sílaba suena cada vez";
	}

	public EstadoDeLaPrueba getEstado() {
		return mEstado;
	}

	public Silaba getSilabaPendiente() {
		mSilaba = null;
		if (mSilabas.size() > 0) {
			int indice = (mSilabas.size()-1==0) ? 0 : elegirUnNumeroEntreCeroY(mSilabas.size()-1);
			mSilaba = mSilabas.get(indice);
			mSilabas.remove(indice);
		}

		return mSilaba;
	}

	private int elegirUnNumeroEntreCeroY(int i) {
		Random random = new Random();
		return random.nextInt(i);
	}

	public boolean jugar(Silaba silaba) {

		if (mSilaba == null)
			return false;

		boolean resultadoCorrecto = mSilaba.equals(silaba);
		if (mEstado != EstadoDeLaPrueba.Finalizada) {
			if (resultadoCorrecto) {
				mEstado = EstadoDeLaPrueba.PendienteMasInformacion;
				if (mSilabas.size() == 0)
					mEstado = EstadoDeLaPrueba.Finalizada;
			} else {
				mEstado = EstadoDeLaPrueba.EsperandoRespuesta;
			}
		}
		return resultadoCorrecto;

	}

}
