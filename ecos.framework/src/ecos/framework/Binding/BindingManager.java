package ecos.framework.Binding;

import android.app.Activity;


public interface BindingManager {
	public void manageOnChangeFor(String propertyName, BindingAction bindingAction,Activity activity);

	public OnChangeListener getOnChangeListener();

}