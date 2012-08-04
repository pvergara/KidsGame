package ecos.kidsgame.game;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.OnChangeListener;
import ecos.kidsgame.appdomain.Game.SilabesGame;
import ecos.kidsgame.appdomain.Game.Dto.SilabaDto;
import ecos.kidsgame.game.viewmodel.JuegoViewModel;

public class JuegoViewModelTest {

	private JuegoViewModel mJuegoViewModel;
	private List<SilabaDto> mSilabas = new ArrayList<SilabaDto>();
	private SilabaDto caSilaba = new SilabaDto();
	private SilabaDto ceSilaba = new SilabaDto();
	private OnChangeListener onChangeListener = mock(OnChangeListener.class);

	@Before
	public void setUp() {
		mJuegoViewModel = new JuegoViewModel();

		mSilabas.addAll(new ArrayList<SilabaDto>(Arrays.asList(new SilabaDto[] { caSilaba, ceSilaba })));

		BindingManager bindingManagerMock = mock(BindingManager.class);
		when(bindingManagerMock.getOnChangeListener()).thenReturn(onChangeListener);
		mJuegoViewModel.bindingManager = bindingManagerMock;

		SilabesGame silabesGameMock = mock(SilabesGame.class);
		when(silabesGameMock.getSilabes()).thenReturn(mSilabas);
		mJuegoViewModel.appGame = silabesGameMock;
	}

	@Test
	public void alIniciarSeObtieneElGestorDeBinding_SeObtienenLasSilabasQueSeEscucharanYSeLanzaraUnEventoDeActualizacionALasCincoSilabas() {

		mJuegoViewModel.init();

		verify(mJuegoViewModel.bindingManager).getOnChangeListener();
		verify(mJuegoViewModel.appGame).getSilabes();
		verify(onChangeListener).onChange("silaba1Tag", caSilaba);
	}

}
