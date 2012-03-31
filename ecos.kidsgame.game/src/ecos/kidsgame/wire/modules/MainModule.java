package ecos.kidsgame.wire.modules;

import roboguice.config.AbstractAndroidModule;
import ecos.kidsgame.game.framework.ActivityHandler;
import ecos.kidsgame.game.framework.CurrentActivityHandler;

public class MainModule extends AbstractAndroidModule {

    @Override
    public void configure() {
	bind(ActivityHandler.class).to(CurrentActivityHandler.class);
    }

}
