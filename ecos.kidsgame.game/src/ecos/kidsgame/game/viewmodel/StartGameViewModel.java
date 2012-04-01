package ecos.kidsgame.game.viewmodel;

import com.google.inject.Inject;

import ecos.framework.ActivityHandler;
import ecos.kidsgame.game.JuegoActivity;
import ecos.kidsgame.game.StartGameActivity;

public class StartGameViewModel {
    ActivityHandler mActivityHandler;

    @Inject
    public StartGameViewModel(ActivityHandler activityHandler) {
	mActivityHandler = activityHandler;
    }

    // Command
    public void iniciarSiguienteActividad(StartGameActivity currectContext) {
	mActivityHandler.showActivity(currectContext, JuegoActivity.class);
    }

}
