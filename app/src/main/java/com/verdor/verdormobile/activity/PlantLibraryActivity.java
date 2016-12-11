package com.verdor.verdormobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.verdor.verdormobile.R;

public class PlantLibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_library);

        Button b1 = (Button) findViewById(R.id.button7);
        Button b2 = (Button) findViewById(R.id.button9);
        Button b3 = (Button) findViewById(R.id.button11);
        Button b4 = (Button) findViewById(R.id.button12);

        b1.setOnClickListener(new View.OnClickListener() {

            /*public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),OperationsActivity.class);
                startActivity(intent);*/
            public void onClick(View v) {
                Intent myintent2 = new Intent(PlantLibraryActivity.this, CultivationActivity.class);
                startActivity(myintent2);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            /*public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),OperationsActivity.class);
                startActivity(intent);*/
            public void onClick(View v) {
                Intent myintent2 = new Intent(PlantLibraryActivity.this, CultivationActivity.class);
                startActivity(myintent2);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {

            /*public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),OperationsActivity.class);
                startActivity(intent);*/
            public void onClick(View v) {
                Intent myintent2 = new Intent(PlantLibraryActivity.this, CultivationActivity.class);
                startActivity(myintent2);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {

            /*public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),OperationsActivity.class);
                startActivity(intent);*/
            public void onClick(View v) {
                Intent myintent2 = new Intent(PlantLibraryActivity.this, CultivationActivity.class);
                startActivity(myintent2);

            }
        });
    }
}
