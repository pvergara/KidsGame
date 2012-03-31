package ecos.kidsgame.game.viewmodel;

import com.google.inject.Inject;

import ecos.kidsgame.game.JuegoActivity;
import ecos.kidsgame.game.StartGameActivity;
import ecos.kidsgame.game.framework.ActivityHandler;

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
