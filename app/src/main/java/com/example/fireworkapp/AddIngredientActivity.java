package com.example.fireworkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        Button addIngredientButton = (Button) findViewById(R.id.button_add_ingredient);
        final EditText newName = findViewById(R.id.new_ingredient_label);

        addIngredientButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle add ingredient promt
                Ingredient.AddIngredient(getIntent().getStringExtra("category"), "", newName.getText().toString());
                finish();
            }
        });
    }
}