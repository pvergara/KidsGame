package ecos.kidsgame.appdomain.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ecos.kidsgame.appdomain.Game.Dto.AgrupacionDto;
import ecos.kidsgame.appdomain.Game.Dto.PalabraDto;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;
import ecos.kidsgame.domainlogin.Agrupacion;
import ecos.kidsgame.domainlogin.EstadoDeLaPrueba;
import ecos.kidsgame.domainlogin.Fonema;
import ecos.kidsgame.domainlogin.InformacionPendiente;
import ecos.kidsgame.domainlogin.JuegoDeSilabas;
import ecos.kidsgame.domainlogin.Palabra;
import ecos.kidsgame.domainlogin.Representacion;
import ecos.kidsgame.domainlogin.Silaba;
import ecos.kidsgame.domainlogin.Usuario;
import ecos.kidsgame.domainlogin.challenge.PruebaCompletarPalabrasConSilabas;
import ecos.kidsgame.domainlogin.challenge.PruebaEscucharLasSilabas;
import ecos.kidsgame.domainlogin.challenge.PruebaSeleccionarLasSilabasIndicadas;
import ecos.kidsgame.domainlogin.challenge.PruebasDelJuego;

public class CurrentSilabesGame implements SilabesGame {

	private Collection<Silaba> silabas;
	private PruebaEscucharLasSilabas escucharLasSilabas;
	private PruebaSeleccionarLasSilabasIndicadas encontrarLasSilabas;
	private Usuario mUsuario;
	private String mExplicacionJuego;
	private PruebaCompletarPalabrasConSilabas completarPalabras;
	private Collection<Palabra> mPalabras;
	private Palabra palabraIncompleta;
	private Palabra palabraCompleta;

	public CurrentSilabesGame() {
		mUsuario = new Usuario("Sofía");
		silabas = new ArrayList<Silaba>(Arrays.asList(new Silaba[] {
				new Silaba(Fonema.desde("ca"), Representacion.desde("Ca")),
				new Silaba(Fonema.desde("ce"), Representacion.desde("Ce")),
				new Silaba(Fonema.desde("ci"), Representacion.desde("Ci")),
				new Silaba(Fonema.desde("co"), Representacion.desde("Co")),
				new Silaba(Fonema.desde("cu"), Representacion.desde("Cu")) }));

		mPalabras = new ArrayList<Palabra>(Arrays.asList(new Palabra[]{
				new Palabra(Arrays.asList(new Fonema[]{Fonema.desde("CA"),Fonema.desde("SA")})),
				new Palabra(Arrays.asList(new Fonema[]{Fonema.desde("CE"),Fonema.desde("RE"),Fonema.desde("ZA")})),
				new Palabra(Arrays.asList(new Fonema[]{Fonema.desde("CI"),Fonema.desde("MA")})),
				new Palabra(Arrays.asList(new Fonema[]{Fonema.desde("CO"),Fonema.desde("MI"),Fonema.desde("DA")})),
				new Palabra(Arrays.asList(new Fonema[]{Fonema.desde("CU"),Fonema.desde("CHA"),Fonema.desde("RA")}))
		}));
		
		PruebasDelJuego juego = new PruebasDelJuego(mUsuario, silabas);
		mExplicacionJuego = juego.getExplicacion();
		escucharLasSilabas = juego.getPruebaEscucharLasSilabas();
		encontrarLasSilabas = juego.getSeleccionarLasSilabasIndicadas();
		completarPalabras = juego.getCompletarPalabrasConSilabas(mPalabras);
	}

	public List<SilabaDto> getSilabes() {
		List<SilabaDto> resultado = new ArrayList<SilabaDto>();
		for (Silaba silaba : silabas) {
			resultado.add(generarSilabaDto(silaba));
		}
		return resultado;
	}

	public String getExplannation() {
		return escucharLasSilabas.getExplicacion();
	}

	public String getExplannationEncontrar() {
		return encontrarLasSilabas.getExplicacion();
	}

	public SilabaDto getSilabaPendiente() {
		Silaba silaba = encontrarLasSilabas.getSilabaPendiente();
		return generarSilabaDto(silaba);

	}

	private SilabaDto generarSilabaDto(Silaba silaba) {
		SilabaDto silabaDto = new SilabaDto();
		silabaDto.setFonema(silaba.getFonema().toString());
		silabaDto.setTexto(silaba.getRepresentacion().toString());
		return silabaDto;
	}

	public void play(SilabaDto silabe) {
		Silaba silaba = generarSilaba(silabe);
		escucharLasSilabas.jugar(silaba);
	}

	public Boolean accomplished() {
		return escucharLasSilabas.getEstado().equals(EstadoDeLaPrueba.Finalizada);
	}

	public Boolean accomplishedEncontrar() {
		return encontrarLasSilabas.getEstado().equals(EstadoDeLaPrueba.Finalizada);
	}

