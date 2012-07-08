package ecos.framework.Binding;


public interface BindingManager {
	public void manageOnChangeFor(String propertyName, BindingAction bindingAction);

	public OnChangeListener getOnChangeListener();

}