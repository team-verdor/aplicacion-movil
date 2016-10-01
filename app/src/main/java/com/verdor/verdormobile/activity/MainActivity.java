package com.verdor.verdormobile.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.verdor.verdormobile.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.button8);
        Button b2 = (Button) findViewById(R.id.button10);
        Button b3 = (Button) findViewById(R.id.button7);

        b1.setOnClickListener(new View.OnClickListener() {

            /*public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),OperationsActivity.class);
                startActivity(intent);*/
            public void onClick(View v) {
                Intent myintent2 = new Intent(MainActivity.this, OperationsActivity.class);
                startActivity(myintent2);

            }
        });
    }
}