	public Boolean playExplicacion(SilabaDto silabe) {
		Silaba silaba = generarSilaba(silabe);
		return encontrarLasSilabas.jugar(silaba);

	}

	private Silaba generarSilaba(SilabaDto silabaDto) {
		return new Silaba(Fonema.desde(silabaDto.getFonema()), Representacion.desde(silabaDto.getTexto()));
	}

	public List<List<SilabaDto>> getSilabesGroup() {
		List<List<Silaba>> agrupacionSilabas = JuegoDeSilabas.getInstancia().getAgrupacionDeSilabas();
		List<List<SilabaDto>> agrupacionSilabasDto = new ArrayList<List<SilabaDto>>();
		for (List<Silaba> grupoDeSilabas : agrupacionSilabas) {
			agrupacionSilabasDto.add(generarSilabasDto(grupoDeSilabas));
		}
		return agrupacionSilabasDto;
	}


	public List<AgrupacionDto> getGameDataGroups() {
		List<Agrupacion> agrupaciones = JuegoDeSilabas.getInstancia().getAgrupaciones();
		List<AgrupacionDto> agrupacionesDto = new ArrayList<AgrupacionDto>();
		
		for (Agrupacion agrupacion : agrupaciones) {
			AgrupacionDto agrupacionDto = generarAgrupacionDto(agrupacion);
			agrupacionesDto.add(agrupacionDto);
		}
		return agrupacionesDto;
	}
	
	
	private AgrupacionDto generarAgrupacionDto(Agrupacion agrupacion) {
		AgrupacionDto resultado = new AgrupacionDto();
		resultado.silabasDto = generarSilabasDto(agrupacion.getSilabas());
		resultado.palabrasDto = generarPalabrasDto(agrupacion.getPalabras());
		return resultado;
	}

	private List<PalabraDto> generarPalabrasDto(List<Palabra> palabras2) {
		List<PalabraDto> palabrasDto = new ArrayList<PalabraDto>();
		for (Palabra palabra : palabras2) {
			palabrasDto.add(generarPalabraDto(palabra));
		}
		return palabrasDto;
	}

	private PalabraDto generarPalabraDto(Palabra palabra) {
		PalabraDto palabraDto = new PalabraDto();
		palabraDto.setTexto(palabra.toRepresentacion().toString());
		palabraDto.setSilabas(generarSilabasDto(palabra.getSilabas()));
		return palabraDto;
	}

	private List<SilabaDto> generarSilabasDto(List<Silaba> silabas2) {
		List<SilabaDto> silabasDto = new ArrayList<SilabaDto>();
		for (Silaba silaba : silabas2) {
			silabasDto.add(generarSilabaDto(silaba));
		}
		return silabasDto;
	}

	public void establecerGrupoSilabasSeleccionado(AgrupacionDto agrupacionSilabasDto) {
		silabas.clear();

		for (SilabaDto silabaDto : agrupacionSilabasDto.silabasDto) {
			silabas.add(generarSilaba(silabaDto));
		}

		PruebasDelJuego juego = new PruebasDelJuego(mUsuario, silabas);
		mExplicacionJuego = juego.getExplicacion();
		escucharLasSilabas = juego.getPruebaEscucharLasSilabas();
		encontrarLasSilabas = juego.getSeleccionarLasSilabasIndicadas();
		completarPalabras = juego.getCompletarPalabrasConSilabas(generarPalabras(agrupacionSilabasDto.palabrasDto));
	}

	private Collection<Palabra> generarPalabras(List<PalabraDto> palabrasDto) {
		Collection<Palabra> palabras = new ArrayList<Palabra>();
		for (PalabraDto palabraDto : palabrasDto) {
			palabras.add(generarPalabra(palabraDto));
		}
		return palabras;
	}

	private Palabra generarPalabra(PalabraDto palabraDto) {
		Collection<Fonema> fonemas = new ArrayList<Fonema>();
		for (SilabaDto silaba : palabraDto.getSilabas()) {
			fonemas.add(Fonema.desde(silaba.getFonema()));
		}
		return new Palabra(fonemas);
	}

	public String getExplannationJuego() {
		return mExplicacionJuego;
	}

	public String getExplicacionPruebaCompletarPalabras() {
		return completarPalabras.getExplicacion();
	}

	public String getPalabraPendienteDeCompletar() {
		InformacionPendiente ip = completarPalabras.getInformacionPendiente();
		palabraIncompleta = ip.getPalabraIncompleta();
		palabraCompleta = ip.getPalabraCompleta();
		return palabraIncompleta.toRepresentacion().toString();
	}

	public Boolean playCompletar(SilabaDto silabaDto) {
		return completarPalabras.jugar(generarSilaba(silabaDto));
	}

	public String getPalabraPendienteCompleta() {
		return palabraCompleta.toRepresentacion().toString();
	}

	public Boolean accomplishedCompletarPalabra() {
		return completarPalabras.getEstado()==EstadoDeLaPrueba.Finalizada;
	}

}