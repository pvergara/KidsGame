package ecos.kidsgame.game.viewmodel;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;

import ecos.framework.ActivityHandler;
import ecos.framework.CurrentActivityHandler;
import ecos.kidsgame.game.JuegoActivity;
import ecos.kidsgame.game.StartGameActivity;

@EBean
public class StartGameViewModel {

    @Bean(CurrentActivityHandler.class)
    ActivityHandler mActivityHandler;

    public void setActivityHandlerOnlyForTest(ActivityHandler ActivityHandler)
    {
	mActivityHandler = ActivityHandler;
    }
    
    
    // Command
    public void iniciarSiguienteActividad(StartGameActivity currectContext) {
	mActivityHandler.showActivity(currectContext, JuegoActivity.class);
    }

}
