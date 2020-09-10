package com.example.johndeere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Main3Activity extends AppCompatActivity {
    String token = "f3938a1543cc4637af12702689c4fb7d";
    private static final String TAG = "Main3Activity";
    RequestQueue rq;
    public static TextView temperature, humidity;
    public static EditText time;
    String city,temp,humi;
    public static Button click,proceed;
    public static final String SHARED_PREFS = "sharedPrefs";
    private static final String TEMP = "text1";
    public static final String HUM = "text2";
    public static final String TIME = "text3";
    public String send_temp,send_humi,send_time;
    //private Object JsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        temperature = (TextView) findViewById(R.id.temperature);
        humidity = (TextView) findViewById(R.id.humidity);
        time = (EditText) findViewById(R.id.time);
        click = (Button) findViewById(R.id.button);
        proceed = (Button) findViewById(R.id.button2);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fetch abc = new fetch();
                //abc.execute();
                String url ="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+token;//f3938a1543cc4637af12702689c4fb7d";
                Log.d(TAG, "onCreate: "+ url);
                sendJsonRequest(url);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                loadData();
                switchAct();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.location);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //AdapterView.OnItemSelectedListener()
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // An item was selected. You can retrieve the selected item using
                city = (String) parent.getItemAtPosition(position);
                Log.d(TAG, "onItemSelected: "+ city);
                //temperature.setText(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

        rq = Volley.newRequestQueue(this);

    }
    //String url ="https://samples.openweathermap.org/data/2.5/weather?q="+city+"&appid="+token;//f3938a1543cc4637af12702689c4fb7d";



    public void sendJsonRequest(String url){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    try {
                        Log.d(TAG, "onResponse: " + response.toString());
                        JSONObject abc = response.getJSONObject("main");
                        temp = abc.getString("temp");
                        humi = abc.getString("humidity");
                        Double temp_F = ((((Double.parseDouble(temp)-273.15)/5)*9)+32);
                        String tempF = String.format("%.2f",temp_F);
                        tempF = tempF + "F";
                        humi = humi + "%";
                        //tempF = String.format("%.2f",tempF);
                        temperature.setText(tempF);
                        humidity.setText(humi);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error: " + error.toString());
                temperature.setText("sss");
            }
        });
        rq.add(jsonObjectRequest);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEMP, temperature.getText().toString());
        editor.putString(HUM, humidity.getText().toString());
        editor.putString(TIME, time.getText().toString());

    }

    public void loadData(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        send_temp = sharedPreferences.getString(TEMP , "");
        send_time = sharedPreferences.getString(TIME , "");
        send_humi = sharedPreferences.getString(HUM , "");

    }

    public void switchAct(){
        Intent intent1 = new Intent(this, Main4Activity.class);
        intent1.putExtra("humidity",humidity.getText().toString());
        intent1.putExtra("temp",temperature.getText().toString());
        intent1.putExtra("time", time.getText().toString());
        Log.d(TAG, "switchAct: "+ send_humi+send_temp+send_time);
        startActivity(intent1);

    }


}
