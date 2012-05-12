package ecos.kidsgame.game;

import roboguice.activity.RoboActivity;
import android.os.Bundle;

public class JuegoActivity extends RoboActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);        
    }

}
