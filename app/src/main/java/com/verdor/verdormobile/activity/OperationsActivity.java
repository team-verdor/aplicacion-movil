package com.verdor.verdormobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.verdor.verdormobile.R;

public class OperationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);
        Button b1 = (Button) findViewById(R.id.button5);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //       Intent intent = new Intent(FirstActivity.this, OrderScreen.class);
                Intent button_uno = new Intent(OperationsActivity.this, PlantLibraryActivity.class);
                startActivity(button_uno);
            }
        });

        // addListeners();

        //this.myNet = new Net();
    }


   /* Net myNet;

   private void addListeners() {
        Button send = ((Button) findViewById(R.id.button_bomba_off));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSend("?comando=activar");
            }
        });

        Button clear = ((Button) findViewById(R.id.button_bomba_on));
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSend("?comando=desactivar");
            }
        });
    }

    private void processSend(String param) {
        final String inputString = "http://192.168.1.101/api.php" + param;

        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                Log.d("Net", "sending: " + inputString);
                return myNet.requestString(params[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                Log.d("Net", "Result: " + s);
                Toast.makeText(OperationsActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };

        task.execute(inputString);
    }*/
}
