package com.example.johndeere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = "Main4Activity";
    public EditText land,seeds,fertilizers,pesticides, water;
    public TextView budget;
    Button Calculate,submit;
    Double land_u,seeds_u,fertilizers_u,pesticides_u,water_u,budget_u;
    Double land_v = 400.0,seed_v = 100.00, fertilizer_v = 100.0,pesticides_v=150.0,water_v=15.0;
    public String temp,humidity,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().setTitle("Store");
        land = findViewById(R.id.patch);
        seeds = findViewById(R.id.seeds);
        fertilizers = findViewById(R.id.fertilizers);
        pesticides = findViewById(R.id.pesticides);
        water = findViewById(R.id.water1);
        budget = findViewById(R.id.budget);
        Calculate = findViewById(R.id.calculate);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchAct();
            }
        });
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(budget_u<3000.0) {
                    budget.setText("3000");
                }
                land_u = Double.parseDouble(land.getText().toString());
                seeds_u = Double.parseDouble(seeds.getText().toString());
                fertilizers_u = Double.parseDouble(fertilizers.getText().toString());
                pesticides_u = Double.parseDouble(pesticides.getText().toString());
                water_u = Double.parseDouble(water.getText().toString());
                budget_u = Double.parseDouble(budget.getText().toString());
                if(land_u.toString().isEmpty()||seeds_u.toString().isEmpty()||fertilizers_u.toString().isEmpty()||pesticides_u.toString().isEmpty()||water_u.toString().isEmpty()){
                    land_u = 0.0;
                    seeds_u = 0.0;
                    fertilizers_u = 0.0;
                    pesticides_u = 0.0;
                    water_u = 0.0;
                }
                budget_u = budget_u - land_u*land_v - seed_v*seeds_u-fertilizer_v*fertilizers_u-pesticides_u*pesticides_v-water_u*water_v;
                String Budget = budget_u.toString();
                budget.setText(Budget);
            }

        });

        budget_u = 3000.0;



    }

    public void switchAct(){
        Intent intent1 = getIntent();
        temp = intent1.getStringExtra("temp");
        humidity = intent1.getStringExtra("humidity");
        time = intent1.getStringExtra("time");
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("temp",temp);
        intent.putExtra("humidity",humidity);
        intent.putExtra("time",time);
        intent.putExtra("balance",budget_u.toString());
        intent.putExtra("land",land_u.toString());
        intent.putExtra("seeds", seeds_u.toString());
        intent.putExtra("fertilizers", fertilizers_u.toString());
        intent.putExtra("pesticides", pesticides_u.toString());
        intent.putExtra("water", water_u.toString());
        Log.d(TAG, "switchAct: "+ temp +humidity +time);
        startActivity(intent);

    }
}
