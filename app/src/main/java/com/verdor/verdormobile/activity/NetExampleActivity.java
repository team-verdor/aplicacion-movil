
package com.verdor.verdormobile.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.verdor.verdormobile.net.Net;
import com.verdor.verdormobile.R;

import java.util.HashMap;
import java.util.Map;

public class NetExampleActivity extends AppCompatActivity {

    Net myNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_net);
        addListeners();

        this.myNet = new Net();
    }

    private void addListeners() {
        Button send = ((Button) findViewById(R.id.button_send));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSend();
            }
        });

        Button clear = ((Button) findViewById(R.id.button_clear));
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processClear();
            }
        });
    }

    private void processSend() {
        String inputString = ((EditText) findViewById(R.id.input_route)).getText().toString();
        Map<String, String> params = stringToMap(((EditText) findViewById(R.id.input_parameters)).getText().toString());

        Log.i("param", params.toString());
        AsyncTask<Object, Void, String> task = new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                Log.d("actividad", "doing background");
                return myNet.requestString((String) params[0], (Map<String, String>) params[1]);
            }

            @Override
            protected void onPostExecute(String s) {
                Log.d("", s);
                ((EditText) findViewById(R.id.input_response)).setText(s);
            }
        };

        task.execute(inputString, params);
    }

    private Map<String, String> stringToMap(String s) {
        Map<String, String> m = new HashMap<String, String>();

        String[] params = s.split(",");

        for (String s2 : params) {
            String[] pair = s2.split(":");

            if (pair.length != 2) continue;

            String key = pair[0];
            String value = pair[1];

            m.put(key, value);
        }

        return m;
    }

    private void processClear() {
        ((EditText) findViewById(R.id.input_response)).setText("");
    }
}

