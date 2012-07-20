package ecos.kidsgame.domainlogin;

import java.util.ArrayList;
import java.util.Collection;

public class SeleccionarLasSilabasIndicadas {

    private EstadoDeLaPrueba mEstado;
    private ArrayList<Silaba> mSilabas;
    private Silaba mSilaba;

    public SeleccionarLasSilabasIndicadas(Collection<Silaba> silabas) {
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
	    mSilaba = mSilabas.get(0);
	    mSilabas.remove(0);
	}

	return mSilaba;
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
