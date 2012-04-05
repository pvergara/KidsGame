package ecos.kidsgame.game;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

import ecos.kidsgame.game.viewmodel.StartGameViewModel;

@EActivity(R.layout.main)
public class StartGameActivity extends Activity {

    @ViewById(R.id.main_iniciar)
    Button mIniciar;
    @Bean
    StartGameViewModel mStartGameActivityViewModel;

    @Click({ R.id.main_iniciar })
    public void siguienteActividad() {
	mStartGameActivityViewModel.iniciarSiguienteActividad(StartGameActivity.this);
    }

}