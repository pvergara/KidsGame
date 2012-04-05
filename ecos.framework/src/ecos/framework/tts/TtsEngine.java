package ecos.framework.tts;

public interface TtsEngine {
    void setOnFinishedSpeech(OnFinishedSpeech onFinishedSpeech);
    void speak(String textToSpeak);
}
