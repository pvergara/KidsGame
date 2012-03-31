package ecos.kidsgame.game;

import java.util.List;

import roboguice.application.RoboApplication;
import android.app.Instrumentation;
import android.content.Context;

import com.google.inject.Module;

import ecos.kidsgame.wire.modules.MainModule;

public class KidsGameApplication extends RoboApplication {

    public KidsGameApplication() {}
    
    public KidsGameApplication(Instrumentation instrumentation) {
	super();
	attachBaseContext(instrumentation.getTargetContext());
    };

    public KidsGameApplication(Context context) {
	super();
	 attachBaseContext(context);	
    }

    protected void addApplicationModules(List<Module> modules) {
	// add your module with custom bindings
	modules.add(new MainModule());
    }

    @Override
    public void onCreate() {
	super.onCreate();
    }

}
