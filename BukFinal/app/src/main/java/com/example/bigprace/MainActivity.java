package com.example.bigprace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner zaklad;
private Spinner Topping1;
private Spinner Topping2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zaklad=findViewById(R.id.zaklad);
        Topping1=findViewById(R.id.topping1);
        Topping2=findViewById(R.id.topping2);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.topping1, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Topping1.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.topping2, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Topping2.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.zaklad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zaklad.setAdapter(adapter);

    }
    public void swopAct (View view){
        Intent z = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(z);
    }













}