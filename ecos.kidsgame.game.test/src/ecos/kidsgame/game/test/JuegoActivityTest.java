package ecos.kidsgame.game.test;

import roboguice.test.RoboActivityUnitTestCase;
import android.content.Intent;
import ecos.kidsgame.game.JuegoActivity;
import ecos.kidsgame.game.KidsGameApplication;

public class JuegoActivityTest extends RoboActivityUnitTestCase<JuegoActivity> {

    private Intent mIntent= new Intent(Intent.ACTION_MAIN);
    public JuegoActivityTest() {
	super(JuegoActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
	super.setUp();
    }

    public void testTodosLosElementosDeLaActividadEstanEnSuEstadoInicial() {
	setApplication( new KidsGameApplication( getInstrumentation().getTargetContext() ) );
        final JuegoActivity activity = startActivity(mIntent, null, null);

        // Test some things
        assertNotNull(activity);
    }
}
