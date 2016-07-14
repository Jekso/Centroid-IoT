package com.ahmed.example.factoryapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import Apis.OnTaskComplete;
import Apis.UpdateApi;

public class UpdateSystem extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, OnTaskComplete {
    EditText user_tempedittxt;
    Switch ac_modebut,ac1but,ac2but,ac3but,ac4but;
    Button submitbut;
    String ac_mode,ac1,ac2,ac3,ac4,user_temp,systemid,username;
    boolean ac1_state,ac2_state,ac3_state,ac4_state,manual_mode;
    int role;
    UpdateApi updateApi;
    ProgressDialog progressDialog;

    TextView usertempview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_system);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try{
            Intent camedintent=getIntent();
            role=camedintent.getExtras().getInt("role");
            username=camedintent.getExtras().getString("username");
            systemid=camedintent.getExtras().getString("system_id");
            ac_mode=camedintent.getExtras().getString("ac_mode");
            ac1=camedintent.getExtras().getString("ac1");
            ac2=camedintent.getExtras().getString("ac2");
            ac3=camedintent.getExtras().getString("ac3");
            ac4=camedintent.getExtras().getString("ac4");
            ac1_state = check(ac1);
            ac2_state = check(ac2);
            ac3_state = check(ac3);
            ac4_state = check(ac4);
            manual_mode=check(ac_mode);
            user_temp=camedintent.getExtras().getString("user_temp");
            Log.i("wezzamode", manual_mode+"");
            Log.i("wezzamode", ac_mode+"");



        }


        catch (Exception e)
        {
            Toast.makeText(this,"error data",Toast.LENGTH_LONG).show();
        }

        user_tempedittxt=(EditText)findViewById(R.id.Update_usertemp_editText);
        ac_modebut= (Switch) findViewById(R.id.Update_ac_mode_toggleButton);
        ac1but=(Switch)findViewById(R.id.Update_ac1_toggleButton);
        ac2but=(Switch)findViewById(R.id.Update_ac2_toggleButton);
        ac3but=(Switch)findViewById(R.id.Update_ac3_toggleButton);
        ac4but=(Switch)findViewById(R.id.Update_ac4_toggleButton);
        submitbut=(Button)findViewById(R.id.Update_submit_button);
        usertempview=(TextView)findViewById(R.id.Update_usertemp_textView);
        submitbut.setOnClickListener(this);
        if(ac_mode.equals("1"))
        {   ac1but.setEnabled(true);
            ac2but.setEnabled(true);
            ac3but.setEnabled(true);
            ac4but.setEnabled(true);


        }
        else if(ac_mode.equals("0"))
        {
            ac1but.setEnabled(false);
            ac2but.setEnabled(false);
            ac3but.setEnabled(false);
            ac4but.setEnabled(false);
        }
        ac1but.setChecked(check(ac1));
        ac2but.setChecked(check(ac2));
        ac3but.setChecked(check(ac3));
        ac4but.setChecked(check(ac4));
        ac_modebut.setChecked(check(ac_mode));
        user_tempedittxt.setText(user_temp);
        ac_modebut.setOnCheckedChangeListener(this);
        ac1but.setOnCheckedChangeListener(this);
        ac2but.setOnCheckedChangeListener(this);
        ac3but.setOnCheckedChangeListener(this);
        ac4but.setOnCheckedChangeListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v==submitbut)
        {user_temp=user_tempedittxt.getText().toString();
            progressDialog=new ProgressDialog(this);
            if(manual_mode==true)
            {
                updateApi=new UpdateApi(this,progressDialog,systemid,user_temp,retstring(manual_mode),retstring(ac1_state),retstring(ac2_state),retstring(ac3_state),retstring(ac4_state));
            }
            else if(manual_mode==false)
            {
                updateApi=new UpdateApi(this,progressDialog,systemid,user_temp,retstring(manual_mode));
            }
        }updateApi.execute();

    }
    public boolean check(String check)

    {
        return (check.equals("1") ) ? true : false ;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView==ac_modebut)
        {
            manual_mode= isChecked;
            if(manual_mode)
            {
                ac1but.setEnabled(true);
                ac2but.setEnabled(true);
                ac3but.setEnabled(true);
                ac4but.setEnabled(true);
            }
            else if(!manual_mode)
            {
                ac1but.setEnabled(false);
                ac2but.setEnabled(false);
                ac3but.setEnabled(false);
                ac4but.setEnabled(false);
            }
        }
        else if(buttonView==ac1but)
        {
            ac1_state=isChecked;
        }
         else if(buttonView==ac2but)
        {
            ac2_state=isChecked;
        }
        else if(buttonView==ac3but)
        {
            ac3_state=isChecked;
        }
       else if(buttonView==ac4but)
        {
            ac4_state=isChecked;
        }

    }
    public String retstring(boolean check)
    {
        return (check==true) ? "1" : "0" ;
    }

    @Override
    public void onComplete() throws ExecutionException, InterruptedException {
        Toast.makeText(this,"Succeed",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,Profile.class);
        intent.putExtra("system_id",Integer.parseInt(systemid));
        intent.putExtra("username",username);
        intent.putExtra("role",role);
        startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent=new Intent(this,Profile.class);
            intent.putExtra("system_id",Integer.parseInt(systemid));
            intent.putExtra("username",username);
            intent.putExtra("role",role);
            startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
