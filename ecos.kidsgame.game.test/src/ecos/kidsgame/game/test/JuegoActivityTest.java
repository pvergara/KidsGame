package ecos.kidsgame.game.test;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import ecos.kidsgame.game.JuegoActivity;

public class JuegoActivityTest extends ActivityInstrumentationTestCase2<JuegoActivity> {

    private Instrumentation mInstrumentation;
    private JuegoActivity mActivity;

    public JuegoActivityTest() {
	super(JuegoActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
	super.setUp();
	mInstrumentation = getInstrumentation();

	mActivity = getActivity();
    }

    public void testTodosLosElementosDeLaActividadEstanEnSuEstadoInicial() {
	// Test some things
	assertNotNull(mActivity);
    }
}
