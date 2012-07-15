package ecos.framework.Speech;

import android.app.Application;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

import com.google.inject.Inject;


public class TTSSpeechEngine implements SpeechEngine, OnInitListener
{
	private TextToSpeech mTts;
	@Inject
    Application application;
	private Context mContext;
	private boolean initialized=false;

	public void speak(String silaba)
	{
		init();
		
		if(initialized)
		{
			mTts.speak(silaba, TextToSpeech.QUEUE_FLUSH, null);
			Log.d("SpeechEngine", "mTts.speak");
		}
		else
		{
			Log.d("SpeechEngine", "mTts.speak NO ENTRA");			
		}
		
	}

	private void init()
	{
		if(!initialized){
			mContext = application.getApplicationContext();
			mTts = new TextToSpeech(mContext, this);
		}

		
	}

	public void onInit(int arg0)
	{
		initialized = true;		
	}

}
