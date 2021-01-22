package com.example.bmi_sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //variables

    EditText eNumberOne;
    EditText eNumberTwo;
    EditText eNumberThree;
    Button btnGo;
    TextView Result;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eNumberOne = findViewById(R.id.editName);
        eNumberTwo = findViewById(R.id.editNumberTwo);
        eNumberThree = findViewById(R.id.editNumberThree);
        Result = findViewById(R.id.txtResult);
        btnGo = findViewById(R.id.btnCalculate);

        sharedPref = getSharedPreferences("MyPref", MODE_PRIVATE);

        String name=sharedPref.getString("name", null);
        String weight=sharedPref.getString("weight", null);
        String height=sharedPref.getString("height", null);


        eNumberOne.setHint("Name: " + name);
        eNumberTwo.setHint("Weight (kg): " + weight);
        eNumberThree.setHint("Height (cm): " + height);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("name", eNumberOne.getText().toString());
                editor.putString("weight", eNumberTwo.getText().toString());
                editor.putString("height", eNumberThree.getText().toString());

                editor.commit();

                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();

                String name = eNumberOne.getText().toString().trim();
                String str1 = eNumberTwo.getText().toString();
                String str2 = eNumberThree.getText().toString();


                float weight = Float.parseFloat(str1);
                float height = Float.parseFloat(str2)/100;
                float total = (weight / (height * height));

                eNumberTwo.setText("");
                eNumberThree.setText("");

                Result.setText("Name: " +name +"\nBody Mass Index: " +total);

            }
        });

    }



}