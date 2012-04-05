package ecos.kidsgame.game;

import android.app.Activity;
import android.os.Bundle;

import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.juego) 
public class JuegoActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);        
    }

}
