package ecos.kidsgame.game;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.framework.Speech.SpeakFinished;
import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;
import ecos.kidsgame.game.viewmodel.CompletarPalabrasViewModel;

public class CompletarPalabraViewModelTest {

	private CompletarPalabrasViewModel mCompletarPalabrasViewModel;
	private BindingManager bindingManagerMocked;
	private SilabesGame appSilabesGameMocked;
	private OnChangeListener changeListener;
	private List<SilabaDto> silabas;
	private SpeechEngine speechEngineMocked;
	private String explicacion = "";
	private String palabraACompletar = "__labra";
	private SilabaDto silaba;
	private String palabraCompleta = "palabra";
	@Before
	public void setUp() {
		mCompletarPalabrasViewModel = new CompletarPalabrasViewModel();

		changeListener = mock(OnChangeListener.class);

		bindingManagerMocked = mock(BindingManager.class);
		when(bindingManagerMocked.getOnChangeListener()).thenReturn(changeListener);
		mCompletarPalabrasViewModel.bindingManager = bindingManagerMocked;

		silabas = new ArrayList<SilabaDto>();

		appSilabesGameMocked = mock(SilabesGame.class);
		when(appSilabesGameMocked.getSilabes()).thenReturn(silabas);
		when(appSilabesGameMocked.getExplicacionPruebaCompletarPalabras()).thenReturn(explicacion);
		when(appSilabesGameMocked.getPalabraPendienteDeCompletar()).thenReturn(palabraACompletar);
		when(appSilabesGameMocked.getPalabraPendienteCompleta()).thenReturn(palabraCompleta);

		speechEngineMocked = mock(SpeechEngine.class);
		Mockito.doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				 mCompletarPalabrasViewModel.getCurrentOnSpeakFinished().fireFinished();
				return null;
			}
		}).when(speechEngineMocked).speak(anyString(), any(SpeakFinished.class));
		mCompletarPalabrasViewModel.appGame = appSilabesGameMocked;
		mCompletarPalabrasViewModel.speechEngine = speechEngineMocked;

		silaba = mock(SilabaDto.class);
		when(silaba.getTexto()).thenReturn("");
	}

	@Test
	public void alIniciarSeObtieneElGestorDeBinding() {
		mCompletarPalabrasViewModel.init();

		verify(mCompletarPalabrasViewModel.bindingManager).getOnChangeListener();
	}

	@Test
	public void alIniciarSeConsultanLasSilabasConLasQueSeLlevaraACaboLaPrueba() {
		mCompletarPalabrasViewModel.init();

		verify(appSilabesGameMocked).getSilabes();
	}

	@Test
	public void alIniciarConsultanLaExplicacionDeLaPrueba() {
		mCompletarPalabrasViewModel.init();

		verify(appSilabesGameMocked).getExplicacionPruebaCompletarPalabras();
	}

	@Test
	public void alIniciarSeNotificaLaModificacionDeLasSilabas() {
		mCompletarPalabrasViewModel.init();

		verify(changeListener).onChange("Silabas", silabas);
	}

	@Test
	public void alInvocarExplicarSePronunciaLaExplicacionUtilizandoElSpeechEngine() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.explicar();

		verify(speechEngineMocked).speak(explicacion);
	}

	@Test
	public void alInvocarIniciarSeActivanLasSiglasConLasQueSepPuedenJugar() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();

		verify(changeListener).onChange("SilabasActivas", true);
	}

	@Test
	public void alInvocarIniciarSeDesactivaElBotonDeIniciado() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();

		verify(changeListener).onChange("IniciarActivo", false);
	}

	@Test
	public void alInvocarIniciarSeConsultaLaPrimeraPalabraPendienteDeCompletarYSeNotificaLaPalabra() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();

		verify(appSilabesGameMocked).getPalabraPendienteDeCompletar();
		verify(changeListener).onChange("Palabra", palabraACompletar);
	}

	@Test
	public void alInvocarIniciarSeConsultaLaPrimeraPalabraCompleta() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();

		verify(appSilabesGameMocked).getPalabraPendienteCompleta();
	}

	@Test
	public void alInvocarJugarSeJuegaConUnaDeterminadaSilaba() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();

		mCompletarPalabrasViewModel.jugar(silaba);

		verify(appSilabesGameMocked).playCompletar(silaba);
	}

	@Test
	public void alInvocarJugarSILaSilabaNOEsLaCorrectaSePronunciaraUnMensajeParaQueLoVuelvaAIntentarConElSpeechEngine() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();

		when(appSilabesGameMocked.playCompletar(silaba)).thenReturn(false);
		mCompletarPalabrasViewModel.jugar(silaba);

		verify(speechEngineMocked)
				.speak("Incorrecto. Vuelve a intentarlo.",mCompletarPalabrasViewModel.getCurrentOnSpeakFinished());
	}

	@Test
	public void alInvocarJugarSILaSilabaNOEsLaCorrectaSeMostraraLaPalabraQueSeCompondriaConLaSilabaIncorrectaYDespuesDeHablarSeVolveraAColocarLaPalabraConUnaSilabaMenos() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();
		verify(changeListener,times(1)).onChange("Palabra", palabraACompletar);

		String string="pe";
		when(silaba.getTexto()).thenReturn(string);
		when(appSilabesGameMocked.playCompletar(silaba)).thenReturn(false);

		mCompletarPalabrasViewModel.jugar(silaba);

		verify(changeListener).onChange("PalabraIncorrecta", palabraACompletar.replace("__", string));
		verify(changeListener,times(2)).onChange("Palabra", palabraACompletar);
	}

	@Test
	public void alInvocarJugarSILaSilabaESLaCorrectaSePronunciaraUnMensajeDeAprobacionIndicandoLaPalabraBuscadaConElSpeechEngineYALTERMINARDEHABLARSeBuscaraLaSiguientePalabraACompletar() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();

		when(appSilabesGameMocked.playCompletar(silaba)).thenReturn(true);
		mCompletarPalabrasViewModel.jugar(silaba);

		verify(speechEngineMocked).speak(String.format("Perfecto. La palabra completa es %s.",palabraCompleta),mCompletarPalabrasViewModel.getCurrentOnSpeakFinished());
	}

	@Test
	public void alInvocarJugarSILaSilabaESLaCorrectaSeMostraraLaPalabraCompletaHastaQueTermineElMensajeDeAprobacion() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();

		when(appSilabesGameMocked.playCompletar(silaba)).thenReturn(true);
		mCompletarPalabrasViewModel.jugar(silaba);

		verify(changeListener).onChange("PalabraCorrecta", palabraCompleta);
	}

	@Test
	public void alBuscarLaSiguientePalabraACompletarSeConsultaSiLaPruebaHaTerminado() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();
		when(appSilabesGameMocked.playCompletar(silaba)).thenReturn(true);
		mCompletarPalabrasViewModel.jugar(silaba);

		verify(appSilabesGameMocked).accomplishedCompletarPalabra();

	}

	@Test
	public void alConsultarSiLaPruebaHaTerminado_SILaPruebaNOhaTerminado_SeConsultaLaSiguientePalabraYSeNotificaLaNuevaPalabra() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();
		verify(appSilabesGameMocked,times(1)).getPalabraPendienteDeCompletar();
		verify(changeListener,times(1)).onChange("Palabra", palabraACompletar);
		when(appSilabesGameMocked.playCompletar(silaba)).thenReturn(true);
		mCompletarPalabrasViewModel.jugar(silaba);
		when(appSilabesGameMocked.accomplishedCompletarPalabra()).thenReturn(false);

		verify(appSilabesGameMocked,times(2)).getPalabraPendienteDeCompletar();
		verify(changeListener,times(2)).onChange("Palabra", palabraACompletar);
	}

	@Test
	public void alConsultarSiLaPruebaHaTerminado_SILaPruebaNOhaTerminado_SeConsultaLaPalabraCompleta() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();
		verify(appSilabesGameMocked,times(1)).getPalabraPendienteCompleta();
		when(appSilabesGameMocked.playCompletar(silaba)).thenReturn(true);
		mCompletarPalabrasViewModel.jugar(silaba);
		when(appSilabesGameMocked.accomplishedCompletarPalabra()).thenReturn(false);

		verify(appSilabesGameMocked,times(2)).getPalabraPendienteCompleta();
	}

	@Test
	public void alConsultarSiLaPruebaHaTerminado_CUANDOLaPruebaTermina_SePronunciaUnMensajeDeFelicitacion() {
		mCompletarPalabrasViewModel.init();
		mCompletarPalabrasViewModel.iniciar();
		when(appSilabesGameMocked.playCompletar(silaba)).thenReturn(true);
		when(appSilabesGameMocked.accomplishedCompletarPalabra()).thenReturn(true);
		mCompletarPalabrasViewModel.jugar(silaba);

		verify(speechEngineMocked).speak("Perfecto. Terminaste la prueba.");
	}

}