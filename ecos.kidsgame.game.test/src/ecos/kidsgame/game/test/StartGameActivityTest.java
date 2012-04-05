package ecos.kidsgame.game.test;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import ecos.kidsgame.game.StartGameActivity;

public class StartGameActivityTest extends ActivityInstrumentationTestCase2<StartGameActivity> {

    private Instrumentation mInstrumentation;
    private StartGameActivity mActivity;
    public StartGameActivityTest() {
	super(StartGameActivity.class);
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
//        assertEquals( ((TextView)mActivity.findViewById(ecos.kidsgame.game.R.id.main_iniciar)).getText(), "Iniciar juego");
    }
}
