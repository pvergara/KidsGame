package ecos.framework.Speech;


public interface SpeechEngine
{
	void tryInit();
	void speak(String silaba);
	void speak(String silaba, SpeakFinished mOnSilabeSpeakFinished);
}
