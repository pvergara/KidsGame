package ecos.framework;

import android.app.Activity;
import android.content.Context;

public interface ActivityHandler {
    public void showActivity(Context currentContext,Class<? extends Activity> activityToBeShowed);
}
