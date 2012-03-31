package ecos.kidsgame.game;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.inject.Inject;

import ecos.kidsgame.game.viewmodel.StartGameViewModel;

public class StartGameActivity extends RoboActivity {

    @InjectView(R.id.main_iniciar) Button mIniciar;
    @Inject StartGameViewModel mStartGameActivityViewModel;
    private OnClickListener mIniciarClick = new OnClickListener() {
        public void onClick(View v) {
            mStartGameActivityViewModel.iniciarSiguienteActividad(StartGameActivity.this);
        }
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mIniciar.setOnClickListener(mIniciarClick);
    }
}