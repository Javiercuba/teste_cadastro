package com.javier.testecadastro;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

public class SplashScreen extends AppCompatActivity {
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_splash);


        TextView inicio_text =findViewById(R.id.bemvindo);
        TextView second_text= findViewById(R.id.second_text);

        anim = AnimationUtils.loadAnimation(this,R.anim.splash);

        inicio_text.setAnimation(anim);

        ObjectAnimator titleview2 = ObjectAnimator.ofFloat(second_text, "rotation", 1470);
        titleview2.start();

        ObjectAnimator titleview = ObjectAnimator.ofFloat(inicio_text, "rotation", 370);
        titleview.start();

        titleview2.setDuration(2000);
        titleview.setDuration(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(SplashScreen.this, MainActivity.class);
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(SplashScreen.this.getApplicationContext(),R.anim.fade_out,R.anim.fade_in);
                ActivityCompat.startActivity(SplashScreen.this,intent,activityOptionsCompat.toBundle());
                finish();
            }
        },2500);


    }
}