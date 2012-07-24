package ecos.framework.Binding;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;

public class CurrentBindingManager implements BindingManager,OnChangeListener {
	private Map<String,BindingAction> mActions = new HashMap<String, BindingAction>();
	private Map<String, Activity> mActivities = new HashMap<String, Activity>();
	

	public void manageOnChangeFor(String propertyName, BindingAction bindingAction,Activity activity) {
		mActions.put(propertyName, bindingAction);		
		mActivities.put(propertyName, activity);		
	}

	public OnChangeListener getOnChangeListener() {
		return this;
	}

	@Override
	public void onChange(String propertyName, final Object sourceElementValue)
	{
		if(mActions.containsKey(propertyName) && mActions.containsKey(propertyName)) {
			final BindingAction action = mActions.get(propertyName);
			Activity activity = mActivities.get(propertyName);;
			Runnable runnable = new Runnable() {				
					public void run() {
						action.fireAction(sourceElementValue);
					}
				};
			activity.runOnUiThread(runnable); 
			
		}	
	}
}
