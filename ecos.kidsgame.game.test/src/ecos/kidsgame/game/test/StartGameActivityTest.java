package ecos.kidsgame.game.test;

import android.test.ActivityInstrumentationTestCase2;
import ecos.kidsgame.game.StartGameActivity;

public class StartGameActivityTest extends
		ActivityInstrumentationTestCase2<StartGameActivity> {

	private StartGameActivity mActivity;

	public StartGameActivityTest() {
		super(StartGameActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		getInstrumentation();

		mActivity = getActivity();
	}

	public void testTodosLosElementosDeLaActividadEstanEnSuEstadoInicial() {
		// Test some things
		assertNotNull(mActivity);
		// assertEquals(
		// ((TextView)mActivity.findViewById(ecos.kidsgame.game.R.id.main_iniciar)).getText(),
		// "Iniciar juego");
	}
}
