package ecos.framework;

import roboguice.activity.RoboActivity;
import android.content.Context;
import android.content.Intent;

public class CurrentActivityHandler implements ActivityHandler {

	public void showActivity(Context currentContext,Class<? extends RoboActivity> activityToBeShowed) {
	    Intent myItent = new Intent(currentContext, activityToBeShowed);
	    currentContext.startActivity(myItent);
	}

}
