package ecos.kidsgame.wire.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

import ecos.framework.ActivityHandler;
import ecos.framework.CurrentActivityHandler;
import ecos.framework.Binding.BindingManager;
import ecos.framework.Binding.CurrentBindingManager;
import ecos.framework.Speech.SpeechEngine;
import ecos.framework.Speech.TTSSpeechEngine;

public class MainModule extends AbstractModule implements Module {


	@Override
	protected void configure() {
		bind(ActivityHandler.class).to(CurrentActivityHandler.class);		
		bind(BindingManager.class).to(CurrentBindingManager.class);		
		bind(SpeechEngine.class).to(TTSSpeechEngine.class);		
	}

}
