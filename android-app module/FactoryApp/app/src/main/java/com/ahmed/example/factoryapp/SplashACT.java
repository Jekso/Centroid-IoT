package com.ahmed.example.factoryapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ahmed ezz on 2/28/2016.
 */
public class SplashACT extends Activity {
    TextView txt;
    ImageView img;
    private final int SPLASH_DISPLAY_LENGTH = 5000;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.content_splach);


        Thread myThread = new Thread(){
            @Override
            public void run() {
                try
                {
                    sleep(4000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    Intent x = new Intent(SplashACT.this,MainActivity.class);
                    x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    x.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                        x.addFlags(0x8000); // equal to Intent.FLAG_ACTIVITY_CLEAR_TASK which is only
                    startActivity(x);
                }
            }
        };
        myThread.start();

    }
}