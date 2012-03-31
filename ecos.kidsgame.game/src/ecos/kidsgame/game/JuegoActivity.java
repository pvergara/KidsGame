package ecos.kidsgame.game;

import android.os.Bundle;
import ecos.kidsgame.game.R;
import roboguice.activity.RoboActivity;

public class JuegoActivity extends RoboActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);        
    }

}
