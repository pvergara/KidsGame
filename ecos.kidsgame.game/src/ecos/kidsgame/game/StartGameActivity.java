package ecos.kidsgame.game;

import android.app.Activity;
import android.os.Bundle;

public class StartGameActivity extends Activity {

//    @InjectView(R.id.main_iniciar)
//    Button mIniciar;
//    @Inject                            
//    StartGameViewModel mStartGameActivityViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }    
    
//    public void siguienteActividad() {
////	mStartGameActivityViewModel.iniciarSiguienteActividad(StartGameActivity.this);
//    }

}