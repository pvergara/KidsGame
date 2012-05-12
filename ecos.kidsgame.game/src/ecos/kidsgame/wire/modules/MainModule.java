package ecos.kidsgame.wire.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

import ecos.framework.ActivityHandler;
import ecos.framework.CurrentActivityHandler;

public class MainModule extends AbstractModule implements Module {


	@Override
	protected void configure() {
		bind(ActivityHandler.class).to(CurrentActivityHandler.class);		
	}

}
