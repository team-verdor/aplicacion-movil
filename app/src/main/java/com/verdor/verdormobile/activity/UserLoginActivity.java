package com.verdor.verdormobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.verdor.verdormobile.R;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        Button b6 = (Button) findViewById(R.id.button6);

        b6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = new Intent(UserLoginActivity.this, MainActivity.class);
                startActivity(myintent2);

            }
        });
    }
}
