
package com.verdor.verdormobile.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.verdor.verdormobile.Net;
import com.verdor.verdormobile.R;

public class NetExampleActivity extends AppCompatActivity {

    Net myNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_net);
        addListeners();
        Log.e("actividad", "inicio");

        this.myNet = new Net();
    }

    private void addListeners() {
        Button send = ((Button) findViewById(R.id.button_bomba_off));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("actividad", "click");

                processSend();
            }
        });

        Button clear = ((Button) findViewById(R.id.button_bomba_on));
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(null, "click2");
                processClear();
            }
        });
    }

    private void processSend() {
        String inputString = ((EditText) findViewById(R.id.input_message)).getText().toString();

        Log.d("actividad", "sending" + inputString);
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                Log.d("actividad", "doing background");
                return myNet.requestString(params[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                Log.d("", s);
                ((EditText) findViewById(R.id.input_response)).setText(s);
            }
        };

        task.execute(inputString);
    }

    private void processClear() {
        ((EditText) findViewById(R.id.input_response)).setText("");
    }
}

