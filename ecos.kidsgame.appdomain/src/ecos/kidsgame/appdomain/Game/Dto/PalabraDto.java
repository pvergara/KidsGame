package ecos.kidsgame.appdomain.Game.Dto;

import java.util.List;

public class PalabraDto {

	private String mTexto;
	private List<SilabaDto> mSilabas;

	public void setTexto(String texto) {
		mTexto = texto;
	}

	public void setSilabas(List<SilabaDto> silabasDto) {
		mSilabas = silabasDto;
	}

	public String getTexto() {
		return mTexto;
	}

	public List<SilabaDto> getSilabas() {
		return mSilabas;
	}

}
