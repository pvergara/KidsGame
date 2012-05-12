package ecos.framework;

import roboguice.activity.RoboActivity;
import android.content.Context;

public interface ActivityHandler {
    public void showActivity(Context currentContext,Class<? extends RoboActivity> activityToBeShowed);
}
