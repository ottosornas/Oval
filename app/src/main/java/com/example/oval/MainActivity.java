package com.example.oval;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

//snowboy hotword detection för att aktivera
//använd androids inbyggda speech recognition https://www.techjini.com/blog/android-speech-to-text-tutorial-part1/

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextToSpeech t1;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private ImageButton mSpeakBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button one = findViewById(R.id.button1);
        one.setOnClickListener(this);
        Button two = findViewById(R.id.button2);
        two.setOnClickListener(this);
        Button three = findViewById(R.id.button3);
        three.setOnClickListener(this);
        Button four = findViewById(R.id.button4);
        four.setOnClickListener(this);
        Button five = findViewById(R.id.button5);
        five.setOnClickListener(this);
        Button six = findViewById(R.id.button6);
        six.setOnClickListener(this);
        Button seven = findViewById(R.id.button7);
        seven.setOnClickListener(this);
        Button eight = findViewById(R.id.button8);
        eight.setOnClickListener(this);
        Button nine = findViewById(R.id.button9);
        nine.setOnClickListener(this);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        mSpeakBtn = findViewById(R.id.micbutton);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    shelfSelector(result.get(0));
                }
                break;
            }

        }
    }

    private void shelfSelector(String result) {
        String[] words = result.split(" ");
        for(String s : words) {
            Log.d("Current word:", s);
            if(s.equals("1") || s.toLowerCase().equals("one")) {
                textToSpeech("1");
            }else if(s.equals("2") || s.toLowerCase().equals("two")) {
                textToSpeech("2");
            }else if(s.equals("3") || s.toLowerCase().equals("three")) {
                textToSpeech("3");
            }else if(s.equals("4") || s.toLowerCase().equals("four")) {
                textToSpeech("4");
            }else if(s.equals("5") || s.toLowerCase().equals("five")) {
                textToSpeech("5");
            }else if(s.equals("6") || s.toLowerCase().equals("six")) {
                textToSpeech("6");
            }else if(s.equals("7") || s.toLowerCase().equals("seven")) {
                textToSpeech("7");
            }else if(s.equals("8") || s.toLowerCase().equals("eight")) {
                textToSpeech("8");
            }else if(s.equals("9") || s.toLowerCase().equals("nine")) {
                textToSpeech("9");
            }else {
                Toast.makeText(MainActivity.this, "I did not catch that", Toast.LENGTH_LONG).show();
                speakError();
            }
        }
    }

    private void textToSpeech(String s) {
        String toSpeak = "You have selected shelf number " + s;
        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void speakError() {
        String errorMsg = "Sorry, I did not quite catch that";
        t1.speak(errorMsg, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button1:
                textToSpeech("1");
                break;

            case R.id.button2:
                textToSpeech("2");
                break;

            case R.id.button3:
                textToSpeech("3");
                break;

            case R.id.button4:
                textToSpeech("4");
                break;

            case R.id.button5:
                textToSpeech("5");
                break;

            case R.id.button6:
                textToSpeech("6");
                break;

            case R.id.button7:
                textToSpeech("7");
                break;

            case R.id.button8:
                textToSpeech("8");
                break;

            case R.id.button9:
                textToSpeech("9");
                break;

            default:
                break;
        }
    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }

}
