package com.example.oval;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import java.util.ArrayList;

public class SpeechListener extends Activity implements RecognitionListener {
    String message;
    SpeechRecognizer speech;
    Intent recognizerIntent;

    public SpeechListener(){
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);

        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {
        switch (error) {

            case SpeechRecognizer.ERROR_AUDIO:

                message = "Error audio";

                break;

            case SpeechRecognizer.ERROR_CLIENT:

                message = "Error client";

                break;

            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:

                message = "Error permission";

                break;

            case SpeechRecognizer.ERROR_NETWORK:

                message = "Error network";

                break;

            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:

                message = "Error network timeout";

                break;

            case SpeechRecognizer.ERROR_NO_MATCH:

                message = "Error no match";

                break;

            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:

                message = "Error recognizer busy";

                break;

            case SpeechRecognizer.ERROR_SERVER:

                message = "Error server";

                break;

            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:

                message = "Error speech timeout";

                break;

            default:

                message = "Error understand";

                break;
        }

    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results
            .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        for(String s : matches) {
            Log.d("Result", s);
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

}


