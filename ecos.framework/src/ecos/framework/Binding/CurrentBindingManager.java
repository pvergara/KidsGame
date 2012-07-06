package ecos.framework.Binding;

import java.util.HashMap;
import java.util.Map;

public class CurrentBindingManager implements BindingManager {
	private Map<String,BindingAction> mActions = new HashMap<String, BindingAction>();
	private OnChangeListener mChangeListener =new OnChangeListener() {
		public void onChange(String propertyName, Object sourceElementValue)
		{
			if(mActions.containsKey(propertyName)) {
				BindingAction action = mActions.get(propertyName);
				action.fireAction(sourceElementValue);
			}	
		}
	};

	public void manageOnChangeFor(String propertyName, BindingAction bindingAction) {
		mActions.put(propertyName, bindingAction);		
	}

	public OnChangeListener getOnChangeListener() {
		return mChangeListener;
	}
}
