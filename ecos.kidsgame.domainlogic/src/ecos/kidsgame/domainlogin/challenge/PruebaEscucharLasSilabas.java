package ecos.kidsgame.domainlogin.challenge;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ecos.kidsgame.domainlogin.EstadoDeLaPrueba;
import ecos.kidsgame.domainlogin.Silaba;

public class PruebaEscucharLasSilabas
{

	private EstadoDeLaPrueba mEstado;
	private Map<Silaba, Boolean> mSilabasJugadas;

	public PruebaEscucharLasSilabas(Collection<Silaba> silabas)
	{
		mEstado = EstadoDeLaPrueba.Inicial;
		mSilabasJugadas = new HashMap<Silaba, Boolean>();
		for (Silaba silaba : silabas)
		{
			mSilabasJugadas.put(silaba, false);
		}

	}

	public EstadoDeLaPrueba getEstado()
	{
		return mEstado;
	}

	public String getExplicacion()
	{
		mEstado = EstadoDeLaPrueba.EsperandoRespuesta;
		return "En esta prueba tienes que pulsar sobre todas las sílabas, al menos una vez para que sepas como se pronuncian";
	}

	public Silaba jugar(Silaba silaba)
	{
		mSilabasJugadas.put(silaba, true);
		if (hasTodasLasSilabasJugadas())
			mEstado = EstadoDeLaPrueba.Finalizada;
		return silaba;
	}

	private boolean hasTodasLasSilabasJugadas()
	{
		int contadorDeSilabasAFalso = mSilabasJugadas.size();
		for (Entry<Silaba, Boolean> item : mSilabasJugadas.entrySet())
		{
			if (item.getValue())
				contadorDeSilabasAFalso--;
		}

		boolean resultado = false;
		if (contadorDeSilabasAFalso == 0)
			resultado = true;
		return resultado;
	}

}
