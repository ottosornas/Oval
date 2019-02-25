package com.example.oval;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView myImageView= (ImageView)findViewById(R.id.logo);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myImageView.startAnimation(myFadeInAnimation); //Set animation to your ImageView

        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                startMain();
            }
        }.start();
    }

    private void startMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
