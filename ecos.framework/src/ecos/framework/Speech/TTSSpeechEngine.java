package ecos.framework.Speech;

import java.util.HashMap;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;

import com.google.inject.Inject;

public class TTSSpeechEngine implements SpeechEngine, OnInitListener, OnUtteranceCompletedListener{
	private TextToSpeech mTts;
	@Inject
	Application application;
	private Context mContext;
	private boolean initialized = false;
	private SpeakFinished mOnSilabeSpeakFinished;

	public void speak(String silaba) {
		init();
		if (initialized) {
			HashMap<String, String> myHashAlarm = new HashMap<String, String>();
		    myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
		    myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SOME MESSAGE");			
			mTts.speak(silaba, TextToSpeech.QUEUE_FLUSH, myHashAlarm);
			Log.d("SpeechEngine", "mTts.speak");
		} else {
			Log.d("SpeechEngine", "mTts.speak NO ENTRA");
		}

	}

	private void init() {
		if (!initialized) {
			mContext = application.getApplicationContext();
			mTts = new TextToSpeech(mContext, this);
		}

	}

	public void onInit(int status) {
		if(status == TextToSpeech.SUCCESS) {
			Log.d("SpeechEngine", "TextToSpeech.SUCCESS");
			initialized = true;
			mTts.setOnUtteranceCompletedListener(this);
		}
	}

	public void speak(String silaba, SpeakFinished onSilabeSpeakFinished) {
		mOnSilabeSpeakFinished = onSilabeSpeakFinished;
		speak(silaba);		
	}

	public void onUtteranceCompleted(String utteranceId) {
		Log.d("SpeechEngine", "OnUtteranceCompletedListener");
		if(mOnSilabeSpeakFinished!=null)
		{
			mOnSilabeSpeakFinished.fireFinished();
			mOnSilabeSpeakFinished = null;
		}
		
	}


	public void tryInit() {
		init();
	}
	

}