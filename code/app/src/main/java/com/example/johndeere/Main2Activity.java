package com.example.johndeere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class Main2Activity extends AppCompatActivity {

    public String temp, humidity, time;
    private static final String TAG = "Main2Activity";
    public String balance,land1,seeds1,fertilizers1,pesticides1,water1;
    public TextView budget,plot, Seeds ,fertilizer,pesticide, Water,questions,ans1,ans2;
    public Button fertilizer1,pesticides2,water2,nota;
    public int i = 0,count = 0;
    String[] question = {"You field is Attacked by Pests. How will you solve the problem","Your soil is low in Nitrogen Content. What will you do??","The soil is getting dry. What will you do to help the corn plants gain moisture??(Remember about humidity/moisture)","The field is unfertilized. What will you do??"};
    /*String[] answer = {"Use Pesticide","Use Fertilizer","Use Water","None of the above"};
    String ques1 = "You field is Attacked by Pests. How will you solve the problem";
    String answer11 = "Use Pesticide";
    String answer12 = "Use Fertilizer";
    String ques2 = "Your soil is low in Nitrogen Content. What will you do??";
    String answer22 = "Use Pesticide";
    String answer23 = "Use Fertilizer";
    String ques3 = "The soil is dry. What will you do to help the corn plants gain moisture??";
    String answer33 = "Use Fertilizer";
    String answer34 = "Use Water";
    String ques4 = "The field is unfertilized. What will you do??";
    String answer44 = "Use Water";
    String answer45 = "Use Fertilizer";
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
        Log.d(TAG, "onCreate: " + temp + humidity + time + balance+ land1 + seeds1 + fertilizers1 + pesticides1+ water1);
        budget = findViewById(R.id.budget);
        budget.setText(balance);
        plot = findViewById(R.id.plot);
        plot.setText(land1);
        Seeds = findViewById(R.id.seed);
        Seeds.setText(seeds1);
        fertilizer = findViewById(R.id.fertilizers);
        fertilizer.setText(fertilizers1);
        pesticide = findViewById(R.id.pesticides);
        pesticide.setText(pesticides1);
        Water = findViewById(R.id.water);
        Water.setText(water1);
        questions = findViewById(R.id.question);
        fertilizer1 = findViewById((R.id.ferti));
        pesticides2 = findViewById(R.id.pesti);
        water2 = findViewById(R.id.water1);
        nota = findViewById(R.id.none);

        questoins();

        fertilizer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double A = Double.parseDouble(fertilizer.getText().toString());
                A = A-1;
                fertilizer.setText(A.toString());
                if(i==1 || i==3)
                    count++;
                Log.d(TAG, "onClickfer: "+count);
                i++;
                questoins();
            }
        });

        pesticides2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double A = Double.parseDouble(pesticide.getText().toString());
                A = A-1;
                pesticide.setText(A.toString());
                if(i==0)
                    count++;
                Log.d(TAG, "onClickpesti: "+count);
                i++;
                questoins();
            }
        });

        water2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double A = Double.parseDouble(Water.getText().toString());
                A = A-1;
                Water.setText(A.toString());
                if(i==2 && Integer.parseInt(humidity.substring(0,1))<60 )
                    count++;
                Log.d(TAG, "onClickwater: "+count);
                i++;
                questoins();
            }
        });

        nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " +humidity.substring(0,2));
                if(i==2 && Integer.parseInt(humidity.substring(0,2))>=96 )
                    count++;
                i++;
                questoins();
            }
        });



    }

    public void questoins(){
        if(i>=question.length || i>=Integer.parseInt(time)) {

            Intent intent = new Intent(this, Main5Activity.class);
            intent.putExtra("temp",temp);
            intent.putExtra("humidity",humidity);
            intent.putExtra("time",time);
            intent.putExtra("balance",budget.getText().toString());
            intent.putExtra("land",plot.getText().toString());
            intent.putExtra("seeds", Seeds.getText().toString());
            intent.putExtra("fertilizers", fertilizer.getText().toString());
            intent.putExtra("pesticides", pesticide.getText().toString());
            intent.putExtra("water", Water.getText().toString());
            intent.putExtra("count",Integer.toString(count));
            startActivity(intent);
        }
        else if(i<Integer.parseInt(time))
            questions.setText(question[i]);
    }
}
