package ecos.kidsgame.game;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.inject.Inject;

import ecos.framework.Binding.BindingAction;
import ecos.framework.Binding.BindingManager;
import ecos.kidsgame.game.viewmodel.JuegoViewModel;

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

	private BindingManager mBindingManager;

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);        
        
        mJuegoViewModel.init();
        mBindingManager =  mJuegoViewModel.getBindingManager();
        
        mSilaba1.setOnClickListener(mSilabasOnClick);        
        mSilaba2.setOnClickListener(mSilabasOnClick);
        mSilaba3.setOnClickListener(mSilabasOnClick);
        mSilaba4.setOnClickListener(mSilabasOnClick);
        mSilaba5.setOnClickListener(mSilabasOnClick);        

        mBindingManager.manageOnChangeFor("silabaCAEnabled",mBindingActionSilaba1);
		mBindingManager.manageOnChangeFor("silabaCEEnabled",mBindingActionSilaba2);
		mBindingManager.manageOnChangeFor("silabaCIEnabled",mBindingActionSilaba3);
		mBindingManager.manageOnChangeFor("silabaCOEnabled",mBindingActionSilaba4);
		mBindingManager.manageOnChangeFor("silabaCUEnabled",mBindingActionSilaba5);
		
    }

	private OnClickListener mSilabasOnClick = new OnClickListener() {
		
		public void onClick(View v) {
			TextView silaba = (TextView) v;
			mJuegoViewModel.silabaPulsada(silaba.getText().toString());
		}
	};

    BindingAction mBindingActionSilaba1 = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba1.setEnabled((Boolean) sourceElementValue);
		}
    };
	
    BindingAction mBindingActionSilaba2 = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba2.setEnabled((Boolean) sourceElementValue);
		}
    };
	
    BindingAction mBindingActionSilaba3 = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba3.setEnabled((Boolean) sourceElementValue);
		}
    };
	
    BindingAction mBindingActionSilaba4 = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba4.setEnabled((Boolean) sourceElementValue);
		}
    };
	
    BindingAction mBindingActionSilaba5 = new BindingAction() {
		public void fireAction(Object sourceElementValue) {
			mSilaba5.setEnabled((Boolean) sourceElementValue);
		}
    };
	
}
