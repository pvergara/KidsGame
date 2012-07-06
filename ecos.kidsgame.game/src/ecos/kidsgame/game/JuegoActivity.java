package ecos.kidsgame.game;

import java.util.HashMap;
import java.util.Map;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;

import ecos.kidsgame.game.viewmodel.BindingAction;
import ecos.kidsgame.game.viewmodel.JuegoViewModel;
import ecos.kidsgame.game.viewmodel.OnChangeListener;

public class JuegoActivity extends RoboActivity {
    @InjectView(R.id.juegoBoton1)
    Button mSilaba1;

    @InjectView(R.id.juegoBoton2)
    Button mSilaba2;

    @InjectView(R.id.juegoBoton3)
    Button mSilaba3;

    @InjectView(R.id.juegoBoton4)
    Button mSilaba4;

    @InjectView(R.id.juegoBoton5)
    Button mSilaba5;

    @Inject                            
    JuegoViewModel mJuegoViewModel;

	private Map<String,BindingAction> mActions = new HashMap<String, BindingAction>();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);        
        
        mJuegoViewModel.setOnChangeListener(mChange);
        
        mSilaba1.setOnClickListener(mSilabasOnClick);
        
        manageOnChangeFor("silabaCAEnabled",new BindingAction() {
        		public void fireAction(Object sourceElementValue) {
        			mSilaba1.setEnabled((Boolean) sourceElementValue);
    			}
		});

        
        mSilaba2.setOnClickListener(mSilabasOnClick);
        mSilaba3.setOnClickListener(mSilabasOnClick);
        mSilaba4.setOnClickListener(mSilabasOnClick);
        mSilaba5.setOnClickListener(mSilabasOnClick);        
    }
    

	private void manageOnChangeFor(String propertyName, BindingAction bindingAction) {
		mActions.put(propertyName, bindingAction);
		
	}


	private OnClickListener mSilabasOnClick = new OnClickListener() {
		
		public void onClick(View v) {
			TextView silaba = (TextView) v;
			mJuegoViewModel.silabaPulsada(silaba.getText().toString());
		}
	};
	
	private OnChangeListener mChange = new OnChangeListener() {
		public void onChange(String elementName, Object sourceElementValue)
		{
			if(mActions.containsKey(elementName)) {
				BindingAction action = mActions.get(elementName);
				action.fireAction(sourceElementValue);
			}
			
		}
	};
    

}
