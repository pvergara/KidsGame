package ecos.framework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.googlecode.androidannotations.annotations.EBean;

@EBean
public class CurrentActivityHandler implements ActivityHandler {

	public void showActivity(Context currentContext,Class<? extends Activity> activityToBeShowed) {
	    Intent myItent = new Intent(currentContext, activityToBeShowed);
	    currentContext.startActivity(myItent);
	}

}
