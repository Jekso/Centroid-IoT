package com.ahmed.example.factoryapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Apis.OnTaskComplete;
import Apis.UserApi;
import Classes.UserApiClass;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnTaskComplete{
    EditText passwordtxt,usernametxt;
    Button logbutton;
    UserApi loginapi;
    ProgressDialog  loginprogress;
    String username,password,token;
    ArrayList<UserApiClass>userdata;
    int role,system_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        passwordtxt=(EditText)findViewById(R.id.Main_password_editText);
        usernametxt=(EditText)findViewById(R.id.Main_UserName_editText);
        logbutton=(Button)findViewById(R.id.Main_login_button);
        logbutton.setOnClickListener(this);






    }


    @Override
    public void onClick(View v) {
        if(v==logbutton)
        {   username=usernametxt.getText().toString();
            password=passwordtxt.getText().toString();
            loginprogress=new ProgressDialog(this);
            loginapi=new UserApi(this,loginprogress,username,password);
            loginapi.execute();

        }

    }

    @Override
    public void onComplete() throws ExecutionException, InterruptedException {
        try {
            userdata = loginapi.get();
            if (userdata.get(0).getError() == 1) {
                Toast.makeText(this, "wrong username or password", Toast.LENGTH_LONG).show();
            } else if (userdata.get(0).getError() == 0) {
                role = userdata.get(0).getRole();
                system_id = userdata.get(0).getSystem_id();
                token = userdata.get(0).getToken();
                Intent intent = new Intent(this, Profile.class);
                intent.putExtra("role", role);
                intent.putExtra("system_id", system_id);
                intent.putExtra("token", token);
                intent.putExtra("username", username);
                startActivity(intent);


            }
        }
        catch (Exception e)
        {

        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
