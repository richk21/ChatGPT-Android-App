package com.example.chatgpt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class splashScreen extends AppCompatActivity {

    Animation rotateAnimation;
    ImageView logoImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        logoImage = (ImageView) findViewById(R.id.logo);

        rotateAnimation();
    }

    private void rotateAnimation(){
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_rotate);
        logoImage.startAnimation(rotateAnimation);
        Intent intent = new Intent(this, MainActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        }, 2000);
        //startActivity(intent);
        //finish();
    }
}
