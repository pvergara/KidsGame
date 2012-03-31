package ecos.kidsgame.game;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.xtremelabs.robolectric.RobolectricTestRunner;

import ecos.kidsgame.game.framework.ActivityHandler;
import ecos.kidsgame.game.viewmodel.StartGameViewModel;

@RunWith(RobolectricTestRunner.class)
public class FirstRobolectricTest {

    @Test
    public void alInvocarIniciarSiguienteActividadSePideAbrirJuegoActivity() throws Exception {
	ActivityHandler mockedActivityHandler =  mock(ActivityHandler.class);
	StartGameViewModel startGameViewModel = new StartGameViewModel(mockedActivityHandler);
	
	StartGameActivity currectContext = mock(StartGameActivity.class);
	startGameViewModel.iniciarSiguienteActividad(currectContext);
	
	verify(mockedActivityHandler).showActivity(currectContext, JuegoActivity.class);
    }
}