package ecos.framework.Speech;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

import com.google.inject.Inject;

public class TinySpeechEngine implements SpeechEngine
{
	@Inject
    Application application;
	private MediaPlayer mPlayer;


	public void speak(String text)
	{		
		Context context = application.getApplicationContext();
			
		if(application!=null) {
			int silabaResId = 
					context.getResources().
						getIdentifier(text.toLowerCase(), "raw", context.getPackageName());
			
			if(silabaResId!=0) {
				mPlayer = MediaPlayer.create(application.getApplicationContext(), silabaResId);				
			    mPlayer.start();
			}
		}
	}

}
