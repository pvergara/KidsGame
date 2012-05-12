package ecos.kidsgame.game.viewmodel;

import com.google.inject.Inject;

import ecos.framework.ActivityHandler;
import ecos.kidsgame.game.JuegoActivity;
import ecos.kidsgame.game.StartGameActivity;

public class StartGameViewModel {

    @Inject                            
	ActivityHandler mActivityHandler;

	public void setActivityHandlerOnlyForTest(ActivityHandler ActivityHandler) {
		mActivityHandler = ActivityHandler;
	}

	// Command
	public void iniciarSiguienteActividad(StartGameActivity currectContext) {
		mActivityHandler.showActivity(currectContext, JuegoActivity.class);
	}

}
