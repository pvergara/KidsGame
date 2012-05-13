package ecos.framework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class CurrentActivityHandler implements ActivityHandler {

	public void showActivity(Context currentContext,Class<? extends Activity> activityToBeShowed) {
	    Intent myItent = new Intent(currentContext, activityToBeShowed);
	    currentContext.startActivity(myItent);
	}

}
