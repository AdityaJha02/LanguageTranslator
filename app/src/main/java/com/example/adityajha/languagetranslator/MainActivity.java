package com.example.adityajha.languagetranslator;

import android.content.Intent;
import android.nfc.Tag;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.MySingelton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 String pub,info,go;
 int length;
EditText edittext;
    String baseURL= "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20180804T225008Z.3e41a28559d27c06.f8aa2d5cc04c09da3d38da93682dd26446ca04da";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        edittext = (EditText) findViewById(R.id.edittext);
    }
    
    void micTapped(View view){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        if(intent.resolveActivity(getPackageManager())!= null) {
            startActivityForResult(intent, 10);
        }

        else
            Toast.makeText(this,"Not Supported in Your Device",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    edittext.setText(result.get(0));
                    go = result.get(0);
                }
                break;
        }
        JSonCalling();
    }

    public void JSonCalling(){
        String myURL=baseURL+"&text=" + go+"&lang=en-hi ";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("JSon","JSon:" + response);

                        try {
                            Log.i("Go","Check");
                            info = response.getString("text");
//
                            JSONArray array = new JSONArray(info);

                            length = info.length();
                            pub= info.substring(2,length-2);
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i("Error","Something went wrong"+ error);
                    }
                }

        );

        MySingelton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);

    }

    public void translate(View view) {
        Intent intent = new Intent(this,Translation.class);

            intent.putExtra("text",pub);

        startActivity(intent);

    }

}


