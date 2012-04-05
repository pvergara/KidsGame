package ecos.framework.tts;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;

public abstract class CurrentTtsEngineActivity extends Activity implements TtsEngine, OnInitListener, OnUtteranceCompletedListener {

    private static final int MY_DATA_CHECK_CODE = 7;
    private TextToSpeech mTts;
    private OnFinishedSpeech mOnFinishedSpeech;

    public CurrentTtsEngineActivity() {
	Intent checkIntent = new Intent();
	checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
	startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
	mTts.setOnUtteranceCompletedListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode == MY_DATA_CHECK_CODE) {
	    if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
		// success, create the TTS instance
		mTts = new TextToSpeech(this, this);
	    } else {
		// missing data, install it
		Intent installIntent = new Intent();
		installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
		startActivity(installIntent);
	    }
	}

    }
    
    public void onInit(int arg0) {
	Locale esLocale = new Locale("spa", "ESP");
	if (mTts.isLanguageAvailable(esLocale) == TextToSpeech.LANG_COUNTRY_AVAILABLE)
	    mTts.setLanguage(esLocale);
    }

    public void setOnFinishedSpeech(OnFinishedSpeech onFinishedSpeech) {
	mOnFinishedSpeech = onFinishedSpeech;
    }

    public void speak(String textToSpeak) {
	mTts.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onUtteranceCompleted(String utteranceId) {
	if(mOnFinishedSpeech!=null)
	    mOnFinishedSpeech.speechFinished();
    }

}
