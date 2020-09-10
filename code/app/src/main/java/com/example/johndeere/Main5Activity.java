package com.example.johndeere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {
    String temp,humidity,time,balance,land1,seeds1,fertilizers1,pesticides1,water1,count;
    public TextView budget,plot, Seeds ,fertilizer,pesticide, Water,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Intent intent1 = getIntent();
        temp = intent1.getStringExtra("temp");
        humidity = intent1.getStringExtra("humidity");
        time = intent1.getStringExtra("time");
        balance = intent1.getStringExtra("balance");
        land1 = intent1.getStringExtra("land");
        seeds1 = intent1.getStringExtra("seeds");
        fertilizers1 = intent1.getStringExtra("fertilizers");
        pesticides1 = intent1.getStringExtra("pesticides");
        water1 = intent1.getStringExtra("water");
        count = intent1.getStringExtra("count");
        budget = findViewById(R.id.budget2);
        budget.setText(balance);
        plot = findViewById(R.id.plot2);
        plot.setText(land1);
        Seeds = findViewById(R.id.seed2);
        Seeds.setText(seeds1);
        fertilizer = findViewById(R.id.fertilizers2);
        fertilizer.setText(fertilizers1);
        pesticide = findViewById(R.id.pesticides2);
        pesticide.setText(pesticides1);
        Water = findViewById(R.id.water2);
        Water.setText(water1);
        result = findViewById(R.id.Result);
        String ABC = "The final score is "+count+"/4";
        result.setText(ABC);
    }
}
