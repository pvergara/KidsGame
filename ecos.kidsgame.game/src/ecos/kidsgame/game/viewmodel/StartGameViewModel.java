package ecos.kidsgame.game.viewmodel;

import ecos.framework.ActivityHandler;
import ecos.kidsgame.game.JuegoActivity;
import ecos.kidsgame.game.StartGameActivity;

public class StartGameViewModel {

	ActivityHandler mActivityHandler;

	public void setActivityHandlerOnlyForTest(ActivityHandler ActivityHandler) {
		mActivityHandler = ActivityHandler;
	}

	// Command
	public void iniciarSiguienteActividad(StartGameActivity currectContext) {
		mActivityHandler.showActivity(currectContext, JuegoActivity.class);
	}

}
