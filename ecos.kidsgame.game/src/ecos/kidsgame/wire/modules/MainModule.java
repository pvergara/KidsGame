package ecos.kidsgame.wire.modules;

import roboguice.config.AbstractAndroidModule;
import ecos.framework.ActivityHandler;
import ecos.framework.CurrentActivityHandler;
import ecos.framework.tts.CurrentTtsEngine;
import ecos.framework.tts.TtsEngine;

public class MainModule extends AbstractAndroidModule {

    @Override
    public void configure() {
	bind(ActivityHandler.class).to(CurrentActivityHandler.class);
	bind(TtsEngine.class).to(CurrentTtsEngine.class);
    }

}
