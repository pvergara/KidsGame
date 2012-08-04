package ecos.kidsgame.game;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.inject.Inject;

import ecos.framework.Speech.SpeechEngine;
import ecos.kidsgame.game.viewmodel.StartGameViewModel;

public class StartGameActivity extends RoboActivity {

    @InjectView(R.id.main_iniciar)
    Button mIniciar;
    @Inject                            
    StartGameViewModel mStartGameActivityViewModel;
    @Inject                            
    SpeechEngine mSpeechEngine;
	
    
    private OnClickListener mIniciarOnClick = new OnClickListener() {
		
		public void onClick(View v) {
			siguienteActividad();
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mIniciar.setOnClickListener(mIniciarOnClick);
        
        mStartGameActivityViewModel.init();
    }    
    
    public void siguienteActividad() {
    	mStartGameActivityViewModel.iniciarSiguienteActividad(StartGameActivity.this);
    }

}