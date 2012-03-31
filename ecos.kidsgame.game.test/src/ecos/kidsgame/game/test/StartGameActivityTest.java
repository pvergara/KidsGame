package ecos.kidsgame.game.test;

import roboguice.test.RoboActivityUnitTestCase;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import ecos.kidsgame.game.KidsGameApplication;
import ecos.kidsgame.game.StartGameActivity;

public class StartGameActivityTest extends RoboActivityUnitTestCase<StartGameActivity> {

    private Intent mIntent= new Intent(Intent.ACTION_MAIN);;
    public StartGameActivityTest() {
	super(StartGameActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
	super.setUp();
    }

    public void testTodosLosElementosDeLaActividadEstanEnSuEstadoInicial() {
	setApplication( new KidsGameApplication( getInstrumentation().getTargetContext() ) );
        final Activity activity = startActivity(mIntent, null, null);

        // Test some things
        assertNotNull(activity);
        assertEquals( ((TextView)activity.findViewById(ecos.kidsgame.game.R.id.main_iniciar)).getText(), "Iniciar juego");
    }
}
