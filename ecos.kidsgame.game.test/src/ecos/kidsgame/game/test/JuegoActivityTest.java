package ecos.kidsgame.game.test;

import android.test.ActivityInstrumentationTestCase2;
import ecos.kidsgame.game.JuegoActivity;

public class JuegoActivityTest extends ActivityInstrumentationTestCase2<JuegoActivity> {

    private JuegoActivity mActivity;

    public JuegoActivityTest() {
	super(JuegoActivity.class);
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
    }
}
