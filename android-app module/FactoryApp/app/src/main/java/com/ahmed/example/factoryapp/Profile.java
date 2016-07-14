package com.ahmed.example.factoryapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Apis.OnTaskComplete;
import Apis.SystemApi;
import Classes.SystemApiClass;

public class Profile extends AppCompatActivity implements View.OnClickListener, OnTaskComplete {
    TextView welcometxt, current_temptxt, door_statetxt;

    Button updatebut, logoutbut;
    String username, token;
    int system_id, role, id;
    SystemApi systemApi;
    boolean logedout = false;
    String systemid, ac_mode, ac1, ac2, ac3, ac4, door_state, current_temp, user_temp, created_at, updated_at;

    ArrayList<SystemApiClass> systemdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            Intent camedintent = getIntent();
            username = camedintent.getExtras().getString("username");
            token = camedintent.getExtras().getString("token");
            system_id = camedintent.getExtras().getInt("system_id");
            role = camedintent.getExtras().getInt("role");
        } catch (Exception e) {
            Log.i("getdata", "error");
        }
        welcometxt = (TextView) findViewById(R.id.Profile_welcome_textView);
        current_temptxt = (TextView) findViewById(R.id.Profile_currenttemp_textView);
        door_statetxt = (TextView) findViewById(R.id.Profile_door_textView);
        updatebut = (Button) findViewById(R.id.Profile_update_button);
        updatebut.setOnClickListener(this);
        welcometxt.setText("Welcome" + " " + username);
        if (role == 1) {
            updatebut.setVisibility(View.VISIBLE);

        } else if (role == 2) {
            updatebut.setVisibility(View.INVISIBLE);

        }
        //while (true)

        GetData();


    }

    @Override
    public void onClick(View v) {
        if (v == updatebut) {
            try {
                if (user_temp.isEmpty() == false) {
                    Intent intent = new Intent(this, UpdateSystem.class);
                    intent.putExtra("user_temp", user_temp);
                    intent.putExtra("ac_mode", ac_mode);
                    intent.putExtra("ac1", ac1);
                    intent.putExtra("ac2", ac2);
                    intent.putExtra("ac3", ac3);
                    intent.putExtra("ac4", ac4);
                    intent.putExtra("system_id", systemid);
                    intent.putExtra("username", username);
                    intent.putExtra("role", role);
                    startActivity(intent);
                }


            } catch (Exception e) {

            }
        }

    }

    @Override
    public void onComplete() throws ExecutionException, InterruptedException {


        systemdata = systemApi.get();
        if (systemdata.get(0).getError() == 1) {
            Toast.makeText(this, "Stop Hacking MuthaFucker -_-", Toast.LENGTH_LONG).show();
        } else if (systemdata.get(0).getError() == 0) {
            systemid = systemdata.get(0).getSystem_id();
            ac_mode = systemdata.get(0).getAc_mode();
            ac1 = systemdata.get(0).getAc1();
            ac2 = systemdata.get(0).getAc2();
            ac3 = systemdata.get(0).getAc3();
            ac4 = systemdata.get(0).getAc4();
            door_state = systemdata.get(0).getDoor_state();
            current_temp = systemdata.get(0).getCurrent_temp();
            user_temp = systemdata.get(0).getUser_temp();
            created_at = systemdata.get(0).getCreated_at();
            updated_at = systemdata.get(0).getUpdated_at();
            Log.i("wezzadoor", door_state);
            if (door_state.equals("0")) {
                door_statetxt.setText("Closed");

            } else if (door_state.equals("1")) {
                door_statetxt.setText("Open");
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);

                mBuilder.setContentTitle("Door is Open");
                mBuilder.setContentText("Opened at " + updated_at);
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                mBuilder.setSound(alarmSound);
                NotificationManager mNotifiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder.setAutoCancel(true);

                mNotifiManager.notify(1, mBuilder.build());

            }
            if (Double.parseDouble(current_temp) >= Double.parseDouble(user_temp)) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher);

                mBuilder.setContentTitle("High Temperature (" + current_temp + ")");
                mBuilder.setContentText("Measured (" + current_temp + ") at " + updated_at);
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                mBuilder.setSound(alarmSound);
                NotificationManager mNotifiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder.setAutoCancel(true);
                mNotifiManager.notify(2, mBuilder.build());
            }

            current_temptxt.setText(current_temp);


        }


    }

    public void GetData() {
        systemApi = new SystemApi(this, String.valueOf(system_id));
        systemApi.execute();
        if (!logedout) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    GetData();

                }
            }, 10000);
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            Intent intent = new Intent(this, MainActivity.class);
            logedout = true;
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }


    }
